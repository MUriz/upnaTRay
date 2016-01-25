/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ray_tracer;

import projection.Camera;

/**
 *
 * @author linux1
 */
public abstract class RayGenerator {
    
    // Camara
    // Ancho (W)
    // Altura (H)
    
    private final Camera camera;
    private final int W;
    private final int H;
    
    public RayGenerator(final Camera camera, final int W, final int H) {
        this.camera = camera;
        this.W = W;
        this.H = H;
    }
    
    public Camera getCamera() {
        return this.camera;
    }
    
    public int getW() {
        return this.W;
    }
    
    public int getH() {
        return this.H;
    }
    
    public abstract Ray getRay(final int m, final int n);
    
}
