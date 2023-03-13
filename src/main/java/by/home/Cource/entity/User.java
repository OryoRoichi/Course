package by.home.Cource.entity;

import by.home.Cource.entity.enm.Role;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Entity
@Data
@Builder
@NoArgsConstructor
@Table(name = "org_user")
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class User implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(unique = true)
    String login;

    String password;

    String name;
    @OneToMany
    List<Cource> cource;
    List<Role> role;
    @OneToMany(mappedBy = "orgUser", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    List<Authority> authorities;

    public Collection<? extends GrantedAuthority> getAuthorities(){
        return this.authorities;
    }
    @Override
    public String getUsername() {
        return null;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
