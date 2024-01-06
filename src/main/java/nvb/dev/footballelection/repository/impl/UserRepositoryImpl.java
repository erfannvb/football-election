package nvb.dev.footballelection.repository.impl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import nvb.dev.footballelection.base.repository.impl.BaseRepositoryImpl;
import nvb.dev.footballelection.entity.User;
import nvb.dev.footballelection.repository.UserRepository;

import java.util.Optional;

public class UserRepositoryImpl extends BaseRepositoryImpl<Long, User> implements UserRepository {

    protected final EntityManager entityManager;

    public UserRepositoryImpl(EntityManager entityManager) {
        super(entityManager);
        this.entityManager = entityManager;
    }

    @Override
    public Class<User> getEntityClass() {
        return User.class;
    }

    @Override
    public Optional<User> findUserByUsernameAndPassword(String username, String password) {
        String hql = "from User where username =: username and password =: password";
        TypedQuery<User> userTypedQuery = entityManager.createQuery(hql, User.class);
        userTypedQuery.setParameter("username", username);
        userTypedQuery.setParameter("password", password);
        return Optional.ofNullable(userTypedQuery.getSingleResult());
    }

    @Override
    public Optional<User> findUserByVoteId(long voteId) {
        String hql = "from User u where u.vote.id =: voteId";
        TypedQuery<User> userTypedQuery = entityManager.createQuery(hql, User.class);
        userTypedQuery.setParameter("voteId", voteId);
        return Optional.ofNullable(userTypedQuery.getSingleResult());
    }
}
