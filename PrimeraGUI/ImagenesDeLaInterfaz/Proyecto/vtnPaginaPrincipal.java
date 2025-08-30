package Proyecto;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import java.awt.*;
import java.awt.event.*;
import java.net.URL;

public class vtnPaginaPrincipal extends JFrame {

	Gestor logica = new Gestor();

	public vtnPaginaPrincipal() {

		this.setTitle("Menu principal");//Establece el título de la ventana
	    this.setSize(600, 400);//Tamaño en PX. Primero el ancho y luego el largo
	    URL iconURL = getClass().getResource("/Proyecto/logo.png");
	    ImageIcon icon = new ImageIcon(iconURL);
	    this.setIconImage(icon.getImage());

	    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//Acción default al cerrar

	    this.setExtendedState(MAXIMIZED_BOTH);
		
		// JPanel panelPrueba = new JPanel();

		JPanel pArriba = new JPanel(new GridLayout(1, 7));
		// pArriba.setLayout(new FlowLayout(FlowLayout.LEFT));
		pArriba.setBackground(Color.WHITE);

		JPanel pArribaIzq = new JPanel(new FlowLayout(FlowLayout.LEFT));
		pArribaIzq.setBackground(Color.WHITE);

		JPanel pArribaIzq2 = new JPanel(new FlowLayout(FlowLayout.LEFT));
		pArribaIzq2.setBackground(Color.WHITE);

		JPanel pRegistros = new JPanel();
		pRegistros.setBackground(Color.WHITE);

		JPanel pIzq = new JPanel();
		pIzq.setBackground(Color.WHITE);
		pIzq.setLayout(new GridLayout(6, 1, 10, 20));
		// pIzq.setLayout(new BoxLayout(pIzq,BoxLayout.Y_AXIS));

		JPanel pCentro = new JPanel();
		pCentro.setBackground(Color.WHITE);
		pCentro.setLayout(new BorderLayout());

		ImageIcon imagenRegis = new ImageIcon(getClass().getResource("/Proyecto/Registros.png"));
		Image imagenRegis1 = imagenRegis.getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH);
		ImageIcon imagenRegis2 = new ImageIcon(imagenRegis1);

		JButton btnRegistros = new JButton("Registros", imagenRegis2);
		btnRegistros.setBackground(new Color(72, 207, 174));

		JButton btnDetalles = new JButton("Detalles");
		btnDetalles.setBackground(new Color(144, 213, 255));

		ImageIcon imagenElimin = new ImageIcon(getClass().getResource("/Proyecto/Eliminar.png"));
		Image imagenElimin1 = imagenElimin.getImage().getScaledInstance(15, 15, Image.SCALE_SMOOTH);
		ImageIcon imagenElimin2 = new ImageIcon(imagenElimin1);
		JButton btnElimLibro = new JButton("Eliminar", imagenElimin2);
		btnElimLibro.setBackground(new Color(72, 207, 174));

		ImageIcon imagenEdit = new ImageIcon(getClass().getResource("/Proyecto/Editar.png"));
		Image imagenEdit1 = imagenEdit.getImage().getScaledInstance(15, 15, Image.SCALE_SMOOTH);
		ImageIcon imagenEdit2 = new ImageIcon(imagenEdit1);
		JButton btnEditLibro = new JButton("Editar", imagenEdit2);
		btnEditLibro.setBackground(new Color(72, 207, 174));

		ImageIcon imagenNoti = new ImageIcon(getClass().getResource("/Proyecto/Notificaciones.png"));
		Image imagenNoti1 = imagenNoti.getImage().getScaledInstance(32, 32, Image.SCALE_SMOOTH);
		ImageIcon imagenNoti2 = new ImageIcon(imagenNoti1);
		JButton btnNotificaciones = new JButton(imagenNoti2);
		btnNotificaciones.setBackground(new Color(72, 207, 174));

		ImageIcon imagenGest = new ImageIcon(getClass().getResource("/Proyecto/Ojo.png"));
		Image imagenGest1 = imagenGest.getImage().getScaledInstance(32, 32, Image.SCALE_SMOOTH);
		ImageIcon imagenGest2 = new ImageIcon(imagenGest1);
		JButton btnPrestamosCompu = new JButton("Ver Prestamos Compu", imagenGest2);
		btnPrestamosCompu.setBackground(new Color(255,192,103));

		JButton btnPrestamosLibro = new JButton("Ver Prestamos Libro", imagenGest2);
		btnPrestamosLibro.setBackground(new Color(255,192,103));//247, 174, 98

		ImageIcon imagenCompu = new ImageIcon(getClass().getResource("/Proyecto/Compu.png"));
		Image imagenCompu1 = imagenCompu.getImage().getScaledInstance(32, 32, Image.SCALE_SMOOTH);
		ImageIcon imagenCompu2 = new ImageIcon(imagenCompu1);
		JButton btnPrestCompu = new JButton("Prestar compu", imagenCompu2);
		btnPrestCompu.setBackground(new Color(255, 238, 140));

		ImageIcon imagenLibro = new ImageIcon(getClass().getResource("/Proyecto/Libro.png"));
		Image imagenLibro1 = imagenLibro.getImage().getScaledInstance(32, 32, Image.SCALE_SMOOTH);// Botón Prestar Libro
		ImageIcon imagenLibro2 = new ImageIcon(imagenLibro1);
		JButton btnPrestLibro = new JButton("Prestar Libro", imagenLibro2);
		btnPrestLibro.setBackground(new Color(255, 238, 140));

		ImageIcon imagenMas = new ImageIcon(getClass().getResource("/Proyecto/Mas.png"));
		Image imagenMas1 = imagenMas.getImage().getScaledInstance(32, 32, Image.SCALE_SMOOTH);
		ImageIcon imagenMas2 = new ImageIcon(imagenMas1);

		JButton btnAgregLibro = new JButton("Agregar Libro", imagenMas2);
		btnAgregLibro.setBackground(new Color(119, 221, 119));

		ImageIcon imagenCerrarSes = new ImageIcon(getClass().getResource("/Proyecto/CerrarSesion.png"));
		Image imagenCerrarSes1 = imagenCerrarSes.getImage().getScaledInstance(32, 32, Image.SCALE_SMOOTH);
		ImageIcon imagenCerrarSes2 = new ImageIcon(imagenCerrarSes1);

		JButton btnCerrarSesion = new JButton("Cerrar sesion", imagenCerrarSes2);
		btnCerrarSesion.setBackground(new Color(220, 80, 80));

		JPanel pBtnDetalles = new JPanel(new FlowLayout(FlowLayout.LEFT));
		pBtnDetalles.setBackground(Color.white);

		JLabel lbl1 = new JLabel("  ");
		JLabel lbl2 = new JLabel("Libros", SwingConstants.CENTER);
		lbl2.setSize(30, 40);
		lbl2.setFont(new Font("Arial", Font.BOLD, 18));
		JLabel lbl3 = new JLabel();
		JLabel lbl4 = new JLabel("Poner logo");
		// PONER logo

		DefaultTableModel modelo = new DefaultTableModel();
		modelo.addColumn("ISBN");
		modelo.addColumn("Autor");
		modelo.addColumn("Titulo");
		modelo.addColumn("Ubicación");
		modelo.addColumn("Stock");
		modelo.addColumn("Cant. Prestados");

		JTable tabla = new JTable(modelo);
		tabla.setBackground(Color.white);
		tabla.getTableHeader().setReorderingAllowed(false);//Hace que las columnas no se puedan mover
		JScrollPane panelDesplazable = new JScrollPane(tabla);
		panelDesplazable.setBackground(Color.white);

		this.add(pArriba, BorderLayout.NORTH);
		pArriba.add(pArribaIzq);
		pArribaIzq.add(btnNotificaciones);

		pArriba.add(pArribaIzq2);
		pArribaIzq2.add(btnEditLibro);
		pArribaIzq2.add(btnElimLibro);

		pArriba.add(pBtnDetalles);
		pBtnDetalles.add(lbl1);
		pBtnDetalles.add(btnDetalles);
		pArriba.add(lbl2);

		pArriba.add(lbl3);
		pArriba.add(pRegistros);
		pRegistros.add(btnRegistros);

		pArriba.add(lbl4);

		this.add(pCentro, BorderLayout.CENTER);

		pCentro.add(panelDesplazable);

		// this.add(panelPrueba,BorderLayout.WEST);

		// panelPrueba.add(pIzq);
		this.add(pIzq, BorderLayout.WEST);

		pIzq.add(btnPrestamosCompu);
		pIzq.add(btnPrestamosLibro);
		pIzq.add(btnPrestCompu);
		pIzq.add(btnPrestLibro);
		pIzq.add(btnAgregLibro);
		pIzq.add(btnCerrarSesion);

		btnPrestamosCompu.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				vtnVerPrestamoCeibalitas vtn3 = new vtnVerPrestamoCeibalitas();
				vtn3.setVisible(true);

			}
		});

		btnPrestamosLibro.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				vtnVerPrestamoLibro vtn2 = new vtnVerPrestamoLibro();
				vtn2.setVisible(true);

			}
		});

		btnPrestCompu.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				vtnGuardarPrestamoCeibalita vtn2 = new vtnGuardarPrestamoCeibalita();
				vtn2.setVisible(true);

			}
		});

		btnPrestLibro.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				vtnGuardarPrestamoLibro vtn3 = new vtnGuardarPrestamoLibro();
				vtn3.setVisible(true);

			}
		});

		btnAgregLibro.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				vtnGuardarLibro vtn4 = new vtnGuardarLibro();
				vtn4.setVisible(true);

			}
		});

		btnCerrarSesion.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				vtnPaginaPrincipal.this.dispose();

				vtnInicioSesion vtn5 = new vtnInicioSesion();
				vtn5.setVisible(true);

			}
		});

		btnNotificaciones.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				vtnNotificaciones vtn6 = new vtnNotificaciones();
				vtn6.setVisible(true);

			}
		});

		btnEditLibro.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				vtnEditLibro vtn7 = new vtnEditLibro();
				vtn7.setVisible(true);

			}
		});

		btnElimLibro.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				vtnConfirmarEliminacion vtn8 = new vtnConfirmarEliminacion();
				vtn8.setVisible(true);

			}
		});

		btnDetalles.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				vtnDetalles vtn8 = new vtnDetalles();
				vtn8.setVisible(true);

			}
		});

		btnRegistros.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				vtnRegistros vtn8 = new vtnRegistros();
				vtn8.setVisible(true);

			}
		});
	}

}