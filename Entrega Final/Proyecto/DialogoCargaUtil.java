package Proyecto;

import java.awt.*;
import javax.swing.*;

 class DialogoCargaUtil {

    public static JDialog mostrarDialogoCarga(JFrame parent) {
        JDialog dialogoCarga = new JDialog(parent);
        dialogoCarga.setUndecorated(true); // sin título ni bordes
        dialogoCarga.setBackground(new Color(0, 0, 0, 0)); // totalmente transparente
        dialogoCarga.setLayout(new GridBagLayout()); // para centrar el texto

        // JLabel con el texto
        JLabel lblCargando = new JLabel("Cargando...");
        lblCargando.setFont(new Font("Arial", Font.BOLD, 20));
        lblCargando.setForeground(Color.BLACK); // color del texto
        lblCargando.setOpaque(false); // sin fondo

        dialogoCarga.add(lblCargando);

        dialogoCarga.pack();
        dialogoCarga.setLocationRelativeTo(parent); // centrar sobre el JFrame

        dialogoCarga.setVisible(true);

        return dialogoCarga;
    }

     public static void cerrarDialogoCarga(JDialog dialogo) {
        if (dialogo != null && dialogo.isVisible()) {
            dialogo.dispose();
        }
    }
}
