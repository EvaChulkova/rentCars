package rentCars.servlet.CreateServlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import rentCars.dto.CreateDto.CreateCarDto;
import rentCars.entity.enums.CarColorEnum;
import rentCars.entity.enums.CarStatusEnum;
import rentCars.service.CarService;
import rentCars.util.JSPHelper;

import java.io.IOException;

@MultipartConfig(fileSizeThreshold = 1024 * 1024)
@WebServlet("/add_new_car")
public class CreateCarServlet extends HttpServlet {
    private final CarService carService = CarService.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("add_car", carService.findAll());
        req.setAttribute("color", CarColorEnum.values());
        req.setAttribute("status", CarStatusEnum.values());

        req.getRequestDispatcher(JSPHelper.getPath("createCar"))
                .forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        CreateCarDto createCarDto = CreateCarDto.builder()
                .brand(req.getParameter("brand"))
                .color(req.getParameter("color"))
                .seatAmount(req.getParameter("seatAmount"))
                .price(req.getParameter("price"))
                .status(req.getParameter("status"))
                .image(req.getPart("image"))
                .build();

        carService.create(createCarDto);
        resp.sendRedirect("/cars");
    }
}
