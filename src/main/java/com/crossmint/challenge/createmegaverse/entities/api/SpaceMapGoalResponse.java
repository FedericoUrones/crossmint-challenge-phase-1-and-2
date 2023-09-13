package com.crossmint.challenge.createmegaverse.entities.api;

import lombok.Data;

import java.util.List;

@Data
public class SpaceMapGoalResponse {
    private List<List<String>> goal;
}
