package com.footballmanager.Football.Manager.service;

import com.footballmanager.Football.Manager.model.entity.Player;
import com.footballmanager.Football.Manager.model.entity.User;
import com.footballmanager.Football.Manager.repository.PlayerRepository;
import com.footballmanager.Football.Manager.repository.UserRepository;
import org.springframework.stereotype.Service;
import java.util.Objects;

@Service
public class UserService {

    private final PlayerRepository playerRepository;
    private final UserRepository userRepository;

    public UserService(PlayerRepository playerRepository,
                       UserRepository userRepository) {
        this.playerRepository = playerRepository;
        this.userRepository = userRepository;
    }

    public void addPlayerToTeam(Long playerId, Long userId) {
        Player player = playerRepository.findById(playerId).orElse(null);
        User user = userRepository.findById(userId).orElse(null);

        Objects.requireNonNull(user).addPlayer(player);

        userRepository.save(user);
    }

    public void clearUserTeam(Long userId) {
        User user = userRepository.findById(userId).orElse(null);
        Objects.requireNonNull(user).clearTeam();
        userRepository.save(user);
    }

}
