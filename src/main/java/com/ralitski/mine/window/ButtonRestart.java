/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ralitski.mine.window;

import com.ralitski.mine.Game;
import java.awt.Button;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *
 * @author STELLGAB000
 */
public class ButtonRestart extends Button implements ActionListener {
    
    private Game game;
    
    public ButtonRestart(Game game, String label) {
        super(label);
        this.game = game;
        //bad form but whatever
        addActionListener(this);
    }
    
//    public void setup() {
//        addActionListener(this);
//    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        game.restart();
    }
    
}
