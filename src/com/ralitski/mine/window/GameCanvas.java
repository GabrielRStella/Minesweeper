/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ralitski.mine.window;

import com.ralitski.mine.Game;
import com.ralitski.mine.gui.board.RectangularBoard;
import com.ralitski.mine.gui.board.PlayerBoard;
import com.ralitski.mine.render.Renderer;
import com.ralitski.mine.render.RendererImage;
import com.ralitski.mine.render.RendererText;
import com.ralitski.mine.render.RendererCombined;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.AffineTransform;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author stellgab000
 */
public class GameCanvas extends Canvas implements MouseListener {
    
    public static final int TILE_SIZE = 20;
    
    private final Game game;
    private final PlayerBoard board;
    private final Map<Integer, Renderer> renderers;
    private Renderer def;
    
    public GameCanvas(Game game, PlayerBoard board) {
        this.game = game;
        this.board = board;
        renderers = new HashMap<>();
    }
    
    public void setup() {
        //huehuehue
        addMouseListener(this);
        
        int w = board.getWidth() * TILE_SIZE;
        int h = board.getHeight() * TILE_SIZE;
        Dimension d = new Dimension(w, h);
        
        setMinimumSize(d);
        setSize(d);
        setPreferredSize(d);
        setMaximumSize(d);
        
        setBackground(Color.white);
        
        //add tile renderers
        def = new RendererCombined(new RendererImage("tile"), RendererText.NULL);
        RendererImage covered = new RendererImage("covered");
        setRenderer(new RendererImage("mine"), RectangularBoard.MINE);
        setRenderer(covered, PlayerBoard.COVERED);
        setRenderer(new RendererCombined(covered, new RendererImage("flagged")), PlayerBoard.FLAGGED);
        setRenderer(new RendererCombined(covered, new RendererImage("marked")), PlayerBoard.MARKED);
//        def = new RendererCombined(new RendererImage(Images.TILE), RendererText.NULL);
//        setRenderer(new RendererImage(Images.MINE), RectangularBoard.MINE);
//        setRenderer(new RendererImage(Images.COVERED), PlayerBoard.COVERED);
    }
    
    @Override
    public void paint(Graphics g) {
        doPaint((Graphics2D)g);
    }
    
    public void doPaint(Graphics2D g) {
        drawTiles(g, 0, 0, board.getWidth() - 1, board.getHeight() - 1);
    }
    
    public void drawTiles(int minx, int miny, int maxx, int maxy) {
        drawTiles((Graphics2D)getGraphics(), minx, miny, maxx, maxy);
    }
    
    public void drawTiles(Graphics2D g, int minx, int miny, int maxx, int maxy) {
        for(int x = minx; x <= maxx; x++) {
            for(int y = miny; y <= maxy; y++) {
                renderTile(g, x, y);
            }
        }
    }
    
    public void renderTile(Graphics2D g, int x, int y) {
        AffineTransform t = g.getTransform();
        g.translate(x * TILE_SIZE, y * TILE_SIZE);
        
        int state = board.peak(x, y);
        
        g.setColor(Color.WHITE);
        renderTile(g, state);
        g.setColor(Color.WHITE);
        
        g.setTransform(t);
    }
    
    public void renderTile(Graphics2D g, int state) {
        //temp system - TODO images
        getRenderer(state).renderTile(this, g, state);
    }
    
    public void setRenderer(Renderer r, int... states) {
        for(int state : states) renderers.put(state, r);
    }
    
    public Renderer getRenderer(int state) {
        Renderer r = getRendererRaw(state);
        return r != null ? r : def;
    }
    
    public Renderer getRendererRaw(int state) {
        return renderers.get(state);
    }

    @Override
    public void mouseClicked(MouseEvent me) {
        //pressed and released
        int x = me.getX();
        int y = me.getY();
        int xp = x % TILE_SIZE;
        int yp = y % TILE_SIZE;
        int xt = (x - xp) / TILE_SIZE;
        int yt = (y - yp) / TILE_SIZE;
        game.onTileClick(me.getButton(), xt, yt);
    }

    @Override
    public void mousePressed(MouseEvent me) {
    }

    @Override
    public void mouseReleased(MouseEvent me) {
    }

    @Override
    public void mouseEntered(MouseEvent me) {
    }

    @Override
    public void mouseExited(MouseEvent me) {
    }
    
}
