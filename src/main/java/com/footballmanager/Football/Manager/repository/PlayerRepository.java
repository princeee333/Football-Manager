package com.footballmanager.Football.Manager.repository;

import com.footballmanager.Football.Manager.model.entity.Player;
import com.footballmanager.Football.Manager.model.entity.Position;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;


@Repository
public interface PlayerRepository extends JpaRepository<Player, Long> {

    List<Player> findAllByPosition(Position position);


}
