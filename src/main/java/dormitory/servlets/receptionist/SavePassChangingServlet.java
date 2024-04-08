package dormitory.servlets.receptionist;

import dormitory.manager.ReceptionistManager;
import dormitory.models.Receptionist;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/savePassChanging")
public class SavePassChangingServlet extends HttpServlet {
    ReceptionistManager receptionistManager = new ReceptionistManager();
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        Receptionist receptionist = (Receptionist) session.getAttribute("receptionist");
        receptionistManager.changePasswordById(receptionist.getId(),receptionist.getPassword());
        resp.sendRedirect("/control");
    }
}
