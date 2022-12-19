package rentCars.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import rentCars.service.ClientService;
import rentCars.util.JSPHelper;

import java.io.IOException;

@WebServlet("/clients")
public class ClientServlet extends HttpServlet {
    private final ClientService clientService = ClientService.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("clients", clientService.findAll());

        req.getRequestDispatcher(JSPHelper.getPath("clients"))
                        .forward(req, resp);
    }
}
