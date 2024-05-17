package dormitory.servlets.receptionist;

import dormitory.manager.ReceptionistManager;
import dormitory.models.Receptionist;
import dormitory.models.ReceptionistRole;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/saveNameSurname")
public class NameSurnameSaveServlet extends HttpServlet {
    ReceptionistManager receptionistManager = new ReceptionistManager();

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        Receptionist receptionist = (Receptionist) session.getAttribute("receptionist");
        receptionistManager.changeNameSurnameById(receptionist.getId(), receptionist.getName(), receptionist.getSurname());
        req.setAttribute("doneMsg","your name and surname has been changed!");
        if (receptionist.getReceptionistRole().equals(ReceptionistRole.DIRECTOR)) {
            req.getRequestDispatcher("WEB-INF/receptionist/director/control.jsp").forward(req, resp);
        } else if (receptionist.getReceptionistRole().equals(ReceptionistRole.ADMIN)){
            req.getRequestDispatcher("WEB-INF/receptionist/admin/control.jsp").forward(req, resp);
        }
    }
}
