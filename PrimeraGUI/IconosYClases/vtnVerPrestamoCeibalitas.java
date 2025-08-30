package Proyecto;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class vtnVerPrestamoCeibalitas extends JFrame{

	public vtnVerPrestamoCeibalitas() {
		
		this.setTitle("Prestamos Libros");
		URL iconURL = getClass().getResource("/Proyecto/logo.png");
		ImageIcon icon = new ImageIcon(iconURL);
		this.setIconImage(icon.getImage());
		this.setExtendedState(MAXIMIZED_BOTH);
		
		JPanel panelArriba = new JPanel(new GridLayout(1,7));
		
		
		JPanel pArribaIzq = new JPanel(new FlowLayout(FlowLayout.LEFT));
		
		
		JPanel pArribaIzq2 = new JPanel(new FlowLayout(FlowLayout.LEFT));
		
		
		JPanel panelCentro = new JPanel();
		panelCentro.setLayout(new BorderLayout());
		
		
		ImageIcon imagenVolver = new ImageIcon(getClass().getResource("/Proyecto/Volver.png"));
		Image imagenVolver1 = imagenVolver.getImage().getScaledInstance(32, 32, Image.SCALE_SMOOTH);
		ImageIcon imagenVolver2 = new ImageIcon(imagenVolver1);
		
		JButton btnVolver = new JButton("Volver",imagenVolver2);
		btnVolver.setBackground(new Color(72,207,174));
		
		ImageIcon imagenEdit = new ImageIcon(getClass().getResource("/Proyecto/Editar.png"));
		Image imagenEdit1 = imagenEdit.getImage().getScaledInstance(15, 15, Image.SCALE_SMOOTH);
		ImageIcon imagenEdit2 = new ImageIcon(imagenEdit1);
		JButton btnEditar = new JButton("Editar", imagenEdit2);
		btnEditar.setBackground(new Color(72,207,174));
		
		ImageIcon imagenElimin = new ImageIcon(getClass().getResource("/Proyecto/Eliminar.png"));
		Image imagenElimin1 = imagenElimin.getImage().getScaledInstance(15, 15, Image.SCALE_SMOOTH);
		ImageIcon imagenElimin2 = new ImageIcon(imagenElimin1);
		JButton btnEliminar = new JButton("Eliminar",imagenElimin2);
		btnEliminar.setBackground(new Color(72,207,174));
		
		
		
		JLabel lbl1 = new JLabel("Prestamos Ceibalitas", SwingConstants.CENTER);
		lbl1.setOpaque(true);
		lbl1.setSize(30, 40);
		lbl1.setFont(new Font("Arial",Font.BOLD,18));
		
		JLabel lbl2 = new JLabel();
		
		JLabel lbl3 = new JLabel();
		
		JLabel lbl4 = new JLabel();
		
		JLabel lbl5 = new JLabel("Hora");
		

		
		
		
		DefaultTableModel modelo = new DefaultTableModel();
		modelo.addColumn("Número Ceibalita");
		modelo.addColumn("Nombre");
		modelo.addColumn("Apellido");
		modelo.addColumn("CI");
		modelo.addColumn("Fecha Inicio");
		modelo.addColumn("Hora");
		
		JTable tabla = new JTable(modelo);
		tabla.getTableHeader().setReorderingAllowed(false);
		JScrollPane panelDesplazable = new JScrollPane(tabla);
		
		
		this.add(panelArriba,BorderLayout.NORTH);
		
		panelArriba.add(pArribaIzq);//1,1
		
		
		
		pArribaIzq.add(btnVolver);
		
		
		
		panelArriba.add(pArribaIzq2);//1,2
		pArribaIzq2.add(btnEditar);
		pArribaIzq2.add(btnEliminar);
		
		panelArriba.add(lbl2);//1,3
		
		panelArriba.add(lbl1);//1,4
		
		
		panelArriba.add(lbl3);
		
		panelArriba.add(lbl4);
		
		panelArriba.add(lbl5);
		
		
	
		
		this.add(panelCentro,BorderLayout.CENTER);
		
		panelCentro.add(panelDesplazable);
		
		
		btnVolver.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				vtnVerPrestamoCeibalitas.this.dispose();
				
				
			}
		});
		
		btnEditar.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				vtnEditarPrestamoCeibalita vtn1 = new vtnEditarPrestamoCeibalita();
				vtn1.setVisible(true);
				
				
			}
		});
		
		btnEliminar.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {

				vtnConfirmarEliminacion vtn1 = new vtnConfirmarEliminacion();
				vtn1.setVisible(true);
				
				
			}
		});
		
	}
	
}
