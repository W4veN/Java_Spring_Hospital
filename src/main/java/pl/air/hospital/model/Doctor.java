package pl.air.hospital.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity
@Table(name = "doctors")
@AttributeOverride(column = @Column(name = "doc_id"), name = "id")
@NoArgsConstructor
@Getter
@Setter
public class Doctor extends BaseEntity {

    @NotBlank
    @Size(max=30)
    @Column(name = "first_name", nullable = false)
    private String firstName;

    @NotBlank
    @Size(max = 100)
    @Column(name = "last_name", nullable = false)
    public String lastName;

}
