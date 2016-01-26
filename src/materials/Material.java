package materials;


import java.awt.Color;
import lights.Lights;
import primitives.Group;
import reflectances.GlossyReflectance;
import utils.Point3D;
import utils.Vector3D;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author txumauriz
 */
public abstract class Material {
    
    private final float ka;
    private final float ks;
    private final float kd;
    private final Color baseColor;
    private final GlossyReflectance Fs;
    private final boolean especular;
    
    public Material (final float ka, final float ks, final float kd, final Color baseColor, final GlossyReflectance Fs, final boolean especular) {
        this.ka = ka;
        this.ks = ks;
        this.kd = kd;
        this.Fs = Fs;
        this.baseColor = baseColor;
        this.especular = especular;
    }
    
    protected Color addColors(final Color c1, final Color c2) {
        final int red = Math.min(255, c1.getRed() + c2.getRed());
        final int green = Math.min(255, c1.getGreen() + c2.getGreen());
        final int blue = Math.min(255, c1.getBlue() + c2.getBlue());
        return new Color(red, green, blue);
        //return new Color(c1.getRGB() + c2.getRGB());
    }
    
    protected Color multColor(final Color c, final float f) {
        int red = Math.min(255, (int)(c.getRed()*f));
        int green = Math.min(255, (int)(c.getGreen()*f));
        int blue = Math.min(255, (int)(c.getBlue()*f));
        return new Color(red, green, blue);
    }

    public float getKa() {
        return ka;
    }

    public float getKs() {
        return ks;
    }

    public float getKd() {
        return kd;
    }

    public Color getBaseColor() {
        return baseColor;
    }

    public GlossyReflectance getFs() {
        return Fs;
    }
    
    public boolean isEspecular() {
        return especular;
    }
    
    public abstract Color getColor(final Group G, final Lights L, final Point3D P,
                                    final Vector3D normal, final Point3D V);
    
}