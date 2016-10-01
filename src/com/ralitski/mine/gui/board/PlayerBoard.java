/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ralitski.mine.gui.board;

/**
 *
 * @author stellgab000
 */
public class PlayerBoard {
    
    private static final int UNCOVERED = 0; //allows access to actual board data
    public static final int COVERED = -2; //a plain covered tile
    public static final int FLAGGED = -3; //a tile with a flag
    public static final int MARKED = -4; //a tile with a question mark
    public static final int REVEALED = -5; //a mine that has been revealed because the game was ended
    
    public static boolean isCovered(int i) {
        return (i == COVERED || i == FLAGGED || i == MARKED);
    }
    
    private final RectangularBoard board;
    
    private final int width;
    private final int height;
    private int[][] states; //false is covered (default)

    public PlayerBoard(int x, int y) {
        this(new RectangularBoard(x, y));
    }

    public PlayerBoard(RectangularBoard board) {
        this.board = board;
        this.width = board.getWidth();
        this.height = board.getHeight();
        this.states = new int[width][height];
    }

    public RectangularBoard getBoard() {
        return board;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }
    
    public void cover() {
        for(int x = 0; x < width; x++) {
            for(int y = 0; y < height; y++) {
                states[x][y] = COVERED;
            }
        }
    }
    
    public boolean isValid(int x, int y) {
        return (x >= 0 && x < width) && (y >= 0 && y < height);
    }
    
    public boolean uncovered(int x, int y) {
        return !covered(x, y);
    }
    
    public boolean covered(int x, int y) {
        return isValid(x, y) ? isCovered(states[x][y]) : true;
    }
    
    public int uncover(int x, int y) {
        if(!isValid(x, y)) return 0; //TODO: return covered?
        states[x][y] = UNCOVERED;
        return board.getState(x, y);
    }
    
    public int peak(int x, int y) {
        if(!isValid(x, y)) return COVERED;
        int state = states[x][y];
        return state == UNCOVERED ? board.getState(x, y) : state;
    }
    
    public void flag(int x, int y) {
        if(!isValid(x, y) || states[x][y] == UNCOVERED) return;
        states[x][y] = FLAGGED;
    }
    
    public void mark(int x, int y) {
        if(!isValid(x, y) || states[x][y] == UNCOVERED) return;
        states[x][y] = MARKED;
    }
    
    public void floodUncover(int x, int y) {
        if(board.getState(x, y) != 0) return;
        for(int i = x - 1; i <= x + 1; i++) {
            for(int j = y - 1; j <= y + 1; j++) {
                if(isValid(i, j) && isCovered(states[i][j])) {
                    int state = board.getState(i, j);
                    if(!RectangularBoard.isMine(state)) {
                        states[i][j] = UNCOVERED;
                        if(state == 0) floodUncover(i, j);
                    }
                }
            }
        }
    }
    
}
