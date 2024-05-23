package dormitory.servlets.receptionist;

import dormitory.emailVerifycation.EmailSender;
import dormitory.manager.StudentManager;
import dormitory.models.Student;
import dormitory.models.StudentStatus;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/updateRemark")
public class UpdateRemarkServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (req.getParameter("id") != null || req.getParameter("id").isEmpty()) {
            try {
                int id = Integer.parseInt(req.getParameter("id"));
                StudentManager studentManager = new StudentManager();
                Student student = studentManager.getById(id);
                if (student.getId() != 0 && student.getStudentStatus().equals(StudentStatus.ACTIVE)) {
                    if (student.getRemark() < 2) {
                        studentManager.updateRemarkById(student.getId());
                        req.setAttribute("doneMsg", "the remark was incremented by one!");
                    } else if (student.getRemark() == 2) {

                        studentManager.blockStudentById(student.getId());
                        EmailSender emailSender = new EmailSender();
                        String subject = "You have been blocked ";
                        String mail = "You have been blocked and deprived of your available room. " +
                                "\n Let us remind you that in our dormitory students are reprimanded " +
                                "for bad behavior and you have already crossed the threshold of 3 reprimands";
                        emailSender.sendInformantMail(student.getEmail(), subject, mail);
                        req.setAttribute("doneMsg", "the student has been blocked!");
                    }
                    req.getRequestDispatcher("WEB-INF/receptionist/director/reprimand.jsp").forward(req, resp);
                } else {
                    resp.sendRedirect("/reprimandStudent");
                }
            } catch (NumberFormatException e) {
                e.printStackTrace();
                resp.sendRedirect("/directorControl");
            }
        }
    }
}
