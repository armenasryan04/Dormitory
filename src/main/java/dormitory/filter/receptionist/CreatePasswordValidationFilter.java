package dormitory.filter.receptionist;

import dormitory.emailVerifycation.EmailSender;
import dormitory.models.Receptionist;
import dormitory.validation.Validation;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Random;

@WebFilter(urlPatterns = {"/registrantEmailVerify"})
public class CreatePasswordValidationFilter implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpServletResponse resp = (HttpServletResponse) servletResponse;
        Receptionist receptionist = (Receptionist) req.getSession().getAttribute("receptionist");
        if (receptionist != null && receptionist.getReceptionistRole() == null) {
            String password = req.getParameter("password");
            String confirmPassword = req.getParameter("confirmPassword");
            if (!Validation.isValidPassword(password) && !password.equals(confirmPassword)) {
                req.setAttribute("errMsg", "Invalid Password");
                req.getRequestDispatcher("WEB-INF/receptionist/registrant/createPassword.jsp").forward(req, resp);
            } else {
                Random random = new Random();
                int randomNumber = random.nextInt(900000) + 100000;
                receptionist.setVerifyCode(String.valueOf(randomNumber));
                EmailSender emailSender = new EmailSender();
                emailSender.sendMail(receptionist.getEmail(), randomNumber);
                receptionist.setPassword(password);
                filterChain.doFilter(req, resp);
            }
        } else {
            resp.sendRedirect("/login");
        }

    }
}
