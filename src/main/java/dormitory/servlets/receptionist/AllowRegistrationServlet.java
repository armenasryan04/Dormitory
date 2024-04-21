package dormitory.servlets.receptionist;

import dormitory.manager.PermissionManager;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/allowRegistration")
public class AllowRegistrationServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PermissionManager pm = new PermissionManager();
        pm.changer();
        resp.sendRedirect("/controlOverStaffs");
    }
}
