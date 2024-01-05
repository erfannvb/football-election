package nvb.dev.footballelection.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import nvb.dev.footballelection.base.base.BaseEntity;

@Entity
@Table(name = "tbl_user")
@AllArgsConstructor
@NoArgsConstructor
@Data
@EqualsAndHashCode(callSuper = true)
public class User extends BaseEntity<Long> {

    @NotNull(message = "firstName cannot be null")
    @NotEmpty(message = "firstName cannot be empty")
    @Column(name = "first_name", nullable = false)
    private String firstName;

    @NotNull(message = "lastName cannot be null")
    @NotEmpty(message = "lastName cannot be empty")
    @Column(name = "last_name", nullable = false)
    private String lastName;

    @NotNull(message = "username cannot be null")
    @NotEmpty(message = "username cannot be empty")
    @Column(name = "username", nullable = false)
    private String username;

    @NotNull(message = "password cannot be null")
    @NotEmpty(message = "password cannot be empty")
    @Column(name = "password", nullable = false)
    private String password;

}
