package ir.torshizi.upload_with_base64.exception_handling.exceptions;
import org.springframework.util.StringUtils;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.IntStream;

public class EntityNotFoundException extends MainException {

    public EntityNotFoundException(Class<?> clazz, String... searchParamsMap) {
        super(generateMessage(getClassName(clazz), toMap(String.class, String.class, searchParamsMap)));
    }

    private static String generateMessage(String entity, Map<String, String> searchParams) {
        return StringUtils.capitalize(entity) +
                " was not found for parameters " +
                searchParams;
    }

}
