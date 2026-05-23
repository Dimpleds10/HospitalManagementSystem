package com.hms.backend.repository;

import com.hms.backend.entity.Patient;

import org.springframework.data.jpa.repository.JpaRepository;

public interface PatientRepository
        extends JpaRepository<Patient, Integer> {
}