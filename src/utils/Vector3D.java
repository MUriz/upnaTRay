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
public class Vector3D extends Tuple4f {
    
    public Vector3D(final float x, final float y, final float z) {
        super(x, y, z, 0f);
    }
    
    private Vector3D(final Vector3D v) {
        super(v.getX(), v.getY(), v.getZ(), 0f);
    }
    
    // Crear un vector a partir de dos puntos. Parte de p1 y va ha p2
    public Vector3D(final Point3D p1, final Point3D p2) {
        //Vecotor que parte de p1 y va hacia p2
        //p1p2 => p2 - p1
        this(p2.substract(p1));
    }
    
    public float getNorm() {
        return (float)Math.sqrt(this.getX()*this.getX() + this.getY()*this.getY() + this.getZ()*this.getZ());
    }
    
    public float dotProduct(final Vector3D v) {
        
        return this.x * v.getX() + this.y * v.getY() + this.z * v.getZ();
        
    }
    
    public Point3D multiply(final float s) {
        return new Point3D(s*this.getX(), s*this.getY(), s*this.getZ());
    }
    
    // Obtener una copia de este vector pero en sentido contrario
    public Vector3D getOposite() {
        return new Vector3D(-1*this.x, -1*this.y, -1*this.z);
    }
    
    public void normalize() {
        if ((this.getNorm() == 0) || (this.getNorm()==1)) {
            return;
        }
        float t = 1/this.getNorm();
        this.x *= t;
        this.y *= t;
        this.z *= t;
    }
    
    // Escalar el vector y devolver el resultado sin alterar el objeto actual
    public Vector3D getScaled(final float s) {
        return new Vector3D(this.getX()*s, this.getY()*s, this.getZ()*s);
    }
    
    // Restar 2 vectores y devolver el resultado en un nuevo vector sin alterar este objeto
    public Vector3D substract(final Vector3D v) {
        return new Vector3D(this.getX() - v.getX(), this.getY() - v.getY(), this.getZ() - v.getZ());
    }
    
    public Vector3D crossProduct(Vector3D v) {
        
        //"Construimos" un determinante
        //| x  this.x  v.x |
        //| y  this.y  v.y |
        //| z  this.z  v.z |
        //x
        //| this.y  v.y |
        //| this.z  v.z |
        //final float newX = det2D(this.getY(), v.getY(), this.getZ(), v.getZ());
        final float newX = this.getY()*v.getZ()-this.getZ()*v.getY();
        //y*-1
        //| this.x  v.x |
        //| this.z  v.z |
        //final float newY = -1*det2D(this.getX(), v.getX(), this.getZ(), v.getZ());
        final float newY = -1*(this.getX()*v.getZ()-v.getX()*this.getZ());
        //z
        //| this.x  v.x |
        //| this.y  v.y |
        //final float newZ = det2D(this.getX(), v.getX(), this.getY(), v.getY());
        final float newZ = this.getX()*v.getY()-v.getX()*this.getY();
        return new Vector3D(newX, newY, newZ);
        
    }
    
    private float det2D(final float a11, final float a12, final float a21, final float a22) {
        return a11*a22 - a12*a21;
    }
    
}
