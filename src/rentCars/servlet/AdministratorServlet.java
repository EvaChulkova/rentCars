package rentCars.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import rentCars.service.AdministratorService;
import rentCars.util.JSPHelper;

import java.io.IOException;

@WebServlet("/admins")
public class AdministratorServlet extends HttpServlet {
    private final AdministratorService administratorService = AdministratorService.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("admins", administratorService.findAll());

        req.getRequestDispatcher(JSPHelper.getPath("administrators"))
                        .forward(req, resp);

        /*resp.setContentType("text/html");
        resp.setCharacterEncoding(StandardCharsets.UTF_8.name());

        try (PrintWriter printWriter = resp.getWriter()) {
            printWriter.write("<h1>Администраторы:</h1>");
            printWriter.write("<ul>");
            administratorService.findAll().forEach(administratorDto -> {
                printWriter.write("""
                        <li>
                        %d - %s
                        </li>
                        """.formatted(administratorDto.getId(), administratorDto.getDescription()));
            });
            printWriter.write("</ul>");
        }*/
    }
}
