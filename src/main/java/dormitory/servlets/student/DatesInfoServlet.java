package dormitory.servlets.student;

import dormitory.manager.StudentManager;
import dormitory.models.Student;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/dateInfo")
public class DatesInfoServlet extends HttpServlet {
    StudentManager studentManager = new StudentManager();

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (req.getParameter("id") != null) {
            try {
                int id = Integer.parseInt(req.getParameter("id"));
                Student student = studentManager.getById(id);
                req.setAttribute("student", student);
                req.getRequestDispatcher("WEB-INF/student/dateInfo.jsp").forward(req, resp);
            } catch (NumberFormatException e) {
                resp.sendRedirect("/directorControl");
            }
        } else {
            resp.sendRedirect("/directorControl");
        }

    }
}
