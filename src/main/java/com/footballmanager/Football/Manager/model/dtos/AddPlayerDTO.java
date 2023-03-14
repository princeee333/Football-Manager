package com.footballmanager.Football.Manager.model.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


import javax.validation.constraints.*;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AddPlayerDTO {

    @Size(min = 3, max = 20)
    @NotBlank
    private String firstName;

    @Size(min = 3, max = 20)
    @NotBlank
    private String lastName;

    @Positive
    @NotNull
    @Min(14)
    @Max(38)
    private int age;

    @NotBlank
    private String position;


    @Positive
    @NotNull
    private double value;

}
