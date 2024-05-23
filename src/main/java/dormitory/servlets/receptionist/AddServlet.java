package dormitory.servlets.receptionist;

import dormitory.manager.ReceptionistManager;
import dormitory.models.Receptionist;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/addReceptionist")
public class AddServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ReceptionistManager receptionistManager = new ReceptionistManager();
        Receptionist receptionist = (Receptionist) req.getSession().getAttribute("receptionist");
        receptionistManager.addToDb(receptionist);
        req.getSession().removeAttribute("receptionist");
        req.setAttribute("doneMsg", "thanks for registration :-) we call you soon!");
        req.getRequestDispatcher("WEB-INF/receptionist/global/login.jsp").forward(req, resp);
    }
}
