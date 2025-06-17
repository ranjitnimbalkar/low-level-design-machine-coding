package org.example.tictactoe.strategies;

import org.example.tictactoe.model.Board;
import org.example.tictactoe.model.Move;
import org.example.tictactoe.model.Player;

public interface WinningStrategy {

    boolean checkWinner(Board board, Move move);

    void undo(Move move);
}
