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
public class Ortographic extends Projection{
    
    public static class OrtographicRayGenerator extends RayGenerator {
        
        public OrtographicRayGenerator(final Camera camera, final int W, final int H) {
            super(camera, W, H);
        }

        @Override
        public Ray getRay(int m, int n) {
            final float w = this.getCamera().getProjection().getW();
            final float h = this.getCamera().getProjection().getH();
            float x = (float)((m+1/2f)*(w/this.getW())-w/2);
            float y = (float)((n+1/2f)*(h/this.getH())-h/2);
            float z = 0;
            return new Ray(this.getCamera().pointToScene(new Point3D(x, y, z)), this.getCamera().getLook());
        }
        
    }
    
    public Ortographic(final float w, final float h) {
        super(0, w, h);
    }

    @Override
    public RayGenerator getRayGenerator(Camera camera, int W, int H) {
        return new OrtographicRayGenerator(camera, W, H);
        
    }
    
}
