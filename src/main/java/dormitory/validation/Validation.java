package dormitory.validation;

import dormitory.manager.RoomManager;
import dormitory.manager.StudentManager;
import dormitory.models.Student;

import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Date;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Validation {
    public static Student removeStudentInvalidData(Student student,StudentManager studentManager) {
        if (!isEmailAddressValid(student.getEmail()) || student.getEmail() == null || student.getEmail().isEmpty()) {
            student.setEmail("");
        }
        if (!isValidId(student.getId())) {
            student.setId(0);
        }
        if (studentManager.getByEmailOrId(student.getEmail(), student.getId()).getId() != 0) {
            student.setId(0);
            student.setEmail("");
        }
        if (!isNameValid(student.getName())) {
            student.setName("");
        }
        if (!isSurnameValid(student.getSurname())) {
            student.setSurname("");
        }

        if (isValidatePhoneNumber(student.getPhoneNum()) || student.getPhoneNum() == null || student.getPhoneNum().isEmpty()) {
            student.setPhoneNum("");
        }
        if (!isDateValid(student.getDate())){
            Date date = new Date();
            student.setDate(date);
        }
        return student;
    }

    public static String studentValidation(Student student,RoomManager roomManager,StudentManager studentManager) {

        String validation = null;

        if (!isEmailAddressValid(student.getEmail()) || student.getEmail() == null || student.getEmail().isEmpty()) {
            validation = "Incorrect Email try again!";
            return validation;
        }
        if (!isValidId(student.getId())) {
            validation = "Incorrect Inspection Booklet Num try again!";
            student.setId(0);
            return validation;
        }
        if ( studentManager.getByEmailOrId(student.getEmail(),student.getId()).getId() != 0) {
            student = studentManager.getByEmailOrId(student.getEmail(), student.getId());
            student.setId(0);
            student.setEmail("");
            switch (student.getStudentStatus()) {
                case BAN:
                    validation = "We already have this student! \n he (she) is in Ban!";
                    return validation;
                case ARCHIVE:
                    validation = "We already have this student! \n " +
                            "he (she) is in Archive! \n " +
                            "but you can change status on Active";
                    return validation;
                case ACTIVE:
                    validation = "We already have this student!";
                    return validation;
                default:
                    return null;
            }
        }
        if (!roomManager.isFree(student.getRoom().getId())){
            validation = "We already have student in this room!";
            return validation;
        }
        if (!isNameValid(student.getName())) {
            validation = "Incorrect Name try again!";
            return validation;
        }
        if (!isSurnameValid(student.getSurname())) {
            validation = "Incorrect Surname try again!";
            return validation;
        }

        if (isValidatePhoneNumber(student.getPhoneNum()) || student.getPhoneNum() == null || student.getPhoneNum().isEmpty()) {
            validation = "Incorrect Phone try again!";
            return validation;
        }
        if (!isDateValid(student.getDate())){
            validation = "incorrect Date try again!";
            Date date = new Date();
            student.setDate(date);
            return validation;
        }
        return validation;
    }

     public static boolean isValidId(int id) {
        return String.valueOf(id).length() >= 3 && String.valueOf(id).length() <= 4;
    }


      static boolean isValidatePhoneNumber(String phoneNumber) {
        String phonePattern = "^\\+374\\d{6}$";
        Pattern pattern = Pattern.compile(phonePattern);
        Matcher matcher = pattern.matcher(phoneNumber);
        return matcher.matches();
    }

    public static boolean isEmailAddressValid(String email) {
        boolean isValid;
        try {
            InternetAddress internetAddress = new InternetAddress(email);
            internetAddress.validate();
            isValid = isEmailDomainExists(email);
        } catch (AddressException e) {
         isValid = false;
        }
        return isValid;
    }


    public static boolean isNameValid(String name) {
        if (name == null || name.isEmpty()) {
            return false;
        }

        name = name.trim();

        if (name.length() < 3 || name.length() > 10) {
            return false;
        }

        for (char c : name.toCharArray()) {
            if (!Character.isLetter(c)) {
                return false;
            }
        }
        if (!Character.isUpperCase(name.charAt(0))) {
            return false;
        }
        for (int i = 1; i < name.length(); i++) {
            if (!Character.isLowerCase(name.charAt(i))) {
                return false;
            }
        }
        return true;
    }

    public static boolean isSurnameValid(String surname) {
        if (surname == null || surname.isEmpty()) {
            return false;
        }

        surname = surname.trim();
        if (surname.length() < 6 || surname.length() > 15) {
            return false;
        }

        for (char c : surname.toCharArray()) {
            if (!Character.isLetter(c)) {
                return false;
            }
        }
        if (!Character.isUpperCase(surname.charAt(0))) {
            return false;
        }
        for (int i = 1; i < surname.length(); i++) {
            if (!Character.isLowerCase(surname.charAt(i))) {
                return false;
            }
        }
        return true;
    }
    public static boolean isDateValid(Date date){
        Date today = new Date();
        long tomorrowMillis = today.getTime() + (24 * 60 * 60 * 1000);
        Date tomorrow = new Date(tomorrowMillis);
        return !date.before(tomorrow);
    }
    public static boolean isEmailFree(Student student ,String email,StudentManager studentManager){
        Student getDataFromDB = studentManager.getByEmail(email);
        if (getDataFromDB.getId() != student.getId()){
            return false;
        }else {
            return true;
        }
    }
    public static boolean isValidPassword(String password) {
        if (password.length() < 8 || password.length() > 20) {
            return false;
        }

        if (!password.matches(".*[a-z].*")) {
            return false;
        }

        if (!password.matches(".*\\d.*")) {
            return false;
        }

        return true;
    }
    private static boolean isEmailDomainExists(String email) {
        boolean exists = false;
        try {
            InternetAddress internetAddress = new InternetAddress(email);
            String domain = internetAddress.getAddress().split("@")[1];
            InetAddress inetAddress = InetAddress.getByName(domain);
            exists = inetAddress.isReachable(5000);
        } catch (UnknownHostException e) {
            e.printStackTrace();
            exists = false;
        } catch (Exception e) {
           e.printStackTrace();
        }
        return exists;
    }
}
