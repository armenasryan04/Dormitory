package dormitory.filter.student;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
@WebFilter(urlPatterns = {"/studentDataFilling"})
public class CheckRoomChooseForAddFilter implements Filter {

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpServletResponse resp = (HttpServletResponse) servletResponse;
        if (req.getSession().getAttribute("room")!=null||req.getParameter("roomId") != null){
            filterChain.doFilter(req,resp);
        }else {
            req.getSession().setAttribute("errMsg","true");
         resp.sendRedirect("/freeRooms");
        }
    }
}
