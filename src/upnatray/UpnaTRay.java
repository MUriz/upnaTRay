/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package upnatray;

import java.awt.Color;
import java.awt.FlowLayout;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import lights.Lights;
import parser.Parser;
import primitives.Group;
import projection.Camera;
import projection.Projection;

/**
 *
 * @author linux1
 */
public class UpnaTRay {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        Image i = new Image(512, 512);
        
        
        try {
            
            Parser p = new Parser(new BufferedReader(new FileReader("scenes/scene0_mat")));
            Camera c = p.parseCamera();
            Projection pr = p.parseProjection();
            c.setProjection(pr);
            Color bc = p.parseBackgroundColor();
            Image.setBackgroundColor(bc);
            Lights L = p.parseLights();
            Group g = p.parseGroup();
            //Lights L = new Lights(1f);
            //SpotLight sl = new SpotLight(new Point3D(15, 0, 15), 200f, new Point3D(0,0,0), (float) Math.PI);
            //L.addLight(sl);
            //sl = new SpotLight(new Point3D(-10, 0, -10), 100f, new Point3D(0,0,0), (float) Math.PI/4);
            //L.addLight(sl);
            i.synthesis(g, c, L);
        } catch (Exception ex) {
            Logger.getLogger(UpnaTRay.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        
        JFrame frame = new JFrame();
        frame.getContentPane().setLayout(new FlowLayout());
        frame.getContentPane().add(new JLabel(new ImageIcon(i.getBufferedImage())));
        frame.pack();
        frame.setVisible(true);
        
    }
    
}
