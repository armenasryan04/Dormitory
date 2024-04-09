package dormitory.filter.receptionist;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter(urlPatterns = {"/*"})
public class AuthFilter implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpServletResponse resp = (HttpServletResponse) servletResponse;
        String url = req.getRequestURI();
        if ("/".equals(url) || "/login".equals(url) ||"/loginConductor".equals(url) || "/singInUp.jsp".equals(url) || "/studentList".equals(url) || "/roomsInfo".equals(url)|| "/logout".equals(url) ) {
            filterChain.doFilter(req, resp);
        }else {
            HttpSession session = req.getSession();
            if (session.getAttribute("receptionist") != null) {
                filterChain.doFilter(req, resp);
            } else {
                resp.sendRedirect("/login");
            }
        }
    }
}
