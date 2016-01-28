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
public abstract class GlossyReflectance {
    
    private final float q;
    
    public GlossyReflectance(final float q) {
        this.q = q;
    }
    
    public abstract float reflectance(final Point3D P, final Vector3D normal, 
                                    final Vector3D v, final Point3D L);
    
    public float getQ() {
        return this.q;
    }
    
}
