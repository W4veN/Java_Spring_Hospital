package pl.air.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.air.hospital.exception.NoDataFoundException;
import pl.air.hospital.model.Department;
import pl.air.hospital.model.Doctor;
import pl.air.hospital.model.Patient;
import pl.air.hospital.repo.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping(value = "/patients")
public class PatientController {
    @Autowired private DepartmentRepository depRepo;
    @Autowired private PatientRepository patRepo;
    @Autowired private DiagnosisRepository diagRepo;
    @Autowired private NurseRepository nurRepo;
    @Autowired private DoctorRepository docRepo;
    @Autowired private TreatmentRepository treRepo;

    //READ
    @GetMapping
    public String displayAll(Model model) {
        List<Patient> all = patRepo.findAll();
        model.addAttribute("patients",all);
        return "patients";
    }
    @GetMapping(value = "/{id}")
    public String displayOne(@PathVariable Long id, Model model) {
        Optional<Patient> opt = patRepo.findById(id);
        if (opt.isPresent()) {
            Patient patient = opt.get();
            model.addAttribute("patient", patient);
        }
        else {
            throw new NoDataFoundException("Nie znaleziono pacjenta o id = " + id);
        }

        return "patient";
    }
    //CREATE
    @GetMapping(value = "/form")
    public String displayForm(Model model) {
        Patient patient = new Patient();
        model.addAttribute("patient", patient);
        addPatientDataToModel(model);

        return "patient-form";
    }

    private void addPatientDataToModel(Model model) {
        List<Department> departments = depRepo.findAll();
        List<Doctor> doctors = docRepo.findAll();
        model.addAttribute("departments", departments);
        model.addAttribute("doctors", doctors);
    }

    /* -------------------------------------------------------- */
    /* UPDATE */

    @GetMapping(value = "/form/{id}")
    public String displayForm(@PathVariable Long id, Model model) {
        Optional<Patient> opt = patRepo.findById(id);

        if (opt.isPresent()) {
            Patient patient = opt.get();
            model.addAttribute("patient", patient);
            addPatientDataToModel(model);
        }
        else {
            throw new NoDataFoundException("Nie znaleziono pacjenta o id = " + id);
        }

        return "patient-form";
    }

    /* SAVE Form (CREATE or UPDATE) */

    @PostMapping(value = "/save")
    public String saveOne(@Valid Patient patient, Errors errors, Model model) {
        if (errors.hasErrors()) {
            addPatientDataToModel(model);
            return "patient-form";
        }

        patRepo.save(patient);

        return "redirect:/patients";
    }

    /* -------------------------------------------------------- */
    /* DELETE */

    @GetMapping(value = "/delete/{id}")
    public String deleteOne(@PathVariable Long id) {
        if (! patRepo.existsById(id))
            throw new NoDataFoundException("Nie znaleziono pacjenta o id = " + id);

        patRepo.deleteById(id);

        return "redirect:/patients";
    }
}
