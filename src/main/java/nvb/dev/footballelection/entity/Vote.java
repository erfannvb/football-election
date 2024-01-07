package nvb.dev.footballelection.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import nvb.dev.footballelection.base.base.BaseEntity;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "tbl_vote")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Vote extends BaseEntity<Long> {

    @NotNull(message = "vote cannot be null")
    @NotEmpty(message = "vote cannot be empty")
    @Column(name = "team", nullable = false)
    private String team;

    @OneToMany(mappedBy = "vote", cascade = CascadeType.ALL)
    private Set<User> userSet = new HashSet<>();

    public Vote(String team) {
        this.team = team;
    }
}
