package org.example.tictactoe.validations;

import org.example.tictactoe.model.Game;
import org.example.tictactoe.model.Player;

import java.util.List;

public class DimensionAndPlayerValidation {

    public static void validate(int dimension, List<Player> players) {
        if(players.size() <= 1 || dimension <= players.size()) {
            throw new RuntimeException("Players and Dimension is not valid.");
        }
    }

}
