package dormitory.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegistrationPermission {
   private boolean isAllowed;
   private Date deadline;
   public boolean isTimeLeft(){
        try {
            Date deadline = this.deadline;
            long millisecondsPerHour = 1000 * 60 * 60;
            long currentTime = System.currentTimeMillis();
            long endDateTime = deadline.getTime() + millisecondsPerHour;
            long timeDiff = endDateTime - currentTime;
            if (timeDiff <= 0) {
                return false;
            } else {
                return true;
            }
        } catch (NullPointerException e) {
            return false;
        }
    }
    public static String insertTimer() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DAY_OF_MONTH, 5);
        String formattedDate = sdf.format(calendar.getTime());
        return formattedDate;
    }


}
