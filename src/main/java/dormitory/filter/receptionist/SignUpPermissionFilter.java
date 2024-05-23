package dormitory.filter.receptionist;

import dormitory.manager.PermissionManager;
import dormitory.manager.URLManager;
import dormitory.models.Receptionist;
import dormitory.models.RegistrationPermission;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter(urlPatterns = {"/signUp", "/createPassword", "/registrantEmailVerify", "/addReceptionist"})
public class SignUpPermissionFilter implements Filter {

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpServletResponse resp = (HttpServletResponse) servletResponse;
        PermissionManager permissionManager = new PermissionManager();
        if (permissionManager.getPermission()) {
            filterChain.doFilter(req, resp);
        } else {
            resp.sendRedirect("/login");
        }
    }
}
