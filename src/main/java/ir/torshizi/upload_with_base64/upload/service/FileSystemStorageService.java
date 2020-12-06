package ir.torshizi.upload_with_base64.upload.service;

import ir.torshizi.upload_with_base64.upload.FileUploadProperties;
import ir.torshizi.upload_with_base64.exception_handling.exceptions.FileNotFoundException;
import ir.torshizi.upload_with_base64.exception_handling.exceptions.FileStorageException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;


@Service
public class FileSystemStorageService {
    private final Path dirLocation;

    @Autowired
    public FileSystemStorageService(FileUploadProperties fileUploadProperties) {
        this.dirLocation = Paths.get(fileUploadProperties.getLocation()).toAbsolutePath().normalize();
    }

    @PostConstruct
    public void init() {
        try {
            Files.createDirectories(this.dirLocation);
        } catch (Exception exception) {
            throw new FileStorageException();
        }
    }


    public String saveFile(String fileExtension, byte[] file) {
        try {
            String fileName = generateUniqueFileName(fileExtension);
            Path destinationFile = this.dirLocation.resolve(fileName);
            Files.write(destinationFile, file);
            return fileName;
        } catch (Exception exception) {
            throw new FileStorageException();
        }
    }

    String generateUniqueFileName(String fileExtension) {
        return (UUID.randomUUID().toString()
                .concat("_")
                .concat(String.valueOf(System.currentTimeMillis()))
                .concat(".")
                .concat(fileExtension));
    }


    public Resource loadFile(String fileName) {
        try {
            Path file = this.dirLocation.resolve(fileName).normalize();
            Resource resource = new UrlResource(file.toUri());

            if (resource.exists() || resource.isReadable()) {
                return resource;
            } else {
                throw new FileNotFoundException(fileName);
            }
        } catch (MalformedURLException exception) {
            throw new FileStorageException();
        }
    }
}
