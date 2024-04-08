package dormitory.validation;


import javax.servlet.http.HttpServletRequest;

public class RemoveFormSessionAttributes {
    public static void remove(HttpServletRequest req) {
        req.getSession().removeAttribute("email");
        req.getSession().removeAttribute("password");
        req.getSession().removeAttribute("newEmail");
        req.getSession().removeAttribute("student");
        req.getSession().removeAttribute("room");
    }
}
