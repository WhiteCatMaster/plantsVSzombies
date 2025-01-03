package gui;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import domain.Planta;
import domain.Zombie;

public class SelecAlmanaque extends JFrame{
	private static final long serialVersionUID = 1L;
	
	public SelecAlmanaque() {
		//setExtendedState(JFrame.MAXIMIZED_BOTH); 
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("Selecciona el almanaque");
		setSize(Ajustes.resolucionx(),Ajustes.resoluciony());

	    MusicaMenu player = new MusicaMenu();
	    Thread musicThread = new Thread(player);
        MusicaMenu.sonidoM = "/sonidos/sly.wav";
        musicThread.start();
		
		
		JPanel central = new JPanel();
		central.setLayout(new BorderLayout());
		add(central);
		central.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
		
		JButton plantas = new JButton();

		ImageIcon bannerplantas = new ImageIcon("src/imagenes/bannerplantas.png");
		Image img = bannerplantas.getImage();

		// Escucha cambios en el tamaño del botón
		plantas.addComponentListener(new java.awt.event.ComponentAdapter() {
		    @Override
		    public void componentResized(java.awt.event.ComponentEvent e) {
		        // Redimensiona la imagen al tamaño actual del botón
		        int width = plantas.getWidth();
		        int height = plantas.getHeight();
		        Image resizedImg = img.getScaledInstance(width, height, Image.SCALE_SMOOTH); // Ajusta tamaño
		        plantas.setIcon(new ImageIcon(resizedImg)); // Configura el nuevo icono
		    }
		});

		plantas.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				ArrayList<Planta> sig = new ArrayList<Planta>();
		        MenuPlantas.cargarPlantasCSV(sig, "src/DatosCsv/TODAS.csv");
		        MenuPlantas ventana = new MenuPlantas(sig);
		        ventana.setLocationRelativeTo(null);
		        dispose();
			}
		}); 
		
		JButton zombies = new JButton();
		ImageIcon bannerzombies = new ImageIcon("src/imagenes/bannerzombies.png");
		Image img1 = bannerzombies.getImage();

		// Escucha cambios en el tamaño del botón
		zombies.addComponentListener(new java.awt.event.ComponentAdapter() {
		    @Override
		    public void componentResized(java.awt.event.ComponentEvent e) {
		        // Redimensiona la imagen al tamaño actual del botón
		        int width = zombies.getWidth();
		        System.out.println(width);
		        int height = zombies.getHeight();
		        Image resizedImg = img1.getScaledInstance(width, height, Image.SCALE_SMOOTH); // Ajusta tamaño
		        zombies.setIcon(new ImageIcon(resizedImg)); // Configura el nuevo icono
		    }
		});

		// Configura el botón con el icono redimensionado
		zombies.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				ArrayList<Zombie> sig = new ArrayList<Zombie>();
		        MenuZombies ventana = new MenuZombies(sig, "src/DatosCsv/zombies.csv");
		        ventana.setLocationRelativeTo(null);
		        dispose();
			}
		}); 
		
		central.setLayout(new GridLayout(2, 1, 20, 20));
		central.setOpaque(true);
		central.add(plantas);
		central.add(zombies);
		setLocationRelativeTo(null);
		setVisible(true);
	}
	
	public static void main(String[] args) {
		SelecAlmanaque ventana = new SelecAlmanaque();
		ventana.setLocationRelativeTo(null);
	}

}
