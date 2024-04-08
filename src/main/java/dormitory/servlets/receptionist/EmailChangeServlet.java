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

@WebServlet("/changeEmail")
public class EmailChangeServlet extends HttpServlet {
    ReceptionistManager receptionistManager = new ReceptionistManager();
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        Receptionist receptionist = (Receptionist) session.getAttribute("receptionist");
        receptionistManager.changeEmailById(receptionist.getId(),receptionist.getEmail());
        session.removeAttribute("newEmail");
        session.removeAttribute("email");
        session.removeAttribute("password");
        resp.sendRedirect("/control");
    }
}
