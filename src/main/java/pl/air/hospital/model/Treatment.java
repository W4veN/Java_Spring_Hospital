package pl.air.hospital.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name="treatments")
@AttributeOverride(column = @Column(name = "tre_id"), name="id") //mapping tre id from id
@NoArgsConstructor
@Getter
@Setter
public class Treatment extends BaseEntity {

    @NotBlank
    @Size(max=30)
    @Column(name = "name", nullable = false)
    private String name;

}
