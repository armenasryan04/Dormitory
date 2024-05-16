package dormitory.servlets.receptionist;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/verifyControlCode")
public class VerifyControlCodeServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("reqMsg","do you want to quit your job?");
        req.getRequestDispatcher("WEB-INF/receptionist/admin/verifyControlCode.jsp").forward(req, resp);
    }
}
