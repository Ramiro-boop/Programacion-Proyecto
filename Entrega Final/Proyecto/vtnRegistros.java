package Proyecto;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowStateListener;
import java.net.URL;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class vtnRegistros extends JFrame {

	Logica gestor = new Logica();

	public vtnRegistros() {
		this.setTitle("Registros");
		this.setSize(1080, 720);
		this.setLocationRelativeTo(null);
		URL iconURL = getClass().getResource("/Proyecto/logo.png");
		ImageIcon icon = new ImageIcon(iconURL);
		this.setIconImage(icon.getImage());

		JPanel panelariba = new JPanel(new BorderLayout());

		JPanel panelCentro = new JPanel(new BorderLayout());

		JLabel text = new JLabel("REGISTROS", SwingConstants.CENTER);
		text.setSize(30, 40);
		text.setFont(new Font("Arial", Font.BOLD, 25));

		panelariba.add(text, BorderLayout.CENTER);

		String[] ColumnasRegistros = { "IdRegistro", "Acción", "Fecha", "Hora" };// ,"Cant. Prestados"
		DefaultTableModel modeloRegistros = new DefaultTableModel(null, ColumnasRegistros);

		JTable tablaRegistros = new JTable(modeloRegistros);
		tablaRegistros.setBackground(Color.white);
		tablaRegistros.getTableHeader().setReorderingAllowed(false);
		tablaRegistros.setDefaultEditor(Object.class, null); // No editable

		// Ajustes de tamaño
		tablaRegistros.setRowHeight(28); // Alto de fila

		// Ancho preferido de columnas
		tablaRegistros.getColumnModel().getColumn(0).setPreferredWidth(50); // IdRegistro
		tablaRegistros.getColumnModel().getColumn(1).setPreferredWidth(300); // Acción
		tablaRegistros.getColumnModel().getColumn(2).setPreferredWidth(150); // Fecha
		tablaRegistros.getColumnModel().getColumn(3).setPreferredWidth(150); // Hora

		// Tamaño visible del JTable dentro del JScrollPane
		tablaRegistros.setPreferredScrollableViewportSize(new Dimension(800, 400));
		tablaRegistros.setFillsViewportHeight(true);

		JScrollPane panelDesplazableRegistros = new JScrollPane(tablaRegistros);
		panelDesplazableRegistros.setBackground(Color.white);

		panelCentro.add(panelDesplazableRegistros);

		gestor.ListarRegistros(modeloRegistros);

		this.setLayout(new BorderLayout());
		this.add(panelariba, BorderLayout.NORTH);
		this.add(panelCentro, BorderLayout.CENTER);

	}

}
