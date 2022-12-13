package rentCars.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import rentCars.service.ClientService;

import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;

@WebServlet("/clients")
public class ClientServlet extends HttpServlet {
    private final ClientService clientService = ClientService.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        resp.setCharacterEncoding(StandardCharsets.UTF_8.name());

        try (PrintWriter printWriter = resp.getWriter()) {
            printWriter.write("<h1>Клиенты:</h1>");
            printWriter.write("<ul>");
            clientService.findAll().forEach(clientDto -> {
                printWriter.write("""
                        <li>
                        %d - %s
                        </li>
                        """.formatted(clientDto.getId(), clientDto.getDescription()));
            });
            printWriter.write("</ul>");
        }

    }
}
