package appswing;

import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.List;
import java.awt.event.ActionEvent;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;


import modelo.Time;
import regras_negocio.Fachada;

import javax.swing.JTable;
import javax.swing.JLabel;


public class TelaTime {

	private JFrame frmTimes;
	private JTable table;
	private JScrollPane scrollPane;
	private JTextField textField_1;
	private JTextField textField_2;
	private JButton btnNewButton_3;
	private JLabel lblNewLabel_1;
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaTime window = new TelaTime();
					window.frmTimes.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public TelaTime() {
		initialize();
		frmTimes.setVisible(true);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmTimes = new JFrame();
		frmTimes.setTitle("Times");
		frmTimes.setBounds(100, 100, 1059, 460);
		frmTimes.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmTimes.getContentPane().setLayout(null);
		frmTimes.addWindowListener(new WindowAdapter() {
			@Override
			public void windowOpened(WindowEvent e) {
				Fachada.inicializar();
			
			}
			@Override
			public void windowClosing(WindowEvent e) {
				Fachada.finalizar();
			}
		});
		
		JButton btnNewButton = new JButton("Listar todos os times");
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				listagem();
			}
		});
		btnNewButton.setBounds(31, 22, 157, 31);
		frmTimes.getContentPane().add(btnNewButton);
		
				
		scrollPane = new JScrollPane();
		scrollPane.setBounds(26, 66, 819, 227);
		frmTimes.getContentPane().add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		table.setGridColor(Color.BLACK);
		table.setRequestFocusEnabled(false);
		table.setFocusable(false);
		table.setBackground(Color.WHITE);
		table.setFillsViewportHeight(true);
		table.setRowSelectionAllowed(true);
		table.setFont(new Font("Tahoma", Font.PLAIN, 12));
		table.setBorder(new LineBorder(new Color(0, 0, 0)));
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table.setShowGrid(true);
		table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		
		JLabel lblNewLabel = new JLabel("Nome do time:");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel.setBounds(31, 319, 122, 22);
		frmTimes.getContentPane().add(lblNewLabel);
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(131, 317, 184, 31);
		frmTimes.getContentPane().add(textField_1);
		
		textField_2 = new JTextField();
		textField_2.setColumns(10);
		textField_2.setBounds(395, 317, 184, 31);
		frmTimes.getContentPane().add(textField_2);
		
		JLabel lblOrigem = new JLabel("Origem:");
		lblOrigem.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblOrigem.setBounds(336, 319, 58, 22);
		frmTimes.getContentPane().add(lblOrigem);
		
		JButton btnNewButton_2 = new JButton("Apagar time selecionado");
		btnNewButton_2.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					if (table.getSelectedRow() >= 0) {
						String nome = (String) table.getValueAt(table.getSelectedRow(), 0);
						Fachada.apagarTime(nome);
						lblNewLabel_1.setText("Time apagado: " + nome);
						listagem();
					}
				}
				catch(Exception ex) {
					lblNewLabel_1.setText(ex.getMessage());
				}
			}
		});
		btnNewButton_2.setBounds(855, 81, 173, 23);
		frmTimes.getContentPane().add(btnNewButton_2);
		
		btnNewButton_3 = new JButton("Criar novo time");
		btnNewButton_3.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					if(textField_1.getText().isEmpty() ||
					   textField_2.getText().isEmpty() )
					{
						lblNewLabel_1.setText("Você deve digitar algo em ambos os campos.");
						return;
					}

					
					String nome = textField_1.getText();
					String origem = textField_2.getText();
					Time time = Fachada.criarTime(nome, origem);
					lblNewLabel_1.setText("Time criado com sucesso: "+time.getNome()+"de "+ time.getOrigem());
					listagem();
				}
				catch(Exception ex) {
					lblNewLabel_1.setText(ex.getMessage());
				}
			}
		});
		btnNewButton_3.setBounds(245, 370, 173, 23);
		frmTimes.getContentPane().add(btnNewButton_3);
		
		
		lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setBounds(10, 396, 230, 14);
		frmTimes.getContentPane().add(lblNewLabel_1);
		
	}
	
	public void listagem () {
		try{
			List<Time> lista = Fachada.listarTimes();

			//model contem todas as linhas e colunas da tabela
			DefaultTableModel model = new DefaultTableModel();
			//colunas
			model.addColumn("Nome");
			model.addColumn("Origem");
			model.addColumn("Jogos");
			
			//linhas
			for(Time time: lista) {
				model.addRow(new Object[] {
						time.getNome()+"", time.getOrigem(), time.getJogos()
				});
			}
			table.setModel(model);
			lblNewLabel_1.setText("Resultado da busca: "+lista.size()+ " times");
		}
		catch(Exception erro){
			lblNewLabel_1.setText(erro.getMessage());
		}
	}
	
}
