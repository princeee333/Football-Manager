package com.footballmanager.Football.Manager.model.dtos;
import com.footballmanager.Football.Manager.model.entity.Player;
import com.footballmanager.Football.Manager.model.entity.Position;
import lombok.Getter;

@Getter
public class PlayerDTO {

    private final long id;
    private final String firstName;
    private final String lastName;
    private final int age;
    private final double value;
    private final Position position;


    public PlayerDTO(Player player) {
        this.id = player.getId();
        this.firstName = player.getFirstName();
        this.lastName = player.getLastName();
        this.age = player.getAge();
        this.value = player.getValue();
        this.position = player.getPosition();

    }


    @Override
    public String toString() {
        return String.format("Full name: %s %s; Age: %d; Value: %.2f $", firstName, lastName, age, value);
    }
}