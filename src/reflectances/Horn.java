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
 * @author mikel.uriz
 */
public class Horn extends GlossyReflectance {

    public Horn(final float q) {
        super(q);
    }

    @Override
    public float reflectance(Point3D P, Vector3D normal, Vector3D v, Point3D L) {
        
        // Calcular el angulo entre el vector de de vista (v) y de reflexion especular ideal (r)
        // cos alphha = v dot r
        // 2*(I dot n) * (v dot n) - (v dot I)
        
        final Vector3D I = new Vector3D(P, L);
        I.normalize();
        final float cosAlpha = 2*(I.dotProduct(normal))*(v.dotProduct(normal)) - (v.dotProduct(I));
        return (float) Math.pow(cosAlpha, this.getQ());
        
    }
    
}
