package com.omerfpekgoz.stok.project.ui;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.omerfpekgoz.stok.project.models.Staff;
import com.omerfpekgoz.stok.project.utils.CourseUtils;
import com.omerfpekgoz.stok.project.utils.dao.DbServicessBase;

public class LoginScreen extends JFrame{
	private JPanel panel;
	private JLabel lblKullancAd;
	private JTextField txtKullaniciadi;
	private JLabel lblifre;
	private JTextField txtSifre;
	private JButton btnGiris;
	private JButton btnIptal;
	private JLabel label;
	public LoginScreen() {
		initialize();
	}

	private void initialize() {
		setTitle("SATI\u015E VE STOK UYGULAMASI\r\n");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setBounds(300, 100, 680, 500);
		getContentPane().setLayout(null);
		getContentPane().add(getPanel());
	}
	private JPanel getPanel() {
		if (panel == null) {
			panel = new JPanel();
			panel.setBounds(0, 0, 664, 461);
			panel.setLayout(null);
			panel.add(getLblKullancAd());
			panel.add(getTxtKullaniciadi());
			panel.add(getLblifre());
			panel.add(getTxtSifre());
			panel.add(getBtnGiris());
			panel.add(getBtnIptal());
			panel.add(getLabel());
		}
		return panel;
	}
	private JLabel getLblKullancAd() {
		if (lblKullancAd == null) {
			lblKullancAd = new JLabel("Kullan\u0131c\u0131 Ad\u0131:");
			lblKullancAd.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 14));
			lblKullancAd.setForeground(Color.BLUE);
			lblKullancAd.setBounds(369, 67, 102, 27);
		}
		return lblKullancAd;
	}
	private JTextField getTxtKullaniciadi() {
		if (txtKullaniciadi == null) {
			txtKullaniciadi = new JTextField();
			txtKullaniciadi.setBounds(481, 72, 156, 20);
			txtKullaniciadi.setColumns(10);
		}
		return txtKullaniciadi;
	}
	private JLabel getLblifre() {
		if (lblifre == null) {
			lblifre = new JLabel("\u015Eifre:");
			lblifre.setForeground(Color.BLUE);
			lblifre.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 14));
			lblifre.setBounds(420, 126, 46, 27);
		}
		return lblifre;
	}
	private JTextField getTxtSifre() {
		if (txtSifre == null) {
			txtSifre = new JTextField();
			txtSifre.setColumns(10);
			txtSifre.setBounds(481, 131, 156, 20);
		}
		return txtSifre;
	}
	private JButton getBtnGiris() {
		if (btnGiris == null) {
			btnGiris = new JButton("G\u0130R\u0130\u015E");
			btnGiris.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					DbServicessBase<Staff> dao=new DbServicessBase<Staff>();
					Staff loginStaff=new Staff();
					loginStaff.setNameSurname(txtKullaniciadi.getText());
					loginStaff.setPassword(txtSifre.getText());
					List<Staff> staffList=dao.search(loginStaff);
					if (staffList.size()>0) {
						CourseUtils.loginedUser=staffList.get(0);
						JOptionPane.showMessageDialog(LoginScreen.this, "Giriþ Baþarýlý\n Hoþgeldiniz "+CourseUtils.loginedUser.getNameSurname());
						new MainScreen().setVisible(true);
						LoginScreen.this.dispose();
					}
					
					else {
						JOptionPane.showMessageDialog(LoginScreen.this, "Giriþ Baþarýsýz");
					}
				}
			});
			btnGiris.setBackground(new Color(255, 255, 255));
			btnGiris.setIcon(new ImageIcon("images\\giris.png"));
			btnGiris.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 14));
			btnGiris.setBounds(420, 207, 217, 55);
		}
		return btnGiris;
	}
	private JButton getBtnIptal() {
		if (btnIptal == null) {
			btnIptal = new JButton("\u0130PTAL");
			btnIptal.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					System.exit(0);
				}
			});
			btnIptal.setBackground(new Color(255, 255, 255));
			btnIptal.setIcon(new ImageIcon("images\\iptal.png"));
			btnIptal.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 14));
			btnIptal.setBounds(420, 288, 217, 55);
		}
		return btnIptal;
	}
	private JLabel getLabel() {
		if (label == null) {
			label = new JLabel("");
			label.setIcon(new ImageIcon("images\\login6.png"));
			label.setBounds(0, 0, 664, 461);
		}
		return label;
	}
}
