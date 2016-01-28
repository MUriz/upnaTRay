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
 * @author mikel
 * Intento de hacer la diferencia de dos esferas
 * SI CORTA UNA ESFERA PERO NO BIEN DEL TODO
 */
public class SphereDiff extends Object3D {

    private final Sphere s1, s2;
    
    public SphereDiff(final Point3D p1, final float r1, final Point3D p2, final float r2, final Material material) {
        
        super(material);
        s1 = new Sphere(p1, r1, material);
        s2 = new Sphere(p2, r2, material);
        
    }
    
    public SphereDiff(final Sphere s1, final Sphere s2) {
        
        super(s1.getMaterial());
        this.s1 = s1;
        this.s2 = s2;
        
    }
    
    @Override
    public Hit intersect(Ray r, float tmin) {
        
        final Hit hit1 = s1.intersect(r, tmin);
        final Hit hit2 = s2.intersect(r, tmin);
        // Si la primera es void, o la segunda es void, devolvemos la primera
        if (!hit1.hits() || !hit2.hits()){
            return hit1;
        } else {
            // Si pega en la primera o en la segunda
            // Calculamos el vecor que parte del centro de la primera
            // y va a el punto de interseccion de la segunda
            final Vector3D v1 = new Vector3D(s1.getCenter(), hit2.getPoint());
            // Si la longtud de ese vector es menor o igual al radio de 
            // la primera esfera, corta, por lo tanto devolvemos void
            if (v1.getNorm() <= s1.getRadius()) {
                return Hit.getVoidHit();
            } else {
                // Si no corta devolvemos el primer hit
                return hit1;
            }
        }
        /*
        if (hit1.getT() < hit2.getT()) {
            if (hit2.getT() < (hit1.getT()+s1.getRadius())) {
                return Hit.getVoidHit();
            } else {
                return hit1;
            }
        } else {
            if (hit1.getT() < (hit2.getT() + s2.getRadius())) {
                return Hit.getVoidHit();
            } else {
                return hit2;
            }
        }
        */
    }
    
    
    
}
