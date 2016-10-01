package com.ralitski.mine.window;


import java.awt.Canvas;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.Graphics2D;
import java.awt.Panel;

/**
 *
 * @author STELLGAB000
 */
public class Window {
    
    private final String title;
    private final int width;
    private final int height;
    
    private boolean setup;
    private volatile boolean running;
    private Frame frame;
    private Panel panel;
    
    public Window() {
        this(400);
    }
    
    public Window(int size) {
        this(size, size);
    }
    
    public Window(int width, int height) {
        this("Window", width, height);
    }
    
    public Window(String title) {
        this(400);
    }
    
    public Window(String title, int size) {
        this(size, size);
    }
    
    public Window(String title, int width, int height) {
        this.title = title;
        this.width = width;
        this.height = height;
    }
    
    public boolean setup() {
        if(setup) return false;
        
        this.frame = new Frame(title);
        
        Dimension d = new Dimension(width, height);

        panel = new Panel();
        panel.setBackground(Color.BLACK);
        
//        panel.setMinimumSize(d);
        panel.setSize(d);
//        panel.setPreferredSize(d);
//        panel.setMaximumSize(d);
        
        frame.add(panel);
        frame.pack();
        frame.setVisible(true);
        frame.setResizable(false);
        
        setup = true;
        
        return true;
    }
    
    public void start() {
        running = true;
    }
    
    public void stop() {
        if(frame != null)
            frame.dispose();
        frame = null;
        panel = null;
        setup = false;
        running = false;
    }
    
    public boolean running() {
        return running;
    }
    
    public Runnable getStopper() {
        return new Stopper();
    }
    
    public void addCloser() {
        if(frame != null) {
            frame.addWindowListener(new WindowCloser(getStopper()));
        }
    }

    public String getTitle() {
        return title;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public Frame getFrame() {
        return frame;
    }

    public Panel getPanel() {
        return panel;
    }
    
    //
    
    public void pack() {
        frame.pack();
    }
    
    public void add(Component c) {
        panel.add(c);
    }
    
    //
    
    private class Stopper implements Runnable {

        @Override
        public void run() {
            stop();
        }
    }
}
