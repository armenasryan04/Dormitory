package dormitory.servlets.receptionist;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Random;

@WebServlet("/resetPassword")
public class ResetPasswordServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Random random = new Random();
        int randomNum = random.nextInt(900000) + 100000;
        req.getSession().setAttribute("verifyCode", randomNum);
        req.getRequestDispatcher("WEB-INF/receptionist/global/email.jsp").forward(req, resp);
    }
}
