package appswing;

import regras_negocio.Fachada;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import java.awt.FlowLayout;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import javax.swing.JComboBox;
import java.awt.Font;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.List;

import javax.swing.JTextArea;

import modelo.Time;

import javax.swing.JTable;


public class TelaTime {

    private JFrame frmTimes;
    private JTable table;
    private JComboBox<String> comboBox;

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
        frmTimes.setBounds(100, 100, 735, 429);
        frmTimes.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frmTimes.getContentPane().setLayout(null);

        JLabel lblNewLabel = new JLabel("Time");
        lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 13));
        lblNewLabel.setBounds(10, 11, 46, 14);
        frmTimes.getContentPane().add(lblNewLabel);

        comboBox = new JComboBox();
        comboBox.setBounds(10, 27, 267, 32);

        frmTimes.getContentPane().add(comboBox);

        frmTimes.addWindowListener(new WindowAdapter() {
            @Override
            public void windowOpened(WindowEvent e) {
                Fachada.inicializar();
                listarCombo();

            }
            @Override
            public void windowClosing(WindowEvent e) {
                Fachada.finalizar();
            }
        });



        JLabel lblNewLabel_1 = new JLabel("Origem");
        lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 13));
        lblNewLabel_1.setBounds(313, 10, 70, 16);
        frmTimes.getContentPane().add(lblNewLabel_1);

        JTextArea textArea = new JTextArea();
        textArea.setBounds(313, 31, 230, 22);
        frmTimes.getContentPane().add(textArea);

        table = new JTable();
        table.setBounds(10, 94, 699, 285);
        frmTimes.getContentPane().add(table);

        JLabel lblNewLabel_2 = new JLabel("Próximos jogos");
        lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 13));
        lblNewLabel_2.setBounds(10, 70, 97, 16);
        frmTimes.getContentPane().add(lblNewLabel_2);
    }
    

    
    public void listarCombo() {
	  List<Time> times = Fachada.listarTimes();
	  for (Time time : times) {
	  	comboBox.addItem(time.getNome());
	  }
    }
    
}
