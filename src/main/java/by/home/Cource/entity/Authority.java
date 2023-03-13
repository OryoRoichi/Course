package by.home.Cource.entity;

import by.home.Cource.entity.enm.Role;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;

@Entity
@Data
@RequiredArgsConstructor
@Builder
@EqualsAndHashCode
public class Authority implements GrantedAuthority {
    private static final String SEQ_NAME = "authority_id_seq";

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = SEQ_NAME)
    @SequenceGenerator(name = SEQ_NAME, sequenceName = SEQ_NAME, allocationSize = 1)
    private Long id;

    @Enumerated(value = EnumType.STRING)
    private Role authority;
    @ManyToOne
    @JoinColumn(name = "org_user")
    private User orgUser;
    @Override
    public String getAuthority() {
        return authority.name();
    }
}
