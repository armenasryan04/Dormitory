package dormitory.servlets.receptionist;


import dormitory.manager.StudentManager;
import dormitory.models.Student;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/reprimandStudent")
public class ReprimandStudentServlet extends HttpServlet {
   StudentManager studentManager = new StudentManager();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
     String search = req.getParameter("search");
     Student student = new Student();
        if (search != null){
            try {
                int id = Integer.parseInt(search);
                student = studentManager.getByIdActive(id);
                req.setAttribute("student", student);
                req.getRequestDispatcher("WEB-INF/receptionist/director/reprimand.jsp").forward(req, resp);
            } catch (NumberFormatException e) {
                resp.sendRedirect("/directorControl");
            }
        }
        req.setAttribute("student",student);
        req.getRequestDispatcher("WEB-INF/receptionist/director/reprimand.jsp").forward(req,resp);

    }
}
