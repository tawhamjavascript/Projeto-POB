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

import com.db4o.ObjectContainer;

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

	private ObjectContainer manager;
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
		
		label = new JLabel("");		//label de mensagem
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
						ArrayList<Time> resultado1 = Fachada.timesQueJogaramEmUmLocal(local);
						consultaLocalTime(resultado1);
						break;
					case 1: 
						String data = JOptionPane.showInputDialog("Digite o modelo");
						ArrayList<Time> resultado2 = Fachada.timesQueJogaramEmUmaData(data);
						consultaDataTime(resultado2);
						break;
					case 2: 
						ArrayList<Time> resultado3 = Fachada.timesQuePossuemIngressosDisponiveis();
						consultaIngressos(resultado3);
						break;
					case 3: 
						String time = JOptionPane.showInputDialog("Digite o Time");
						ArrayList<Jogo> resultado4 = Fachada.jogosDeUmTimeEspecifico(time);
						consultaJogosTime(resultado4);
						break;
					case 4: 
						ArrayList<Jogo> resultado5 = Fachada.jogosComMaisDeUmIngresso(numero);
						consultaMaisDeUm(resultado5);
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
		
	public void consultaLocalTime(ArrayList<Time> lista) {
		try{
			// o model armazena todas as linhas e colunas do table
			DefaultTableModel model = new DefaultTableModel();
			
			//adicionar colunas no model
			
			model.addColumn("nome");
			model.addColumn("origem");
			model.addColumn("jogos");
			
			//adicionar linhas no model
			
			for (Time time : lista) {
				model.addRow(new Object[]{time.getNome(), time.getOrigem(), time.getJogos()});
			}
			
			//atualizar model no table(visualizacao)
			
			table.setModel(model);
			
			lblResultadosDaConsulta.setText("Resultados: "+lista.size()+"objetos");
		}
		catch(Exception erro) {
			label.setText(erro.getMessage());
		
			
		}
	}
	
	public void consultaDataTime(ArrayList<Time> lista) {
	
		try {
			DefaultTableModel model = new DefaultTableModel();
		
			model.addColumn("nome");
			model.addColumn("data");
			
		}
		
	}
	
	public void consultaIngressos(ArrayList<Time> lista) {
		
	}
	
	public void consultaJogosTime(ArrayList<Jogo> lista) {
		
	}
	
	public void consultaMaisDeUm(ArrayList<Jogo> lista) {
		
	}
	
}
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


