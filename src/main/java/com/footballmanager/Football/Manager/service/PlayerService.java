package com.footballmanager.Football.Manager.service;

import com.footballmanager.Football.Manager.model.dtos.AddPlayerDTO;
import com.footballmanager.Football.Manager.model.dtos.PlayerDTO;
import com.footballmanager.Football.Manager.model.entity.Player;
import com.footballmanager.Football.Manager.model.entity.Position;
import com.footballmanager.Football.Manager.model.entity.PositionType;
import com.footballmanager.Football.Manager.repository.PlayerRepository;
import com.footballmanager.Football.Manager.repository.PositionRepository;
import com.footballmanager.Football.Manager.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PlayerService {

    private final UserRepository userRepository;

    private final PositionRepository positionRepository;

    private final PlayerRepository playerRepository;

    @Autowired
    public PlayerService(UserRepository userRepository,
                         PositionRepository positionRepository,
                         PlayerRepository playerRepository) {
        this.userRepository = userRepository;
        this.positionRepository = positionRepository;
        this.playerRepository = playerRepository;
    }

    public void created(AddPlayerDTO addPlayerDTO) {
        PositionType positionType = PositionType.valueOf(addPlayerDTO.getPosition());
        Position position = this.positionRepository.findByPositionType(positionType);

        Player player = new Player();
        player.setFirstName(addPlayerDTO.getFirstName());
        player.setLastName(addPlayerDTO.getLastName());
        player.setAge(addPlayerDTO.getAge());
        player.setValue(addPlayerDTO.getValue());
        player.setPosition(position);


        this.playerRepository.save(player);
    }

    public List<PlayerDTO> getPositionPlayer(PositionType positionType) {
        Position position = positionRepository.findByPositionType(positionType);

        return this.playerRepository.findAllByPosition(position)
                .stream()
                .map(PlayerDTO::new)
                .collect(Collectors.toList());
    }

    public List<PlayerDTO> getUserLoggedTeam(long loggedUserId) {
        return this.userRepository.findById(loggedUserId)
                .orElseThrow()
                .getTeam()
                .stream()
                .map(PlayerDTO::new)
                .collect(Collectors.toList());
    }

}
