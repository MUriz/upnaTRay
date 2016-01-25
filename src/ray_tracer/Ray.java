/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ray_tracer;

import utils.Point3D;
import utils.Vector3D;

/**
 *
 * @author linux1
 */
public class Ray {

    // Punto de donde parte el rayo.
    private final Point3D R;
    // Vector director del rayo
    private final Vector3D v;
    
    // Rayo que parte de punto R y pasa por el punto Q
    public Ray (final Point3D R, final Point3D Q) {
        
        this.R = R;
        // Calculamos el vector director como la resta de Q y R (RQ = Q - R)
        this.v = new Vector3D(R, Q);
        // Normalizamos el vector
        this.v.normalize();
        
    }
    
    // Rayo que parte del punto R y con direccion v
    public Ray (final Point3D R, final Vector3D v) {
        
        // Asignamos R y v
        this.R = R;
        this.v = v;
        // Normalizamos v
        this.v.normalize();
        
    }
    
    @Override
    public String toString() {
        
        return "FROM: " + this.R + "\nWITH DIR: " + this.v;
        
    }
    
    // Punto que se encuentra en R + v*t
    public Point3D pointAtParameter (final float t) {
        
        return R.add(v.multiply(t));
        
    }
    
    public Point3D getR() {
        
        return this.R;
        
    }
    
    public Vector3D getV() {
        
        return this.v;
        
    }
    
}
