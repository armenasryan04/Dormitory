package dormitory.servlets.receptionist;

import dormitory.manager.StudentManager;
import dormitory.models.Student;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/adderInfo")
public class AdderInfoServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id");
        if (id != null && !id.isEmpty()) {
            StudentManager studentManager = new StudentManager();
            Student student = studentManager.getById(Integer.parseInt(id));
            if (student.getReceptionist()!=null){
                req.setAttribute("adder", student.getReceptionist());
                req.getRequestDispatcher("WEB-INF/receptionist/director/adderInfo.jsp").forward(req, resp);
            }else {
                resp.sendRedirect("/directorControl");
            }

        } else {
            resp.sendRedirect("/directorControl");
        }
    }
}
