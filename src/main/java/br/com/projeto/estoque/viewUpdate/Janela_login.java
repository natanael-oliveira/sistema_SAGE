package br.com.projeto.estoque.viewUpdate;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.formdev.flatlaf.FlatDarkLaf;
import com.formdev.flatlaf.FlatIntelliJLaf;

import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.ImageIcon;
import javax.swing.JProgressBar;
import java.awt.Font;
import java.awt.SystemColor;
import javax.swing.JFormattedTextField;
import java.awt.Color;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import javax.swing.JTabbedPane;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Janela_login extends JFrame {

	private JPanel contentPane;
	private JPasswordField passwordField;
	private JPasswordField passwordField_1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UIManager.setLookAndFeel( new FlatDarkLaf());
					Janela_login frame = new Janela_login();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Janela_login() {
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100,600, 697);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setLocationRelativeTo(null);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setOpaque(true);
		tabbedPane.setBounds(37, 290, 520, 349);
		contentPane.add(tabbedPane);
		
		JPanel panel = new JPanel();
		tabbedPane.addTab("Entrar como gerente", null, panel, null);
		panel.setLayout(null);
		
		JLabel lblNewLabel_2 = new JLabel("CPF:");
		lblNewLabel_2.setBackground(SystemColor.control);
		lblNewLabel_2.setForeground(SystemColor.controlHighlight);
		lblNewLabel_2.setFont(new Font("Bahnschrift", Font.PLAIN, 14));
		lblNewLabel_2.setBounds(90, 63, 330, 14);
		panel.add(lblNewLabel_2);
		
		JFormattedTextField formattedTextField = new JFormattedTextField();
		formattedTextField.setForeground(Color.BLACK);
		formattedTextField.setBackground(SystemColor.control);
		formattedTextField.setBounds(90, 84, 330, 35);
		panel.add(formattedTextField);
		
		JLabel lblNewLabel_2_1 = new JLabel("SENHA:");
		lblNewLabel_2_1.setBackground(SystemColor.control);
		lblNewLabel_2_1.setForeground(SystemColor.controlHighlight);
		lblNewLabel_2_1.setFont(new Font("Bahnschrift", Font.PLAIN, 14));
		lblNewLabel_2_1.setBounds(90, 130, 330, 14);
		panel.add(lblNewLabel_2_1);
		
		passwordField = new JPasswordField();
		passwordField.setForeground(Color.BLACK);
		passwordField.setBackground(SystemColor.control);
		passwordField.setBounds(90, 152, 330, 35);
		panel.add(passwordField);
		
		JButton btnVoltar = new JButton("Voltar");
		//btnVoltar.setBackground(SystemColor.menu);
		btnVoltar.setBounds(115, 216, 135, 35);
		panel.add(btnVoltar);
		
		JButton btnNewButton = new JButton("Entrar");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Janela_principal jp = new Janela_principal();
				jp.setVisible(true);
				dispose();
			}
		});
		//btnNewButton.setBackground(SystemColor.menu);
		btnNewButton.setBounds(273, 216, 135, 35);
		panel.add(btnNewButton);
		
		JPanel panel_1 = new JPanel();
		panel_1.setLayout(null);
		tabbedPane.addTab("Entrar como supervisor", null, panel_1, null);
		
		JLabel lblNewLabel_2_2 = new JLabel("CPF:");
		lblNewLabel_2_2.setForeground(SystemColor.controlHighlight);
		lblNewLabel_2_2.setFont(new Font("Bahnschrift", Font.PLAIN, 14));
		lblNewLabel_2_2.setBounds(90, 63, 330, 14);
		panel_1.add(lblNewLabel_2_2);
		
		JFormattedTextField formattedTextField_1 = new JFormattedTextField();
		formattedTextField_1.setForeground(Color.BLACK);
		formattedTextField_1.setBackground(SystemColor.control);
		formattedTextField_1.setBounds(90, 84, 330, 35);
		panel_1.add(formattedTextField_1);
		
		JLabel lblNewLabel_2_1_1 = new JLabel("SENHA:");
		lblNewLabel_2_1_1.setForeground(SystemColor.controlHighlight);
		lblNewLabel_2_1_1.setFont(new Font("Bahnschrift", Font.PLAIN, 14));
		lblNewLabel_2_1_1.setBounds(90, 130, 330, 14);
		panel_1.add(lblNewLabel_2_1_1);
		
		passwordField_1 = new JPasswordField();
		passwordField_1.setForeground(Color.BLACK);
		passwordField_1.setBackground(SystemColor.control);
		passwordField_1.setBounds(90, 152, 330, 35);
		panel_1.add(passwordField_1);
		
		JButton btnVoltar_1 = new JButton("Voltar");
		//btnVoltar_1.setBackground(SystemColor.menu);
		btnVoltar_1.setBounds(115, 216, 135, 35);
		panel_1.add(btnVoltar_1);
		
		JButton btnNewButton_1 = new JButton("Entrar");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Janela_principal jp = new Janela_principal();
				jp.setVisible(true);
				dispose();
			}
		});
		//btnNewButton_1.setBackground(SystemColor.menu);
		btnNewButton_1.setBounds(273, 216, 135, 35);
		panel_1.add(btnNewButton_1);
		JLabel lblNewLabel = new JLabel(new ImageIcon(getClass().getResource("/sage_icons/fundo-login.png")));
		lblNewLabel.setBounds(0, 0, 594, 672);
		contentPane.add(lblNewLabel);
	}
}