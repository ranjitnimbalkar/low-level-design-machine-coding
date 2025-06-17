package org.example.tictactoe.model;

import java.util.Scanner;

public class HumanPlayer extends Player{

    public HumanPlayer(int playerId, String name, Symbol symbol) {
        super(playerId, name, symbol, PlayerType.HUMAN);
    }

    @Override
    public Move makeMove(Board board) {
        System.out.println("Please enter row : ");
        Scanner intScanner = new Scanner(System.in);
        int row = intScanner.nextInt();
        System.out.println("Please enter column : ");
        int column = intScanner.nextInt();
        Cell cell = new Cell(row, column);
        return new Move(cell, this);
    }
}
