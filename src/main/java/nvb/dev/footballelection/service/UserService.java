package nvb.dev.footballelection.service;

import nvb.dev.footballelection.base.service.BaseService;
import nvb.dev.footballelection.entity.User;

import java.util.Optional;

public interface UserService extends BaseService<Long, User> {

    boolean validate(User user);

    Optional<User> findUserByUsernameAndPassword(String username, String password);

    Optional<User> findUserByVoteId(long voteId);

}
