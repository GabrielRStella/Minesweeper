/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ralitski.mine.render;

import com.ralitski.mine.window.GameCanvas;
import java.awt.Graphics2D;

/**
 *
 * @author STELLGAB000
 */
public class RendererCombined implements Renderer {
    
    private final Renderer[] internal;
    
    public RendererCombined(Renderer... internal) {
        this.internal = internal;
    }

    @Override
    public void renderTile(GameCanvas c, Graphics2D g, int state) {
        for(Renderer renderer: internal) renderer.renderTile(c, g, state);
    }
    
}
