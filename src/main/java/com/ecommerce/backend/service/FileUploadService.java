package com.ecommerce.backend.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Objects;

@Service
@Slf4j
public class FileUploadService {

    @Value("${file.upload.max-size:10485760}") // Default 10 MB
    private long maxFileSize;

    @Value("${file.upload.allowed-extensions:pdf}")
    private String[] allowedExtensions;

    /**
    * Saves a file in the specified directory with the given name
     *
    * @param file      uploaded file (MultipartFile)
    * @param name      name to save the file as (e.g. quotation_15.pdf)
    * @param uploadDir destination folder (e.g. uploads/quotations)
    * @return full path of the saved file
     */
    public String uploadFile(MultipartFile file, String name, String uploadDir) throws IOException {
        validateFile(file);

        Path uploadPath = Paths.get(uploadDir);
        ensureDirectoryExists(uploadPath);

        Path filePath = uploadPath.resolve(name);
        log.info("Saving file to: {}", filePath.toAbsolutePath());

        Files.copy(file.getInputStream(), filePath, java.nio.file.StandardCopyOption.REPLACE_EXISTING);

        return filePath.toString();
    }

    private void validateFile(MultipartFile file) {
        if (file.isEmpty()) {
            throw new IllegalArgumentException("The file is empty.");
        }

        if (file.getSize() > maxFileSize) {
            throw new IllegalArgumentException("The file exceeds the maximum allowed size of " +
                    (maxFileSize / (1024 * 1024)) + "MB.");
        }

        String extension = Objects.requireNonNull(
                StringUtils.getFilenameExtension(file.getOriginalFilename())
        ).toLowerCase();

        if (!isValidExtension(extension)) {
            throw new IllegalArgumentException("Only files allowed: " + String.join(", ", allowedExtensions));
        }
    }

    private boolean isValidExtension(String extension) {
        for (String allowed : allowedExtensions) {
            if (allowed.equalsIgnoreCase(extension)) {
                return true;
            }
        }
        return false;
    }

    private void ensureDirectoryExists(Path path) throws IOException {
        File directory = path.toFile();
        if (!directory.exists() && !directory.mkdirs()) {
            throw new IOException("Could not create directory: " + path.toAbsolutePath());
        }
    }
}
