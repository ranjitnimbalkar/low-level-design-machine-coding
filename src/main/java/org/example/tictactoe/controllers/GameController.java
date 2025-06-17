package org.example.tictactoe.controllers;

import org.example.tictactoe.model.Game;
import org.example.tictactoe.model.GameState;
import org.example.tictactoe.model.Move;
import org.example.tictactoe.model.Player;
import org.example.tictactoe.strategies.WinningStrategy;

import java.util.List;

public class GameController {

    Game game;

    public Game startGame(int dimension, List<Player> players, List<WinningStrategy> winningStrategies) {
       return Game
               .getBuilder()
               .setDimension(dimension)
               .setPlayers(players)
               .setWinningStrategies(winningStrategies)
               .build();
    }

    public GameState checkGameState(Game game) {
        return game.getGameState();
    }

    public void makeMove(Game game) {
        game.makeMove();
    }

    private int nextPlayerIndex(int currentPlayerIndex, int size) {
        int nextPlayerIndex = currentPlayerIndex + 1;
        return nextPlayerIndex < size ? nextPlayerIndex : nextPlayerIndex % size;
    }

    public void undo(Game game) {

    }

    public void display(Game game) {
        game.displayBoard();
    }
}
