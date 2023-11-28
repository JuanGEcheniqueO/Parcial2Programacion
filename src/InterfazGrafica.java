import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class InterfazGrafica extends JFrame {

    private final TableroDeJuego tablero;
    private final JButton[][] botones;

    public InterfazGrafica(Jugador jugador1, Jugador jugador2) {
        tablero = new TableroDeJuego(jugador1, jugador2);
        botones = new JButton[3][3];

        // Configuración de la ventana
        setTitle("Ta Te Ti");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(300, 300);
        setLayout(new GridLayout(3, 3));

        // Crea botones y los agrega al tablero
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                botones[i][j] = new JButton("");
                botones[i][j].setFont(new Font("Arial", Font.PLAIN, 40));
                botones[i][j].addActionListener(new BotonListener(i, j));
                add(botones[i][j]);
            }
        }

        setVisible(true);
    }

    private class BotonListener implements ActionListener {
        private final int fila;
        private final int columna;

        public BotonListener(int fila, int columna) {
            this.fila = fila;
            this.columna = columna;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            Jugador jugadorActual = (tablero.getTurno() % 2 == 0) ? tablero.getJugador1() : tablero.getJugador2();

            if (jugadorActual instanceof Jugador) {
                jugadorActual.ponerFicha(fila, columna, tablero);
                botones[fila][columna].setText(String.valueOf(tablero.obtenerSimboloJugador(jugadorActual)));
            }

            if (tablero.hayGanador()) {
                JOptionPane.showMessageDialog(InterfazGrafica.this, "¡" + jugadorActual.nombre() + " ha ganado!");
                reiniciarJuego();
            } else if (tablero.tableroLleno()) {
                JOptionPane.showMessageDialog(InterfazGrafica.this, "¡Empate!");
                reiniciarJuego();
            } else {
                // Cambiar al siguiente turno
                tablero.incrementarTurno();
                System.out.println("Turno después de incrementar: " + tablero.getTurno());
            }
        }
    }

    private void reiniciarJuego() {
        int opcion = JOptionPane.showConfirmDialog(InterfazGrafica.this, "¿Quieres jugar otra vez?", "Fin del juego", JOptionPane.YES_NO_OPTION);
        if (opcion == JOptionPane.YES_OPTION) {
            // Reiniciar el juego
            tablero.reiniciarTablero();
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    botones[i][j].setText("");
                }
            }
        } else {
            // Cerrar la aplicación
            System.exit(0);
        }
    }
}