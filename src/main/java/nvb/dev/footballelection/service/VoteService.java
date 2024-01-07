package nvb.dev.footballelection.service;

import nvb.dev.footballelection.base.service.BaseService;
import nvb.dev.footballelection.entity.Vote;

import java.util.Optional;

public interface VoteService extends BaseService<Long, Vote> {

    boolean validate(Vote vote);

    Optional<Vote> findVoteByTeam(String team);

}
