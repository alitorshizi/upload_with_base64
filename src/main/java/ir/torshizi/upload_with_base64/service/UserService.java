package ir.torshizi.upload_with_base64.service;

import ir.torshizi.upload_with_base64.exception_handling.exceptions.EntityNotFoundException;
import ir.torshizi.upload_with_base64.object.*;
import ir.torshizi.upload_with_base64.repository.UserRepository;
import ir.torshizi.upload_with_base64.upload.service.FileSystemStorageService;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.util.Base64Utils;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final FileSystemStorageService fileSystemStorageService;

    public UserService(UserRepository userRepository,
                       FileSystemStorageService fileSystemStorageService) {
        this.userRepository = userRepository;
        this.fileSystemStorageService = fileSystemStorageService;
    }

    public UserDto save(UserDto user) {
        byte[] fileBytes = Base64Utils.decodeFromString(user.getPicFile().split(",")[1]);
        String uploadedFileName = fileSystemStorageService.saveFile(getFileExtension(user.getPicFile()), fileBytes);
        String fileUrl = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/loadFile/")
                .path(uploadedFileName)
                .toUriString();
        UserEntity userEntity = UserMapper.INSTANCE.toUserEntity(user);
        userEntity.setPicUrl(fileUrl);
        return (UserMapper.INSTANCE.toUserDto(userRepository.save(userEntity)));
    }

    private String getFileExtension(String file) {
        return file.substring(file.indexOf("/") + 1, file.indexOf(";"));
    }

    public Resource loadFile(String fileName) {
        return fileSystemStorageService.loadFile(fileName);
    }

    public UserDto getUser(Long id){
        Optional<UserEntity> userEntity = userRepository.findById(id);
        return UserMapper.INSTANCE.toUserDto(userEntity.orElseThrow(()-> new EntityNotFoundException(UserEntity.class,"id", id.toString())));
    }

}
