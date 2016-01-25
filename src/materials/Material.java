package materials;


import java.awt.Color;
import lights.Lights;
import primitives.Group;
import utils.Point3D;
import utils.Vector3D;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author txumauriz
 */
public abstract class Material {
    
    public abstract Color getColor(final Group G, final Lights L, final Point3D P,
                                    final Vector3D normal, final Point3D V);
    
}