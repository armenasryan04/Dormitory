package dormitory.servlets.receptionist;

import dormitory.manager.ReceptionistManager;
import dormitory.models.Receptionist;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/additionalInformation")
public class AdditionalInformationServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (req.getParameter("id") != null && !req.getParameter("id").isEmpty()) {
            try {
                int id = Integer.parseInt(req.getParameter("id"));
                ReceptionistManager receptionistManager = new ReceptionistManager();
                Receptionist receptionist = receptionistManager.getById(id);
                req.setAttribute("receptionist", receptionist);
                req.getRequestDispatcher("WEB-INF/receptionist/director/additionalInformation.jsp").forward(req, resp);
            } catch (NumberFormatException e) {
                resp.sendRedirect("/listOfReceptionists");
            }
        } else {
            resp.sendRedirect("/listOfReceptionists");
        }
    }
}
