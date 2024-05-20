package dormitory.servlets.receptionist;


import dormitory.manager.StudentManager;
import dormitory.models.Student;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/studentsBlockList")
public class StudentsBlockListServlet extends HttpServlet {
   StudentManager studentManager = new StudentManager();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
     String search = req.getParameter("search");
        studentManager.checkStatusToChange();
        List<Student> all;
        if (search == null){
            all = studentManager.getAllBan();
        }else{
            all = studentManager.getByNameOrSurnameBan(search);
        }
        int numberOfStudents = studentManager.getBanStudentsNumber();
        req.setAttribute("numberOfStudents", numberOfStudents);
        req.setAttribute("students",all);
        req.getRequestDispatcher("WEB-INF/receptionist/director/blockList.jsp").forward(req,resp);

    }
}
