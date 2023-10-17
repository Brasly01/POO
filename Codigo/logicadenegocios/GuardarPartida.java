package logicaof;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class GuardarPartida extends Ganador {
    private String configuracion;

    public void guardarPartida(
        String todoNumero, 
        char tipoJuego, 
        List<String> ganadores) {
        
        // Obtener la fecha y hora actuales
        LocalDate fechaActual = LocalDate.now();
        LocalTime horaActual = LocalTime.now();

        // Formatear la fecha y la hora en cadenas (Strings)
        DateTimeFormatter formatoFecha = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        DateTimeFormatter formatoHora = DateTimeFormatter.ofPattern("HH:mm:ss");

        String fechaComoString = fechaActual.format(formatoFecha);
        String horaComoString = horaActual.format(formatoHora);
        String numerosComoString = "";
        String ganadorComoString = "";

        //for (int numero : todoNumero) {
            //numerosComoString += String.valueOf(numero);
        //}

        for (String ganadorCedula : ganadores) {
            ganadorComoString += ganadorCedula;
            System.out.println("Ganadores: " + ganadorCedula);
        }
        String pJuego = Character.toString(tipoJuego);
        // Llamar a un método para agregar los datos al archivo XML (no se proporciona en el código actual)
        WriteXML.addXMLRecord(pJuego, todoNumero, ganadorComoString, fechaComoString, horaComoString);
    }
}
