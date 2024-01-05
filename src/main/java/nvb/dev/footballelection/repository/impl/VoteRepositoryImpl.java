package nvb.dev.footballelection.repository.impl;

import jakarta.persistence.EntityManager;
import nvb.dev.footballelection.base.repository.impl.BaseRepositoryImpl;
import nvb.dev.footballelection.entity.Vote;
import nvb.dev.footballelection.repository.VoteRepository;

public class VoteRepositoryImpl extends BaseRepositoryImpl<Long, Vote> implements VoteRepository {

    protected final EntityManager entityManager;

    public VoteRepositoryImpl(EntityManager entityManager) {
        super(entityManager);
        this.entityManager = entityManager;
    }

    @Override
    public Class<Vote> getEntityClass() {
        return Vote.class;
    }
}
