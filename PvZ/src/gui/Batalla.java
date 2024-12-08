package gui;

import java.awt.GridLayout;
import java.awt.Image;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;

import domain.Planta;
import domain.Zombie;

public class Batalla extends JFrame{
	private static final long serialVersionUID = 1L;
	private HashMap<ArrayList<Integer>, Planta> mapaFinalPlantas;
	private HashMap<ArrayList<Integer>, Zombie> mapaFinalZombies;
	public static void main(String[] args) {
		
		@SuppressWarnings("unused")
		Simulacionv1 ventanaSimulacionv1 = new Simulacionv1();
	}
	public Batalla(HashMap<ArrayList<Integer>, Planta> mapaFinal1, HashMap<ArrayList<Integer>, Zombie> mapaFinal2) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("ventana de batalla");
		this.mapaFinalPlantas = mapaFinal1;
		this.mapaFinalZombies = mapaFinal2;
		setLayout(new GridLayout(5, 20));
		
		ArrayList<JButton> botones = new ArrayList<JButton>();
		for(int i = 0; i<100;i++) {
			int fila = i / 20; // 10 columnas por fila
		    int columna = i % 20;
			JButton botonCesped = new JButton();
			botonCesped.putClientProperty("fila", fila);
			botonCesped.putClientProperty("columna", columna);
			botonCesped.addActionListener(e -> System.out.println(botonCesped.getClientProperty("fila")+","+ botonCesped.getClientProperty("columna")));
			
			botones.add(botonCesped);
			add(botonCesped);
		}
		for(int i = 0; i<100;i++) {
			int fila = i / 20; // 10 columnas por fila
		    int columna = i % 20;
		    ArrayList<Integer> coordenadas = new ArrayList<Integer>();
		    coordenadas.add(fila);
		    coordenadas.add(columna);
		    if(mapaFinalPlantas.containsKey(coordenadas)) {
		    	try {
					botones.get(i).setIcon(new ImageIcon(Simulacionv1.getBuferedimagePlanta(mapaFinalPlantas.get(coordenadas)).getScaledInstance(24, 24, Image.SCALE_SMOOTH)));
				} catch (IOException e) {
					e.printStackTrace();
				}
		    } else if (mapaFinalZombies.containsKey(coordenadas)) {
		    	try {
					botones.get(i).setIcon(new ImageIcon(Simulacionv2.getBuferedimagePlanta(mapaFinalZombies.get(coordenadas)).getScaledInstance(24, 24, Image.SCALE_SMOOTH)));
				} catch (IOException e) {
					e.printStackTrace();
				}
		    }
		}
		
		
		
		pack();
		setVisible(true);
		setSize(Ajustes.resolucionx(),Ajustes.resoluciony());
		setLocationRelativeTo(null);
	}

}
