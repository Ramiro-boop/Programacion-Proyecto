package Proyecto;



	import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

	public class vtnGuardarLibro extends JFrame {
		public vtnGuardarLibro() {
		        this.setTitle("Añadir libro");
		        this.setSize(500, 500);// Tamaño en PX. Primero el ancho y luego el largo
				this.setLocationRelativeTo(null);
				
		        JPanel panelariba = new JPanel(new BorderLayout());
		        JLabel texto = new JLabel("AÑADIR LIBRO", SwingConstants.CENTER);
		        texto.setSize(30, 40);
		    	texto.setFont(new Font("Arial",Font.BOLD,25));
		        
		        panelariba.add(texto, BorderLayout.CENTER);
		    
		        
		        JPanel panel1 = new JPanel(new GridLayout(10, 0, 5, 5));
		      

		        
		       
		        JLabel nomlib = new JLabel("Titulo:");
		        JTextField a = new JTextField(5);
		        a.setBackground(new Color(72, 207, 174));
		        
		        JLabel autor = new JLabel("Autor:");
		        JTextField b = new JTextField(5);
		        b.setBackground(new Color(72, 207, 174));
		        
		        JLabel isbn = new JLabel("ISBN:");
		        JTextField d = new JTextField(5);
		        d.setBackground(new Color(72, 207, 174));
		        
		        JLabel stock = new JLabel("Stock:");
		        JTextField e = new JTextField(5);
		        e.setBackground(new Color(72, 207, 174));
		        
		        JLabel genero = new JLabel("Genero:");
		        JTextField f = new JTextField(5);
		        f.setBackground(new Color(72, 207, 174));
		        
		        JLabel mat = new JLabel("Materia:");
		        JTextField g = new JTextField(5);	
		        g.setBackground(new Color(72, 207, 174));
		        
		        JLabel nota = new JLabel("Notas:");
		        JTextField h = new JTextField(5);	
		        h.setBackground(new Color(72, 207, 174));
		       


		        
		        panel1.add(nomlib);
		        panel1.add(a);
		       
		        panel1.add(autor);
		        panel1.add(b);
		        
		        panel1.add(isbn);
		        panel1.add(d);
		        
		        panel1.add(stock);
		        panel1.add(e);
		       
		        panel1.add(genero);
		        panel1.add(f);
		        
		        panel1.add(mat);
		        panel1.add(g);
		      
		        panel1.add(nota);
		        panel1.add(h);
		  
		 	 
		 	   
		       

		   JPanel panelinf = new JPanel(new BorderLayout());
		   
		   JButton btn1 = new JButton("Aplicar");
		   JButton btn2 = new JButton("Descartar");
		   
		   
		   JPanel panelAbajo2 = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		   
		  
			
		   panelinf.add(panelAbajo2, BorderLayout.SOUTH);
			panelAbajo2.add(btn2);
			panelAbajo2.add(btn1);
		     
			 this.add(panelinf);
		        
		        this.setLayout(new BorderLayout());
		        this.add(panelariba, BorderLayout.NORTH);
		        
		        this.add(panelinf, BorderLayout.SOUTH);
		        this.add(panel1);
		        
		        
		        
		 btn1.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				vtnPaginaPrincipal vtn1 = new vtnPaginaPrincipal();
				vtn1.setVisible(true);
				
			}
		});
		 
		 btn2.addActionListener(new ActionListener() {
				
			@Override
			public void actionPerformed(ActionEvent e) {
				
				vtnPaginaPrincipal vtn1 = new vtnPaginaPrincipal();
				vtn1.setVisible(true);
				
			}
		});
		        
		}
		
		
		
	
}