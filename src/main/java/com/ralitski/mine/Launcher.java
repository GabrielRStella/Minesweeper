package com.ralitski.mine;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author STELLGAB000
 */
public class Launcher {
    
    /**
     * TODO:
     * -allow movement of the game board (and have a maximum screen size)
     * -better-looking ui
     * -position gui at top-left corner of screen
     * -add win condition
     * -keep track of flags and mines
     * 
     * @param args 
     */
    
    public static void main(String[] args) {
        new Controller().start(new Game());
    }
}
