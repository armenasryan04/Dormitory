package dormitory.servlets.student;

import dormitory.manager.StudentManager;
import dormitory.models.Receptionist;
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
        Receptionist receptionist = (Receptionist) req.getSession().getAttribute("receptionist");
        Date endDate = (Date) student.getDeadline();
        Date registerDate = (Date) student.getRegisterDate();
        studentManager.statusToActive(id, roomId,student.getEmail(),endDate,registerDate,receptionist);
        resp.sendRedirect("/control");
    }
}
