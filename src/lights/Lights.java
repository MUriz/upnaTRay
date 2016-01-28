/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lights;

import java.util.ArrayList;

/**
 *
 * @author mikel.uriz
 */
public class Lights {
    
    private float ambientalIrradiance;
    private final ArrayList<Light> lights;
    
    public Lights(final float ambientalIrradiance) {
        this.ambientalIrradiance = ambientalIrradiance;
        this.lights = new ArrayList<>();
    }
    
    public void setAmbientIrradiance(final float ambientIrradiance) {
        this.ambientalIrradiance = ambientIrradiance;
    }
    
    public float getAmbientalIrradiance() {
        return this.ambientalIrradiance;
    }
    
    public void addLight(final Light L) {
        lights.add(L);
    }
    
    public ArrayList<Light> getLights() {
        return this.lights;
    }
    
}
