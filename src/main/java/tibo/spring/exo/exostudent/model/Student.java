package tibo.spring.exo.exostudent.model;

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
    private String firstName;
    private String lastName;
    private Gender gender;
    private int age;
    private String email;
    private String photo;

    public enum Gender {
        MALE, FEMALE, OTHER
    }
}
