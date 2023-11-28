import javax.swing.*;
import java.util.Scanner;
public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            Scanner scanner = new Scanner(System.in);

            System.out.println("Ingrese el nombre del Jugador 1:");
            String nombreJugador1 = scanner.nextLine();

            System.out.println("Ingrese el nombre del Jugador 2:");
            String nombreJugador2 = scanner.nextLine();

            Jugador jugador1 = new Jugador(nombreJugador1);
            Jugador jugador2 = new Jugador(nombreJugador2);

            new InterfazGrafica(jugador1, jugador2);
        });
    }
}