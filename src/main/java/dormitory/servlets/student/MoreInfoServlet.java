package dormitory.servlets.student;

import dormitory.manager.StudentManager;
import dormitory.models.Student;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/moreInfo")
public class MoreInfoServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
       String id =  req.getParameter("id");
       if (id != null && !id.isEmpty()) {
           StudentManager studentManager = new StudentManager();
           try{
               Student student = studentManager.getById(Integer.parseInt(id));
               if (student.getId() != 0 ){
                   req.setAttribute("student", student);
                   req.getRequestDispatcher("WEB-INF/receptionist/director/moreInfo.jsp").forward(req, resp);
               }else {
                   resp.sendRedirect("/directorControl");
               }
           }catch (NumberFormatException e){
               resp.sendRedirect("/directorControl");
           }

       }else {
           resp.sendRedirect("/directorControl");
       }
    }
}
