package ir.torshizi.upload_with_base64.object;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
public class UserDto {

    private Long id;

    @NotNull
    private String firstName;

    @NotEmpty
    private String lastName;

    @NotEmpty
    private String picFile;

    private String picUrl;
}
