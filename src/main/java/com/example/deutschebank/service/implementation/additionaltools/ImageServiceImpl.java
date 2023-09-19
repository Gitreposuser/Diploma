package com.example.deutschebank.service.implementation.additionaltools;

import com.example.deutschebank.service.interfaces.additionaltools.ImageService;
import org.springframework.stereotype.Component;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

@Component
public class ImageServiceImpl implements ImageService {
    @Override
    public byte[] convertImageToByteArray(String path) throws Exception {
        try {
            Path imagePath = Path.of(path);
            byte[] imageBytes = Files.readAllBytes(imagePath);
            return imageBytes;
        } catch (IOException e) {
            e.printStackTrace();
        }
        throw new Exception();
    }
}