/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package primitives;

import java.util.ArrayList;
import ray_tracer.Hit;
import ray_tracer.Ray;
import utils.Point3D;

/**
 *
 * @author linux1
 */
public class Group extends Object3D {
    
    private final ArrayList<Object3D> objects;
    
    public Group(final int numElements) {
        //Como extiende de Object3D, le pasamos un color negro (por poner un color)
        super(null);
        this.objects = new ArrayList<>(numElements);
    }
    //Metodo tal como aparece en el pdf
    public void addObject(final int index, final Object3D obj) {
        objects.add(index, obj);
    }
    //Metodo utilizado por el parser
    public void addObject(final Object3D obj) {
        objects.add(obj);
    }
    
    public Object3D getObject(final int index) {
        return objects.get(index);
    }

    @Override
    public Hit intersect(Ray r, float tmin) {
        Hit ret_hit = Hit.getVoidHit();
        for (Object3D o : objects) {
            final Hit aux_hit = o.intersect(r, tmin);
            if ((aux_hit.getT() <= ret_hit.getT())){// && (aux_hit.getT() >= tmin)) {
                ret_hit = aux_hit;
            }
        }
        return ret_hit;
    }
    
    public boolean anyIntersection(Ray r, final Point3D P) {
        for (Object3D o : objects) {
            final Hit h = o.intersect(r, 0);
            if (h.hits() && !h.getPoint().equals(P)) {
                return true;
            }
        }
        return false;
    }
    
}
