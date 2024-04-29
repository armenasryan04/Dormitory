package dormitory.filter.receptionist;

import dormitory.manager.ReceptionistManager;
import dormitory.manager.RoomManager;
import dormitory.models.Gender;
import dormitory.models.Receptionist;
import dormitory.validation.Validation;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Random;

@WebFilter(urlPatterns = {"/createPassword"})
public class RegistrantDataValidationFilter implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpServletResponse resp = (HttpServletResponse) servletResponse;
        if (req.getSession().getAttribute("receptionist") == null) {
            ReceptionistManager receptionistManager = new ReceptionistManager();
            req.setCharacterEncoding("UTF-8");
            try {
                String gender = req.getParameter("gender");
                if (!gender.equals("MALE") && !gender.equals("FEMALE") && !gender.equals("null")) {
                    req.setAttribute("errMsg", "something suspicious was noticed :-(");
                    req.getRequestDispatcher("WEB-INF/receptionist/global/login.jsp").forward(req, resp);
                } else if (gender.equals("null")) {
                    req.setAttribute("errMsg", "please choose gender");
                    req.getRequestDispatcher("WEB-INF/receptionist/registrant/signUp.jsp").forward(req, resp);
                }
                String birthday = req.getParameter("birthday");
                java.util.Date utilBirthday;
                java.sql.Date sqlBirthday;
                try {
                    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                    utilBirthday = dateFormat.parse(birthday);
                    sqlBirthday = new java.sql.Date(utilBirthday.getTime());
                    Receptionist receptionist = Receptionist.builder()
                            .name(req.getParameter("name").trim())
                            .surname(req.getParameter("surname").trim())
                            .email(req.getParameter("email").trim())
                            .phone(req.getParameter("phone").trim())
                            .experienceInformation(req.getParameter("experienceInformation").trim())
                            .gender(Gender.valueOf(gender))
                            .birthday(sqlBirthday)
                            .build();
                    String checkAnswer = Validation.checkValidation(receptionist, receptionistManager);
                    if (checkAnswer == null) {
                        req.getSession().setAttribute("receptionist", receptionist);
                        filterChain.doFilter(req, resp);
                    } else {
                        req.getSession().setAttribute("receptionist", Validation.removeInvalidData(receptionist, receptionistManager));
                        req.setAttribute("errMsg", checkAnswer);
                        req.getRequestDispatcher("WEB-INF/receptionist/registrant/signUp.jsp").forward(req, resp);
                    }
                } catch (ParseException e) {
                    e.printStackTrace();
                    req.getSession().invalidate();
                    req.setAttribute("errMsg", "something suspicious was noticed :-(");
                    req.getRequestDispatcher("WEB-INF/receptionist/global/login.jsp").forward(req, resp);

                }
            } catch (NullPointerException e) {
                resp.sendRedirect("/login");
            }
        }else {
            filterChain.doFilter(req, resp);
        }
    }
}
