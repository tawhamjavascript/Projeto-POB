package appswing;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.List;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;


import javax.swing.JFrame;

import regras_negocio.Fachada;
import modelo.Jogo;
import modelo.Time;

public class TelaConsulta {

	private JFrame frmConsultas;
	private JTable table;
	private JScrollPane scrollPane;
	private JButton button;
	private JLabel label;
	private JLabel lblResultadosDaConsulta;

	private JComboBox comboBox;


	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaConsulta window = new TelaConsulta();
					window.frmConsultas.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public TelaConsulta() {
		initialize();
		frmConsultas.setVisible(true);

	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmConsultas = new JFrame();
		frmConsultas.setResizable(false);
		frmConsultas.setTitle("Consultas");
		frmConsultas.setBounds(100, 100, 729, 385);
		frmConsultas.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frmConsultas.getContentPane().setLayout(null);
		frmConsultas.addWindowListener(new WindowAdapter() {
			@Override
			public void windowOpened(WindowEvent e) {
				Fachada.inicializar();

			}

			@Override
			public void windowClosing(WindowEvent e) {
				Fachada.finalizar();
			}

		});

		scrollPane = new JScrollPane();
		scrollPane.setBounds(21, 68, 674, 229);
		frmConsultas.getContentPane().add(scrollPane);

		table = new JTable();
		scrollPane.setViewportView(table);
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				lblResultadosDaConsulta.setText("selecionado="+ (String) table.getValueAt( table.getSelectedRow(), 0));
			}
		});

		table.setGridColor(Color.BLACK);
		table.setRequestFocusEnabled(false);
		table.setFocusable(false);
		table.setBackground(Color.PINK);
		table.setFillsViewportHeight(true);
		table.setRowSelectionAllowed(true);
		table.setFont(new Font("Tahoma", Font.PLAIN, 14));
		table.setBorder(new LineBorder(new Color(0, 0, 0)));
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table.setShowGrid(true);
		table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);

		label = new JLabel("");        //label de mensagem
		label.setForeground(Color.BLUE);
		label.setBounds(21, 321, 688, 14);
		frmConsultas.getContentPane().add(label);

		lblResultadosDaConsulta = new JLabel("Resultados da consulta:");
		lblResultadosDaConsulta.setBounds(21, 43, 431, 14);
		frmConsultas.getContentPane().add(lblResultadosDaConsulta);

		button = new JButton("Consultar");
		button.setFont(new Font("Tahoma", Font.PLAIN, 12));
		button.addActionListener(new ActionListener() {
									 public void actionPerformed(ActionEvent e) {
										 int index = comboBox.getSelectedIndex();
										 if(index<0)
											 lblResultadosDaConsulta.setText("consulta nao selecionada");
										 else
											 switch(index) {
												 case 0:
													 String local = JOptionPane.showInputDialog("Digite o local");
													 try {
														 List<Time> resultado1 = Fachada.timesQueJogaramEmUmLocal(local);
														 consultaLocalTime(resultado1);

													 } catch (Exception err) {
														 System.out.println(err);
													 }

													 break;
												 case 1:
													 String data = JOptionPane.showInputDialog("Digite a data");
													 try {
														 List<Time> resultado2 = Fachada.timesQueJogaramEmUmaData(data);
														 consultaDataTime(resultado2);

													 } catch (Exception err) {
														 System.out.println(err);
													 }

													 break;
												 case 2:
													 try {
														 List<Time> resultado3 = Fachada.timesQuePossuemIngressosDisponiveis();
														 consultaIngressos(resultado3);
													 } catch (Exception err) {
														 System.out.println(err);
													 }

													 break;
												 case 3:
													 try {
														 String time = JOptionPane.showInputDialog("Digite o Time");
														 List<Jogo> resultado4 = Fachada.jogosDeUmTimeEspecifico(time);
														 consultaJogosTime(resultado4);

													 } catch (Exception err) {
														 System.out.println(err);
													 }

													 break;
												 case 4:
													 try {
														 List<Jogo> resultado5 = Fachada.jogosComMaisDeUmIngresso();
														 consultaMaisDeUm(resultado5);

													 }
													 catch (Exception err) {
														 System.out.println(err);
													 }

													 break;

										     }
									 }
		});

        button.setBounds(606, 10, 89, 23);
        frmConsultas.getContentPane().add(button);

		comboBox = new JComboBox();
        comboBox.setModel(new DefaultComboBoxModel(new String[] {"Times que jogaram em um local", "Times que jogaram em uma data", "Times que possuem ingressos disponíveis","Jogos de um time específico", "Jogos com mais de um ingresso"}));
        comboBox.setBounds(21, 10, 513, 22);
        frmConsultas.getContentPane().add(comboBox);
	}


				/**
				 * CONSULTA 1 timesQueJogaramEmUmLocal
				 **/

		public void consultaLocalTime(List<Time> lista) {
			try{
				// o model armazena todas as linhas e colunas do table
				DefaultTableModel model = new DefaultTableModel();

				//adicionar colunas no model

				model.addColumn("nome");
				model.addColumn("origem");
				model.addColumn("jogos");
				
				//adicionar linhas no model
				String jogosInfo = "";
			

				for (Time time : lista) {
					jogosInfo = "";
					for (Jogo jogo : time.getJogos()) {
						jogosInfo += jogo.getLocal() +" "+ jogo.getData() +" "+ jogo.getTime1().getNome()+" vs. "+ jogo.getTime2().getNome()+", ";
					}
					model.addRow(new Object[]{time.getNome(), time.getOrigem(), jogosInfo});
				}

				//atualizar model no table(visualizacao)

				table.setModel(model);

				lblResultadosDaConsulta.setText("Resultados: "+lista.size()+"objetos");
			} catch(Exception erro) {
				label.setText(erro.getMessage());


			}
		}

		public void consultaDataTime(List<Time> lista) {

			try {
				DefaultTableModel model = new DefaultTableModel();

				model.addColumn("nome");
								
				String jogosInfo = "";
								
				
				for (Time time : lista) {
					jogosInfo = "";
					for (Jogo jogo : time.getJogos()) {
						jogosInfo += jogo.getLocal() +" "+ jogo.getData() +" "+ jogo.getTime1().getNome()+" vs. "+ jogo.getTime2().getNome()+", ";
					}
				
					model.addRow(new Object[]{time.getNome(), jogosInfo});
				}
				
				//atualizar model no table(visualizacao)

				table.setModel(model);

				lblResultadosDaConsulta.setText("Resultados: "+lista.size()+"objetos");

			} catch (Exception erro) {
				System.out.println(erro.getMessage());
			}

		}

		public void consultaIngressos(List<Time> lista) {
			
			try {
				DefaultTableModel model = new DefaultTableModel();

				model.addColumn("times");
				
				
				for (Time time : lista) {
					model.addRow(new Object[]{time.getNome()});
				}
				
				//atualizar model no table(visualizacao)

				table.setModel(model);

				lblResultadosDaConsulta.setText("Resultados: "+lista.size()+"objetos");

			} catch (Exception erro) {
				System.out.println(erro.getMessage());
			}
			

		}

		public void consultaJogosTime(List<Jogo> lista) {
			try {
				DefaultTableModel model = new DefaultTableModel();

				model.addColumn("data");
				model.addColumn("local");
				model.addColumn("time 1");
				model.addColumn("time 2");
				
				
				for (Jogo jogo : lista) {
					model.addRow(new Object[]{jogo.getData(), jogo.getLocal(), jogo.getTime1().getNome(), jogo.getTime2().getNome()});
				}
				
				//atualizar model no table(visualizacao)

				table.setModel(model);

				lblResultadosDaConsulta.setText("Resultados: "+lista.size()+"objetos");

			} catch (Exception erro) {
				System.out.println(erro.getMessage());
			}

		}

		public void consultaMaisDeUm(List<Jogo> lista) {
			try {
				DefaultTableModel model = new DefaultTableModel();

				model.addColumn("data");
				model.addColumn("local");
				model.addColumn("time 1");
				model.addColumn("time 2");
				
				
				for (Jogo jogo : lista) {
					model.addRow(new Object[]{jogo.getData(), jogo.getLocal(), jogo.getTime1().getNome(), jogo.getTime2().getNome()});
				}
				
				//atualizar model no table(visualizacao)

				table.setModel(model);

				lblResultadosDaConsulta.setText("Resultados: "+lista.size()+"objetos");

			} catch (Exception erro) {
				System.out.println(erro.getMessage());
			}
		}}
		
//		public void separarInfor(String inforJogos) {
//		 
//		 
//
//	}
/**
 * CONSULTA 2 timesQueJogaramEmUmaData
 **/


/**
 * CONSULTA 3 TimesQuePossuemIngressosDisponiveis
 **/


/**
 * CONSULTA 4 JogosDeUmTimeEspecifico
 **/


/**
 * CONSULTA 5 jogosComMaisDeUmIngresso
 **/


