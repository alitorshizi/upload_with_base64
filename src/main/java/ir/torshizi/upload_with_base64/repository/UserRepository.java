package ir.torshizi.upload_with_base64.repository;

import ir.torshizi.upload_with_base64.object.UserEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<UserEntity,Long> {
}
