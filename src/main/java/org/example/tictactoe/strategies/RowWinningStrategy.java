package org.example.tictactoe.strategies;

import org.example.tictactoe.model.Board;
import org.example.tictactoe.model.Move;
import org.example.tictactoe.model.Player;
import org.example.tictactoe.model.Symbol;

import java.util.HashMap;
import java.util.Map;

public class RowWinningStrategy implements WinningStrategy{

    Map<Integer, Map<Symbol, Integer>> counter = new HashMap<>();
    @Override
    public boolean checkWinner(Board board, Move move) {
        //update the value
        int row = move.getCell().getRow();

        counter.putIfAbsent(row,  new HashMap<>());
        Map<Symbol, Integer> rowCounts = counter.get(row);

        Symbol symbol = move.getPlayer().getSymbol();
        rowCounts.putIfAbsent(symbol, 0);
        rowCounts.put(symbol, rowCounts.get(symbol) + 1);

        //check the value
        return rowCounts.get(symbol) == board.getDimension();
    }

    @Override
    public void undo(Move move) {
        Map<Symbol, Integer> rowCounts = counter.get(move.getCell().getRow());
        Symbol symbol = move.getPlayer().getSymbol();
        rowCounts.put(symbol, rowCounts.get(symbol) - 1);
    }
}
