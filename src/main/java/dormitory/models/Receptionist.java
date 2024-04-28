package dormitory.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Receptionist {
    private int id;
    private String name;
    private String surname;
    private Date birthday;
    private String phone;
    private String email;
    private String password;
    private Date employmentDate;
    private Date resignationDate;
    private String experienceInformation;
    private ReceptionistRole receptionistRole;
    private Gender gender;
    private String controlCode;
    private String verifyCode;
}

