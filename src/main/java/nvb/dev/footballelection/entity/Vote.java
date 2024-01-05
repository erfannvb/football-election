package nvb.dev.footballelection.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import nvb.dev.footballelection.base.base.BaseEntity;

@Entity
@Table(name = "tbl_vote")
@AllArgsConstructor
@NoArgsConstructor
@Data
@EqualsAndHashCode(callSuper = true)
public class Vote extends BaseEntity<Long> {

    @NotNull(message = "vote cannot be null")
    @NotEmpty(message = "vote cannot be empty")
    @Column(name = "vote", nullable = false)
    private String vote;

}
