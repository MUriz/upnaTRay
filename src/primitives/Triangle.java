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
import utils.Matrix3x3;
import utils.Point3D;
import utils.Vector3D;

/**
 *
 * @author linux1
 */
public class Triangle extends Object3D{

    private final Point3D A;
    private final Vector3D AB;
    private final Vector3D AC;
    private final Vector3D n;
    
    public Triangle(final Point3D A, final Point3D B, final Point3D C, Material material) {
        super(material);
        this.A = A;
        this.AB = new Vector3D(A,B);
        this.AC = new Vector3D(A,C);
        n = AB.crossProduct(AC);
    }

    @Override
    public Hit intersect(Ray r, float tmin) {
        float c = r.getV().dotProduct(n);
        if (c < 0) {
            float b = (new Vector3D(A, r.getR())).dotProduct(n);
            if (b >= 0) {
                //float a = -b / c;
                final Vector3D AR = new Vector3D(this.A, r.getR());
                final Vector3D res = solveCramer(AB, AC, r.getV().getOposite(), AR);
                float a = res.getZ();
                b = res.getX();
                c = res.getY();
                //Calcular nuevos b y c
                if ((b>=0) && (b <= 1)) {
                    if ((c >= 0) && (c <= 1)){
                        if ((b+c) <= 1){
                            final Point3D p_int = new Point3D(a,b,c);
                            //final float t = (new Vector3D(r.getR(), p_int)).getNorm();
                            if (a < tmin) {
                                return Hit.getVoidHit();
                            } else {
                                return new Hit(a, p_int, n, this.getMaterial());
                            }
                        }
                    }
                }
            }
        }
        return Hit.getVoidHit();
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    // Este metodo no se utiliza, ya que es mas lento
    // Pero resuelve el sistema de ecuaciones utilizando la inversa de la matriz
    // AX = B
    // (Ainv)AX = (Ainv)B
    // IX = (Ainv)B
    // X = (Ainv)B
    // Solo tendriamos que multiplicar la inversa de A (los valores de las incognitas)
    // por el vector de resultados.
    private Vector3D solveInv(Vector3D m_col1, Vector3D m_col2, Vector3D m_col3, Vector3D res) {
        
        //Matriz col1 => m_col1
        //       col2 => m_col2
        //       col3 => m_col3
        //Solucion
        //       col1 => res
        //Calcular la inversa de la matriz
        final Matrix3x3 m = new Matrix3x3(m_col1, m_col2, m_col3);
        m.invert();
        //Multiplicar esta inversa por la solucion
        //Obtenemos una matriz de 1 columna y 3 filas
        //donde el primer elemento es beta, segundo gamma y tercero alpha
        return m.mulCol(res);
        
    }
    
    // Resolver el sistema de ecuaviones mediante el sistema de cramer
    private Vector3D solveCramer(Vector3D m_col1, Vector3D m_col2, Vector3D m_col3, Vector3D res) {
        
        //Matriz col1 => m_col1
        //       col2 => m_col2
        //       col3 => m_col3
        //Solucion
        //       col1 => resz
        //Creamos la matriz y calculamos el determinante
        final Matrix3x3 m = new Matrix3x3(m_col1, m_col2, m_col3);
        final float det_m = m.determinant();
        //Ahora cambiamos a la matriz la primera columna por la solucion
        //y calculamos el determinante y dividimos por el determinante de m
        final float x = (Matrix3x3.setColumn(0, m, res)).determinant()/det_m;
        // lo mismo para la segunda y tercera columna
        final float y = (Matrix3x3.setColumn(1, m, res)).determinant()/det_m;
        final float z = (Matrix3x3.setColumn(2, m, res)).determinant()/det_m;
        //Ahora que tenemos las soluciones, devolvemos un vector3D con estos valores
        return new Vector3D(x,y,z);
        
    }
    
}
