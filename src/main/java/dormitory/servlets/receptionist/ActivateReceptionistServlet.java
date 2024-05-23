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
import java.util.Random;

@WebServlet("/activateReceptionist")
public class ActivateReceptionistServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ReceptionistManager receptionistManager = new ReceptionistManager();
        try {
            if (req.getParameter("id") != null && req.getParameter("id") != "") {
                int id = Integer.parseInt(req.getParameter("id"));
                Receptionist receptionist = receptionistManager.getById(id);
                if (receptionist.getId() != 0 && receptionist.getReceptionistRole().equals(ReceptionistRole.REGISTRANT)) {
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                    Date date = new Date(System.currentTimeMillis());
                    String today = sdf.format(date);
                    Date sqlEmploymentDay = Date.valueOf(today);
                    Random rand = new Random();
                    int randomNumber = rand.nextInt(900000) + 100000;
                    receptionistManager.activateById(id, sqlEmploymentDay, randomNumber);
                    String subject = "Your Information";
                    String text = "you have been registered in our dormitory as an receptionist \n" +
                            "your id" + receptionist.getId() + "\n" +
                            "you already can log in and start your work! \n " +
                            "http://localhost:8080/login \n";
                    EmailSender emailSender = new EmailSender();
                    emailSender.sendInformantMail(receptionist.getEmail(), subject, text);
                    req.setAttribute("doneMsg","everything went well");
                    req.getRequestDispatcher("WEB-INF/receptionist/director/registrantsList.jsp").forward(req, resp);

                } else {
                    resp.sendRedirect("/listOfRegistrants");
                }
            }
        } catch (NumberFormatException e) {
            resp.sendRedirect("/listOfRegistrants");
        }
    }
}
