/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lights;

import primitives.Group;
import ray_tracer.Ray;
import utils.Point3D;
import utils.Vector3D;

/**
 *
 * @author txumauriz
 */
public class SpotLight extends Light{
    
    private final float radiantIntensity;
    final float angle;
    
    public SpotLight(final Point3D location, final float power, final Point3D viewPoint, final float angle) {
        
        super(location, power, viewPoint);
        radiantIntensity = (float) (this.getPower()/(2*Math.PI*(1-Math.cos(angle))));
        this.angle = angle;
     
    }
    
    @Override
    public float getIrradiance(final Group G, final Point3D P, final Vector3D normal) {
        
        final Vector3D I = new Vector3D(P, this.getLocation());
        final float d_2 = I.dotProduct(I);
        I.normalize();
        final float v = -1*this.getDir().dotProduct(I);
        // Punto fuera
        if (Math.cos(angle) > v) {
            return 0f;
        } else {
            // Lanzamos un rayo desde la fuente hacia el punto
            // Si chocamos con algo, devolvemos 0
            final Ray r = new Ray(this.getLocation(), P);
            if (G.anyIntersection(r, P)) {
                return 0f;
            } else {
                return radiantIntensity*I.dotProduct(normal)/d_2;
            }
        }
    }
    
}
