package dormitory.servlets.receptionist;

import dormitory.manager.PermissionManager;
import dormitory.manager.StudentManager;
import dormitory.models.Student;
import dormitory.validation.RemoveFormSessionAttributes;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/control")
public class AdminControlServlet extends HttpServlet {
    StudentManager studentManager = new StudentManager();


    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RemoveFormSessionAttributes.remove(req);
        PermissionManager permissionManager = new PermissionManager();
        permissionManager.changer();
        String search = req.getParameter("search");
        String archive = req.getParameter("status");
        studentManager.checkStatusToChange();
        List<Student> all;
        int numberOfStudents;
        if (archive == null || archive.equals("null")) {
            if (search == null) {
                all = studentManager.getAllActive();
            } else {
                all = studentManager.getByNameOrSurnameActive(search.trim());
            }
            numberOfStudents = studentManager.getActiveStudentsNumber();
        } else {
            req.setAttribute("inArchive", "archive");
            all = studentManager.getAllArchive();

            if (search !=null){
                all = studentManager.getByNameOrSurnameArchive(search.trim());
            }
            if (req.getSession().getAttribute("errMsg")!=null){
                req.getSession().removeAttribute("errMsg");
                req.setAttribute("errMsg","Choose Student for Activate!");
            }
            numberOfStudents = studentManager.getArchiveStudentsNumber();
        }
        req.setAttribute("numberOfStudents", numberOfStudents);
        req.setAttribute("students", all);
        req.getRequestDispatcher("WEB-INF/receptionist/admin/control.jsp").forward(req, resp);
    }
}
