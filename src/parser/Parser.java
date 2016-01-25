package parser;

import java.awt.Color;
import java.io.BufferedReader;
import java.util.StringTokenizer;
import lights.Light;
import lights.Lights;
import lights.SpotLight;
import materials.Material;
import materials.PhongMaterial;
import materials.PhongMaterial_esp;

import projection.Projection;
import projection.Ortographic;
import projection.Perspective;
import projection.SemisphericalFisheye;
import projection.Camera;
import primitives.Group;
import primitives.Object3D;
import utils.Point3D;
import primitives.Sphere;
import primitives.Triangle;
import reflectances.Horn;
import utils.Vector3D;

public class Parser {

  // ATRIBUTOS
  BufferedReader in;

  // METODOS
  public Parser (final BufferedReader in) {
    this.in = in;
  }

  public Color parseBackgroundColor () throws Exception {

    // Iniciar lectura
    in.readLine();

    String line;
            
    // Leer color
    line = in.readLine();
    final StringTokenizer s = new StringTokenizer(line);    
    s.nextToken();
    final int r = Integer.parseInt(s.nextToken());
    final int g = Integer.parseInt(s.nextToken());
    final int b = Integer.parseInt(s.nextToken());

    final Color color = new Color(r, g, b);

    line = in.readLine();

    return color;
  }

  /**
   * Lee la posicion de la camara, su vector de direccion y vertical auxiliar y
   * devuelve un objeto de camara.
   *
   */
  public Camera parseCamera() throws Exception {

    // Iniciar lectura
    in.readLine();
    
    StringTokenizer s;
    String line;
    float x, y, z;    

    // Leer posici�n
    line = in.readLine();
    s = new StringTokenizer(line);
    s.nextToken();
    x = Float.parseFloat(s.nextToken());
    y = Float.parseFloat(s.nextToken());
    z = Float.parseFloat(s.nextToken());
    Point3D cameraPos = new Point3D(x, y, z);

    // Leer direcci�n
    line = in.readLine();
    s = new StringTokenizer(line);
    s.nextToken();
    x = Float.parseFloat(s.nextToken());
    y = Float.parseFloat(s.nextToken());
    z = Float.parseFloat(s.nextToken());
    Point3D look = new Point3D(x, y, z);

    // Leer vector vertical auxiliar
    line = in.readLine();
    s = new StringTokenizer(line);
    s.nextToken();
    x = Float.parseFloat(s.nextToken());
    y = Float.parseFloat(s.nextToken());
    z = Float.parseFloat(s.nextToken());
    Vector3D up = new Vector3D(x, y, z);

    // Finalizar lectura
    line = in.readLine();

    return new Camera(cameraPos, new Vector3D(cameraPos, look), up);
  }

  /**
   * Lee el tipo de proyecci�n, y en funci�n del tipo, las caracter�sticas de la
   * misma, devolviendo un objeto de tipo Projection
   *
   */
  public Projection parseProjection() throws Exception {

    String line = in.readLine();
    StringTokenizer s = new StringTokenizer(line);
    
    final String tipo = s.nextToken();
    switch (tipo) {
      case "Perspective":
      {
        // Leer distancia
        line = in.readLine();
        s = new StringTokenizer(line);
        s.nextToken();
        float distance = Float.parseFloat(s.nextToken());
        
        // Leer fov
        line = in.readLine();
        s = new StringTokenizer(line);
        s.nextToken();
        float fov = Float.parseFloat(s.nextToken());
        
        // Leer aspecto
        line = in.readLine();
        s = new StringTokenizer(line);
        s.nextToken();
        float aspect = Float.parseFloat(s.nextToken());
        
        // Finalizar lectura
        line = in.readLine();
        return new Perspective(distance, fov, aspect);
      }
      case "Ortographic":
      {       
        // Leer anchura
        line = in.readLine();
        s = new StringTokenizer(line);
        s.nextToken();
        float width = Float.parseFloat(s.nextToken());
        
        // Leer altura
        line = in.readLine();
        s = new StringTokenizer(line);
        s.nextToken();
        float height = Float.parseFloat(s.nextToken());
        
        // Finalizar lectura
        line = in.readLine();
        return new Ortographic(width, height);
      }
      case "SemisphericalFisheye":
      {
        // Leer distancia
        line = in.readLine();
        s = new StringTokenizer(line);
        s.nextToken();
        float distance = Float.parseFloat(s.nextToken());
        
        // Finalizar lectura
        line = in.readLine();
        
        return new SemisphericalFisheye(distance);
        
      }/*
      case "AngularFisheye":
      {
        // Leer distancia
        line = in.readLine();
        s = new StringTokenizer(line);
        s.nextToken();
        float distance = Float.parseFloat(s.nextToken());
        
        // Leer angulo
        line = in.readLine();
        s = new StringTokenizer(line);
        s.nextToken();
        float angle = Float.parseFloat(s.nextToken());
        
        // Finalizar lectura
        line = in.readLine();
        
        return new AngularFisheye(distance, angle);
      }*/
    }
    
    return null;

  }

  /**
   * Lee un grupo de objetos cuyo número está indicado en la primera línea.
   *
   * TODO Comprobar si hay tantos objetos como se indica.
   */
  public Group parseGroup() throws Exception {

    String line = in.readLine();

    // Leer n�mero de elementos
    line = in.readLine();
    StringTokenizer s = new StringTokenizer(line);
    s.nextToken();
    int numElements = Integer.parseInt(s.nextToken());
    Group g = new Group(numElements);

    // Leer los elementos tantas veces como se haya le�do anteriormente
    for (int j = 0; j < numElements; ++j) {
      g.addObject(parseElement());
    }

    return g;
  }

  /**
   * Devuelve una esfera que tiene centro, radio y color definidos.
   * @throws java.lang.Exception
   */
  public Object3D parseElement() throws Exception {
    
    String line = in.readLine();
    StringTokenizer s = new StringTokenizer(line);
    
    final String tipo = s.nextToken();
    switch (tipo) {
      case "Sphere":
      {
       
        // Leer posición
        line = in.readLine();
        s = new StringTokenizer(line);
        s.nextToken();
        float x = Float.parseFloat(s.nextToken());
        float y = Float.parseFloat(s.nextToken());
        float z = Float.parseFloat(s.nextToken());
        
        Point3D center = new Point3D(x, y, z);
        
        // Leer radio
        line = in.readLine();
        s = new StringTokenizer(line);
        s.nextToken();
        final float radius = Float.parseFloat(s.nextToken());
        
        // Leer color
        /*line = in.readLine();
        s = new StringTokenizer(line);
        s.nextToken();
        final int cx = Integer.parseInt(s.nextToken());
        final int cy = Integer.parseInt(s.nextToken());
        final int cz = Integer.parseInt(s.nextToken());
        
        Color color = new Color(cx, cy, cz);
        
        Horn horn = new Horn(2);
        PhongMaterial_esp mat = new PhongMaterial_esp(0.5f, 2f, 0.5f, color, horn, true);*/
        Material mat = parseMaterial();
        
        // Finalizar lectura
        line = in.readLine();
        
        return new Sphere(center, radius, mat);
        
      }
      case "Triangle":
      {
       
        // Leer primer vertice
        line = in.readLine();
        s = new StringTokenizer(line);
        s.nextToken();
        float x = Float.parseFloat(s.nextToken());
        float y = Float.parseFloat(s.nextToken());
        float z = Float.parseFloat(s.nextToken());
        
        Point3D A = new Point3D(x, y, z);
        
        // Leer segundo vertice
        line = in.readLine();
        s = new StringTokenizer(line);
        s.nextToken();
        x = Float.parseFloat(s.nextToken());
        y = Float.parseFloat(s.nextToken());
        z = Float.parseFloat(s.nextToken());
        
        Point3D B = new Point3D(x, y, z);
        
        // Leer tercer vertice
        line = in.readLine();
        s = new StringTokenizer(line);
        s.nextToken();
        x = Float.parseFloat(s.nextToken());
        y = Float.parseFloat(s.nextToken());
        z = Float.parseFloat(s.nextToken());
        
        Point3D C = new Point3D(x, y, z);
        
        // Leer color
        line = in.readLine();
        s = new StringTokenizer(line);
        s.nextToken();
        final int cx = Integer.parseInt(s.nextToken());
        final int cy = Integer.parseInt(s.nextToken());
        final int cz = Integer.parseInt(s.nextToken());
        
        Color color = new Color(cx, cy, cz);
        
        Horn horn = new Horn(3);
        PhongMaterial mat = new PhongMaterial(0.5f, 0.3f, 0.2f, color, horn);
        
        // Finalizar lectura
        line = in.readLine();
        
        return new Triangle(A, B, C, mat);
      }
      case "Group":
      {
        // Leer número de elementos
        line = in.readLine();
        s = new StringTokenizer(line);        
        s.nextToken();
        int numElements = Integer.parseInt(s.nextToken());
        Group g = new Group(numElements);

        // Leer los elementos tantas veces como se haya le�do anteriormente
        for (int j = 0; j < numElements; ++j) {
          g.addObject(parseElement());
        }
        
        // Finalizar lectura
        line = in.readLine();
        
        return g;
      }      
    }
    
    return null;
    
  }
  
  public Material parseMaterial() throws Exception {
      
        String line = in.readLine();
        StringTokenizer s = new StringTokenizer(line);

        final String tipo = s.nextToken();
        switch (tipo) {
            case "Phong":
                // ka
                line = in.readLine();
                s = new StringTokenizer(line);
                s.nextToken();
                float ka = Float.parseFloat(s.nextToken());
                // ks
                line = in.readLine();
                s = new StringTokenizer(line);
                s.nextToken();
                float ks = Float.parseFloat(s.nextToken());
                // kd
                line = in.readLine();
                s = new StringTokenizer(line);
                s.nextToken();
                float kd = Float.parseFloat(s.nextToken());
                // especular
                line = in.readLine();
                s = new StringTokenizer(line);
                s.nextToken();
                int especular = Integer.parseInt(s.nextToken());
                // Leer color
                line = in.readLine();
                s = new StringTokenizer(line);
                s.nextToken();
                final int cx = Integer.parseInt(s.nextToken());
                final int cy = Integer.parseInt(s.nextToken());
                final int cz = Integer.parseInt(s.nextToken());
                
                // Finalizar lectura
                in.readLine();

                Color color = new Color(cx, cy, cz);
                Horn h = new Horn(2);
                return new PhongMaterial_esp(ka, ks, kd, color, h, especular==1);
        }
                
      return null;
  }
  
  
  public Light parseLight() throws Exception {
      
      String line = in.readLine();
      
      // Posicion
      line = in.readLine();
      StringTokenizer s = new StringTokenizer(line);
      s.nextToken();
      Point3D pos = new Point3D(Float.parseFloat(s.nextToken()), Float.parseFloat(s.nextToken()), Float.parseFloat(s.nextToken()));
      
      // Punto de vista
      line = in.readLine();
      s = new StringTokenizer(line);
      s.nextToken();
      Point3D view = new Point3D(Float.parseFloat(s.nextToken()), Float.parseFloat(s.nextToken()), Float.parseFloat(s.nextToken()));
      // Potencia
      line = in.readLine();
      s = new StringTokenizer(line);
      s.nextToken();
      float power = Float.parseFloat(s.nextToken());
      // Angulo en grados
      line = in.readLine();
      s = new StringTokenizer(line);
      s.nextToken();
      float angle = Float.parseFloat(s.nextToken());
      in.readLine();
      return new SpotLight(pos, power, view, (float) Math.toRadians(angle));
  }
  
    public Lights parseLights() throws Exception {

        String line = in.readLine();

        // Leemos poder ambiental
        line = in.readLine();
        StringTokenizer s = new StringTokenizer(line);
        s.nextToken();
        float ambiental = Float.parseFloat(s.nextToken());
        // Leer numero de elementos
        line = in.readLine();
        s = new StringTokenizer(line);
        s.nextToken();
        int numElements = Integer.parseInt(s.nextToken());
        Lights l = new Lights(ambiental);

        // Leer los elementos tantas veces como se haya le�do anteriormente
        for (int j = 0; j < numElements; ++j) {
          l.addLight(parseLight());
        }
        
        in.readLine();

        return l;
    }
  
}