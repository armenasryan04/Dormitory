package dormitory.filter.receptionist;

import dormitory.emailVerifycation.EmailSender;
import dormitory.manager.ReceptionistManager;
import dormitory.models.Receptionist;
import dormitory.models.ReceptionistRole;
import dormitory.validation.Validation;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Random;

@WebFilter(urlPatterns = {"/emailVerifyForReset"})
public class EmailVerifyForResetFilter implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpServletResponse resp = (HttpServletResponse) servletResponse;
        req.setCharacterEncoding("UTF-8");
        ReceptionistManager receptionistManager = new ReceptionistManager();
        try {
            if (req.getParameter("email") != null && !req.getParameter("email").isEmpty() && Validation.isEmailAddressValid(req.getParameter("email"))) {
                Receptionist receptionist = receptionistManager.getByEmail(req.getParameter("email"));
                if (receptionist.getId() != 0 && !receptionist.getReceptionistRole().equals(ReceptionistRole.REGISTRANT)) {
                    int randomNum = (int) req.getSession().getAttribute("verifyCode");
                    req.getSession().setAttribute("email", receptionist.getEmail());
                    EmailSender emailSender = new EmailSender();
                    emailSender.sendMail(req.getParameter("email"), randomNum);
                    filterChain.doFilter(req, resp);
                } else {
                    req.setAttribute("errMsg", "we dont have employee with this email");
                    req.getRequestDispatcher("WEB-INF/receptionist/global/verifyEmailResetPass.jsp").forward(req, resp);
                }
            } else {
                req.setAttribute("errMsg", "invalid email address");
                req.getRequestDispatcher("WEB-INF/receptionist/global/verifyEmailResetPass.jsp").forward(req, resp);
            }
        } catch (NullPointerException e) {
            e.printStackTrace();
            resp.sendRedirect("/login");
        }

    }
}



