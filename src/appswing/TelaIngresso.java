package appswing;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;

import modelo.Ingresso;
import modelo.IngressoGrupo;
import modelo.IngressoIndividual;
import modelo.Jogo;
import regras_negocio.Fachada;

public class TelaIngresso {
	private JFrame frame;
	private JTable table;
	private JScrollPane scrollPane;
	private JLabel label;
	private JLabel label_6;
	private JLabel label_2;
	private JButton button;
	private JButton button_1;
	private JLabel label_3;
	private JButton button_2;
	private JButton button_3;



	/**
	 * Launch the application.
	 */
	//	public static void main(String[] args) {
	//		EventQueue.invokeLater(new Runnable() {
	//			public void run() {
	//				try {
	//					TelaJogo window = new TelaJogo();
	//					window.frame.setVisible(true);
	//				} catch (Exception e) {
	//					e.printStackTrace();
	//				}
	//			}
	//		});
	//	}

	/**
	 * Create the application.
	 */
	public TelaIngresso() {
		initialize();
		frame.setVisible(true);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setResizable(false);
		frame.setTitle("Ingressos");
		frame.setBounds(100, 100, 729, 385);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.addWindowListener(new WindowAdapter() {
			@Override
			public void windowOpened(WindowEvent e) {
				Fachada.inicializar();
				listagem();
			}
			@Override
			public void windowClosing(WindowEvent e) {
				Fachada.finalizar();
			}
		});

		scrollPane = new JScrollPane();
		scrollPane.setBounds(21, 43, 674, 148);
		frame.getContentPane().add(scrollPane);

		table = new JTable();
		table.setGridColor(Color.BLACK);
		table.setRequestFocusEnabled(false);
		table.setFocusable(false);
		table.setBackground(Color.WHITE);
		table.setFillsViewportHeight(true);
		table.setRowSelectionAllowed(true);
		table.setFont(new Font("Tahoma", Font.PLAIN, 14));
		scrollPane.setViewportView(table);
		table.setBorder(new LineBorder(new Color(0, 0, 0)));
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table.setShowGrid(true);
		table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);

		label = new JLabel("");
		label.setForeground(Color.BLUE);
		label.setBounds(21, 321, 688, 14);
		frame.getContentPane().add(label);

		label_6 = new JLabel("selecione");
		label_6.setBounds(21, 190, 431, 14);
		frame.getContentPane().add(label_6);

		label_2 = new JLabel("jogos:");
		label_2.setHorizontalAlignment(SwingConstants.LEFT);
		label_2.setFont(new Font("Tahoma", Font.PLAIN, 12));
		label_2.setBounds(467, 261, 47, 14);
		frame.getContentPane().add(label_2);

		button_1 = new JButton("Criar ingresso individual");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					String id = JOptionPane.showInputDialog("digite o id do jogo");
					IngressoIndividual ingresso = Fachada.criarIngressoIndividual(Integer.parseInt(id));
					label_3.setText("Codigo:" + ingresso.getCodigo());
					label_2.setText("Jogo:" + ingresso.getJogo().getId());
					label.setText("ingresso criado: ");
					listagem();
				}catch(NumberFormatException ex) {
					label.setText("id nao numerico:");
				}catch(Exception ex) {
					label.setText(ex.getMessage());
				}
			}
		});
		button_1.setFont(new Font("Tahoma", Font.PLAIN, 12));
		button_1.setBounds(21, 243, 201, 23);
		frame.getContentPane().add(button_1);

		button = new JButton("Listar");
		button.setFont(new Font("Tahoma", Font.PLAIN, 12));
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				listagem();
			}
		});
		button.setBounds(267, 9, 89, 23);
		frame.getContentPane().add(button);

		label_3 = new JLabel("codigo:");
		label_3.setHorizontalAlignment(SwingConstants.LEFT);
		label_3.setFont(new Font("Tahoma", Font.PLAIN, 12));
		label_3.setBounds(284, 261, 63, 14);
		frame.getContentPane().add(label_3);

		button_2 = new JButton("Cancelar Ingresso");
		button_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try{
					if (table.getSelectedRow() >= 0){
						String codigo = (String) table.getValueAt( table.getSelectedRow(), 1);
						Fachada.apagarIngresso(Integer.parseInt(codigo));
						label.setText("cancelou ingresso " +codigo);
						listagem();
					}
					else
						label.setText("ingresso nao selecionado");
				}
				catch(Exception ex) {
					label.setText(ex.getMessage());
				}
			}
		});
		button_2.setFont(new Font("Tahoma", Font.PLAIN, 12));
		button_2.setBounds(428, 9, 145, 23);
		frame.getContentPane().add(button_2);

		button_3 = new JButton("Criar ingresso grupo");
		button_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					String id="";
					ArrayList<Integer> lista = new ArrayList<>();
					//leitura dos ids
					do{
						try {
							id = JOptionPane.showInputDialog("digite o id do jogo ou <enter>");
							lista.add(Integer.parseInt(id));
						}
						catch(NumberFormatException ex) {
							label.setText("id nao numerico:");
						}
					}while(id.isEmpty());

					//converter o arraylist num array
					int[] array = lista.stream().mapToInt(Integer::intValue).toArray();
					IngressoGrupo ingresso = Fachada.criarIngressoGrupo(array);
					label_3.setText("Codigo:" + ingresso.getCodigo());
					label_2.setText("Jogos:" + array);
					label.setText("ingresso criado: ");
					listagem();
				}catch(Exception ex) {
					label.setText(ex.getMessage());
				}
			}
		});
		button_3.setFont(new Font("Tahoma", Font.PLAIN, 12));
		button_3.setBounds(21, 272, 201, 23);
		frame.getContentPane().add(button_3);
	}

	public void listagem() {
		try{
			List<Ingresso> lista = Fachada.listarIngressos();

			//model contem todas as linhas e colunas da tabela
			DefaultTableModel model = new DefaultTableModel();

			//colunas
			model.addColumn("tipo");
			model.addColumn("codigo");
			model.addColumn("valor");
			model.addColumn("jogos");

			//linhas
			String texto;
			for(Ingresso ingresso : lista) {
				if(ingresso instanceof IngressoIndividual ind) {
					int id = ind.getJogo().getId();
					model.addRow(new Object[]{"Individual" ,ingresso.getCodigo(), ingresso.calcularValor(), id});
				}
				else 	
					if(ingresso instanceof IngressoGrupo gp) {
						texto="";
						for(Jogo j : gp.getJogos()) 	//obter os id  dos jogos
							texto += j.getId()+ "," ;

						model.addRow(new Object[]{"Grupo" ,ingresso.getCodigo(), ingresso.calcularValor(), texto});
					}
			}
			
			table.setModel(model);
			label_6.setText("resultados: "+lista.size()+ " ingressos   - selecione uma linha");
		}
		catch(Exception erro){
			label.setText(erro.getMessage());
		}
	}
}
