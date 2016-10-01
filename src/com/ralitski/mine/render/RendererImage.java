/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ralitski.mine.render;

import com.ralitski.mine.window.GameCanvas;
import com.ralitski.mine.util.Images;
import static com.ralitski.mine.window.GameCanvas.TILE_SIZE;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;


public class RendererImage implements Renderer {
    
    private final BufferedImage img;
    
    public RendererImage(String s) {
        this(Images.getImage(s));
    }
    
    public RendererImage(BufferedImage img) {
        this.img = img;
        if(img.getWidth() > TILE_SIZE || img.getHeight() > TILE_SIZE) {
            throw new IllegalArgumentException("Illegal image given. Must be square of side " + TILE_SIZE);
        }
    }

    @Override
    public void renderTile(GameCanvas c, Graphics2D g, int state) {
        g.drawImage(img, 0, 0, c);
    }
    
}
