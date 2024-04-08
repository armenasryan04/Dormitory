package dormitory.servlets.receptionist;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/directorControl")
public class DirectorControlServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        if (session.getAttribute("receptionist") !=null){
            req.getRequestDispatcher("WEB-INF/receptionist/admin/control.jsp").forward(req,resp);
        }else {
            resp.sendRedirect("/loginConductor");
        }
    }
}
