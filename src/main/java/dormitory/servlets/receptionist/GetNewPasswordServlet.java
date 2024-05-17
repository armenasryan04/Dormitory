package dormitory.servlets.receptionist;

import dormitory.emailVerifycation.EmailSender;
import dormitory.manager.ReceptionistManager;
import dormitory.models.Receptionist;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/sendNewPassword")
public class GetNewPasswordServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ReceptionistManager receptionistManager = new ReceptionistManager();
        EmailSender emailSender = new EmailSender();
        String newPassword = Receptionist.generatePassword();
        receptionistManager.changePasswordByEmail(req.getParameter("email"), newPassword);
        emailSender.sendNewPassword(req.getParameter("email"), newPassword);
        req.setAttribute("doneMsg","we send your new password in your email");
        req.getRequestDispatcher("WEB-INF/receptionist/global/login.jsp").forward(req, resp);
    }
}
