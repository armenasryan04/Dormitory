package dormitory.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

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
    private String email;
    private String password;
    private ReceptionistRole receptionistRole;
    private String verifyCode;

}

