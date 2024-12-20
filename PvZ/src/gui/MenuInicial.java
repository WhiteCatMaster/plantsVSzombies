package gui;
// del señor pellets
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

import domain.Planta;

public class MenuInicial extends JFrame {

    private static final long serialVersionUID = 1L;
    
    MusicaMenu player = new MusicaMenu();
    Thread musicThread = new Thread(player);
    
    
    public MenuInicial() {
        super("Ventana mínima");
        MusicaMenu.sonidoM = "/sonidos/sly.wav";
        musicThread.start();
        // Ajustes de la ventana
        setSize(800, 600);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Crear panel 
        JPanel panel = new JPanel() {
            private static final long serialVersionUID = 1L;

            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                BufferedImage envy = null; // la imagen
                try {
                    envy = ImageIO.read(getClass().getResourceAsStream("/imagenes/surface.jpg"));
                } catch (IOException e) {
                    e.printStackTrace();
                }
                g.drawImage(envy, 0, 0, getWidth(), getHeight(), null);
            }
        };
        ;
        //panel.setLayout(new GridLayout(1, 2));
        panel.setLayout(null);
        // lo que pregunta el usurname
        
        JTextField EdwardElric = new JTextField(); // save del usuario
        EdwardElric.setBounds(30, 50, 300, 60);
        panel.add(EdwardElric);
        JButton KingBradley = new JButton("Guardar usuario"); //usuario
        KingBradley.setBounds(30, 110, 300, 60);
        panel.add(KingBradley);

        
        KingBradley.addActionListener(new GuardarUsuarioListener(EdwardElric));

        
        //botones

           
        //boton 1
        JButton selim = new JButton("SIMULADOR"); // eder y jhojan
        selim.setBounds(350, 100, 500, 100);
        selim.setBackground(java.awt.Color.GRAY);
        selim.setForeground(java.awt.Color.WHITE);
        selim.setFont(new Font("Arial Black", Font.BOLD, 24));
        ActionListener s = new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
				player.stopPlaying();
				new SelecPlantas();	
			}
		};
		selim.addActionListener(s);
      
				
        //boton 2
        JButton yoki = new JButton("ALMANAQUE"); // parte de aitor
        yoki.setBounds(350, 200, 500, 100);
        yoki.setBackground(java.awt.Color.GRAY);
        yoki.setForeground(java.awt.Color.WHITE);
        yoki.setFont(new Font("Arial Black", Font.BOLD, 24)); 
        ActionListener l = new ActionListener() {
		
			
			@Override
			public void actionPerformed(ActionEvent e) {
				ArrayList<Planta> sig = new ArrayList<Planta>(); //plantas 
		        MenuPlantas.cargarPlantasCSV(sig, "src/DatosCsv/plantas.csv");
		        new SelecAlmanaque();
		        player.stopPlaying();
		        dispose();
			}
		};
		yoki.addActionListener(l);
        //boton 3
        JButton alphonse = new JButton("AJUSTES"); // parte de pele (?)
        alphonse.setBounds(350, 300, 500, 100); 
        alphonse.setBackground(java.awt.Color.GRAY);
        alphonse.setForeground(java.awt.Color.WHITE);
        alphonse.setFont(new Font("Arial Black", Font.BOLD , 24));
        alphonse.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				new Ajustes();
				dispose();
				player.stopPlaying();
				
			}
		});
        


            
      
        //boton 4
        JButton mustang = new JButton("CREDITOS"); // pele
        mustang.setBounds(350, 400, 500, 100);
        mustang.setBackground(java.awt.Color.GRAY);
        mustang.setForeground(java.awt.Color.WHITE);
        mustang.setFont(new Font("Arial Black", Font.BOLD, 24));
        mustang.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				new Creditos();
				dispose();
				player.stopPlaying();
				
			}
		});
        // Añadimos botones
        panel.add(selim); //1
        panel.add(yoki); //2
        panel.add(alphonse); //3
        panel.add(mustang); //4
        
        add(panel);

        setVisible(true);
    }

    public static void main (String[] args) {
        new MenuInicial();
    }
}