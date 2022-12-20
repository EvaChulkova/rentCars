package rentCars.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

import static rentCars.util.UrlPath.LOCALE;
import static rentCars.util.UrlPath.LOGIN;

@WebServlet(LOCALE)
public class LocaleServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String language = req.getParameter("lang");
        req.getSession().setAttribute("lang", language);

        String prevPage = req.getHeader("referer");
        var page = prevPage != null ? prevPage : LOGIN;
        resp.sendRedirect(prevPage + "?lang=" + language);
    }
}
