/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import javax.vecmath.Matrix3f;

/**
 *
 * @author mikel
 * Clase para calcular los determinantes para cramer
 */
public class Matrix3x3 extends Matrix3f{
    
    public Matrix3x3() {
        super();
    }
    
    public Matrix3x3(final Vector3D col1, final Vector3D col2, final Vector3D col3) {
        super(col1.getX(), col2.getX(), col3.getX(),
                col1.getY(), col2.getY(), col3.getY(),
                col1.getZ(), col2.getZ(), col3.getZ());
    }
    
    // Crear una matriz igual, excepto la columna col, sustituida por el vector v
    public static Matrix3x3 setColumn(final int col, final Matrix3x3 m, final Vector3D v) {
        
        final Matrix3x3 m_ret = new Matrix3x3();
        m_ret.set(m);
        m_ret.setColumn(col, v.getX(), v.getY(), v.getZ());
        return m_ret;
        
    }
    
    public Vector3D mulCol(final Vector3D col) {
        //Nos pasan una vector columna (3x1)
        //Devolvemos el vector del resultado de Matrix*col
        //Construimos una matriz con la primera columna
        //Igual a col, el resto a 0
        //Multiplicamos la matriz por esta nueva matriz
        //Y el resultado de la primera columna es el resultado a devolver
        final Matrix3f m_aux = new Matrix3f(col.getX(), 0, 0,
            col.getY(), 0, 0, col.getZ(), 0, 0);
        //Clonamos la matriz que tenemos
        final Matrix3f this_clone = new Matrix3f();
        this_clone.set(this);
        //Multiplicamos. El resultado se queda en this_clone
        this_clone.mul(m_aux);
        //Nos quedamos con la primera columna
        return new Vector3D(this_clone.getM00(), this_clone.getM10(), this_clone.getM20());
    }
    
}
