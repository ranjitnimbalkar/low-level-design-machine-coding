package org.example.tictactoe.model;

import java.util.LinkedList;
import java.util.List;

public class Board {

    private int dimension;
    private List<List<Cell>> grid;

    public Board(int dimension) {
        this.dimension = dimension;
        //initialize grid
        grid = new LinkedList<>();
        for(int i = 0; i < dimension; i++) {
            grid.add(new LinkedList<>());
            for(int j = 0; j < dimension; j++) {
                grid.get(i).add(new Cell(i,j));
            }
        }
    }

    public int getDimension() {
        return dimension;
    }

    public void setDimension(int dimension) {
        this.dimension = dimension;
    }

    public List<List<Cell>> getGrid() {
        return grid;
    }

    public void setGrid(List<List<Cell>> grid) {
        this.grid = grid;
    }

    public void display() {
        for(List<Cell> row : grid) {
            for(Cell cell : row) {
                cell.display();
            }
            System.out.println();
        }
    }
}
