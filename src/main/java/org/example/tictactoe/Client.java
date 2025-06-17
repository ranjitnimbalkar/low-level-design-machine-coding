package org.example.tictactoe;

import org.example.tictactoe.controllers.GameController;
import org.example.tictactoe.model.*;
import org.example.tictactoe.strategies.ColumnWinningStrategy;
import org.example.tictactoe.strategies.RowWinningStrategy;
import org.example.tictactoe.strategies.WinningStrategy;

import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class Client {

    public static void main(String[] args) {

        List<Player> players = new LinkedList<>();
        Player player1 = new HumanPlayer(1, "Ranjit", new Symbol('X'));
        Player player2 = new BotPlayer(2, "Ashvini", new Symbol('O'), BotDifficultyLevel.EASY);
        players.add(player1);
        players.add(player2);

        List<WinningStrategy> winningStrategies = new LinkedList<>();
        RowWinningStrategy rowWinningStrategy = new RowWinningStrategy();
        ColumnWinningStrategy columnWinningStrategy = new ColumnWinningStrategy();
        winningStrategies.add(rowWinningStrategy);
        winningStrategies.add(columnWinningStrategy);

        GameController gameController = new GameController();
        Game game = gameController.startGame(3, players, winningStrategies);

        while (gameController.checkGameState(game) == GameState.IN_PROGRESS) {
           gameController.makeMove(game);
           gameController.display(game);
           if(game.getMoves().getLast().getPlayer().getPlayerType().equals(PlayerType.HUMAN)) {
               System.out.println("Do you want to undo? Press 1 to undo & 2 to undo.");
               Scanner sc = new Scanner(System.in);
               int undo = sc.nextInt();

               if (undo == 2) {
                   gameController.undo(game);
               }
           }

        }

        if(gameController.checkGameState(game) == GameState.SUCCESS) {
            System.out.println("Player win : " + game.getWinner().getSymbol().getSymbol());
        } else if(gameController.checkGameState(game) == GameState.DRAW) {
            System.out.println("Game is draw!!");
        }

    }
}
