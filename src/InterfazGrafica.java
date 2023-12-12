import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Objects;

public class InterfazGrafica extends JFrame {

    private TableroDeJuego tablero;
    private JButton[][] botones;
    private ContadorVictorias contadorJugador1;
    private ContadorVictorias contadorJugador2;

    public InterfazGrafica(Jugador jugador1, Jugador jugador2) {
        tablero = new TableroDeJuego(jugador1, jugador2);
        botones = new JButton[3][3];
        contadorJugador1 = new ContadorVictorias(jugador1.getNombre());
        contadorJugador2 = new ContadorVictorias(jugador2.getNombre());

        setTitle("Ta Te Ti");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 500);  // Ajusta el tamaño según tus preferencias
        setLayout(new BoxLayout(getContentPane(), BoxLayout.X_AXIS));  // Cambia el layout a BoxLayout horizontal

        // Panel izquierdo para el tablero
        JPanel panelTablero = new JPanel(new GridLayout(3, 3));

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                botones[i][j] = new JButton("");
                botones[i][j].setFont(new Font("Arial", Font.PLAIN, 40));
                botones[i][j].setPreferredSize(new Dimension(80, 80));
                botones[i][j].addActionListener(new BotonListener(i, j));
                panelTablero.add(botones[i][j]);
            }
        }

        // Panel derecho para los contadores
        JPanel panelContadores = new JPanel();
        panelContadores.setLayout(new BoxLayout(panelContadores, BoxLayout.Y_AXIS));
        panelContadores.add(Box.createVerticalGlue());  // Agrega espacio arriba
        panelContadores.add(contadorJugador1);
        panelContadores.add(Box.createRigidArea(new Dimension(0, 20)));  // Agrega espacio entre contadores
        panelContadores.add(contadorJugador2);
        panelContadores.add(Box.createVerticalGlue());  // Agrega espacio abajo

        // Agrega los paneles a la interfaz
        add(Box.createHorizontalGlue());  // Agrega espacio a la izquierda
        add(panelTablero);
        add(Box.createHorizontalStrut(20));  // Agrega espacio entre el tablero y los contadores
        add(panelContadores);
        add(Box.createHorizontalGlue());  // Agrega espacio a la derecha

        setVisible(true);
    }

    private class BotonListener implements ActionListener {
        private int fila;
        private int columna;

        public BotonListener(int fila, int columna) {
            this.fila = fila;
            this.columna = columna;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            Jugador jugadorActual = (tablero.getTurno() % 2 == 0) ? tablero.getJugador1() : tablero.getJugador2();

            if (jugadorActual != null) {
                jugadorActual.ponerFicha(fila, columna, tablero);
                botones[fila][columna].setText(String.valueOf(tablero.obtenerSimboloJugador(jugadorActual)));
            }

            if (tablero.hayGanador()) {
                assert jugadorActual != null;
                JOptionPane.showMessageDialog(InterfazGrafica.this, "¡" + (jugadorActual != null ? Objects.requireNonNull(jugadorActual).nombre() : null) + " ha ganado!");
                if (jugadorActual == tablero.getJugador1()) {
                    contadorJugador1.incrementarContador();
                } else {
                    contadorJugador2.incrementarContador();
                }
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