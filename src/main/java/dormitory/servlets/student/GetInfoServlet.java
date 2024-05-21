package dormitory.servlets.student;


import dormitory.manager.StudentManager;
import dormitory.models.Student;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/getInfo")
public class GetInfoServlet extends HttpServlet {
   StudentManager studentManager = new StudentManager();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

     String search = req.getParameter("search");
        Student student = null;
    try {
        if (search != null){
            int id = Integer.parseInt(search);
           student = studentManager.getById(id);
            req.setAttribute("student",student);
            req.getRequestDispatcher("WEB-INF/student/getInfo.jsp").forward(req,resp);
        }else {
            req.setAttribute("student",student);
            req.getRequestDispatcher("WEB-INF/student/getInfo.jsp").forward(req,resp);
        }
    }catch (NumberFormatException e){
        resp.sendRedirect("/getInfo");
        e.printStackTrace();
    }


    }
}
