package ir.torshizi.upload_with_base64;

import ir.torshizi.upload_with_base64.upload.FileUploadProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties({
		FileUploadProperties.class
})
public class UploadWithBase64Application {

	public static void main(String[] args) {
		SpringApplication.run(UploadWithBase64Application.class, args);
	}

}
