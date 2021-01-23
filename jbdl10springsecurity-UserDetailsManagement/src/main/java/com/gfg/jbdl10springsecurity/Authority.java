package com.gfg.jbdl10springsecurity;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;
import java.util.List;


@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Authority implements GrantedAuthority {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String authority;
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "authority_user",
            joinColumns = @JoinColumn(name ="authority"),
            inverseJoinColumns = @JoinColumn(name ="user"))
    private List<User> users;
    @Override
    public String getAuthority() {
        return this.authority;
    }
}
