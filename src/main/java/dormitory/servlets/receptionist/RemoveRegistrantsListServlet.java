package dormitory.servlets.receptionist;

import dormitory.manager.ReceptionistManager;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/removeRegistrants")
public class RemoveRegistrantsListServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ReceptionistManager receptionistManager = new ReceptionistManager();
        receptionistManager.removeAllRegistrants();
        req.setAttribute("doneMsg", "List of registrants has been  removed");
        req.getRequestDispatcher("WEB-INF/receptionist/director/registrantsList.jsp").forward(req, resp);
    }
}
