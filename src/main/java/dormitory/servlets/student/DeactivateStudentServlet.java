package dormitory.servlets.student;

import dormitory.manager.StudentManager;
import dormitory.models.Student;
import dormitory.models.StudentStatus;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Date;
import java.text.SimpleDateFormat;

@WebServlet("/deactivateStudent")
public class DeactivateStudentServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        StudentManager studentManager = new StudentManager();
        try {
            if (req.getParameter("id") != null && req.getParameter("id") != "") {
                int id = Integer.parseInt(req.getParameter("id"));
                Student student = studentManager.getById(id);
                if (student.getId() != 0 && student.getStudentStatus().equals(StudentStatus.ACTIVE)) {
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                    Date date = new Date(System.currentTimeMillis());
                    String today = sdf.format(date);
                    Date sqlDeadline = Date.valueOf(today);
                    studentManager.deactivateById(student.getId(), sqlDeadline);
                    req.setAttribute("doneMsg", "student has been deactivated!");
                    req.getRequestDispatcher("WEB-INF/receptionist/admin/control.jsp").forward(req, resp);
                } else {
                    resp.sendRedirect("/control");
                }
            }
        } catch (NumberFormatException e) {
            resp.sendRedirect("/control");
        }
    }
}
