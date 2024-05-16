package dormitory.filter.receptionist;

import dormitory.models.Receptionist;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter(urlPatterns = {"/deactivation"})
public class DeactivationFilter implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpServletResponse resp = (HttpServletResponse) servletResponse;
        Receptionist receptionist = (Receptionist) req.getSession().getAttribute("receptionist");
        req.setCharacterEncoding("UTF-8");
        try {
            if (receptionist.getControlCode().equals(req.getParameter("code").trim())) {
                filterChain.doFilter(req,resp);
            }else {
                req.setAttribute("errMsg","not variable code try again!");
                req.getRequestDispatcher("WEB-INF/receptionist/admin/verifyControlCode.jsp").forward(req, resp);
            }
        } catch (NullPointerException e) {
            resp.sendRedirect("/login");
        }

    }
}



