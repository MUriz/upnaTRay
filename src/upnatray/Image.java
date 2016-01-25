/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package upnatray;

import java.awt.Color;
import primitives.Group;
import java.awt.image.BufferedImage;
import lights.Lights;
import materials.Material;
import ray_tracer.Hit;
import ray_tracer.Ray;
import ray_tracer.RayGenerator;
import projection.Camera;
import utils.Point3D;
import utils.Vector3D;

/**
 *
 * @author linux1
 */
public class Image { 
    
    private final BufferedImage bufferedImage;
    private final int W;
    private final int H;
    // Definimos el color de fondo como negro por defecto
    // Pero hay un metodo estatico para cambiarlo
    public static Color backgroundColor = Color.BLACK;
    
    public Image(final int W, final int H) {
        this.W = W;
        this.H = H;
        bufferedImage = new BufferedImage(W, H, BufferedImage.TYPE_INT_RGB);
    }
    
    public static void setBackgroundColor(Color color) {
        backgroundColor = color;
    }
    
    public void synthesis(final Group scene, final Camera camera, final Lights L) {
        final float tmin = camera.getProjection().getDistance();
        final RayGenerator rg = camera.getRayGenerator(W,H); // a su vez se lo pide a la proyeccion
        for (int m = 0; m < W; ++m) {
            for (int n = 0; n < H; ++n) {
                final Ray ray = rg.getRay(m,n);
                // Para el fisheye, cuando el rayo esta fuera de la esfera
                // (ray = null), pitamos como color de fondo
                if (ray == null) {
                    putPixel(m, n, backgroundColor);
                } else {
                //System.out.println(ray);
                    final Hit hit = scene.intersect(ray, tmin);
                    if (hit.hits()) {
                        final Material mat = hit.getMaterial();
                        final Point3D P = hit.getPoint();
                        final Vector3D normal = hit.getNormal();
                        final Point3D V = ray.getR();
                        final Color c = mat.getColor(scene, L, P, normal, V);
                        //Un intento de dar mas color a los puntos donde el rayo
                        // sea mas directo, mas parecido a la normal
                        /*Vector3D v = new Vector3D(hit.getPoint(), ray.getR());
                        v.normalize();
                        float angle = v.dotProduct(hit.getNormal());
                        c = new Color((int) (c.getRGB()));*/
                        putPixel(m,n, c);
                    } else {
                        putPixel(m,n, backgroundColor);
                    }
                }
            }
        }
    }
    
    private void putPixel(int m, int n, Color c) {
        bufferedImage.setRGB(m, n, c.getRGB());
    }
    
    public BufferedImage getBufferedImage() {
        return this.bufferedImage;
    }
    
}
   
