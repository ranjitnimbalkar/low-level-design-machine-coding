package org.example.tictactoe.strategies;

import org.example.tictactoe.model.Board;
import org.example.tictactoe.model.Move;
import org.example.tictactoe.model.Player;
import org.example.tictactoe.model.Symbol;

import java.util.HashMap;
import java.util.Map;

public class ColumnWinningStrategy implements WinningStrategy{

    Map<Integer, Map<Symbol, Integer>> counter = new HashMap<>();
    @Override
    public boolean checkWinner(Board board, Move move) {
        //update the value
         int column = move.getCell().getColumn();

         counter.putIfAbsent(column, new HashMap<>());
         Map<Symbol, Integer> columnCounts = counter.get(column);

         Symbol symbol = move.getPlayer().getSymbol();
         columnCounts.putIfAbsent(symbol, 0);
         columnCounts.put(symbol, columnCounts.get(symbol) + 1);

        //check the value
        return columnCounts.get(symbol) == board.getDimension();
    }
}
