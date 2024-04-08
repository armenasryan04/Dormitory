package dormitory.filter.student;

import dormitory.models.Student;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter(urlPatterns = {"/makeActive"})
public class EmailVerifyForActivateFilter implements Filter {

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpServletResponse resp = (HttpServletResponse) servletResponse;
        req.setCharacterEncoding("UTF-8");
        Student student = (Student) req.getSession().getAttribute("student");
        if (student != null){
            if (student.getVerifyCode().equals(req.getParameter("code").trim())) {
                filterChain.doFilter(req, resp);
            } else {
                req.setAttribute("errMsg", "not variable code try again!");
                req.getRequestDispatcher("WEB-INF/student/setDateAndEmail.jsp").forward(req, resp);
            }
        } else {
            req.getSession().setAttribute("errMsg","true");
            resp.sendRedirect("/control?status=archive");
        }
    }
}



