package dormitory.servlets.receptionist;

import dormitory.manager.PermissionManager;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PermissionManager permissionManager = new PermissionManager();
        req.setAttribute("canReg", permissionManager.getPermission());
        req.getSession().removeAttribute("receptionist");
        req.getRequestDispatcher("WEB-INF/receptionist/global/login.jsp").forward(req, resp);
    }
}
