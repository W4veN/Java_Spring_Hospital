package pl.air.hospital.repo;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import pl.air.hospital.model.Department;

public interface DepartmentRepository extends JpaRepository<Department, Long> {
}
