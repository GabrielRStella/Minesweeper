/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ralitski.mine;

import com.ralitski.mine.window.GameCanvas;
import com.ralitski.mine.gui.board.RectangularBoard;
import com.ralitski.mine.gui.board.PlayerBoard;
import com.ralitski.mine.util.ComponentPath;
import com.ralitski.mine.window.ButtonRestart;
import com.ralitski.mine.window.TextNum;
import java.awt.Color;
import java.awt.Label;
import java.awt.Panel;
import java.awt.event.MouseEvent;
import javax.swing.BoxLayout;

/**
 *
 * @author stellgab000
 */
public class Game {
    
    public static final String DEFAULT_NAME = "Minesweeper";
    public static final int DEFAULT_TILES = 10;
    public static final int DEFAULT_MINES = 10;
    
    private int tilesX;
    private int tilesY;
    private int maxMines;
    private int minesAdded;
    private PlayerBoard board;
    
    private ComponentPath components;
    private GameCanvas canvas;
    private Controller controller;
    
    private boolean gaming = false; //if the game is active (not won or lost)
    
    public Game() {
        this(DEFAULT_TILES);
    }
    
    public Game(int tiles) {
        this(tiles, tiles);
    }
    
    public Game(int x, int y) {
        this(x, y, DEFAULT_MINES);
    }
    
    public Game(int x, int y, int mines) {
        this.tilesX = x;
        this.tilesY = y;
        this.maxMines = mines;
    }
    
    public void setup(Controller controller, ComponentPath components) {
        this.controller = controller;
        this.components = components;
        
        this.board = new PlayerBoard(tilesX, tilesY);
        board.cover();
        //add mines
        minesAdded = 0;
        int timesSkipped = 0;
        RectangularBoard rb = board.getBoard();
        while(minesAdded < maxMines) {
            if(rb.addMine()) minesAdded++;
            else if(timesSkipped++ > maxMines) break;
        }
        
        Panel panel = new Panel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBackground(Color.WHITE);
        components.addComponent("ui", panel);
        
        //add buttons and fields
        
        Panel widthPanel = new Panel();
        widthPanel.setLayout(new BoxLayout(widthPanel, BoxLayout.X_AXIS));
        widthPanel.add(new Label("Width"));
        widthPanel.add(new TextNum(this, tilesX) {

            @Override
            protected void set(Game game, int i) {
                tilesX = i;
            }
        });
        components.addComponent("ui/width", widthPanel);
        
        Panel heightPanel = new Panel();
        heightPanel.setLayout(new BoxLayout(heightPanel, BoxLayout.X_AXIS));
        heightPanel.add(new Label("Height"));
        heightPanel.add(new TextNum(this, tilesY) {

            @Override
            protected void set(Game game, int i) {
                tilesY = i;
            }
        });
        components.addComponent("ui/height", heightPanel);
        
        Panel minesPanel = new Panel();
        minesPanel.setLayout(new BoxLayout(minesPanel, BoxLayout.X_AXIS));
        minesPanel.add(new Label("Mines"));
        minesPanel.add(new TextNum(this, maxMines) {

            @Override
            protected void set(Game game, int i) {
                maxMines = i;
            }
        });
        components.addComponent("ui/mines", minesPanel);
        
        components.addComponent("ui/minesAdded", new Label("Mines: " + minesAdded));
        components.addComponent("ui/restart", new ButtonRestart(this, "Restart"));
        
        //add the game canvas
        Panel gamePanel = new Panel();
        gamePanel.setLayout(new BoxLayout(gamePanel, BoxLayout.Y_AXIS));
        components.addComponent("game", gamePanel);
        
        canvas = new GameCanvas(this, board);
        canvas.setup();
        components.addComponent("game/board", canvas);
        
        Panel infoPanel = new Panel();
        infoPanel.setLayout(new BoxLayout(infoPanel, BoxLayout.X_AXIS));
        components.addComponent("game/info", infoPanel);
        
        gaming = true;
    }
    
    public void update() {
        canvas.repaint();
    }
    
    //etc

    public int getTilesX() {
        return tilesX;
    }

    public void setTilesX(int tilesX) {
        this.tilesX = tilesX;
    }

    public int getTilesY() {
        return tilesY;
    }

    public void setTilesY(int tilesY) {
        this.tilesY = tilesY;
    }

    public int getMaxMines() {
        return maxMines;
    }

    public void setMaxMines(int mines) {
        this.maxMines = mines;
    }

    public int getMinesAdded() {
        return minesAdded;
    }

    public ComponentPath getComponents() {
        return components;
    }
    
    //logic
    
    public void onTileClick(int button, int x, int y) {
        if(gaming) {
            if(button == MouseEvent.BUTTON1) {
                //left click
                int prevstate = board.peak(x, y);
                int state = board.uncover(x, y);
                if(RectangularBoard.isMine(state)) {
                    end(); // :(
                } else if(PlayerBoard.isCovered(prevstate)) {
                    board.floodUncover(x, y);
                    update();
                }
            } else if(button == MouseEvent.BUTTON3) {
                //right click
                int state = board.peak(x, y);
                if(state == PlayerBoard.FLAGGED) {
                     board.mark(x, y);
                } else if(PlayerBoard.isCovered(state)) {
                    board.flag(x, y);
                }
            } else {
                //temp - enable or disable mines
                RectangularBoard b = board.getBoard();
                b.setState(x, y, !b.isMine(x, y));
            }
            //redraw only the affected tiles
            canvas.drawTiles(x - 1, y - 1, x + 1, y + 1);
        } else {
            //frozen; do nothing
        }
    }
    
    public void end() {
        gaming = false;
    }
    
    public void restart() {
        controller.setGame(new Game(tilesX, tilesY, maxMines));
    }
    
}
