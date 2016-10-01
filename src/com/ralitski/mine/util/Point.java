/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ralitski.mine.util;

/**
 *
 * @author STELLGAB000
 */
public class Point implements Cloneable {
    
    private final int x;
    private final int y;
    
    public Point() {
        this(0, 0);
    }
    
    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
    
    public Point setX(int x) {
        return new Point(x, y);
    }
    
    public Point setY(int y) {
        return new Point(x, y);
    }
    
    public Point addX(int x) {
        return new Point(this.x + x, y);
    }
    
    public Point addY(int y) {
        return new Point(x, this.y + y);
    }
    
    public Point add(int x, int y) {
        return new Point(this.x + x, this.y + y);
    }
    
    @Override
    public Point clone() {
        try {
            return (Point)super.clone();
        } catch (CloneNotSupportedException ex) {
            return new Point(x, y);
        }
    }
    
    @Override
    public boolean equals(Object o) {
        return o instanceof Point ? (equalsPoint((Point)o)) : super.equals(o);
    }
    
    public boolean equalsPoint(Point p) {
        return p != null && x == p.x && y == p.y;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 29 * hash + this.x;
        hash = 29 * hash + this.y;
        return hash;
    }
}
