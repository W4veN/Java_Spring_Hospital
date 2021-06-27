package pl.air.hospital.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.air.hospital.model.Diagnosis;

public interface DiagnosisRepository extends JpaRepository<Diagnosis, Long> {
}
