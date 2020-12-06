package ir.torshizi.upload_with_base64.exception_handling.exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.util.StringUtils;

import java.util.Map;

@Getter
@Setter
public class FileStorageException extends MainException {

    public FileStorageException() {
        super(generateMessage());
    }

    private static String generateMessage() {
        return "Can not access to file storage device!";
    }
}
