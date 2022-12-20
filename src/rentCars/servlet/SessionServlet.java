package rentCars.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import rentCars.dto.UserDto;

import java.io.IOException;

import static rentCars.util.UrlPath.SESSIONS;

@WebServlet(SESSIONS)
public class SessionServlet extends HttpServlet {
    private static final String USER = "user";
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        var user = (UserDto) session.getAttribute(USER);
        if (user == null) {
            user = UserDto.builder()
                    .id(2)
                    .login("test1")
                    .build();
            session.setAttribute(USER, user);
        }
    }
}
