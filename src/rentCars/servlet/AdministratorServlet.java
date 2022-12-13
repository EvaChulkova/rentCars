package rentCars.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import rentCars.service.AdministratorService;

import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;

@WebServlet("/admins")
public class AdministratorServlet extends HttpServlet {
    private final AdministratorService administratorService = AdministratorService.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
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
        }
    }
}
