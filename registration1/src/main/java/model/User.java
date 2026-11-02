package model;


import lombok.Getter;
import lombok.Setter;
import java.time.LocalDate;

@Setter
@Getter
public class User {
    private String name;
    private String email;
    private String password;
    private LocalDate dateOfBirth;

}
