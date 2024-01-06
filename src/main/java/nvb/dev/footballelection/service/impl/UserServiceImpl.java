package nvb.dev.footballelection.service.impl;

import jakarta.persistence.EntityManager;
import jakarta.validation.*;
import nvb.dev.footballelection.base.service.impl.BaseServiceImpl;
import nvb.dev.footballelection.entity.User;
import nvb.dev.footballelection.repository.UserRepository;
import nvb.dev.footballelection.service.UserService;
import org.hibernate.validator.messageinterpolation.ParameterMessageInterpolator;

import java.util.Optional;
import java.util.Set;

public class UserServiceImpl extends BaseServiceImpl<Long, User, UserRepository> implements UserService {

    protected final EntityManager entityManager;

    public UserServiceImpl(EntityManager entityManager, UserRepository repository) {
        super(entityManager, repository);
        this.entityManager = entityManager;
    }

    @Override
    public boolean validate(User user) {
        try {

            ValidatorFactory validatorFactory = Validation.byDefaultProvider()
                    .configure()
                    .messageInterpolator(new ParameterMessageInterpolator())
                    .buildValidatorFactory();

            Validator validator = validatorFactory.getValidator();

            Set<ConstraintViolation<User>> constraintViolationSet = validator.validate(user);
            if (!constraintViolationSet.isEmpty()) {
                for (ConstraintViolation<User> violation : constraintViolationSet) {
                    System.out.println(violation.getMessage());
                }
                validatorFactory.close();
                return false;
            } else {
                return true;
            }

        } catch (ValidationException e) {
            e.getStackTrace();
            return false;
        }
    }

    @Override
    public Optional<User> findUserByUsernameAndPassword(String username, String password) {
        return repository.findUserByUsernameAndPassword(username, password);
    }

    @Override
    public Optional<User> findUserByVoteId(long voteId) {
        return repository.findUserByVoteId(voteId);
    }
}
