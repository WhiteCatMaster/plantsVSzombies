package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.TableColumn;

public class Tienda extends JFrame{
	private static final long serialVersionUID = 1L;
	
	Font fuentebarra = new Font("Arial", Font.BOLD, 30);

	public Tienda() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("Tienda");
		ImageIcon solicono = new ImageIcon("src/imagenes/sol.png");
		JLabel soles = new JLabel("Soles: " + "900" + "     ", solicono, JLabel.LEFT); 
		soles.setFont(fuentebarra);
		
		ImageIcon cerebroicono = new ImageIcon("src/imagenes/cerebro.png");
		JLabel cerebros = new JLabel("Cerebros: " + "900" + "      ", cerebroicono, JLabel.LEFT); 
		cerebros.setFont(fuentebarra);
		
		
		JPanel central = new JPanel();
		central.setLayout(new BorderLayout());
		JPanel barra = new JPanel();

		barra.add(soles);
		barra.add(cerebros);
		central.add(barra, BorderLayout.NORTH);
		
	/*	
		// Crear el modelo con las plantas y crear la tabla con el modelo 
		ModeloTabla modelo = new ModeloTabla(plantas);
		JTable tabla = new JTable(modelo);
		tabla.setRowHeight(30);

		// Meter la tabla en el scrollpane
		JScrollPane scrollPane = new JScrollPane(tabla);

		// Implementar el renderer del nombre a la columna 0
		TableColumn nombreColumn = tabla.getColumnModel().getColumn(0);
		nombreColumn.setCellRenderer(new RendererNombre());

		// Crear una lista donde el usuario la llene y pasársela al modelo
		ArrayList<Planta> plantasJ = new ArrayList<Planta>();
		ModeloSeleccionadas modeloSelec = new ModeloSeleccionadas(plantasJ);

		// Crear la tabla con el modelo
		JTable tablaSelec = new JTable(modeloSelec);
		tablaSelec.setRowHeight(30);

		// Meter la tabla en un scrollpane
		JScrollPane scrollSelec = new JScrollPane(tablaSelec);

		// Colocarlo abajo y hacer que no se vea
		add(scrollSelec, BorderLayout.SOUTH);
		scrollSelec.setVisible(false);

	*/	
		
		add(central);
		setSize(Ajustes.resolucionx(),Ajustes.resoluciony());
		setLocationRelativeTo(null);
		setVisible(true);		
	}
	
	public static void main(String[] args) {
		Tienda ventana = new Tienda();
		ventana.setLocationRelativeTo(null);
	}
}
