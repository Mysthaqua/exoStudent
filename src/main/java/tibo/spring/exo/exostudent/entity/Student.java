package tibo.spring.exo.exostudent.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Entity
@Data
@NoArgsConstructor
@Builder
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    @Column(nullable = false)
    @NotBlank(message = "You must enter a first name")
    private String firstName;
    @Column(nullable = false)
    @NotBlank(message = "You must enter a last name")
    private String lastName;
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    @NotNull(message = "You must enter a gender")
    private Gender gender;
    @Column(nullable = false)
    @NotNull(message = "You must enter an age")
    @Min(value = 18, message = "The student must be at least 18 years old")
    private Integer age;
    @Column(nullable = false)
    @NotBlank(message = "You must enter an email address")
    @Email(message = "You must enter a valid email address")
    private String email;
    @Column(nullable = false)
    private String photo;

    private Student(UUID id, String firstName, String lastName, Gender gender, Integer age, String email, String photo) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.gender = gender;
        this.age = age;
        this.email = email;
        if (photo == null || photo.isBlank())
            photo = String.format("/images/student/default/%s-default.png", gender.name().toLowerCase().charAt(0));
        this.photo = photo;
    }

    public enum Gender {
        MALE, FEMALE, OTHER
    }
}
