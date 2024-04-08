package dormitory.filter.student;

import dormitory.emailVerifycation.EmailSender;
import dormitory.manager.RoomManager;
import dormitory.manager.StudentManager;
import dormitory.models.Room;
import dormitory.models.Student;
import dormitory.validation.Validation;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Random;

@WebFilter(urlPatterns = {"/emailReVerify"})
public class ActivateValidationFilter implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpServletResponse resp = (HttpServletResponse) servletResponse;
        StudentManager studentManager = new StudentManager();
        try{
            Student student = (Student) req.getSession().getAttribute("student");
            String date = req.getParameter("date");
            String email = req.getParameter("email");
            if (email != null && !email.isEmpty() || !email.equals(student.getEmail())){
                if (Validation.isEmailAddressValid(student.getEmail()) && Validation.isEmailFree(student,email,studentManager)){
                    student.setEmail(email);
                }else {
                    req.setAttribute("errMsg","invalid email :-(");
                    req.getRequestDispatcher("WEB-INF/student/setDateAndEmail.jsp").forward(req, resp);
                }
            }
            if (!date.isEmpty()){
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                java.util.Date utilDate;
                java.sql.Date sqlDate;
                try {
                    utilDate = dateFormat.parse(date);
                    sqlDate = new java.sql.Date(utilDate.getTime());
                    Random random = new Random();
                    int randomNumber = random.nextInt(900000) + 100000;
                    student.setDate(sqlDate);
                    student.setVerifyCode(String.valueOf(randomNumber));
                    EmailSender emailSender = new EmailSender();
                    if (Validation.isDateValid(student.getDate()) && student.getEmail() != null && Validation.isEmailAddressValid(student.getEmail()) && emailSender.sendMail(student.getEmail(), randomNumber)) {
                        req.setAttribute("student", student);
                        filterChain.doFilter(req, resp);
                    } else {
                        req.setAttribute("errMsg", "invalid date or Email");
                        req.getRequestDispatcher("WEB-INF/student/setDateAndEmail.jsp").forward(req, resp);
                    }
                } catch (ParseException e) {
                    req.getSession().invalidate();
                    req.setAttribute("errMsg", "something suspicious was noticed :-(");

                }
            }else {
                req.setAttribute("errMsg","Please choose date!");
                req.getRequestDispatcher("WEB-INF/student/setDateAndEmail.jsp").forward(req, resp);
            }
        }catch (NullPointerException e){
            resp.sendRedirect("/control?status=archive");
        }

    }
}



