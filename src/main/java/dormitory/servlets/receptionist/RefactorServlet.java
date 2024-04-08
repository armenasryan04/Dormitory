package dormitory.servlets.receptionist;

import dormitory.validation.RemoveFormSessionAttributes;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/refactorMenu")
public class RefactorServlet extends HttpServlet  {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp)  throws ServletException, IOException {
        RemoveFormSessionAttributes.remove(req);
        req.getRequestDispatcher("WEB-INF/receptionist/admin/refactor.jsp").forward(req,resp);
    }
}
