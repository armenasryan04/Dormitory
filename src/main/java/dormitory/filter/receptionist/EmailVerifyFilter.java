package dormitory.filter.receptionist;

import dormitory.models.Receptionist;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter(urlPatterns = {"/addReceptionist","/changeEmail"})
public class EmailVerifyFilter implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpServletResponse resp = (HttpServletResponse) servletResponse;
        Receptionist receptionist = (Receptionist) req.getSession().getAttribute("receptionist");
        req.setCharacterEncoding("UTF-8");
        try {
            if (receptionist.getVerifyCode().equals(req.getParameter("code").trim())) {
                filterChain.doFilter(req,resp);
            }else {
                if (receptionist.getReceptionistRole() != null){
                    req.setAttribute("errMsg","not variable code try again!");
                    req.getRequestDispatcher("WEB-INF/receptionist/global/verifyEmail.jsp").forward(req, resp);
                } else {
                    req.setAttribute("errMsg","not variable code try again!");
                    req.getRequestDispatcher("WEB-INF/receptionist/registrant/verifyEmail.jsp").forward(req, resp);
                }
            }
        } catch (NullPointerException e) {
            resp.sendRedirect("/login");
        }

    }
}



