/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lights;

import primitives.Group;
import primitives.Plane;
import ray_tracer.Hit;
import ray_tracer.Ray;
import utils.Point3D;
import utils.Vector3D;

/**
 *
 * @author mikel.uriz
 */
public class DirectionalLight extends Light {
    
    private final float irradiance;
    private final float radius;

    public DirectionalLight(final Point3D location, final float power, final float s, final Point3D viewPoint) {
        super(location, power, viewPoint);
        irradiance = power/s;
        // La superficie es una esfera
        // s = PI*r*r
        // r = sqrt(s/PI)
        this.radius = (float) Math.sqrt(s/Math.PI);
    }
    
    @Override
    public float getIrradiance(Group G, Point3D P, Vector3D normal) {
        
        // Para determinar si el punto esta dentro del campo iluminado voy a emplear
        // la teoira de interseccion de un rayo con un plano.
        // Creamos un plano que tiene el punto p y la normal la direccion de emision
        // opuesta. 
        // Mandamos un rayo que parte de aqui en direccion de emision
        // Tenemos 2 puntos en el plano, P y el de la interseccion
        // Si la distancia qye hay entre estos 2 es emenor o igual al radio, este
        // punto se ilumna
        // Tenemos 2 puntos
        // Plano
        final Plane plane = new Plane(null, P, this.getDir().getOposite());
        // Rayo que parte de aqui en direccion dir
        final Ray r_p = new Ray(this.getLocation(), this.getDir());
        // Interseccion
        final Hit hit = plane.intersect(r_p, 0);
        final Point3D intersectionPoint = hit.getPoint();
        // Calculamos la distancia como la longitud del vector que une los puntos del plano
        final float distance = (new Vector3D(P, intersectionPoint)).getNorm();
        // Si la distancia es mayor que el radio, esta fuera del campo de iluminacion
        if (distance > this.radius) {
            return 0.0f;
        } else {
            // El punto puede llegar a ser iluminado
            // Lanzamos un rayo desde la fuente hacia el punto
            // Pero el punto desde donde tenemos que lanzar sera el que llegue a P,
            // con direccion dir
            // P = R + t*dir
            // P ya tenemos, queremos calcular R
            // R = P - t*dir
            // R = P + t*dir_ops
            final Point3D R = P.add(this.getDir().getOposite().multiply(hit.getT()));
            // Si chocamos con algo, devolvemos 0
            final Ray r = new Ray(R, this.getDir());
            if (G.anyIntersection(r, P)) {
                return 0f;
            } else {
                final Vector3D I = new Vector3D(P, this.getLocation());
                I.normalize();
                return irradiance*I.dotProduct(normal);
            }
        }
        
    }
    
}
