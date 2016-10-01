/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ralitski.mine.util;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;

/**
 *
 * @author STELLGAB000
 */
public class Images {
    
//    public static final BufferedImage COVERED = getImage("covered");
//    public static final BufferedImage FLAGGED = getImage("flagged");
//    public static final BufferedImage MARKED = getImage("marked");
//    public static final BufferedImage MINE = getImage("mine");
//    public static final BufferedImage TILE = getImage("tile");
    
    public static final File DIR = new File("res/");
    
    public static BufferedImage getImage(String s) {
        File f = new File(DIR, s + ".png");
        try {
            return ImageIO.read(f);
        } catch (IOException ex) {
            Logger.getLogger(Images.class.getName()).log(Level.SEVERE, "Failed to load image: " + f, ex);
            return null;
        }
    }
}
