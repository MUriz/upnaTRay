/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package primitives;

import java.awt.Color;
import materials.Material;
import ray_tracer.Hit;
import ray_tracer.Ray;

/**
 *
 * @author linux1
 */
public abstract class Object3D {
    
    private final Material material;
    
    public Object3D(final Material material) {
        
        this.material = material;
        
    }
    
    public Material getMaterial() {
        return this.material;
    }
    
    public abstract Hit intersect(final Ray r, final float tmin);
    
}
