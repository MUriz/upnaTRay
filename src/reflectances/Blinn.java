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
public class Blinn extends GlossyReflectance {

    public Blinn(float q) {
        super(q);
    }
    
    @Override
    public float reflectance(Point3D P, Vector3D normal, Vector3D v, Point3D L) {
        
        // Calcular el vector I
        final Vector3D I = new Vector3D(P,L);
        I.normalize();
        
        // Vector h
        final Vector3D h = v.add(I);
        h.normalize();
        
        // El valor es cos(alpha/2) ^ q
        // cos(alpha/2) = normal dot h
        return (float) Math.pow(normal.dotProduct(h), this.getQ());
        
    }
    
}
