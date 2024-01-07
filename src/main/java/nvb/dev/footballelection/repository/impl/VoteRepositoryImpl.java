package nvb.dev.footballelection.repository.impl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import nvb.dev.footballelection.base.repository.impl.BaseRepositoryImpl;
import nvb.dev.footballelection.entity.Vote;
import nvb.dev.footballelection.repository.VoteRepository;

import java.util.Optional;

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

    @Override
    public Optional<Vote> findVoteByTeam(String team) {
        String hql = "from Vote where team =: team";
        TypedQuery<Vote> voteTypedQuery = entityManager.createQuery(hql, Vote.class);
        voteTypedQuery.setParameter("team", team);
        return Optional.ofNullable(voteTypedQuery.getSingleResult());
    }
}
