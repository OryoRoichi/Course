package by.home.Course.entity;


import by.home.Course.entity.enums.Role;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;

import static by.home.Course.entity.Authority.SEQ_NAME;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Authority implements GrantedAuthority {
    static final String SEQ_NAME = "authority_id_seq";


    @Id
    @SequenceGenerator(
            name = SEQ_NAME,
            sequenceName = SEQ_NAME,
            allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(value = EnumType.STRING)
    private Role authority;
    @ManyToOne
    @JoinColumn(name = "org_user")
    private User orgUser;

    @Override
    @Transient
    public String getAuthority() {
        return authority.name();
    }
}
