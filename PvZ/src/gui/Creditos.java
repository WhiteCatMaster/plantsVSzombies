package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Creditos extends JFrame{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public Creditos() {
		setSize(Ajustes.resolucionx(),Ajustes.resoluciony());
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
		
	    MusicaMenu player = new MusicaMenu();
	    Thread musicThread = new Thread(player);
        MusicaMenu.sonidoM = "/sonidos/animadas.wav";
        musicThread.start();
		
		JPanel panel = new JPanel() {
		
			private static final long serialVersionUID = 1L;
			@Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                BufferedImage imagenf = null; // la imagen
                try {
                    // Cargar imagen de fondo
                    imagenf = ImageIO.read(getClass().getResourceAsStream("/imagenes/p1.png"));
                } catch (IOException e) {
                    e.printStackTrace();
                }
                // Dibujar la imagen de fondo
                g.drawImage(imagenf, 0, 0, getWidth(), getHeight(), null);
            }
		
		};
		JButton atras = new JButton("Atras");
		atras.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				new MenuInicial();
				dispose();
				player.stopPlaying();	
			}
		});
		
		atras.setForeground(Color.BLACK);
		atras.setBackground(Color.WHITE);
		panel.setLayout(new BorderLayout());
		add(panel);
		panel.add(atras, BorderLayout.NORTH);
		setLocationRelativeTo(null);
		
		
	}
	public static void main(String[] args) {
		new Creditos();
	}
	

}