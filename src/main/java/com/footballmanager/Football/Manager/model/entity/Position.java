package com.footballmanager.Football.Manager.model.entity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.persistence.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "positions")
public class Position {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Enumerated(value = EnumType.STRING)
    @Column(nullable = false, unique = true)
    private PositionType positionType;


    @Column(columnDefinition = "text")
    private String additionalInformationAboutPlayer;

    public Position(PositionType positionType) {
        this.positionType = positionType;
        this.additionalInformationAboutPlayer = positionType.toString();
    }
}
