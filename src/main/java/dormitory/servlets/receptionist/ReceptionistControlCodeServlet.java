package dormitory.servlets.receptionist;

import dormitory.manager.ReceptionistManager;
import dormitory.models.ReceptionistRole;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/controlCode")
public class ReceptionistControlCodeServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ReceptionistManager receptionistManager = new ReceptionistManager();
       try {
           if (req.getParameter("id") != null) {
               int id = Integer.parseInt(req.getParameter("id"));
               String controlCode = receptionistManager.getById(id).getControlCode();
               if (controlCode == null) {
                   controlCode = ":-(";
               }
               req.setAttribute("controlCode", controlCode);
               req.getRequestDispatcher("WEB-INF/receptionist/director/controlCode.jsp").forward(req, resp);
           }else {
               resp.sendRedirect("/listOfReceptionists");
           }
       } catch (NumberFormatException e){
           resp.sendRedirect("/listOfReceptionists");
       }

    }
}
