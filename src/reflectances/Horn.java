/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package reflectances;

import utils.Point3D;
import utils.Vector3D;

/**
 *
 * @author txumauriz
 */
public class Horn extends GlossyReflectance {

    public Horn(final float q) {
        super(q);
    }

    @Override
    public float reflectance(Point3D P, Vector3D normal, Vector3D v, Point3D L) {
        
        // Calcular el angulo entre el vector de de vista (v) y de reflexion especular ideal (r)
        // r = 2*cos(theta)*n - I
        // r = 2*(I dot n)*n - I
        // Vector I = vector que parte de P y se dirige a L
        // I = PL = L - P
        final Vector3D I = new Vector3D(P, L);
        I.normalize();
        final Vector3D r = normal.getScaled(2*I.dotProduct(normal)).substract(I);
        // Normalizamos r
        r.normalize();
        // Calculamos el angulo alpha
        float alpha = r.dotProduct(v);
        // La formula de Horn
        // cos(alpha)^q
        return (float) Math.pow(Math.cos(alpha), this.getQ());
        
    }
    
}
