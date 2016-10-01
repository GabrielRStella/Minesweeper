/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ralitski.mine.gui.board;

import java.util.Random;

/**
 *
 * @author STELLGAB000
 */
public class RectangularBoard {
    
    public static boolean isMine(int state) {
        return state == MINE;
    }
    
    public static int getValue(boolean mine) {
        return mine ? MINE : 0;
    }
    
    public static final int MINE = -1;
    
    private final int width;
    private final int height;
    private final int[][] board;
    private final MineSet mines;
    
    public RectangularBoard(int width, int height) {
        this.width = width;
        this.height = height;
        this.board = new int[width][height];
        this.mines = new MineSet();
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public MineSet getMines() {
        return mines;
    }
    
    public boolean isValid(int x, int y) {
        return (x >= 0 && x < width) && (y >= 0 && y < height);
    }
    
    public int getState(int x, int y) {
        if(!isValid(x, y)) return 0; //eh
        return board[x][y];
    }
    
    public boolean addMine() {
        Random random = new Random();
        int x = random.nextInt(width);
        int y = random.nextInt(height);
        if(isMine(x, y)) return false;
        setState(x, y, true);
        return true;
    }
    
    public void setState(int x, int y, boolean mine) {
        doSetState(x, y, getValue(mine));
        updateWithSurrounding(x, y);
    }
    
    private void doSetState(int x, int y, int value) {
        if(!isValid(x, y)) return;
        board[x][y] = value;
        if(isMine(value)) mines.addMine(x, y);
    }
    
    public boolean isMine(int x, int y) {
        return isMine(getState(x, y));
    }
    
    public void update(int x, int y) {
        if(isMine(x, y)) return;
        int value = 0;
        for(int i = x - 1; i <= x + 1; i++) {
            for(int j = y - 1; j <= y + 1; j++) {
                //avoid the center for counting
                if(i != x || j != y) {
                    if(isMine(i, j)) value++;
                }
            }
        }
        doSetState(x, y, value);
    }
    
    public void updateWithSurrounding(int x, int y) {
        for(int i = x - 1; i <= x + 1; i++) {
            for(int j = y - 1; j <= y + 1; j++) {
                update(i, j);
            }
        }
    }
}
