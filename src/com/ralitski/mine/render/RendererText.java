/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ralitski.mine.render;

import com.ralitski.mine.window.GameCanvas;
import static com.ralitski.mine.window.GameCanvas.TILE_SIZE;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;


public class RendererText implements Renderer {
    
    public static final RendererText NULL = new RendererText(null);
    
    private final Font font;
    
    public RendererText(Font font) {
        this.font = font;
    }

    @Override
    public void renderTile(GameCanvas c, Graphics2D g, int state) {
        if(doDraw(state)) {
            Font f = g.getFont();
            if(font != null) g.setFont(font);
            //draw centered string
            String s = "" + state;
            Font f2 = font != null ? font : f;
            Rectangle2D rect = f2.getStringBounds(s, g.getFontRenderContext());
            float size = TILE_SIZE;
            float stringX = (size - (float) rect.getWidth()) / 2.0F - (float) rect.getX();
            float stringY = (size - (float) rect.getHeight()) / 2.0F - (float) rect.getY();
            g.setColor(getColor(state));
            g.drawString(s, stringX, stringY);
            //reset
            g.setFont(f);
        }
    }
    
    public Color getColor(int state) {
        //temp
        int r = (int)Math.abs(Math.sin(state * 2) * 255);
        int g = (int)Math.abs(Math.sin(state * 3) * 255);
        int b = (int)Math.abs(Math.cos(state * 4) * 255);
        return new Color(r, g, b);
    }
    
    public boolean doDraw(int state) {
        return state != 0;
    }
    
}
