package dormitory.servlets.receptionist;

import dormitory.manager.ReceptionistManager;
import dormitory.manager.StudentManager;
import dormitory.models.Receptionist;
import dormitory.models.Student;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/addHistory")
public class AddHistoryServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (req.getParameter("id") != null && !req.getParameter("id").isEmpty()) {
            try {
                int id = Integer.parseInt(req.getParameter("id"));
                StudentManager studentManager = new StudentManager();
                List<Student> students = studentManager.getAllByReceptionistId(id);
                req.setAttribute("studentsList", students);
                req.getRequestDispatcher("WEB-INF/receptionist/director/addHistory.jsp").forward(req, resp);
            } catch (NumberFormatException e) {
                resp.sendRedirect("/listOfReceptionists");
            }
        } else {
            resp.sendRedirect("/listOfReceptionists");
        }
    }
}
