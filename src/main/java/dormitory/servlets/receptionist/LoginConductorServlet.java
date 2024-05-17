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

@WebServlet("/loginConductor")
public class LoginConductorServlet extends HttpServlet {
    private ReceptionistManager receptionistManager = new ReceptionistManager();

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        if (req.getSession().getAttribute("receptionist") != null ) {

            HttpSession session = req.getSession();
            Receptionist receptionist = (Receptionist) session.getAttribute("receptionist");
                switch (receptionist.getReceptionistRole()) {
                    case ADMIN:
                        session.setAttribute("receptionist", receptionist);
                        resp.sendRedirect("/control");
                        break;
                    case DIRECTOR:
                        session.setAttribute("receptionist", receptionist);
                        resp.sendRedirect("/directorControl");
                        break;
                    case REGISTRANT:
                }
        } else {
            try {
                String email = req.getParameter("email").trim();
                String pass = req.getParameter("password").trim();
                Receptionist receptionist = receptionistManager.getByEmailAndPassword(email, pass);
                if (receptionist.getId() != 0 && !receptionist.getReceptionistRole().equals(ReceptionistRole.INACTIVE)) {
                    HttpSession session = req.getSession();
                    switch (receptionist.getReceptionistRole()) {
                        case ADMIN:
                            session.setAttribute("receptionist", receptionist);
                            resp.sendRedirect("/control");
                            break;

                        case DIRECTOR:
                            session.setAttribute("receptionist", receptionist);
                            resp.sendRedirect("/directorControl");
                            break;
                        case REGISTRANT:
                            req.setAttribute("doneMsg","we call you later!");
                            req.getRequestDispatcher("WEB-INF/receptionist/global/login.jsp").forward(req, resp);
                            break;
                    }
                } else {
                    req.setAttribute("errMsg", "Email or Pass not variable");
                    req.getRequestDispatcher("WEB-INF/receptionist/global/login.jsp").forward(req, resp);
                }
            } catch (NullPointerException e) {
                e.printStackTrace();
                resp.sendRedirect("/");
            }

        }
    }
}
