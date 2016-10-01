package com.ralitski.mine.window;


import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author STELLGAB000
 */
public class WindowCloser implements WindowListener {
    
    private final Runnable stop;
    
    public WindowCloser(Runnable stop) {
        this.stop = stop;
    }

    @Override
    public void windowActivated(WindowEvent event) {
        // TODO window focused
    }

    @Override
    public void windowDeactivated(WindowEvent event) {
        // TODO window focus lost
    }

    @Override
    public void windowClosed(WindowEvent event) {
        // TODO window closed via call to dispose()
        stop.run();
    }

    @Override
    public void windowClosing(WindowEvent event) {
        //red X button clicked
        stop.run();
    }

    @Override
    public void windowDeiconified(WindowEvent event) {
        // TODO window selected from taskbar
    }

    @Override
    public void windowIconified(WindowEvent event) {
        // TODO window minimized to taskbar
    }

    @Override
    public void windowOpened(WindowEvent event) {
        // TODO window created
    }
    
}
