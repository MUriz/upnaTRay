/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projection;

import ray_tracer.RayGenerator;
import utils.Point3D;
import utils.Vector3D;

/**
 *
 * @author linux1
 */
public class Camera {
    
    private final Point3D p;
    private final Vector3D x, y, z;
    private Projection lente;
    private final Vector3D c1;
    private final Vector3D c2;
    
    public Camera(final Point3D p, final Vector3D look, final Vector3D up) {
        
        this.p = p;
        this.z = look.getOposite();
        this.z.normalize();
        this.y = up;
        this.y.normalize();
        this.x = y.crossProduct(z);
        // s = u escalar w
        final float s = y.dotProduct(z);
        // t = (1-s^2)^(1/2)
        final float t = (float)Math.pow((1-s*s), -1/2);
        // Primera columna
        final float x1 = t*(y.getY()*z.getZ() - y.getZ()*z.getY());
        final float y1 = t*(y.getZ()*z.getX() - y.getX()*z.getZ());
        final float z1 = t*(y.getX()*z.getY() - y.getY()*z.getX());
        // Segunda columna
        final float x2 = t*(y.getX() - s*z.getX());
        final float y2 = t*(y.getY() - s*z.getY());
        final float z2 = t*(y.getZ() - s*z.getZ());
        
        // Solo guardo las 2 primeras columnas, ya que la tercera columna es
        // el vector look
        // y la cuarta columna es la piscion de la camara
        c1 = new Vector3D(x1,y1,z1);
        c2 = new Vector3D(x2,y2,z2);
    }
    
    public Point3D pointToScene(final Point3D po) {
        float px = c1.getX()*po.getX() + c2.getX()*po.getY() + z.getX()*po.getZ()+p.getX();
        float py = c1.getY()*po.getX() + c2.getY()*po.getY() + z.getY()*po.getZ()+p.getY();
        float pz = c1.getZ()*po.getX() + c2.getZ()*po.getY() + z.getZ()*po.getZ()+p.getZ();
        return new Point3D(px, py, pz);
    }
    
    public Projection getProjection() {
        return this.lente;
    }
    
    public RayGenerator getRayGenerator(final int W, final int H) {
        return lente.getRayGenerator(this, W, H);
    }
    
    public Point3D getPoint() {
        return this.p;
    }
    
    public Vector3D getLook() {
        return this.z.getOposite();
        //return this.z;
    }
    
    public void setProjection(final Projection projection) {
        this.lente = projection;
    }
    
}
