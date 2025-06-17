package org.example.tictactoe.strategies;

import org.example.tictactoe.model.Board;
import org.example.tictactoe.model.Cell;
import org.example.tictactoe.model.Move;

public interface BotPlayerStrategy {
    public Cell makeMove(Board board);
}
