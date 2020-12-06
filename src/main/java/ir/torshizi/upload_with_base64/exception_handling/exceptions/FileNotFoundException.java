package ir.torshizi.upload_with_base64.exception_handling.exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.util.StringUtils;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.IntStream;

@Getter
@Setter
public class FileNotFoundException extends MainException {
    public FileNotFoundException(String fileName) {
        super(generateMessage(fileName));
    }

    private static String generateMessage(String fileName) {
        return String.format("The file '%s' could not be found!", fileName);
    }

}
