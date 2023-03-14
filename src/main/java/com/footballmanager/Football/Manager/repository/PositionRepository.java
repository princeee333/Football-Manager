package com.footballmanager.Football.Manager.repository;
import com.footballmanager.Football.Manager.model.entity.Position;
import com.footballmanager.Football.Manager.model.entity.PositionType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface PositionRepository extends JpaRepository<Position, Long> {

    Position findByPositionType(PositionType positionType);
}
