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

@WebServlet("/addStudent")
public class AddServlet extends HttpServlet {
    StudentManager studentManager = new StudentManager();

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Student student = (Student) req.getSession().getAttribute("student");
        Receptionist receptionist = (Receptionist) req.getSession().getAttribute("receptionist");
        student.setReceptionist(receptionist);
        studentManager.addToDB(student);
        req.getSession().removeAttribute("room");
        req.getSession().removeAttribute("student");
        resp.sendRedirect("/control");
    }
}
