package pl.air.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.air.hospital.exception.NoDataFoundException;
import pl.air.hospital.model.Department;
import pl.air.hospital.model.Patient;
import pl.air.hospital.repo.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping(value = "/departments")
public class DepartmentController {
    @Autowired
    private DepartmentRepository depRepo;
    @Autowired private PatientRepository patRepo;
    @Autowired private DiagnosisRepository diagRepo;
    @Autowired private NurseRepository nurRepo;
    @Autowired private DoctorRepository docRepo;
    @Autowired private TreatmentRepository treRepo;

    //READ
    @GetMapping
    public String displayAll(Model model) {
        List<Department> all = depRepo.findAll();
        model.addAttribute("departments",all);
        return "departments";
    }
    @GetMapping(value = "/{id}")
    public String displayOne(@PathVariable Long id, Model model) {
        Optional<Department> opt = depRepo.findById(id);
        if (opt.isPresent()) {
            Department department = opt.get();
            model.addAttribute("department", department);
        }
        else {
            throw new NoDataFoundException("Nie znaleziono oddziału o id = " + id);
        }

        return "department";
    }
}
