package dormitory.servlets.student;

import dormitory.emailVerifycation.EmailSender;
import dormitory.manager.StudentManager;
import dormitory.models.Receptionist;
import dormitory.models.ReceptionistRole;
import dormitory.models.Student;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Date;

@WebServlet("/makeActive")
public class MakeActiveServlet extends HttpServlet {
    StudentManager studentManager = new StudentManager();

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Student student = (Student) req.getSession().getAttribute("student");
        int id = student.getId();
        int roomId = student.getRoom().getId();
        String subject = "Your Information";
        String text = "you have been registered in our dormitory! \n" +
                "registration deadline " + student.getDeadline() + "\n" +
                "your room floor:" + student.getRoom().getFloor() + "   room number:" + student.getRoom().getRoomNum() + "\n" +
                "general information can be found here \n " +
                "http://localhost:8080/getInfo?search=" + student.getId() + "\n";
        Receptionist receptionist = (Receptionist) req.getSession().getAttribute("receptionist");
        Date endDate = (Date) student.getDeadline();
        Date registerDate = (Date) student.getRegisterDate();
        studentManager.statusToActive(id, roomId, student.getEmail(), endDate, registerDate, receptionist);
        EmailSender emailSender = new EmailSender();
        emailSender.sendInformantMail(student.getEmail(), subject, text);
        req.setAttribute("doneMsg", "the student has been registered again!");
        if (receptionist.getReceptionistRole().equals(ReceptionistRole.DIRECTOR)) {
            req.getRequestDispatcher("WEB-INF/receptionist/director/control.jsp").forward(req, resp);
        } else if (receptionist.getReceptionistRole().equals(ReceptionistRole.ADMIN)) {
            req.getRequestDispatcher("WEB-INF/receptionist/admin/control.jsp").forward(req, resp);
        }
    }
}
