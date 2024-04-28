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
import java.util.Date;
import java.util.Random;

@WebFilter(urlPatterns = {"/emailVerify"})
public class AddValidationFilter implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException, NullPointerException {
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpServletResponse resp = (HttpServletResponse) servletResponse;
        RoomManager roomManager = new RoomManager();
        StudentManager studentManager = new StudentManager();
        try {
            req.setCharacterEncoding("UTF-8");
            Room room = (Room) req.getSession().getAttribute("room");
            String deadline = req.getParameter("deadline");
            String birthday = req.getParameter("birthday");
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            String today = dateFormat.format(new Date());
            java.util.Date utilReisterDate;
            java.util.Date utilDeadline;
            java.util.Date utilBirthday;
            java.sql.Date sqlRegisterDate;
            java.sql.Date sqlDeadline;
            java.sql.Date sqlBirthday;
            try {
                utilReisterDate = dateFormat.parse(today);
                sqlRegisterDate = new  java.sql.Date(utilReisterDate.getTime());
                utilDeadline = dateFormat.parse(deadline);
                sqlDeadline = new java.sql.Date(utilDeadline.getTime());
                utilBirthday = dateFormat.parse(birthday);
                sqlBirthday = new java.sql.Date(utilBirthday.getTime());
                Random random = new Random();
                int randomNumber = random.nextInt(900000) + 100000;
                Student student = Student.builder()
                        .name(req.getParameter("name").trim())
                        .surname(req.getParameter("surname").trim())
                        .id(Integer.parseInt(req.getParameter("id").trim()))
                        .phoneNum(req.getParameter("phone").trim())
                        .email(req.getParameter("email").trim())
                        .registerDate(sqlRegisterDate)
                        .deadline(sqlDeadline)
                        .birthday(sqlBirthday)
                        .room(room)
                        .verifyCode(String.valueOf(randomNumber))
                        .build();
                EmailSender emailSender = new EmailSender();
                String checkAnswer = Validation.checkValidation(student, roomManager, studentManager);
                if (checkAnswer == null && emailSender.sendMail(student.getEmail(), randomNumber)) {
                    req.getSession().setAttribute("student", student);
                    filterChain.doFilter(req, resp);
                } else {
                    req.setAttribute("errMsg", checkAnswer);
                    req.getSession().setAttribute("student", Validation.removeInvalidData(student, studentManager));
                    req.getRequestDispatcher("WEB-INF/student/dataFilling.jsp").forward(req, resp);
                }
            } catch (ParseException e) {
                req.getSession().invalidate();
                req.setAttribute("errMsg", "something suspicious was noticed :-(");
                req.getRequestDispatcher("WEB-INF/receptionist/global/login.jsp").forward(req, resp);
            }
        } catch (NullPointerException e) {
            resp.sendRedirect("/control");
        }

    }
}



