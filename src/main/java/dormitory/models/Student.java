package dormitory.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Student {
    private int id;
    private int remark;
    private String name;
    private String surname;
    private String email;
    private String phoneNum;
    private Date birthday;
    private Date deadline;
    private Date registerDate;
    private Room room;
    private Receptionist receptionist;
    private StudentStatus studentStatus;
    private String verifyCode;
    public String getDaysUntil() {
        try {
            Date deadline = this.deadline;
            long millisecondsPerDay = 1000 * 60 * 60 * 24;
            long millisecondsPerHour = 1000 * 60 * 60;
            long currentTime = System.currentTimeMillis();
            long endDateTime = deadline.getTime() + millisecondsPerHour;
            long timeDiff = endDateTime - currentTime;
            long days = timeDiff / millisecondsPerDay;
            long hours = (timeDiff % millisecondsPerDay) / millisecondsPerHour;

            if (days <= 0 && hours <= 0) {

                return 0 + "d " + 0 + "h";
            }

            if (hours >= 12) {
                days++;
                hours = 0;
            }
            return days + " d " + hours + " h ";
        } catch (NullPointerException e) {
            return null + " d " + null + " h ";
        }
        }


}