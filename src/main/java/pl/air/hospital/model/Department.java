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
@Table(name = "departments")
@AttributeOverride(column = @Column(name = "dep_id"), name = "id")
@NoArgsConstructor
@Getter
@Setter
public class Department extends BaseEntity {

    @NotBlank
    @Size(max = 50)
    @Column(nullable = false)
    private String name;

    @Size(max = 100)
    private String location;
}
