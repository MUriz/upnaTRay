/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projection;

import ray_tracer.RayGenerator;

/**
 *
 * @author linux1
 */
public abstract class Projection {
    
    private final float d;
    private final float w;
    private final float h;
    
    public Projection(final float d, final float w, final float h) {
        this.w = w;
        this.h = h;
        this.d = d;
    }
    
    public abstract RayGenerator getRayGenerator(final Camera camera, final int W, final int H);
    
    public float getDistance() {
        return this.d;
    }
    
    public float getW() {
        return this.w;
    }
    
    public float getH() {
        return this.h;
    }
    
}
