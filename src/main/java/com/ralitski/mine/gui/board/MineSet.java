/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ralitski.mine.gui.board;

import com.ralitski.mine.util.Point;
import java.util.HashSet;
import java.util.Set;

/**
 *
 * @author STELLGAB000
 */
public class MineSet {
    
    private Set<Point> mines;
    
    public MineSet() {
        mines = new HashSet<>();
    }
    
    public Set<Point> getMines() {
        return mines;
    }
    
    public int size() {
        return mines.size();
    }
    
    public void addMine(int x, int y) {
        addMine(new Point(x, y));
    }
    
    public void addMine(Point p) {
        mines.add(p);
    }
    
    public boolean containsMine(int x, int y) {
        return containsMine(new Point(x, y));
    }
    
    public boolean containsMine(Point p) {
        return mines.contains(p);
    }
}
