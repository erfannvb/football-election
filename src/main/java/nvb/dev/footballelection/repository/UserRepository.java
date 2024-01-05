package nvb.dev.footballelection.repository;

import nvb.dev.footballelection.base.repository.BaseRepository;
import nvb.dev.footballelection.entity.User;

import java.util.Optional;

public interface UserRepository extends BaseRepository<Long, User> {

    Optional<User> findUserByUsernameAndPassword(String username, String password);

}
