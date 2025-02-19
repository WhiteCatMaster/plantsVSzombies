package gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableColumn;

import db.GestorBD;
import domain.Planta;
import domain.Zombie;

public class SelecZombies extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public static void main(String[] args) {
		//se crea la ventana
        @SuppressWarnings("unused")
        SelecZombies ventana = new SelecZombies(null);
	}
	JPanel panelBotones;
	JPanel panelZombies;
	JPanel mainPanel;
	JPanel panelTodosZombies;
	JPanel panelUsuarioZ;
	JLabel tituloT;
	JLabel tituloS;
	ArrayList<Zombie> zombiesj = new ArrayList<Zombie>();
	
	public SelecZombies(ArrayList<Planta> resultadoP) {
		//ajustes de la ventana y 
		//cargar la lista de zombies con la bd
		GestorBD gestor = new GestorBD();
		ArrayList<Zombie> zombies = gestor.getZombies();

		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setTitle("Selecciona Zombies");
		setLocationRelativeTo(null);
		setSize(Ajustes.resolucionx(), Ajustes.resoluciony());
		
		//limpiar los nombres de los zombies
		for (Zombie zombie : zombies) {
			zombie.setTipo(zombie.getTipo().replaceAll("[^a-zA-ZáéíóúÁÉÍÓÚñÑ ]", ""));
		}
		
		
		
		// crear el modelo con los zombies y crear la tabla con el modelo
		ModeloTablaZ modelo = new ModeloTablaZ(zombies);
		JTable tabla = new JTable(modelo);
		tabla.setRowHeight(30);

		
		// meter la tabla en un scrollPane
		JScrollPane scrollPane = new JScrollPane(tabla);
		
		// implementar el renderer del nombre a la columna 0 y ajustar el tamaño de las columnas
		TableColumn nombreColumn = tabla.getColumnModel().getColumn(0);
		nombreColumn.setCellRenderer(new RendererNombre());
		nombreColumn.setMinWidth(150);
		
		TableColumn columna1 = tabla.getColumnModel().getColumn(1);
		columna1.setMinWidth(150);
		
		// centrar las columnas
		DefaultTableCellRenderer centralRenderer = new DefaultTableCellRenderer();
	    centralRenderer.setHorizontalAlignment(JLabel.CENTER); 
	    centralRenderer.setVerticalAlignment(JLabel.CENTER);   
	    
	    for (int i = 1; i < tabla.getColumnCount(); i++) {
            tabla.getColumnModel().getColumn(i).setCellRenderer(centralRenderer);
        }
		
		// botones
		JButton atras = new JButton("Atras");
		// añadir actionListener para que al pulsarlo se abra el SelecPlantas 
		atras.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				new SelecPlantas();
				dispose();
				
			}
		});
		
		 // crear una lista donde el usuario la llene y pasarsela al modelo
		ArrayList<Zombie> zombiesJ = new ArrayList<Zombie>();
		ModeloSelecZombies modeloSelec = new ModeloSelecZombies(zombiesJ);
		
		// crear la tabla con el modelo 
		JTable tablaSelec = new JTable(modeloSelec);
		tablaSelec.setRowHeight(30);

		
		// meter la tabla en un scrollpane
		JScrollPane scrollSelec = new JScrollPane(tablaSelec);
		
		TableColumn columnSelec = tablaSelec.getColumnModel().getColumn(0);
		columnSelec.setPreferredWidth(163);
		
		TableColumn columnNombre = tablaSelec.getColumnModel().getColumn(1);
		columnNombre.setPreferredWidth(170);
		
		scrollSelec.setVisible(false);
		
	    
	    
		//colocarlo abajo y hacer que no se vea
		
		scrollSelec.setVisible(false);
		
		JButton agregar = new JButton("Añadir");
		
		// Actionlistener para que al pulsar el boton 
        //se cree una tabla con con las estadisticas pero con una columna 
        //adicional indicando la cantidad que el usuario quiere utilizar
        // y si el zombie ya este en la tabla que la columna cantidad se vaya sumando
        
        agregar.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				//obtener la fila seleccionada por el usuario y 
				//añadirle el renderer nombre para que aparezcan 
				//iconos junto al nombre
				
				int filaSeleccionada = tabla.getSelectedRow();
				TableColumn nombreColumn = tablaSelec.getColumnModel().getColumn(0);
				nombreColumn.setCellRenderer(new RendererNombre());
				
				
				// mensaje de error si se selecciona mal el zombie
				//(si el getSelectedrow no tiene ninguna fila seleccionada devuelve -1)
				if(filaSeleccionada  == -1) {
					JOptionPane.showMessageDialog(SelecZombies.this, "Selecciona una zombie", "Error", JOptionPane.ERROR_MESSAGE);
					return;
				}
				
				//obtener el numero de filas de y una variable 
				//que nos dice si la tabla existe o no
				int numeroFilas = tablaSelec.getRowCount();
				boolean existe = false;
				
				String nombreZombie = (String) tabla.getValueAt(filaSeleccionada, 0);
				// si existe sumarle la cantidad
				for (int i = 0; i < numeroFilas; i++) {
					if(modeloSelec.getValueAt(i, 0).equals(nombreZombie)) {
						modeloSelec.sumarCantidad(i);
						Zombie plantaSeleccionada = zombies.get(filaSeleccionada);
						zombiesj.add(plantaSeleccionada);
						
						int cantidad = modeloSelec.getCantidades().get(i);
						if (cantidad == 6) {
							JOptionPane.showMessageDialog(SelecZombies.this,
									"No puedes añadir mas de 5 zombies iguales", "Error", JOptionPane.ERROR_MESSAGE);
							modeloSelec.restarCantidad(i);
							zombiesj.remove(plantaSeleccionada);
							return;
						}
						
						existe = true;
						
					}
				}
				// si no añadir la fila seleccionada con la cantidad a 1
				if (!existe) {
				    Zombie plantaSeleccionada = zombies.get(filaSeleccionada);
				    zombiesj.add(plantaSeleccionada);
				    modeloSelec.getZombies().add(plantaSeleccionada);
				    modeloSelec.getCantidades().add(1);
				    modeloSelec.fireTableRowsInserted(modeloSelec.getRowCount() - 1, modeloSelec.getRowCount() - 1);
				  				}
				// al pulsar añadir por primera vez que se vea la tabla

				if (numeroFilas == 0) {
					scrollSelec.setVisible(true);
				    revalidate();
				    repaint();
				}
				
				
				
			}
		});
        
        JButton eliminar = new JButton("Eliminar");
        
        eliminar.addActionListener(new ActionListener() {
        	
        	//actionListener para que al seleccionar una fila y pulsa eliminar 
        	//se reste una a la columna cantidad (la comprobacion si fila si tiene cantidad 
        	// o no para elinarla esta en el modelo de la tabla)
			
			@Override
			public void actionPerformed(ActionEvent e) {
				int filaSeleccionada = tablaSelec.getSelectedRow();
				//si el usuario selecciona mal la planta le saldra un mensaje de error
				if(filaSeleccionada  == -1) {
					JOptionPane.showMessageDialog(SelecZombies.this, "Selecciona un zombie de la tabla de abajo", "Error", JOptionPane.ERROR_MESSAGE);
					return;
				}
				//restar la cantidad
				modeloSelec.restarCantidad(filaSeleccionada);
				Zombie plantaSeleccionada = zombies.get(filaSeleccionada);
				zombiesj.remove(plantaSeleccionada);
				int numeroFilas = tablaSelec.getRowCount();
				//si la tabla se queda sin plantas se oculte la tabla
				if(numeroFilas == 0) {
					scrollSelec.setVisible(false);
					revalidate();
					repaint();
				}
				
				
			}
		} );
        
        // al pulsar continuar que se abra el Simulador
        JButton batalla = new JButton("Simulacion");
        batalla.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				 SwingUtilities.invokeLater(() -> new Simulacionv1(zombiesj, resultadoP));
				 dispose();
				 
			}
		});
        
        DefaultTableCellRenderer Renderer = new DefaultTableCellRenderer();
	    Renderer.setHorizontalAlignment(JLabel.CENTER); 
	    Renderer.setVerticalAlignment(JLabel.CENTER);   
	    
		 for (int i = 1; i < tablaSelec.getColumnCount(); i++) {
	            tablaSelec.getColumnModel().getColumn(i).setCellRenderer(Renderer);
	        }
      //Añadir todas los botones a un panel y ajustarlo todo
		    mainPanel = new JPanel();
		    mainPanel.setLayout(new BorderLayout());
	        panelBotones = new JPanel();  
	        panelBotones.add(atras);
	        panelBotones.add(agregar);
	        panelBotones.add(eliminar);
	        panelBotones.add(batalla);
	        
	        panelTodosZombies = new JPanel();
	        panelTodosZombies.setLayout(new BorderLayout());
	        
	        tituloT = new JLabel("Tus zombies escogidos", SwingConstants.CENTER);
		    tituloT.setFont(new Font("Arial", Font.BOLD, 16));
		    
		    tituloS = new JLabel("Escoge tus zombies", SwingConstants.CENTER);
		    tituloS.setFont(new Font("Arial", Font.BOLD, 16));
	        
	        panelTodosZombies.add(scrollPane, BorderLayout.CENTER);
	        panelTodosZombies.add(tituloS, BorderLayout.NORTH);
		    
	        panelUsuarioZ = new JPanel();
	        panelUsuarioZ.setLayout(new BorderLayout());
	        
	       
	        
	        panelUsuarioZ.add(scrollSelec, BorderLayout.CENTER);
	        panelUsuarioZ.add(tituloT, BorderLayout.NORTH);
	        
	        
	        
	        panelBotones.setPreferredSize(new Dimension(0, 50));
	        
	        
	        panelZombies = new JPanel();
	        panelZombies.setLayout(new GridLayout(2, 1 , 10, 10));
	        panelZombies.add(panelTodosZombies);
	        panelZombies.add(panelUsuarioZ);
	        
	        mainPanel.add(panelBotones, BorderLayout.NORTH);
	        mainPanel.add(panelZombies, BorderLayout.CENTER);
	        
	        add(mainPanel);
	        
	        panelZombies.setPreferredSize(new Dimension(0, 400));
	        
			setLocationRelativeTo(null);
			
	        setResizable(false);

	        
	        setVisible(true);
	}

}