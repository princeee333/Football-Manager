package com.footballmanager.Football.Manager.init;

import com.footballmanager.Football.Manager.model.entity.Position;
import com.footballmanager.Football.Manager.model.entity.PositionType;
import com.footballmanager.Football.Manager.repository.PositionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class PositionSeeder implements CommandLineRunner {

    private final PositionRepository positionRepository;

    @Autowired
    public PositionSeeder(PositionRepository positionRepository) {
        this.positionRepository = positionRepository;
    }


    @Override
    public void run(String... args) throws Exception {

        if(this.positionRepository.count() == 0) {

            List<Position> moods = Arrays.stream(PositionType.values())
                    .map(Position::new)
                    .collect(Collectors.toList());

            this.positionRepository.saveAll(moods);
        }
    }
}
