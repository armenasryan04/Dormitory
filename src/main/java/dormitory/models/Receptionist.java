package dormitory.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.Random;


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

    public static String generatePassword() {
        String letters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
        String digits = "0123456789";
        Random random = new Random();
        StringBuilder password = new StringBuilder();
        for (int i = 0; i < 2; i++) {
            int index = random.nextInt(letters.length());
            password.append(letters.charAt(index));
        }

        for (int i = 0; i < 8; i++) {
            int index = random.nextInt(digits.length());
            password.append(digits.charAt(index));
        }

        return password.toString();
    }
}

