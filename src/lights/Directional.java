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
 * @author mikel.uriz
 */
public class Directional extends Light {
    
    private final float irradiance;

    public Directional(final Point3D location, final float power, final float s) {
        super(location, power);
        irradiance = power/s;
    }
    
    @Override
    public float getIrradiance(Group G, Point3D P, Vector3D normal) {
        
        final Vector3D I = new Vector3D(P, this.getLocation());
        final float d_2 = I.dotProduct(I);
        I.normalize();
        // Lanzamos un rayo desde la fuente hacia el punto
        // Si chocamos con algo, devolvemos 0
        Ray r = new Ray(this.getLocation(), P);
        if (G.anyIntersection(r, P)) {
            return 0f;
            //return irradiance*I.dotProduct(normal)/d_2;
        } else {
            return irradiance*I.dotProduct(normal);
        }
        
    }
    
}
