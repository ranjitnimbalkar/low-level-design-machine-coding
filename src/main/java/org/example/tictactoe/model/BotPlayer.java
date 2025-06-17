package org.example.tictactoe.model;

import org.example.tictactoe.factory.BotPlayingStrategyFactory;
import org.example.tictactoe.strategies.BotPlayerStrategy;

public class BotPlayer extends Player {
    BotDifficultyLevel botDifficultyLevel;
    BotPlayerStrategy botPlayerStrategy;

    public BotPlayer(int playerId, String name, Symbol symbol, BotDifficultyLevel botDifficultyLevel) {
        super(playerId, name, symbol, PlayerType.BOT);
        this.botDifficultyLevel = botDifficultyLevel;
        botPlayerStrategy = BotPlayingStrategyFactory.getStrategy(botDifficultyLevel);
    }

    @Override
    public Move makeMove(Board board) {
        System.out.println("It's bot turn now : "+ this.getName());
        Cell cell = botPlayerStrategy.makeMove(board);
        if(cell != null) {
            return new Move(cell, this);
        }
        return null;
    }
}
