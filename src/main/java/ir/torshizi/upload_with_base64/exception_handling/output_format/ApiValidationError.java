package ir.torshizi.upload_with_base64.exception_handling.output_format;

import ir.torshizi.upload_with_base64.exception_handling.exceptions.MainException;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
@AllArgsConstructor
public class ApiValidationError extends ApiSubError {
    private String object;
    private String field;
    private Object rejectedValue;
    private String message;

    ApiValidationError(String object, String message) {
        this.object = object;
        this.message = message;
    }

    public String getObject() {
        return MainException.getClassName(this.object);
    }
}
