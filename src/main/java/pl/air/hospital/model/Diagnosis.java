package pl.air.hospital.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@Entity
@Table(name = "diagnosiss")
@AttributeOverride(column = @Column(name = "diag_id"), name = "id")
@NoArgsConstructor
@Getter
@Setter

public class Diagnosis extends BaseEntity {

    @NotBlank
    @Size(max=30)
    @Column(name = "name", nullable = false)
    private String name;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "tre_id")
    private Treatment treatment;

}
