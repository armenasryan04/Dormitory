package dormitory.servlets.receptionist;

import dormitory.emailVerifycation.EmailSender;
import dormitory.manager.ReceptionistManager;
import dormitory.models.Receptionist;
import dormitory.models.ReceptionistRole;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Date;
import java.text.SimpleDateFormat;

@WebServlet("/deactivation")
public class DeactivationServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ReceptionistManager receptionistManager = new ReceptionistManager();
        Receptionist receptionist = (Receptionist) req.getSession().getAttribute("receptionist");
                int id = receptionist.getId();
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                    Date date = new Date(System.currentTimeMillis());
                    String today = sdf.format(date);
                    Date sqlEmploymentDay = Date.valueOf(today);
                    receptionistManager.deActivateById(id, sqlEmploymentDay);
                    req.getSession().removeAttribute("receptionist");
                    req.setAttribute("doneMsg","everything went well thanks!");
                    req.getRequestDispatcher("WEB-INF/receptionist/global/login.jsp").forward(req, resp);
    }


}
