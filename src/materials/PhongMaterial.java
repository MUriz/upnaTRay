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
import ray_tracer.Hit;
import ray_tracer.Ray;
import reflectances.GlossyReflectance;
import utils.Point3D;
import utils.Vector3D;

/**
 *
 * @author txumauriz
 */
public class PhongMaterial extends Material {
    
    public PhongMaterial(final float ka, final float ks, final float kd, final Color baseColor, final GlossyReflectance Fs, final boolean especular) {
        
        super(ka, ks, kd, baseColor, Fs, especular);
        
    }

    @Override
    public Color getColor(Group G, Lights lights, Point3D P, Vector3D normal, Point3D V) {
        
        return getColor(G, lights, P, normal, V, 5);
        
        /*Color c = this.ambientColor(lights.getAmbientalIrradiance());
        
        for(Light L : lights.getLights()) {
            c = addColors(c, this.directShader(G, P, normal, V, L));
        }
        
        if (this.isEspecular()) {
            // R = v - 2*(n dor v)*n
            // v: vector de trazado primario
            final Vector3D v = new Vector3D(V, P);
            final Vector3D R = v.substract(normal.getScaled(2*normal.dotProduct(v)));
            R.normalize();
            // El rayo parte de P con direccion R
            final Ray rmirror = new Ray(P, R);
            final Hit h = G.intersect(rmirror, 0);
            if (h.hits()) {
                final Material m = h.getMaterial();
                final Point3D Ps = h.getPoint();
                final Vector3D ns = h.getNormal();
                return addColors(c, getColor(G, lights, Ps, ns, P, 5));
            } else {

                return c;

            }
            
        }
        
        return c;*/
        
    }
    
    private Color getColor(Group G, Lights lights, Point3D P, Vector3D normal, Point3D V, int n) {
        Color c = this.ambientColor(lights.getAmbientalIrradiance());
        //Color c = new Color(0,0,0);
        if (n <= 0) {
            return c;
        }
        for (Light L : lights.getLights()) {
            c = addColors(c, this.directShader(G, P, normal, V, L));
        }
        
        if (this.isEspecular()) {
        
            // R = v - 2*(n dor v)*n
            // v: vector de trazado primario
            final Vector3D v = new Vector3D(V, P);
            final Vector3D R = v.substract(normal.getScaled(2*normal.dotProduct(v)));
            R.normalize();
            // El rayo parte de P con direccion R
            final Ray rmirror = new Ray(P, R);
            final Hit h = G.intersect(rmirror, 0);
            if (h.hits()) {
                final Material m = h.getMaterial();
                final Point3D Ps = h.getPoint();
                final Vector3D ns = h.getNormal();
                return addColors(c, getColor(G, lights, Ps, ns, P, n-1));
            } else {

                return c;

            }
        }
        
        return c;
        
    }
    
    private Color ambientColor(final float ambientalIrradiance) {
        
        return multColor(this.getBaseColor(), this.getKa()*ambientalIrradiance);
        //return new Color((int) (ka*baseColor.getRGB()*ambientalIrradiance));
        
    }
    
    private Color directShader(final Group G, final Point3D P, final Vector3D normal, final Point3D V, final Light L) {
        //(kd+ks*Fs/(n dot I))*E
        final Vector3D I = new Vector3D(P, L.getLocation());
        I.normalize();
        final Vector3D PV = new Vector3D(P, V);
        PV.normalize();
        float mult = this.getKd() + this.getKs()*this.getFs().reflectance(P, normal, PV, L.getLocation())/normal.dotProduct(I);
        //mult = (mult < 0) ? 0 : L.getIrradiance(G, P, normal)*mult;
        mult *= L.getIrradiance(G, P, normal);
        if (mult < 0) {
            return multColor(this.getBaseColor(), 0);
        } else {
            return multColor(this.getBaseColor(), mult);
        }
        
        
        //return multColor(baseColor, mult);
        //return new Color((int) (baseColor.getRGB() * mult * L.getIrradiance(G, P, normal)));
        //Color retColor = addColors(kd, new Color((int) (ks.getRGB()*mult)));
        //return new Color((int) (retColor.getRGB()*L.getIrradiance()));
    }
    
}
