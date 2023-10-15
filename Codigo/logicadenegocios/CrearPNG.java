import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class CrearPNG {
    

    public static void main(String[] args) {
        // Dimensiones de la imagen
        String imagenx="Bingo1";
        imagenx += ".png";
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
        g2d.setColor(Color.BLACK);
        Font fuente = new Font("Arial", Font.BOLD, 55);
        g2d.setFont(fuente);
        // Texto que deseas escribir en la imagen
        String texto = "B     I     N     G     O";
        // Coordenadas donde se escribirá el texto
        int x = 50;
        int y = 100;
        // Dibuja el texto en la imagen
        g2d.drawString(texto, x, y);
         
        
        // matriz
        Font fuente1 = new Font("Arial", Font.BOLD, 20);
        g2d.setFont(fuente1);
        
        String matriz = "1   2    3   4   5";
        
        int x1= 50;
        int y1= 300;
        
        g2d.drawString(matriz, x1, y1);
        
        // Libera los recursos del objeto Graphics2D
        g2d.dispose();
        

        
        
        
        

        // Guarda la imagen como un archivo PNG
        try {
            File archivo = new File(imagenx);
            ImageIO.write(imagen, "png", archivo);
            System.out.println("Imagen creada con éxito.");
        } catch (IOException e) {
            System.err.println("Error al guardar la imagen: " + e.getMessage());
        }
    }
}

