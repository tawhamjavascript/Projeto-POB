package appswing;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.text.ParseException;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.MaskFormatter;

import modelo.Ingresso;
import modelo.IngressoGrupo;
import modelo.IngressoIndividual;
import modelo.Jogo;
import regras_negocio.Fachada;

public class TelaJogo {
	private JFrame frame;
	private JTable table;
	private JScrollPane scrollPane;
	private JButton button;
	private JButton button_4;
	private JTextField textField_1;
	private JTextField textField_2;
	private JLabel label;
	private JLabel label_1;
	private JLabel label_5;
	private JLabel label_3;
	private JLabel label_8;
	private JLabel label_6;
	private JButton button_3;
	private JButton button_5;
	private JFormattedTextField formattedTextField;
	private JLabel label_2;
	private JTextField textField;
	private JLabel label_4;
	private JTextField textField_3;
	private JButton button_1;
	private JTextField textField_4;
	private JButton button_2;



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
	public TelaJogo() {
		initialize();
		frame.setVisible(true);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setFont(new Font("Tahoma", Font.PLAIN, 12));
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

		frame.setTitle("Jogos");
		frame.setBounds(100, 100, 912, 351);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		scrollPane = new JScrollPane();
		scrollPane.setBounds(26, 42, 844, 120);
		frame.getContentPane().add(scrollPane);

		table = new JTable();
		table.setGridColor(Color.BLACK);
		table.setRequestFocusEnabled(false);
		table.setFocusable(false);
		table.setBackground(Color.WHITE);
		table.setFillsViewportHeight(true);
		table.setRowSelectionAllowed(true);
		table.setFont(new Font("Tahoma", Font.PLAIN, 12));
		scrollPane.setViewportView(table);
		table.setBorder(new LineBorder(new Color(0, 0, 0)));
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table.setShowGrid(true);
		table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);


		button = new JButton("Criar jogo");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					if(formattedTextField.getText().isEmpty() || 
							textField_1.getText().isEmpty() ||
							textField_2.getText().isEmpty() ||
							textField_3.getText().isEmpty() ||
							textField.getText().isEmpty() )
					{
						label.setText("campo vazio");
						return;
					}

					String data = formattedTextField.getText();
					String local = textField_1.getText();
					String preco = textField_2.getText();
					String nome1 = textField.getText();
					String nome2 = textField_3.getText();
					Jogo jogo = Fachada.criarJogo(data, local, 50000, Double.parseDouble(preco),nome1,nome2);
					label.setText("jogo criado: "+jogo.getId());
					listagem();
				}
				catch(Exception ex) {
					label.setText(ex.getMessage());
				}
			}
		});
		button.setFont(new Font("Tahoma", Font.PLAIN, 12));
		button.setBounds(662, 210, 95, 23);
		frame.getContentPane().add(button);

		label = new JLabel("");
		label.setForeground(Color.BLUE);
		label.setBackground(Color.RED);
		label.setBounds(26, 287, 830, 14);
		frame.getContentPane().add(label);

		label_1 = new JLabel("data");
		label_1.setHorizontalAlignment(SwingConstants.LEFT);
		label_1.setFont(new Font("Dialog", Font.PLAIN, 12));
		label_1.setBounds(26, 214, 43, 14);
		frame.getContentPane().add(label_1);

		label_5 = new JLabel("local");
		label_5.setHorizontalAlignment(SwingConstants.LEFT);
		label_5.setFont(new Font("Dialog", Font.PLAIN, 12));
		label_5.setBounds(266, 215, 50, 14);
		frame.getContentPane().add(label_5);

		try {
			formattedTextField = new JFormattedTextField(new MaskFormatter("##/##/####"));
		} 
		catch (ParseException e1) {
			label.setText("formato do campo data invalido");
		}
		formattedTextField.setBounds(67, 212, 80, 20);
		frame.getContentPane().add(formattedTextField);

		textField_1 = new JTextField();
		textField_1.setFont(new Font("Dialog", Font.PLAIN, 12));
		textField_1.setColumns(10);
		textField_1.setBounds(307, 212, 169, 20);
		frame.getContentPane().add(textField_1);

		label_3 = new JLabel("(dd/mm/aaaa)");
		label_3.setBounds(158, 215, 88, 14);
		frame.getContentPane().add(label_3);

		label_8 = new JLabel("selecione");
		label_8.setBounds(26, 163, 561, 14);
		frame.getContentPane().add(label_8);

		label_6 = new JLabel("preco");
		label_6.setHorizontalAlignment(SwingConstants.LEFT);
		label_6.setFont(new Font("Dialog", Font.PLAIN, 12));
		label_6.setBounds(507, 215, 43, 14);
		frame.getContentPane().add(label_6);

		textField_2 = new JTextField();
		textField_2.setFont(new Font("Dialog", Font.PLAIN, 12));
		textField_2.setColumns(10);
		textField_2.setBounds(548, 212, 71, 20);
		frame.getContentPane().add(textField_2);

		button_4 = new JButton("Listar todos");
		button_4.setFont(new Font("Tahoma", Font.PLAIN, 12));
		button_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				listagem();
			}
		});
		button_4.setBounds(26, 8, 110, 23);
		frame.getContentPane().add(button_4);

		button_3 = new JButton("Ver ingressos grupo");
		button_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					if (table.getSelectedRow() >= 0){
						String id = (String) table.getValueAt( table.getSelectedRow(), 0);
						Jogo jogo = Fachada.localizarJogo(Integer.parseInt(id));
						String codigos= "Ingressos de grupo:";
						for(Ingresso ing : jogo.getIngressos())
							if(ing instanceof IngressoGrupo)
								codigos+="\n"+ing.getCodigo();

						JOptionPane.showMessageDialog(frame, codigos);
					}
					else
						label.setText("selecione uma linha");
				}
				catch(Exception erro) {
					label.setText(erro.getMessage());
				}
			}
		});
		button_3.setFont(new Font("Tahoma", Font.PLAIN, 12));
		button_3.setBounds(682, 8, 180, 23);
		frame.getContentPane().add(button_3);

		button_5 = new JButton("Ver ingressos individual");
		button_5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					if (table.getSelectedRow() >= 0){
						String id = (String) table.getValueAt( table.getSelectedRow(), 0);
						Jogo jogo = Fachada.localizarJogo(Integer.parseInt(id));
						String codigos= "Ingressos individuais:";
						for(Ingresso ing : jogo.getIngressos())
							if(ing instanceof IngressoIndividual)
								codigos+="\n"+ing.getCodigo();

						JOptionPane.showMessageDialog(frame, codigos);
					}
					else
						label.setText("selecione uma linha");
				}
				catch(Exception erro) {
					label.setText(erro.getMessage());
				}
			}
		});
		button_5.setFont(new Font("Tahoma", Font.PLAIN, 12));
		button_5.setBounds(492, 8, 180, 23);
		frame.getContentPane().add(button_5);

		label_2 = new JLabel("time1");
		label_2.setHorizontalAlignment(SwingConstants.LEFT);
		label_2.setFont(new Font("Dialog", Font.PLAIN, 12));
		label_2.setBounds(26, 242, 50, 14);
		frame.getContentPane().add(label_2);

		textField = new JTextField();
		textField.setFont(new Font("Dialog", Font.PLAIN, 12));
		textField.setColumns(10);
		textField.setBounds(67, 239, 169, 20);
		frame.getContentPane().add(textField);

		label_4 = new JLabel("time2");
		label_4.setHorizontalAlignment(SwingConstants.LEFT);
		label_4.setFont(new Font("Dialog", Font.PLAIN, 12));
		label_4.setBounds(266, 242, 50, 14);
		frame.getContentPane().add(label_4);

		textField_3 = new JTextField();
		textField_3.setFont(new Font("Dialog", Font.PLAIN, 12));
		textField_3.setColumns(10);
		textField_3.setBounds(307, 239, 169, 20);
		frame.getContentPane().add(textField_3);

		button_1 = new JButton("Filtrar por data:");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				listagemPorData();
			}
		});
		button_1.setFont(new Font("Tahoma", Font.PLAIN, 12));
		button_1.setBounds(186, 8, 147, 23);
		frame.getContentPane().add(button_1);

		textField_4 = new JTextField();
		textField_4.setBounds(343, 11, 86, 20);
		frame.getContentPane().add(textField_4);
		textField_4.setColumns(10);
		
		button_2 = new JButton("Apagar jogo");
		button_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (table.getSelectedRow() >= 0){
					String id = (String) table.getValueAt( table.getSelectedRow(), 0);
					System.out.println(id);
					try {
						Fachada.apagarJogo(Integer.parseInt(id));
						listagem();
					} catch (NumberFormatException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			}
		});
		button_2.setFont(new Font("Tahoma", Font.PLAIN, 12));
		button_2.setBounds(650, 173, 123, 23);
		frame.getContentPane().add(button_2);
	}

	//*****************************
	public void listagem () {
		try{
			List<Jogo> lista = Fachada.listarJogos();

			//model contem todas as linhas e colunas da tabela
			DefaultTableModel model = new DefaultTableModel();
			//colunas
			model.addColumn("id");
			model.addColumn("data");
			model.addColumn("loca");
			model.addColumn("estoque");
			model.addColumn("preco");
			model.addColumn("time1");
			model.addColumn("time2");
			model.addColumn("arrecadacao");
			//linhas
			for(Jogo jogo : lista) {
				model.addRow(new Object[]{jogo.getId()+"", jogo.getData(), jogo.getLocal(), jogo.getEstoque(),jogo.getPreco(),
						jogo.getTime1().getNome(), jogo.getTime2().getNome(), jogo.obterValorArrecadado()});
			}
			table.setModel(model);
			label_8.setText("resultados: "+lista.size()+ " jogos  - selecione uma linha");
		}
		catch(Exception erro){
			label.setText(erro.getMessage());
		}
	}

	public void listagemPorData () {
		try{
			String data= textField_4.getText();
			List<Jogo> lista = Fachada.listarJogos(data);

			//model contem todas as linhas e colunas da tabela
			DefaultTableModel model = new DefaultTableModel();
			//colunas
			model.addColumn("id");
			model.addColumn("data");
			model.addColumn("loca");
			model.addColumn("estoque");
			model.addColumn("preco");
			model.addColumn("time1");
			model.addColumn("time2");
			model.addColumn("arrecadacao");
			//linhas
			for(Jogo jogo : lista) {
				model.addRow(new Object[]{jogo.getId()+"", jogo.getData(), jogo.getLocal(), jogo.getEstoque(),jogo.getPreco(),
						jogo.getTime1().getNome(), jogo.getTime2().getNome(), jogo.obterValorArrecadado()});
			}
			table.setModel(model);
			label_8.setText("resultados: "+lista.size()+ " jogos  - selecione uma linha");
		}
		catch(Exception erro){
			label.setText(erro.getMessage());
		}

	}
}
