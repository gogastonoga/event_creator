package com.capgemini.wolimierz.cost;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class DetailedCost {
    private UUID globalId;
    private String name;
    private double price;
}
