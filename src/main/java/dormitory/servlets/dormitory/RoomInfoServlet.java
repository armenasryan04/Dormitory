package dormitory.servlets.dormitory;

import dormitory.manager.StudentManager;
import dormitory.models.Room;
import dormitory.models.Student;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;

@WebServlet("/roomsInfo")
public class RoomInfoServlet extends HttpServlet {
    StudentManager studentManager = new StudentManager();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("id"));
        Student student = studentManager.getById(id);
        if (student.getId() != 0) {
            Room room = student.getRoom();
            Date releaseDay = student.getEndDate();
            String timer = student.getDaysUntil(releaseDay);
            req.setAttribute("timer",timer);
            req.setAttribute("date",releaseDay);
            req.setAttribute("room", room);
            req.getRequestDispatcher("WEB-INF/room/roomInfo.jsp").forward(req,resp);
        }else {
            resp.sendRedirect("/loginConductor");
        }
    }
}
