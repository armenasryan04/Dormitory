package dormitory.servlets.receptionist;

import dormitory.manager.StudentManager;
import dormitory.models.Student;
import dormitory.models.StudentStatus;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/unblockStudent")
public class UnblockStudentServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (req.getParameter("id") != null || req.getParameter("id").isEmpty()) {
            try {
                int id = Integer.parseInt(req.getParameter("id"));
                StudentManager studentManager = new StudentManager();
                Student student = studentManager.getById(id);
                if (student.getId() != 0 && student.getStudentStatus().equals(StudentStatus.BAN)) {
                    studentManager.unBlockStudentById(id);
                    req.setAttribute("doneMsg", "the Student has been unblocked!");
                    req.getRequestDispatcher("WEB-INF/receptionist/director/control.jsp").forward(req, resp);
                } else {
                    resp.sendRedirect("/studentsBlockList");
                }
            } catch (NumberFormatException e) {
                e.printStackTrace();
                resp.sendRedirect("/directorControl");
            }
        }
    }
}
