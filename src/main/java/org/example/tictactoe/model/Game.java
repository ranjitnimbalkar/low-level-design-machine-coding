package org.example.tictactoe.model;

import org.example.tictactoe.strategies.WinningStrategy;
import org.example.tictactoe.validations.DimensionAndPlayerValidation;
import org.example.tictactoe.validations.SymbolValidation;

import java.util.LinkedList;
import java.util.List;

public class Game {

    private Board board;
    private List<Player> players;
    private GameState gameState;
    private List<WinningStrategy> winningStrategies;
    private int nextPlayerIndex;
    private Player winner;
    private List<Move> moves;

    public Game(Builder builder) {
        this.board = new Board(builder.dimension);
        this.players = builder.players;
        this.winningStrategies = builder.winningStrategies;
        this.gameState = GameState.IN_PROGRESS;
        this.winner = null;
        this.nextPlayerIndex = 0;
        this.moves = new LinkedList<>();
    }

    public Board getBoard() {
        return board;
    }

    public void setBoard(Board board) {
        this.board = board;
    }

    public List<Player> getPlayers() {
        return players;
    }

    public void setPlayers(List<Player> players) {
        this.players = players;
    }

    public GameState getGameState() {
        return gameState;
    }

    public void setGameState(GameState gameState) {
        this.gameState = gameState;
    }

    public List<WinningStrategy> getWinningStrategies() {
        return winningStrategies;
    }

    public void setWinningStrategies(List<WinningStrategy> winningStrategies) {
        this.winningStrategies = winningStrategies;
    }

    public int getNextPlayerIndex() {
        return nextPlayerIndex;
    }

    public void setNextPlayerIndex(int nextPlayerIndex) {
        this.nextPlayerIndex = nextPlayerIndex;
    }

    public Player getWinner() {
        return winner;
    }

    public void setWinner(Player winner) {
        this.winner = winner;
    }

    public List<Move> getMoves() {
        return moves;
    }

    public void setMoves(List<Move> moves) {
        this.moves = moves;
    }

    public static Builder getBuilder() {
        return new Builder();
    }

    public void displayBoard() {
        board.display();
    }

    public void makeMove() {
        Player currentPlayer = players.get(nextPlayerIndex);
        Move move;

        do {
            //make move for current player
            move = currentPlayer.makeMove(board);
        } while(!isValidMove(move));

        Cell cellToUpdate = board.getGrid()
                         .get(move.getCell().getRow())
                         .get(move.getCell().getColumn());
        cellToUpdate.setCellState(CellState.FILLED);
        cellToUpdate.setSymbol(currentPlayer.getSymbol());

        //add move to list
        moves.add(move);

        //check the winner
        if(checkWinner(move)) {
            setGameState(GameState.SUCCESS);
            setWinner(currentPlayer);
        } else if(moves.size() == board.getDimension() * board.getDimension()) {
            setGameState(GameState.DRAW);
        }
        //increment next player index
        nextPlayerIndex++;
        nextPlayerIndex = nextPlayerIndex % players.size();

    }

    private boolean checkWinner(Move move) {

        for(WinningStrategy strategy: winningStrategies) {
            if (strategy.checkWinner(board, move)) {
                return true;
            }
        }
        return false;
    }

    private void undoWinningStrategies(Move move) {

        for(WinningStrategy strategy: winningStrategies) {
           strategy.undo(move);
        }
    }

    public boolean isValidMove(Move move) {
        int r = move.getCell().getRow();
        int c = move.getCell().getColumn();

        if(r < 0 || r >= board.getDimension() || c < 0 || c >= board.getDimension()) {
            System.out.println("Invalid Move : Outside of board.");
            return false;
        }

        if(board.getGrid().get(r).get(c).getCellState().equals(CellState.FILLED)) {
            System.out.println("Invalid Move : Cell is filled.");
            return false;
        }

        return true;
    }

    public void undo() {
        if(moves.isEmpty()) {
            System.out.println("No moves to undo");
            return;
        }

        //remove last move from move history
        Move lastMove = moves.removeLast();
        //reset/update cell on the board
        Cell cellToUpdate = board.getGrid().get(lastMove.getCell().getRow()).get(lastMove.getCell().getColumn());
        cellToUpdate.setCellState(CellState.EMPTY);
        cellToUpdate.setSymbol(null);

        setGameState(GameState.IN_PROGRESS);
        setWinner(null);

        //Revert next player index
        nextPlayerIndex--;
        nextPlayerIndex = (nextPlayerIndex + players.size()) % players.size();

        undoWinningStrategies(lastMove);

    }

    //inner class
    public static class Builder {
        private int dimension;
        private List<Player> players;
        private List<WinningStrategy> winningStrategies;

        public Builder setDimension(int dimension) {
            this.dimension = dimension;
            return this;
        }

        public Builder setPlayers(List<Player> players) {
            this.players = players;
            return this;
        }

        public Builder setWinningStrategies(List<WinningStrategy> winningStrategies) {
            this.winningStrategies = winningStrategies;
            return this;
        }

        private void validate() {
            DimensionAndPlayerValidation.validate(this.dimension, this.players);
            SymbolValidation.validate();
        }

        public Game build() {
            validate();
            return new Game(this);
        }
    }

}
