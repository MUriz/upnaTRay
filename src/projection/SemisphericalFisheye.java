/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projection;

import ray_tracer.Ray;
import ray_tracer.RayGenerator;
import utils.Point3D;
import utils.Vector3D;

/**
 *
 * @author mikel
 */
public class SemisphericalFisheye extends Projection {
    
    public static class SemisphericalFisheyeRayGenerator extends RayGenerator {
        
        public SemisphericalFisheyeRayGenerator(final Camera camera, final int W, final int H) {
            super(camera, W, H);
        }

        @Override
        public Ray getRay(int m, int n) {
            final float w = this.getCamera().getProjection().getW();
            final float h = this.getCamera().getProjection().getH();
            float x = (m+1/2f)*(w/this.getW())-w/2;
            float y = (n+1/2f)*(h/this.getH())-h/2;
            float k = this.getCamera().getProjection().getDistance();
            float z = -k;
            float hei = (new Vector3D(new Point3D(0,0,-k), new Point3D(x,y,z))).getNorm();
            if (hei > k) {
                return null;
            } else {
                float s = (2*k*k)/(k*k-hei*hei);
                Point3D p = new Point3D(x*s, y*s, z*s);
                return new Ray(this.getCamera().pointToScene(p), this.getCamera().getLook());
            }
        }
        
    }
    
    public SemisphericalFisheye(final float k) {
        
        super(k, 2*k, 2*k);
        
    }

    @Override
    public RayGenerator getRayGenerator(Camera camera, int W, int H) {
        
        return new SemisphericalFisheyeRayGenerator(camera, W, H);
        
    }
    
}
