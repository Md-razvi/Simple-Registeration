package dto;

import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
@Getter
@Setter
public class RegisterUser {
    @NotBlank(message = "Please enter your first name")
    private String firstName;
    private String middleName;
    @NotBlank(message = "Please enter last name")
    private String lastName;
    @NotNull(message = "Please enter your birthday")
    @Past(message = "Birthday must be in the past")
    private LocalDate dateOfBirth;
    @NotBlank(message = "Email must not be empty")
    @Email(message = "Email format must be correct")
    private String email;
    @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#$%^&*])(?=.*\\d)[a-zA-Z\\d!@#$%^&*]{8,}$",
    message="password must not be empty")
    private String password;
    @NotBlank(message = "password must not be empty")
    private String confirmPassword;
    @AssertTrue(message = "Password must match")
    public boolean isPasswordMatching(){
        if(password==null || confirmPassword==null){
            return true;
        }
        return password.equals(confirmPassword);
    }


    @AssertTrue(message = "You must be at least 18 years old")
    public boolean isAdult() {
       LocalDate cutOfDate=LocalDate.now().minusYears(18);
       if (dateOfBirth==null){
           return true;
       }
       return dateOfBirth.isBefore(cutOfDate)|| dateOfBirth.isEqual(cutOfDate);

    }


}
