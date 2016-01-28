/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package primitives;

import materials.Material;
import ray_tracer.Hit;
import ray_tracer.Ray;
import utils.Point3D;
import utils.Vector3D;

/**
 *
 * @author linux1
 */
public class Plane extends Object3D {
    
    private final Point3D point;
    private final Vector3D normal;

    public Plane(Material material, final Point3D point, final Vector3D normal) {
        super(material);
        this.point = point;
        this.normal = normal;
    }

    @Override
    public Hit intersect(Ray r, float tmin) {
       
        //Calculamos el punto de interseccion
        final Vector3D P1P0 = new Vector3D(r.getR(), point);
        final float t = (P1P0.dotProduct(normal))/(r.getV().dotProduct(normal));
        if (t < tmin) {
            return Hit.getVoidHit();
        } else {
            return new Hit(t, r.pointAtParameter(t), normal, this.getMaterial());
        }
        
        
    }
    
}
