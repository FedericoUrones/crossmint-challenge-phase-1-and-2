package com.crossmint.challenge.createmegaverse.infrastructure.entities;

import lombok.Data;

import java.util.List;

@Data
public class SpaceMapGoalResponse {
    private List<List<String>> goal;
}
