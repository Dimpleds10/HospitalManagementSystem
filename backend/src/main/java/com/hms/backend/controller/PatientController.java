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

    // GET ALL PATIENTS

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

    // ADD PATIENT

    @PostMapping

    public ApiResponse<Patient>
    addPatient(
            @RequestBody Patient patient
    ) {

        patient.setStatus("Admitted");

        patient.setBillStatus("Pending");

        patient.setAdmissionDate(
                LocalDate.now().toString()
        );

        Patient savedPatient =
                repository.save(patient);

        return new ApiResponse<>(

                true,

                "Patient admitted successfully",

                repository.findAll().size(),

                savedPatient
        );
    }

    // DISCHARGE PATIENT

    @PutMapping("/discharge/{id}")

    public ApiResponse<Patient>
    dischargePatient(
            @PathVariable int id
    ) {

        Patient patient =
                repository.findById(id)
                        .orElseThrow();

        patient.setStatus("Discharged");

        patient.setBillStatus("Paid");

        patient.setDischargeDate(
                LocalDate.now().toString()
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