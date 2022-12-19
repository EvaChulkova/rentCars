package rentCars.service;

import lombok.NoArgsConstructor;
import rentCars.util.RentCarsPropertiesUtil;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Optional;

import static java.nio.file.StandardOpenOption.CREATE;
import static java.nio.file.StandardOpenOption.TRUNCATE_EXISTING;
import static lombok.AccessLevel.PRIVATE;

@NoArgsConstructor(access = PRIVATE)
public class ImageService {
    private static final ImageService INSTANCE = new ImageService();
    private final String basePath = RentCarsPropertiesUtil.get("image.base.url");

    public void upload(String imagePath, InputStream imageContent) {
        var imageFullPath = Path.of(basePath, imagePath);
        try(imageContent) {
            Files.createDirectories(imageFullPath.getParent());
            Files.write(imageFullPath, imageContent.readAllBytes(), CREATE, TRUNCATE_EXISTING);
        } catch (IOException e) {
            throw new RuntimeException("Something wrong with upload", e);
        }
    }

    public Optional<InputStream> get(String imagePath) {
        Path imageFullPath = Path.of(basePath, imagePath);
        try {
            return Files.exists(imageFullPath)
                    ? Optional.of(Files.newInputStream(imageFullPath))
                    : Optional.empty();
        } catch (IOException e) {
            throw new RuntimeException("Something wrong with image path", e);
        }
    }

    public static ImageService getInstance() {
        return INSTANCE;
    }
}
