package com.ralitski.mine;

import com.ralitski.mine.window.Window;
import com.ralitski.mine.util.ComponentPath;
import java.awt.Color;
import java.awt.Panel;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author STELLGAB000
 */
public class Controller {
    
    private Window window;
    
    public Controller() {
        
    }

    public void start(Game game) {
        window = new Window();
        window.setup();
        window.addCloser();
        Panel panel = window.getPanel();
        
        startGame(panel, game);
        
        window.start();
        
        while(window.running()) {
            //
        }
        window.stop(); //just in case
        window = null;
    }
    
    public void setGame(Game game) {
        if(window != null) {
            startGame(window.getPanel(), game);
        }
    }
    
    private void startGame(Panel panel, Game game) {
        panel.removeAll();
        ComponentPath path = new ComponentPath(panel);
        game.setup(this, path);
        window.getFrame().pack();
    }
    
}
