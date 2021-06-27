package pl.air.hospital.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.air.hospital.model.Treatment;

public interface TreatmentRepository extends JpaRepository<Treatment, Long> {
}
