package dormitory.filter.receptionist;

import dormitory.emailVerifycation.EmailSender;
import dormitory.manager.ReceptionistManager;
import dormitory.models.Receptionist;
import dormitory.validation.Validation;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Random;

@WebFilter(urlPatterns = {"/savePassChanging"})
public class PasswordChangeValidationFilter implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpServletResponse resp = (HttpServletResponse) servletResponse;
        HttpSession session = req.getSession();
        Receptionist receptionist = (Receptionist) session.getAttribute("receptionist");
        try {
            String password = req.getParameter("password").trim();
            String newPassword = req.getParameter("newPassword").trim();
            String confirmPassword = req.getParameter("confirmPassword").trim();
            if (password != null && password.equals(receptionist.getPassword())) {
                if (newPassword != null && confirmPassword.equals(newPassword)) {
                    receptionist.setPassword(newPassword);
                    filterChain.doFilter(req, resp);
                } else {
                    req.setAttribute("errMsg","Password mismatch:-(");
                    req.getRequestDispatcher("WEB-INF/receptionist/admin/changePassword.jsp").forward(req, resp);
                }
            }else {
                req.setAttribute("errMsg","invalid password :-(");
                req.getRequestDispatcher("WEB-INF/receptionist/admin/changePassword.jsp").forward(req, resp);
            }
        } catch (NullPointerException e){
            req.setAttribute("errMsg","Please create new password:-(");
            req.getRequestDispatcher("WEB-INF/receptionist/admin/changePassword.jsp").forward(req, resp);
        }


    }
}
