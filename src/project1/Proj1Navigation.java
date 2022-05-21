package project1;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.LineBorder;
import AutomatosCelulares2D.*;
import JogoDaVidaOriginal.*;
import dla.*;
import processing.core.PApplet;
import java.util.*;
import java.awt.event.*;

public class Proj1Navigation {
	private JFrame frame;
	private int selection;
	private JTextField textField1;
	private JTextField textField2;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Proj1Navigation window = new Proj1Navigation();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	/**
	 * Create the application.
	 */
	public Proj1Navigation() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame("*.+");
		frame.getContentPane().setLayout(null);
		frame.getContentPane().setBackground(new Color(14, 8, 36));
		
		JLabel text = new JLabel("Selecione o projeto:");
		text.setForeground(new Color(125, 127, 201));
		text.setBounds(32, 47, 116, 13);
		frame.getContentPane().add(text);
		
		JButton openProjectBttn = new JButton("Open!");
		openProjectBttn.setBackground(new Color(107, 101, 169));
		openProjectBttn.setBorder(new LineBorder(new Color(86, 80, 141)));
		openProjectBttn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {	
					// Vemos qual o projeto selecionado e vemos quais as opções que devem ser especificadas
					switch (selection) {
					case 0:
						JogoDaVidaSetUp projeto1 = new JogoDaVidaSetUp();
						PApplet.main(projeto1.getClass());
						// Buscar os valores das regras propostas
						int birth    = Integer.parseInt(textField2.getText());
						int survival = Integer.parseInt(textField1.getText());
						// Transformar em Array List para ser recebido
						ArrayList<Integer> birth_rules = new ArrayList<Integer>();
					    ArrayList<Integer> survival_rules = new ArrayList<Integer>(Arrays.asList());
					    while (birth != 0) {
					    	birth_rules.add(birth % 10); birth = birth / 10;
					    }
					    while (survival != 0) {
					    	survival_rules.add(survival % 10); survival = survival / 10;
					    }
					    // Aplicar as regras
					    ((JogoDaVida)projeto1.getApp()).changeRules(birth_rules, survival_rules);
					    frame.dispose(); // Devido a falta de atualizações na biblioteca G4P, só podemos iniciar
		 				 				 // um projeto de cada vez
						break;
					case 1:
						GameOfLifeSetUp projeto2 = new GameOfLifeSetUp();
						PApplet.main(projeto2.getClass());
						// Buscar os valores das regras propostas
						int birth2    = Integer.parseInt(textField2.getText());
						int survival2 = Integer.parseInt(textField1.getText());
						// Transformar em Array List para ser recebido
						ArrayList<Integer> birth_rules2 = new ArrayList<Integer>();
					    ArrayList<Integer> survival_rules2 = new ArrayList<Integer>(Arrays.asList());
					    while (birth2 != 0) {
					    	birth_rules2.add(birth2 % 10); birth2 = birth2 / 10;
					    }
					    while (survival2 != 0) {
					    	survival_rules2.add(survival2 % 10); survival2 = survival2 / 10;
					    }
					    // Aplicar as regras
					    ((GameOfLife)projeto2.getApp()).changeRules(birth_rules2, survival_rules2);
					    frame.dispose(); // Devido a falta de atualizações na biblioteca G4P, só podemos iniciar
		 				                 // um projeto de cada vez
						break;
					case 2:
						// Ver e validar o número de estados pedido...
						int chosenNStates = Integer.parseInt(textField1.getText());
						MajorityRuleSetUp projeto3 = new MajorityRuleSetUp();
						if(chosenNStates > 1) { // E corre-lo
							projeto3.connectMenu(chosenNStates);
							PApplet.main(projeto3.getClass());
							frame.dispose(); // Devido a falta de atualizações na biblioteca G4P, só podemos iniciar
			 				                 // um projeto de cada vez
						}
						break;
					case 3:
						float value1 = Float.parseFloat(textField1.getText());
						if (value1 >= 0 && value1 <= 1) {
							DiffusionAggregationSetup projeto = new DiffusionAggregationSetup();
							PApplet.main(projeto.getClass());
							dla.setStickiness(value1);
							frame.dispose(); // Devido a falta de atualizações na biblioteca G4P, só podemos iniciar
			 				                 // um projeto de cada vez
						}
						break;
					}
				} catch (Exception e2) {
				}
			}
		});
		openProjectBttn.setBounds(119, 88, 69, 21);
		frame.getContentPane().add(openProjectBttn);
		
		JLabel lblNewLabel = new JLabel("- MSSN -");
		lblNewLabel.setBounds(80, 10, 80, 13);
		lblNewLabel.setForeground(new Color(125, 127, 201));
		frame.getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Projeto 1");
		lblNewLabel_1.setForeground(new Color(125, 127, 201));
		lblNewLabel_1.setBounds(80, 20, 67, 13);
		frame.getContentPane().add(lblNewLabel_1);
		
		textField1 = new JTextField();
		textField1.setBounds(22, 132, 105, 19);
		frame.getContentPane().add(textField1);
		textField1.setColumns(10);
		textField2 = new JTextField();
		textField2.setColumns(10);
		textField2.setBounds(21, 175, 106, 19);
		frame.getContentPane().add(textField2);
		
		JLabel label1 = new JLabel("New label");
		label1.setForeground(new Color(125, 127, 201));
		label1.setBounds(22, 119, 166, 13);
		frame.getContentPane().add(label1);
		
		JLabel label2 = new JLabel("New label");
		label2.setForeground(new Color(125, 127, 201));
		label2.setBounds(21, 162, 167, 13);
		frame.getContentPane().add(label2);
		frame.setBounds(100, 100, 229, 255);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.setResizable(false);
		label1.setText("Regras de Sobrevivência:");
		label2.setText("Regras de Vida:");
		textField1.setText(String.valueOf(23));
		textField2.setText(String.valueOf(3));
		
		JComboBox comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"Jogo da Vida Clássico", "Jogo da Vida Colorido", "Regra da Maioria", "DLA"}));
		comboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				selection = comboBox.getSelectedIndex();
				if (selection == 0 || selection == 1) {textField1.setEnabled(true); textField2.setEnabled(true);}
				if (selection == 2 || selection == 3) {textField1.setEnabled(true); textField2.setEnabled(false);}
				switch (selection) {
					case 0, 1:
						label1.setText("Regras de Sobrevivência:");
						label2.setText("Regras de Vida:");
						textField1.setText(String.valueOf(23));
						textField2.setText(String.valueOf(3));
						break;
					case 2:
						label1.setText("Número de cores:");
						label2.setText("  ---  ");
						textField1.setText(String.valueOf(4));
						textField2.setText("");
						break;
					case 3:
						label1.setText("Stickiness inicial (0-1):");
						label2.setText("  ---  ");
						textField1.setText(String.valueOf(1));
						textField2.setText("");
						break;
					}
				}
		});
		comboBox.setBounds(32, 61, 156, 21);
		frame.getContentPane().add(comboBox);
	}
}
