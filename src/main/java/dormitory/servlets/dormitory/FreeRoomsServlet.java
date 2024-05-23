package dormitory.servlets.dormitory;

import dormitory.manager.RoomManager;
import dormitory.models.Room;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/freeRooms")
public class FreeRoomsServlet extends HttpServlet {
    RoomManager roomManager = new RoomManager();

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getSession().removeAttribute("room");
        String search = req.getParameter("search");
        String id = req.getParameter("id");
        List<Room> all;
        if (id == null || id.equals("null")) {
            if (search != null && search != "") {
                String[] room = search.split("-");
                all = roomManager.getOnlyFreeRoomsByFloorOrRoom(Integer.parseInt(room[0]), Integer.parseInt(room[1]));
            } else {
                all = roomManager.getOnlyFreeRooms();

            }
        } else {
            req.setAttribute("id", id);
            all = roomManager.getOnlyFreeRooms();
            if (search != null && search != "") {
                String[] room = search.split("-");
                all = roomManager.getOnlyFreeRoomsByFloorOrRoom(Integer.parseInt(room[0]), Integer.parseInt(room[1]));
            }
        }

        if (req.getSession().getAttribute("errMsg") != null) {
            req.getSession().removeAttribute("errMsg");
            req.setAttribute("errMsg", "Choose room!");
        }
        req.setAttribute("room", all);
        req.getRequestDispatcher("WEB-INF/room/freeRoms.jsp").forward(req, resp);

    }

}
