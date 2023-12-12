import javax.swing.*;
public class Main {
    public static void main(String[] args) {
        String nombreJugador1 = JOptionPane.showInputDialog("Ingrese el nombre del Jugador 1:");
        String nombreJugador2 = JOptionPane.showInputDialog("Ingrese el nombre del Jugador 2:");

        if (nombreJugador1 == null || nombreJugador2 == null || nombreJugador1.trim().isEmpty() || nombreJugador2.trim().isEmpty()) {
            // El usuario cerró el cuadro de diálogo o ingresó nombres vacíos, puedes manejarlo según tus necesidades.
            System.exit(0);
        }

        SwingUtilities.invokeLater(() -> {
            Jugador jugador1 = new Jugador(nombreJugador1);
            Jugador jugador2 = new Jugador(nombreJugador2);

            new InterfazGrafica(jugador1, jugador2);
        });
    }
}