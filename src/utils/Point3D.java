/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import javax.vecmath.Tuple4f;

/**
 *
 * @author linux1
 */
public class Point3D extends Tuple4f {
    
    public Point3D(final float x, final float y, final float z) {
        super(x,y,z,1f);
    }
    
    // Suma de dos puntos
    public Point3D add(final Point3D p) {
        final float x0 = this.getX() + p.getX();
        final float y0 = this.getY() + p.getY();
        final float z0 = this.getZ() + p.getZ();
        return new Point3D(x0, y0, z0);
        
    }
    
    // Suma de punto y vector
    public Point3D add(final Vector3D v) {
        final float x0 = this.getX() + v.getX();
        final float y0 = this.getY() + v.getY();
        final float z0 = this.getZ() + v.getZ();
        return new Point3D(x0, y0, z0);
        
    }
    
    //Crear un vector a partir de la resta entre este punto y el pasado por parametro
    public Vector3D substract(final Point3D p) {
        final float x0 = this.getX() - p.getX();
        final float y0 = this.getY() - p.getY();
        final float z0 = this.getZ() - p.getZ();
        return new Vector3D(x0, y0, z0);
    }
    
    public boolean equals(Point3D p) {
        return (p.getX() == this.getX()) &&
                (p.getY() == this.getY()) &&
                (p.getZ() == this.getZ()) &&
                (p.getW() == this.getW());
    }
    
}
