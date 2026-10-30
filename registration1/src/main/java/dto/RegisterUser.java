package dto;

import jakarta.validation.constraints.AssertTrue;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
@Getter
@Setter
public class RegisterUser {
    @NotBlank(message = "Please enter your first name")
    private String firstName;
    private String middle_name;
    @NotBlank(message = "Please enter last name")
    private String last_name;
    @NotNull(message = "Please enter your birthday")
    @Past(message = "Birthday must be in the past")
    private LocalDate dateOfBirth;
    @AssertTrue(message = "You must be at least 18 years old")
    public boolean isAdult() {
       LocalDate cutOfDate=LocalDate.now().minusYears(18);
       if (dateOfBirth==null){
           return true;
       }
       return dateOfBirth.isBefore(cutOfDate)|| dateOfBirth.isEqual(cutOfDate);

    }


}
