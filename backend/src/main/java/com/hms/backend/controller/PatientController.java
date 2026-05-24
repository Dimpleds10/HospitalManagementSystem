package com.hms.backend.controller;

import com.hms.backend.dto.ApiResponse;
import com.hms.backend.entity.Patient;
import com.hms.backend.repository.PatientRepository;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController

@RequestMapping("/patients")

@CrossOrigin("*")

public class PatientController {

    @Autowired

    private PatientRepository repository;

    // =========================================
    // GET ALL PATIENTS
    // =========================================

    @GetMapping

    public ApiResponse<List<Patient>>
    getPatients() {

        List<Patient> patients =
                repository.findAll();

        return new ApiResponse<>(

                true,

                "Patients fetched successfully",

                patients.size(),

                patients
        );
    }

    // =========================================
    // ADD PATIENT
    // =========================================

    @PostMapping

    public ApiResponse<Patient>
    addPatient(
            @RequestBody Patient patient
    ) {

        // DEFAULT STATUS

        patient.setStatus(
                "Admitted"
        );

        // BILL STATUS

        patient.setBillStatus(
                "Pending"
        );

        // BILL INITIALLY ZERO

        patient.setFinalBill(0);

        // ADMISSION DATE

        patient.setAdmissionDate(
                LocalDate.now().toString()
        );

        // DISCHARGE DATE EMPTY

        patient.setDischargeDate("-");

        Patient savedPatient =
                repository.save(patient);

        return new ApiResponse<>(

                true,

                "Patient admitted successfully",

                repository.findAll().size(),

                savedPatient
        );
    }

    // =========================================
    // DISCHARGE PATIENT
    // =========================================

    @PutMapping("/discharge/{id}")

    public ApiResponse<Patient>
    dischargePatient(
            @PathVariable int id
    ) {

        Patient patient =
                repository.findById(id)
                        .orElseThrow();

        // STATUS

        patient.setStatus(
                "Discharged"
        );

        // DISCHARGE DATE

        patient.setDischargeDate(
                LocalDate.now().toString()
        );

        // =====================================
        // BILL CALCULATION
        // =====================================

        double roomCharge = 0;

        double diseaseCharge = 0;

        // ROOM CHARGES

        switch (
                patient.getRoomType()
        ) {

            case "ICU":

                roomCharge = 25000;

                break;

            case "Private Room":

                roomCharge = 12000;

                break;

            case "General Ward":

                roomCharge = 5000;

                break;

            default:

                roomCharge = 3000;
        }

        // DISEASE CHARGES

        switch (
                patient.getDisease()
        ) {

            case "Heart Disease":

                diseaseCharge = 15000;

                break;

            case "Fracture":

                diseaseCharge = 7000;

                break;

            case "Diabetes":

                diseaseCharge = 4000;

                break;

            case "Fever":

                diseaseCharge = 2000;

                break;

            default:

                diseaseCharge = 1000;
        }

        // FINAL BILL

        double finalBill =
                roomCharge + diseaseCharge;

        patient.setFinalBill(
                finalBill
        );

        // BILL STATUS

        patient.setBillStatus(
                "Paid"
        );

        Patient updatedPatient =
                repository.save(patient);

        return new ApiResponse<>(

                true,

                "Patient discharged successfully",

                repository.findAll().size(),

                updatedPatient
        );
    }
}