package org.example.tictactoe.strategies;

import org.example.tictactoe.model.Board;
import org.example.tictactoe.model.Move;
import org.example.tictactoe.model.Player;

public class DiagonalWinningStrategy implements WinningStrategy{

    @Override
    public boolean checkWinner(Board board, Move move) {
        return false;
    }

    @Override
    public void undo(Move move) {

    }
}
