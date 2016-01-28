/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lights;

import primitives.Group;
import utils.Point3D;
import utils.Vector3D;

/**
 *
 * @author txumauriz
 */
public abstract class Light {
    
    private final Point3D location;
    private final float power;
    private final Vector3D dir;
    
    public Light(final Point3D location, final float power, final Point3D pointView) {
        this.location = location;
        this.power = power;
        this.dir = new Vector3D(location, pointView);
        this.dir.normalize();
    }
    
    public Point3D getLocation() {
        return this.location;
    }
    
    public float getPower() {
        return this.power;
    }
    
    public Vector3D getDir() {
        return this.dir;
    }
    
    public abstract float getIrradiance(final Group G, final Point3D P, final Vector3D normal);
    
}
