package dormitory.filter.receptionist;

import dormitory.manager.URLManager;
import dormitory.models.Receptionist;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
@WebFilter("/*")
public class AdminFilter implements Filter {
URLManager urlManager = new URLManager();
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpServletResponse resp = (HttpServletResponse) servletResponse;
        String url = req.getRequestURI();
        if ("/".equals(url) || "/login".equals(url) ||"/loginConductor".equals(url)|| "/studentList".equals(url) || "/roomsInfo".equals(url)|| "/logout".equals(url) || "/signUp".equals(url) || "/createPassword".equals(url) ||"/registrantEmailVerify".equals(url)||"/addReceptionist".equals(url) || "/resetPassword".equals(url) || "/sendNewPassword".equals(url) || "/emailVerifyForReset".equals(url)) {
            filterChain.doFilter(req, resp);
        }else {
            HttpSession session = req.getSession();
            Receptionist receptionist = (Receptionist) session.getAttribute("receptionist");
            if (receptionist != null ) {
                if ( urlManager.isAcceptable(url,receptionist.getReceptionistRole())){
                    filterChain.doFilter(req, resp);
                } else {
                   resp.sendRedirect("/loginConductor");
                }
            }else {
                resp.sendRedirect("/login");
            }
        }
    }
}
