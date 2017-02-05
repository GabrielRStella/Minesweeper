/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ralitski.mine.window;

import com.ralitski.mine.Game;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

/**
 *
 * @author stellgab000
 */
public abstract class TextNum extends TextField implements ActionListener, FocusListener {
    
    private Game game;
    
    public TextNum(Game game, int num) {
        this(game, "" + num);
    }
    
    public TextNum(Game game, String string) {
        super(string);
        this.game = game;
        //bad form but whatever
        addActionListener(this);
        addFocusListener(this);
    }
    
//    public void setup() {
//        addActionListener(this);
//    }
    
    public int getNum() {
        return getNum(getText());
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        action(ae.getActionCommand());
    }

    @Override
    public void focusGained(FocusEvent fe) {
    }

    @Override
    public void focusLost(FocusEvent fe) {
        action(getText());
    }
    
    private void action(String s) {
        try {
            int i = Integer.parseInt(s);
            set(game, i);
        } catch(NumberFormatException ex) {
            //strip non-digits and set value
            s = s.replaceAll("\\D", "");
            this.setText(s);
        }
    }
    
    protected abstract void set(Game game, int i);
    
    public static int getNum(String s) {
        return Integer.parseInt(s.replaceAll("\\D", ""));
    }
    
}
