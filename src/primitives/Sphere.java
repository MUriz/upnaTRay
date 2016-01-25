/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package primitives;

import java.awt.Color;
import materials.Material;
import ray_tracer.Hit;
import ray_tracer.Ray;
import utils.Point3D;
import utils.Vector3D;

/**
 *
 * @author linux1
 */
public class Sphere extends Object3D {
    
    private final Point3D point;
    private final float r; 

    public Sphere(Point3D point,float r, Material material) {
        super(material);
        this.r = r;
        this.point = point;
    }

    @Override
    public Hit intersect(Ray r, float tmin) {
        
        final Point3D R = r.getR();
        //final Vector3D RC = this.point.substract(R);
        final Vector3D RC = new Vector3D(R, this.point);
        final float c = RC.dotProduct(RC) - this.r*this.r;
        if (c > 0) {
            final float b = RC.dotProduct(r.getV());
            if (b >= 0) {
                float t=-1;
                if (c == b*b) {
                    t = b;
                    //F = R.add(r.getV().multiply(t)); //Multiplicar t al vector
                    //return new Hit(t, F, new Vector3D(point, F), this.getColor());
                } else if (c < (b*b)) {
                    float d = (float)Math.sqrt(b * b - c);
                    float tp = b + d;
                    t = c/tp;
                    //F = R.add(r.getV().multiply(t)); //Multiplicar t al vector
                    //return new Hit(tm, F, new Vector3D(point, F), this.getColor());
                }
                if (t < tmin) {
                    return Hit.getVoidHit();
                } else {
                    final Point3D F = r.pointAtParameter(t);
                    final Vector3D normal = new Vector3D(point, F);
                    normal.normalize();
                    return new Hit(t, F, normal, this.getMaterial());
                }
            }
        }
        return Hit.getVoidHit();
    }
    
    public float getRadius() {
        return this.r;
    }
    
    public Point3D getCenter() {
        return this.point;
    }
    
}
