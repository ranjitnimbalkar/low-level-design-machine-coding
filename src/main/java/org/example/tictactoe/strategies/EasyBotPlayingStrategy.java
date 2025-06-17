package org.example.tictactoe.strategies;

import org.example.tictactoe.model.Board;
import org.example.tictactoe.model.Cell;
import org.example.tictactoe.model.CellState;
import org.example.tictactoe.model.Move;

import java.util.List;

public class EasyBotPlayingStrategy implements BotPlayerStrategy {
    @Override
    public Cell makeMove(Board board) {

        for(List<Cell> row : board.getGrid()) {
            for(Cell cell : row) {
                if(cell.getCellState().equals(CellState.EMPTY)) {
                    return new Cell(cell.getRow(), cell.getColumn());
                }
            }
        }

        return null;
    }
}
