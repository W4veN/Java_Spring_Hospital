package pl.air.hospital.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name="patients")
@AttributeOverride(column = @Column(name = "pat_id"), name="id") //mapping pat id from id
@NoArgsConstructor @Getter @Setter
public class Patient extends BaseEntity{

    @NotBlank
    @Size(max=30)
    @Column(name = "first_name", nullable = false)
    private String firstName;

    @NotBlank
    @Size(max = 100)
    @Column(name = "last_name", nullable = false)
    private String lastName;

    @NotNull
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column(name = "adm_date")
    private LocalDate admDate;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "dep_id")
    private Department department;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "diag_id")
    private Diagnosis diagnosis;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "doc_id")
    private Doctor doctor;

    @NotNull
    @ManyToMany
    @JoinColumn(name = "nur_id")
    private List<Nurse> nurses;

}
