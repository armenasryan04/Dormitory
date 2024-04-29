package dormitory.servlets.receptionist;

import dormitory.manager.ReceptionistManager;
import dormitory.models.ReceptionistRole;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/receptionistExperience")
public class ReceptionistExperienceServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ReceptionistManager receptionistManager = new ReceptionistManager();
        if (req.getParameter("id") != null && receptionistManager.getById(Integer.parseInt(req.getParameter("id"))).getId() != 0 && receptionistManager.getById(Integer.parseInt(req.getParameter("id"))).getReceptionistRole().equals(ReceptionistRole.REGISTRANT)) {
            int id = Integer.parseInt(req.getParameter("id"));
            String aboutExperience = receptionistManager.getById(id).getExperienceInformation();
            if (aboutExperience == null) {
                aboutExperience = ":-(";
            }
            req.setAttribute("aboutExperience", aboutExperience);
            req.getRequestDispatcher("WEB-INF/receptionist/director/receptionistExperience.jsp").forward(req, resp);
        }else {
            resp.sendRedirect("/listOfRegistrants");
        }
    }
}
