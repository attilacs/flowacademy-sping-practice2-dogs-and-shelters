package hu.flowacademy.dogs.dtos;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Data
public class DogDTO {
    @NotEmpty
    @Pattern(regexp = "^[A-Z]{6}-[0-9]{2}$")
    private String chipId;

    @NotEmpty
    private String name;

    @NotEmpty
    private String gender;

    @NotNull
    private Long shelter_id;
}
