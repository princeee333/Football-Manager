package com.footballmanager.Football.Manager.model.entity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false, unique = true)
    private String username;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false, unique = true)
    private String email;

    @ManyToMany(fetch = FetchType.EAGER)
    private Set<Player> team;

    public User() {
        this.team = new HashSet<>();
    }

    public void addPlayer(Player player) {
        this.team.add(player);
    }

    public void clearTeam() {
        this.team = new HashSet<>();
    }
}