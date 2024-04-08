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
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@WebServlet("/makeActive")
public class makeActiveServlet extends HttpServlet {
    StudentManager studentManager = new StudentManager();
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Student student = (Student) req.getSession().getAttribute("student");
        int id = student.getId();
        int roomId = student.getRoom().getId();
        Receptionist receptionist = (Receptionist) req.getSession().getAttribute("receptionist");
        Date date = student.getDate();
        studentManager.statusToActive(id, roomId,student.getEmail(),(java.sql.Date) date,receptionist);
        resp.sendRedirect("/control");
    }
}
