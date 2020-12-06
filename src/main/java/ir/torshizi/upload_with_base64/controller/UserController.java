package ir.torshizi.upload_with_base64.controller;

import ir.torshizi.upload_with_base64.object.UserDto;
import ir.torshizi.upload_with_base64.service.UserService;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;

@RestController
public class UserController {

    private final UserService userService;
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/create")
    public ResponseEntity<UserDto> create(@RequestBody @Valid UserDto user) {
        return ResponseEntity.status(HttpStatus.OK).body(userService.save(user));
    }

    @GetMapping("/loadFile/{fileName:.+}")
    public ResponseEntity<Resource> loadFile(@PathVariable String fileName) {
        Resource resource = userService.loadFile(fileName);
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; fileName=\"" + resource.getFilename() + "\"")
                .body(resource);
    }

    @GetMapping("/getUser/{id}")
    public ResponseEntity<UserDto> getUser(@PathVariable Long id){
        return ResponseEntity.status(HttpStatus.OK).body(userService.getUser(id));
    }

}
