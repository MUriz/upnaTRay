/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ray_tracer;

import java.awt.Color;
import materials.Material;
import upnatray.Image;
import utils.Point3D;
import utils.Vector3D;

/**
 *
 * @author linux1
 */
public class Hit {
    
    private final float t;
    private final Point3D point;
    private final Material material;
    private final Vector3D normal;
    private static final Hit voidHit = new Hit(Float.POSITIVE_INFINITY, new Point3D(0,0,0), new Vector3D(0,0,0), null);
    
    public Hit(final float t, final Point3D point, final Vector3D normal, final Material material) {
        this.material = material;
        this.t = t;
        this.point = point;
        this.normal = normal;
        this.normal.normalize();
    }
    
    public static Hit getVoidHit() {
        return Hit.voidHit;
    }
    
    public Material getMaterial() {
        return this.material;
    }
    
    public boolean hits() {
        return !Float.isInfinite(this.t);
    }
    
    public float getT() {
        return this.t;
    }
    
    public Vector3D getNormal() {
        return this.normal;
    }
    
    public Point3D getPoint() {
        return this.point;
    }
    
}
