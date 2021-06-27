package pl.air.hospital.init;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.air.hospital.model.*;
import pl.air.hospital.repo.*;

import java.time.LocalDate;
import java.util.List;

@Repository
public class DataLoaderImpl implements DataLoader {


    @Autowired private DiagnosisRepository diagRepo;
    @Autowired private DepartmentRepository depRepo;
    @Autowired private DoctorRepository docRepo;
    @Autowired private NurseRepository nurRepo;
    @Autowired private PatientRepository patRepo;
    @Autowired private TreatmentRepository treRepo;



    @Override
    @Transactional
    public void insertData() {

        //oddziały
        Department pulmunolog = createDepartment("Oddział Pulmunologiczny", "Skrzydło A");
        Department laryngolog = createDepartment("Oddział Laryngologiczny", "Skrzydło B");
        Department kardiolog = createDepartment("Oddział Kardiologiczny", "Skrzydło C");

        //lekarze
        Doctor karol = createDoctor("Karol", "Wajs");
        Doctor piotr = createDoctor("Piotr", "Szatkowski");
        Doctor arek = createDoctor("Arek", "Zakrzewski");

        //pielegniarki
        Nurse pati = createNurse("Patrycja", "Cicha");
        Nurse iga = createNurse("Iga", "Figa");
        Nurse jula = createNurse("Julka", "Pulka");

        //leczenia
        Treatment lek = createTreatment("B-mimetyki");
        Treatment lek2 = createTreatment("Ryfampicyna");
        Treatment lek3 = createTreatment("Histigen");

        //diagnozy
        Diagnosis tac = createDiagnosis("Tachykardia", lek);
        Diagnosis gru = createDiagnosis("Gruźlica", lek2);
        Diagnosis men = createDiagnosis("Choroba Meniera", lek3);

        //pacjenci
        Patient chu = createPatient("Whats", "Up", LocalDate.parse("2021-03-15"),karol,pulmunolog,gru,List.of(pati, iga));
        Patient hoh = createPatient("Hoho", "Huhu",LocalDate.parse("2020-10-03"),piotr,kardiolog,tac,List.of(iga));
        Patient hih = createPatient("Jerzy", "Brzeczek",LocalDate.parse("2019-05-03"),arek,laryngolog,men,List.of(pati,jula));

        depRepo.saveAll(
                List.of(pulmunolog, laryngolog, kardiolog)
        );

        diagRepo.saveAll(
                List.of(tac,gru,men)
        );

        docRepo.saveAll(
                List.of(karol,piotr,arek)
        );

        treRepo.saveAll(
                List.of(lek,lek2,lek3)
        );

        nurRepo.saveAll(
                List.of(pati,iga,jula)
        );

        patRepo.saveAll(
                List.of(chu,hoh,hih)
        );

    }

    private Patient createPatient(String firstName, String lastName, LocalDate admDate, Doctor doctor, Department department, Diagnosis diagnosis, List<Nurse> nurses) {
        Patient one = new Patient();
        one.setFirstName(firstName);
        one.setLastName(lastName);
        one.setAdmDate(admDate);
        one.setDoctor(doctor);
        one.setDepartment(department);
        one.setDiagnosis(diagnosis);
        one.setNurses(nurses);
        return one;
    }

    private Doctor createDoctor(String firstName, String lastName) {
        Doctor one = new Doctor();
        one.setFirstName(firstName);
        one.setLastName(lastName);
        return one;
    }

    private Department createDepartment(String name, String location) {
        Department one = new Department();
        one.setName(name);
        one.setLocation(location);
        return one;
    }

    private Nurse createNurse(String firstName, String lastName) {
        Nurse one = new Nurse();
        one.setFirstName(firstName);
        one.setLastName(lastName);
        return one;
    }

    private Diagnosis createDiagnosis(String name, Treatment treatment) {
        Diagnosis one = new Diagnosis();
        one.setName(name);
        one.setTreatment(treatment);
        return one;
    }

    private Treatment createTreatment(String name) {
        Treatment one = new Treatment();
        one.setName(name);
        return one;
    }
}
