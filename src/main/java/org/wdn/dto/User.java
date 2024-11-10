package org.wdn.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.wdn.util.UserType;
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class User {
    private Long id;
    @NotNull
    private String name;
    private String address;
    private String phoneNumber;
    private UserType userType;
    @Email(message = "Please enter a valid email")
    @NotNull
    private String email;
    @Pattern(regexp = "^(?=.*[A-Za-z])(?=.*[\\d])[A-Za-z\\d]{5,}$",message ="Please create strong password " )
    private String password;
}
