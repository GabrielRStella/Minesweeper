/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ralitski.mine.render;

import com.ralitski.mine.window.GameCanvas;
import static com.ralitski.mine.window.GameCanvas.TILE_SIZE;
import java.awt.Color;
import java.awt.Graphics2D;


public class RendererColor implements Renderer {
    
    public static final RendererColor BLANK = new RendererColor(Color.GRAY);
    
    private final Color color;
    
    public RendererColor(Color color) {
        this.color = color;
    }

    @Override
    public void renderTile(GameCanvas c, Graphics2D g, int state) {
        g.setColor(color);
        g.fillRect(0, 0, TILE_SIZE, TILE_SIZE);
    }
    
}
