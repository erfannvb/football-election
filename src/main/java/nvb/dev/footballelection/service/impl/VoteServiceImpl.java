package nvb.dev.footballelection.service.impl;

import jakarta.persistence.EntityManager;
import jakarta.validation.*;
import nvb.dev.footballelection.base.service.impl.BaseServiceImpl;
import nvb.dev.footballelection.entity.Vote;
import nvb.dev.footballelection.repository.VoteRepository;
import nvb.dev.footballelection.service.VoteService;
import org.hibernate.validator.messageinterpolation.ParameterMessageInterpolator;

import java.util.Set;

public class VoteServiceImpl extends BaseServiceImpl<Long, Vote, VoteRepository> implements VoteService {

    protected final EntityManager entityManager;

    public VoteServiceImpl(EntityManager entityManager, VoteRepository repository) {
        super(entityManager, repository);
        this.entityManager = entityManager;
    }

    @Override
    public boolean validate(Vote vote) {
        try {

            ValidatorFactory validatorFactory = Validation.byDefaultProvider()
                    .configure()
                    .messageInterpolator(new ParameterMessageInterpolator())
                    .buildValidatorFactory();

            Validator validator = validatorFactory.getValidator();
            Set<ConstraintViolation<Vote>> constraintViolationSet = validator.validate(vote);
            if (!constraintViolationSet.isEmpty()) {
                for (ConstraintViolation<Vote> violation : constraintViolationSet) {
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
}
