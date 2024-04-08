package dormitory.servlets.student;

import dormitory.manager.RoomManager;
import dormitory.models.Room;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/studentDataFilling")
public class StudentAddFormServlet extends HttpServlet {
    RoomManager roomManager = new RoomManager();
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException,NullPointerException,NumberFormatException {
       if (req.getSession().getAttribute("room") == null){
           int id = Integer.parseInt(req.getParameter("roomId"));
           Room room = roomManager.getById(id);
           req.getSession().setAttribute("room", room);
           req.setCharacterEncoding("UTF-8");
           req.getRequestDispatcher("WEB-INF/student/dataFilling.jsp").forward(req,resp);
       }else {
           req.getRequestDispatcher("WEB-INF/student/dataFilling.jsp").forward(req,resp);
       }
    }
}
