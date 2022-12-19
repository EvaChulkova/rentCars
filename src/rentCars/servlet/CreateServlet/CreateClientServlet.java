package rentCars.servlet.CreateServlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import rentCars.service.ClientService;
import rentCars.util.JSPHelper;

import java.io.IOException;

@WebServlet("/add_new_client")
public class CreateClientServlet extends HttpServlet {
    private final ClientService clientService = ClientService.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher(JSPHelper.getPath("createClient"))
                .forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        /*var createClientDto = CreateClientDto.builder()
                .userId(String.valueOf(user.getId()))
                .age(req.getParameter("age"))
                .licenceNo(req.getParameter("licenceNo"))
                .validity(req.getParameter("validity"))
                .build();

        clientService.create(createClientDto);
        resp.sendRedirect("/cars");*/
    }
}
