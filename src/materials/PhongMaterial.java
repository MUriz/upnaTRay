/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package materials;

import java.awt.Color;
import lights.Light;
import lights.Lights;
import primitives.Group;
import reflectances.GlossyReflectance;
import utils.Point3D;
import utils.Vector3D;

/**
 *
 * @author txumauriz
 */
public class PhongMaterial extends Material {
    
    private final float ka;
    private final float ks;
    private final float kd;
    private final Color baseColor;
    private final GlossyReflectance Fs;
    
    public PhongMaterial(final float ka, final float ks, final float kd, final Color baseColor, final GlossyReflectance Fs) {
        this.ka = ka;
        this.ks = ks;
        this.kd = kd;
        this.Fs = Fs;
        this.baseColor = baseColor;
    }

    @Override
    public Color getColor(Group G, Lights lights, Point3D P, Vector3D normal, Point3D V) {
        
        Color c = this.ambientColor(lights.getAmbientalIrradiance());
        
        for(Light L : lights.getLights()) {
            c = addColors(c, this.directShader(G, P, normal, V, L));
        }
        
        return c;
        
    }
    
    private Color ambientColor(final float ambientalIrradiance) {
        
        return multColor(baseColor, ka*ambientalIrradiance);
        //return new Color((int) (ka*baseColor.getRGB()*ambientalIrradiance));
        
    }
    
    private Color directShader(final Group G, final Point3D P, final Vector3D normal, final Point3D V, final Light L) {
        //(kd+ks*Fs/(n dot I))*E
        final Vector3D I = new Vector3D(P, L.getLocation());
        I.normalize();
        final Vector3D PV = new Vector3D(P, V);
        PV.normalize();
        float mult = kd + ks*Fs.reflectance(P, normal, PV, L.getLocation())/normal.dotProduct(I);
        //mult = (mult < 0) ? 0 : L.getIrradiance(G, P, normal)*mult;
        mult *= L.getIrradiance(G, P, normal);
        if (mult < 0) {
            return multColor(baseColor, 0);
        } else {
            return multColor(baseColor, mult);
        }
        //return multColor(baseColor, mult);
        //return new Color((int) (baseColor.getRGB() * mult * L.getIrradiance(G, P, normal)));
        //Color retColor = addColors(kd, new Color((int) (ks.getRGB()*mult)));
        //return new Color((int) (retColor.getRGB()*L.getIrradiance()));
    }
    
    private Color addColors(final Color c1, final Color c2) {
        final int red = Math.min(255, c1.getRed() + c2.getRed());
        final int green = Math.min(255, c1.getGreen() + c2.getGreen());
        final int blue = Math.min(255, c1.getBlue() + c2.getBlue());
        return new Color(red, green, blue);
        //return new Color(c1.getRGB() + c2.getRGB());
    }
    
    private Color multColor(final Color c, final float f) {
        int red = Math.min(255, (int)(c.getRed()*f));
        int green = Math.min(255, (int)(c.getGreen()*f));
        int blue = Math.min(255, (int)(c.getBlue()*f));
        return new Color(red, green, blue);
    }

    
    
}
