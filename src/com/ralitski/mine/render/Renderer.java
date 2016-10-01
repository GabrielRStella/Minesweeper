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
 * @author stellgab000
 */
public interface Renderer {
    
    void renderTile(GameCanvas c, Graphics2D g, int state);
}
