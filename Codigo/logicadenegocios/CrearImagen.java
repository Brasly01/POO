package logicadenegocios;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class CrearImagen {
  private String identificador;
  private String nombreArchivo;
  private String linea1;
  private String linea2;
  private String linea3;
  private String linea4;
  private String linea5;
    
  public CrearImagen (CartonBingo pCarton, String pUbicacion) {
    identificador = pCarton.getIdentificador();
    nombreArchivo = pUbicacion + pCarton.getIdentificador() + ".png";
    lineasMatriz(pCarton.getMatriz());
    crearPng ();
  }
  
  public void agregarIdentificador(String pIdentificador) {
    identificador=pIdentificador;
  }
  
  public void agregarIdentificadorPng (String pIdentificador) {
    pIdentificador += ".png";
    nombreArchivo=pIdentificador;
  }
  
  public String getIdentificador () {
    return identificador;
  }
    
  public String getNombreArchivo () {
    return nombreArchivo;
  }
    
  public void lineasMatriz(int[][] pMatriz) {
    String fila1 = "";
    String fila2 = "";
    String fila3 = ""; 
    String fila4 = "";
    String fila5 = "";

    for (int fila = 0; fila < pMatriz.length; fila++) {
      int contador = 0;
      for (int columna = 0; columna < pMatriz[fila].length; columna++) {
        contador +=1;
        String elemento = String.valueOf(pMatriz[fila][columna]);
        if (contador == 1) {
          if (1 == elemento.length()) {
            fila1 += "   0" + elemento;
          }
          else {
            fila1 += "   " + elemento;
          }
        }
        
        if (contador == 2) {
          if (1 == elemento.length()) {
            fila2 += "   0" + elemento;
          }
          else {
            fila2 += "   " + elemento;
          }
        }
        
        if (contador == 3) {
          if (1 == elemento.length()) {
            fila3 += "   0" + elemento;
          }
          else {
            fila3 += "   " + elemento;
          } 
        }
        
        if (contador == 4) {
          if (1 == elemento.length()) {
            fila4 += "   0" + elemento;
          }
          else {
            fila4 += "   " + elemento;
          }
        }
        
        if (contador == 5) {
          if (1 == elemento.length()) {
            fila5 += "   0" + elemento;
          }
          else {
            fila5 += "   " + elemento;
          }
        }   
      }
    }
    
    agrgarLinea1(fila1);
    agrgarLinea2(fila2);
    agrgarLinea3(fila3);
    agrgarLinea4(fila4);
    agrgarLinea5(fila5);
    
  }
  
  public void agrgarLinea1(String pLinea){
    linea1 = pLinea;
  }
  
  public void agrgarLinea2(String pLinea){
    linea2 = pLinea;
  }
  
  public void agrgarLinea3(String pLinea){
    linea3 = pLinea;
  }
  
  public void agrgarLinea4(String pLinea){
    linea4 = pLinea;
  }
  
  public void agrgarLinea5(String pLinea){
    linea5 = pLinea;
  }
  
  public String getLinea1 () {
    return linea1;   
  }
  
  public String getLinea2 () {
    return linea2;   
  }
  
  public String getLinea3 () {
    return linea3;   
  }
  
  public String getLinea4 () {
    return linea4;   
  }
  
  public String getLinea5 () {
    return linea5;   
  }
  
  public void crearPng () {
    int ancho = 600;
    int alto = 600;
    // Crea una nueva imagen BufferedImage
    BufferedImage imagen = new BufferedImage(ancho, alto, BufferedImage.TYPE_INT_ARGB);
    // Obtiene el objeto Graphics2D de la imagen
    Graphics2D g2d = imagen.createGraphics();
    // Establece el fondo blanco
    g2d.setColor(Color.WHITE);
    g2d.fillRect(0, 0, ancho, alto);
    // Establece el color y la fuente para el texto
    g2d.setColor(Color.BLUE);
    Font fuente = new Font("Arial", Font.BOLD, 55);
    g2d.setFont(fuente);
    // Texto que deseas escribir en la imagen
    String texto = "B     I     N     G     O";
    // Coordenadas donde se escribirá el texto
    // Dibuja el texto en la imagen
    g2d.drawString("B", 75, 100);
    g2d.drawString("I", 200, 100);
    g2d.drawString("N", 275, 100);
    g2d.drawString("G", 375, 100);
    g2d.drawString("O", 475 , 100);
    
    g2d.setColor(Color.BLACK);
    Font fuente1 = new Font("Arial", Font.BOLD, 50);
    g2d.setFont(fuente1);
 
    // Dibuja el texto en la imagen
    g2d.drawString(getLinea1 (), 35, 175);
    
    g2d.drawString(getLinea2 (), 35, 250);
    
    g2d.drawString(getLinea3 (), 35, 325);
    
    g2d.drawString(getLinea4 (), 35, 400);
    
    g2d.drawString(getLinea5 (), 35, 475);
    
    g2d.setColor(Color.RED);
    
    g2d.drawString(getIdentificador (), 250, 550);
    
    g2d.setColor(Color.BLACK);
    g2d.drawLine(65, 50, 65, 500);
    g2d.drawLine(160, 50, 160, 500);
    g2d.drawLine(255, 50, 255, 500);
    g2d.drawLine(355, 50, 355, 500);
    g2d.drawLine(455, 50, 455, 500);
    g2d.drawLine(550, 50, 550, 500);
    

    g2d.drawLine(65,50, 550, 50);
    g2d.drawLine(65,115, 550, 115);
    g2d.drawLine(65,190, 550, 190);
    g2d.drawLine(65,270, 550, 270);
    g2d.drawLine(65,345, 550, 345);
    g2d.drawLine(65,415, 550, 415);
    g2d.drawLine(65,500, 550, 500);
    
    g2d.dispose();

    try {
      File archivo = new File(nombreArchivo);
      ImageIO.write(imagen, "png", archivo);
      System.out.println("Imagen creada con éxito.");
      }  catch (IOException e) {
         System.err.println("Error al guardar la imagen: " + e.getMessage());
      }  
  }

}
