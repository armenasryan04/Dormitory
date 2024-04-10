package dormitory.filter.receptionist;

import dormitory.models.Receptionist;
import dormitory.validation.Validation;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;


@WebFilter(urlPatterns = {"/saveNameSurname"})
public class RenameResurnameFilter implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpServletResponse resp = (HttpServletResponse) servletResponse;
        HttpSession session = req.getSession();
        Receptionist receptionist = (Receptionist) session.getAttribute("receptionist");
        String name;
        String surname;
        if (!req.getParameter("name").trim().isEmpty() && req.getParameter("name") != null) {
            name = req.getParameter("name");
            if (Validation.isNameValid(name)) {
                receptionist.setName(name);
            } else {
                req.setAttribute("errMsg", "Invalid Name :-(");
                req.getRequestDispatcher("WEB-INF/receptionist/global/changeNameSurname.jsp").forward(req, resp);
            }
        }
        if (!req.getParameter("surname").trim().isEmpty() && req.getParameter("surname") != null) {
            surname = req.getParameter("surname");
            if (Validation.isSurnameValid(surname)) {
                receptionist.setSurname(surname);
            } else {
                req.setAttribute("errMsg", "Invalid Surname :-(");
                req.getRequestDispatcher("WEB-INF/receptionist/global/changeNameSurname.jsp").forward(req, resp);
            }
        }
        session.setAttribute("receptionist", receptionist);
        filterChain.doFilter(req, resp);
    }
}