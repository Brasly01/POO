package logicaof;
import org.jfree.chart.*;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.PiePlot;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.ui.ApplicationFrame;
import org.jfree.ui.RefineryUtilities;
import javax.swing.JFrame;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.input.SAXBuilder;
import org.jdom2.JDOMException;
import java.io.File;
import java.io.IOException;
import java.util.*;
import org.jfree.chart.plot.RingPlot;  // Necesario para usar RingPlot en JFreeChart
import org.jfree.chart.plot.Plot;       // Necesario para usar Plot en JFreeChart
import org.jfree.chart.plot.PlotOrientation;  // Necesario para usar PlotOrientation en JFreeChart
import org.jfree.data.general.DefaultPieDataset;  // Necesario para usar DefaultPieDataset en JFreeChart
import org.jfree.chart.plot.PiePlot;  // Necesario para usar PiePlot en JFreeChart
import org.jdom2.Document;           // Necesario para trabajar con documentos XML utilizando JDOM
import org.jdom2.Element;            // Necesario para trabajar con elementos XML utilizando JDOM
import org.jdom2.input.SAXBuilder;   // Necesario para construir un árbol XML desde un archivo o flujo
import org.jdom2.JDOMException;     // Necesario para manejar excepciones de JDOM
import java.util.Map;               // Necesario para utilizar el tipo de datos Map
import java.awt.Dimension;
import java.awt.Color;


public class LeerXML {
    // Crear una lista de números únicos
    List<Integer> numeros = new ArrayList<>();

    // Crear una lista de tipos de juego
    List<String> tiposDeJuego = new ArrayList<>(); 
    public void lectura(String letra){
        // Especifica la ruta al archivo XML
        String ruta = "C:/Users/Usuario/Desktop/BingoData/partidas.xml"; // Reemplaza con la ruta correcta
        try {
            // Crea un analizador XML (SAXBuilder)
            SAXBuilder builder = new SAXBuilder();

            // Parsea el archivo XML y lo carga en un objeto Document
            Document document = builder.build(new File(ruta));

            // Obtén la lista de elementos "partida"
            List<Element> partidas = document.getRootElement().getChildren("partida");

            

            // Itera a través de las partidas para obtener la lista de números cantados y tipos de juego
            for (Element  partida : partidas) {
                List<Integer> numerosCantados = parseNumerosCantados(partida.getChildText("númerosCantados"));
                numeros.addAll(numerosCantados);

                // Obtener el tipo de juego y guardarlo en la lista de tipos de juego
                String tipoJuego = partida.getChildText("tipo");
                tiposDeJuego.add(tipoJuego);
            } 
            // Imprimir los tipos de juego
            //System.out.println("Tipos de Juego: " + tiposDeJuego);
            // Comprueba el valor del parámetro "letra"
             if (letra.equals("A")) {
             pt2();
             } 
             else if(letra.equals("B")) {
             pt3();
            }
           }
           catch (JDOMException | IOException e) {
            e.printStackTrace();
          }
        }
        
    private static List<Integer> parseNumerosCantados(String numerosCantados) {
        List<Integer> numeros = new ArrayList<>();
        
        // Elimina los corchetes "[" y "]" y luego divide la cadena en números
        numerosCantados = numerosCantados.replace("[", "").replace("]", "");
        String[] numerosArray = numerosCantados.split(",");

        for (String numeroStr : numerosArray) {
            int numero = Integer.parseInt(numeroStr.trim());
            numeros.add(numero);
        }
        return numeros;
      }
      
    public void pt2() {
     // Contar la frecuencia de cada número
     Map<Integer, Integer> numeroFrecuencia = new HashMap<>();
     for (Integer numero : numeros) {
        numeroFrecuencia.put(numero, numeroFrecuencia.getOrDefault(numero, 0) + 1);
     }

     // Crear una lista de números ordenados por el recuento de ocurrencias
     List<Map.Entry<Integer, Integer>> sortedNumeros = new ArrayList<>(numeroFrecuencia.entrySet());
     sortedNumeros.sort((e1, e2) -> e2.getValue().compareTo(e1.getValue()));

     // Tomar los 10 números más repetidos
     List<Integer> top10 = new ArrayList<>();
     for (int i = 0; i < Math.min(10, sortedNumeros.size()); i++) {
        top10.add(sortedNumeros.get(i).getKey());
     }

     // Imprimir la frecuencia de cada número en top10
     //for (int numero : top10) {
        //int frecuencia = numeroFrecuencia.get(numero);
        //System.out.println("Número: " + numero + ", Frecuencia: " + frecuencia);
     
     crearGraficoBarras(top10, numeroFrecuencia);
   }
   
   public void pt3() {
    // Crear un mapa para contar las ocurrencias de letras
    Map<Character, Integer> letraFrecuencia = new HashMap<>();

    // Iterar a través de la lista de tipos de juego
    for (String tipoJuego : tiposDeJuego) {
        // Iterar a través de cada letra en el tipo de juego
        for (char letra : tipoJuego.toCharArray()) {
            // Verificar si la letra es 'X', 'Z', 'L' o 'E'
            if (letra == 'X' || letra == 'Z' || letra == 'L' || letra == 'E') {
                letraFrecuencia.put(letra, letraFrecuencia.getOrDefault(letra, 0) + 1);
            }
        }
    }

    // Llama al método para crear un gráfico circular
    crearGraficoCircular(letraFrecuencia);
  }


   
   private void crearGraficoBarras(List<Integer> top10, Map<Integer, Integer> numeroFrecuencia) {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();

        for (int numero : top10) {
            dataset.addValue(numeroFrecuencia.get(numero), "Frecuencia", String.valueOf(numero));
        }

        JFreeChart barChart = ChartFactory.createBarChart(
            "Top 10 Números más Cantados",
            "Número",
            "Frecuencia",
            dataset,
            PlotOrientation.VERTICAL,
            false,
            true,
            false
        );
        
        
        // Crear un panel de gráficos y mostrar el gráfico en una ventana
        ChartPanel chartPanel = new ChartPanel(barChart);
        chartPanel.setPreferredSize(new java.awt.Dimension(800, 600));
        barChart.setBackgroundPaint(Color.WHITE);
        JFrame frame = new JFrame("Gráfico de Barras");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.getContentPane().add(chartPanel);
        frame.pack();
        frame.setVisible(false);

        // Guardar el gráfico como archivo PNG
        try {
            ChartUtilities.saveChartAsPNG(new File("grafico_top10.png"), barChart, 400, 400);
            //System.out.println("Gráfico de top 10 guardado como grafico_top10.png");
        } catch (IOException e) {
            e.printStackTrace();
        }
    

     }
  
   public static void crearGraficoCircular(Map<Character, Integer> letraFrecuencia) {
        DefaultPieDataset dataset = new DefaultPieDataset();

        for (char letra : letraFrecuencia.keySet()) {
            dataset.setValue(String.valueOf(letra), letraFrecuencia.get(letra));
        }

        JFreeChart pieChart = ChartFactory.createPieChart(
                "Frecuencia de Letras X, Z, L, y E",
                dataset,
                true,
                true,
                false
        );
        
        
        // Crear un panel de gráficos y mostrar el gráfico circular en una ventana
        ChartPanel chartPanel = new ChartPanel(pieChart);
        chartPanel.setPreferredSize(new Dimension(600, 400));
        pieChart.setBackgroundPaint(Color.WHITE);
        JFrame frame = new JFrame("Gráfico Circular");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.getContentPane().add(chartPanel);
        frame.pack();
        frame.setVisible(false);

        // Guardar el gráfico circular como archivo PNG
        try {
            ChartUtilities.saveChartAsPNG(new File("grafico_circular.png"), pieChart, 800, 600);
            //System.out.println("Gráfico circular guardado como grafico_circular.png");
        } catch (IOException e) {
            e.printStackTrace();
        }
     }
}
