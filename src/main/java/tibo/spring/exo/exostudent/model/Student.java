package tibo.spring.exo.exostudent.model;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Student {
    private UUID id;
    @NotBlank(message = "You must enter a first name")
    private String firstName;
    @NotBlank(message = "You must enter a last name")
    private String lastName;
    @NotNull(message = "You must enter a gender")
    private Gender gender;
    @NotNull(message = "You must enter an age")
    @Min(value = 18, message = "The student must be at least 18 years old")
    private Integer age;
    @NotBlank(message = "You must enter an email address")
    @Email(message = "You must enter a valid email address")
    private String email;
    private String photo;

    public enum Gender {
        MALE, FEMALE, OTHER
    }
}
