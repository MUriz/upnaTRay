/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projection;

import ray_tracer.Ray;
import ray_tracer.RayGenerator;
import utils.Point3D;

/**
 *
 * @author linux1
 */
public class Perspective extends Projection {
    
    public static class PerspectiveRayGenerator extends RayGenerator {
        
        public PerspectiveRayGenerator(final Camera camera, final int W, final int H) {
            super(camera, W, H);
        }

        @Override
        public Ray getRay(int m, int n) {
            final float w = this.getCamera().getProjection().getW();
            final float h = this.getCamera().getProjection().getH();
            float x = (float)((m+1/2f)*(w/this.getW())-w/2);
            float y = (float)((n+1/2f)*(h/this.getH())-h/2);
            float z = -this.getCamera().getProjection().getDistance();
            return new Ray(this.getCamera().getPoint(), this.getCamera().pointToScene(new Point3D(x,y,z)));
        }
        
    }
    
    private Perspective(float dis, float height, float asp, Void p) {
        super(dis, height*asp, height);
    }
    
    public Perspective(final float d, final float fov, final float a) {
        
        // Como h = 2*d*tan(fov/2), y w = h*a, creo un constructor privado
        // Donde le pasamos la distancia, la altura y el aspecto (a), para
        // no caluclar 2 veces h.
        // El ultimpo parametro (null), es para diferenciar el contructor privado
        // de este
        this(d, (float)(2*d*Math.tan(Math.toRadians(fov/2))), a, null);
       
    }

    @Override
    public RayGenerator getRayGenerator(Camera camera, int W, int H) {
        return new PerspectiveRayGenerator(camera, W, H);
    }
    
}
