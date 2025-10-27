package Proyecto;



import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class DiseñoJSpinner {

    /**
     * Configura un JSpinner para:
     * - Permitir solo números.
     * - Cambiar el color de fondo y texto.
     * 
     * 
     */
    public static void aplicarEstiloNumerico(JSpinner spinner, Color colorFondo, Color colorTexto) {
        // Si el spinner no tiene modelo numérico, le asignamos uno básico
        if (!(spinner.getModel() instanceof SpinnerNumberModel)) {
            spinner.setModel(new SpinnerNumberModel(0, 0, 100, 1));
        }

        // Obtener el campo de texto interno del spinner
        JFormattedTextField txt = ((JSpinner.NumberEditor) spinner.getEditor()).getTextField();

        // Bloquear letras
        txt.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                char c = e.getKeyChar();
                if (!Character.isDigit(c)) e.consume();
            }
        });

        // Cambiar colores
        txt.setBackground(colorFondo);
        txt.setForeground(colorTexto);

        // Opcional: cambiar fuente
        txt.setFont(new Font("Arial", Font.BOLD, 14));
    }
}

