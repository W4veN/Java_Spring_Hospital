package pl.air.hospital.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import pl.air.hospital.model.*;


public interface PatientRepository extends JpaRepository<Patient, Long> {

}
