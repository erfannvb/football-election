package nvb.dev.footballelection.repository;

import nvb.dev.footballelection.base.repository.BaseRepository;
import nvb.dev.footballelection.entity.Vote;

import java.util.Optional;

public interface VoteRepository extends BaseRepository<Long, Vote> {

    Optional<Vote> findVoteByTeam(String team);

}
