package dormitory.servlets.receptionist;

import dormitory.manager.ReceptionistManager;
import dormitory.manager.StudentManager;
import dormitory.models.Receptionist;
import dormitory.models.Student;
import dormitory.validation.RemoveFormSessionAttributes;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/listOfReceptionists")
public class ReceptionistListServlet extends HttpServlet {
    ReceptionistManager receptionistManager = new ReceptionistManager();
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String search = req.getParameter("search");
        List<Receptionist> all;
            if (search == null) {
             all = receptionistManager.getAllAdmins();
            } else {
                all = receptionistManager.getByNameOrSurnameAdmin(search.trim());
            }
        req.setAttribute("receptionists", all);
        req.getRequestDispatcher("WEB-INF/receptionist/director/receptionistsList.jsp").forward(req, resp);
    }
}
