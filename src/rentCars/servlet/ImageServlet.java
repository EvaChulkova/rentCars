package rentCars.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import rentCars.service.ImageService;

import java.io.IOException;
import java.io.InputStream;

import static rentCars.util.UrlPath.IMAGES;

@WebServlet(IMAGES + "/*")
public class ImageServlet extends HttpServlet {
    private final ImageService imageService = ImageService.getInstance();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String requestURI = req.getRequestURI();
        var imagePath = requestURI.replace(IMAGES, "");

        imageService.get(imagePath)
                .ifPresentOrElse(image -> {
                    resp.setContentType("application/octet-type");
                    writeImage(image, resp);
                }, () -> resp.setStatus(404));

    }

    private void writeImage(InputStream image, HttpServletResponse resp) {
        try (image; var outputStream = resp.getOutputStream()) {
            int currentByte;
            while((currentByte = image.read()) != -1) {
                outputStream.write(currentByte);
            }
        } catch (IOException e) {
            throw new RuntimeException("Something wrong with writing image", e);
        }
    }
}
