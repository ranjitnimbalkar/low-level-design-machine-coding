package org.example.tictactoe.factory;

import org.example.tictactoe.model.BotDifficultyLevel;
import org.example.tictactoe.strategies.EasyBotPlayingStrategy;

public class BotPlayingStrategyFactory {

    public static EasyBotPlayingStrategy getStrategy(BotDifficultyLevel difficultyLevel) {
        return new EasyBotPlayingStrategy();
    }

}
