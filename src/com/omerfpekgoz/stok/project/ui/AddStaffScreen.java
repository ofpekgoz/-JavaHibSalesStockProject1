package com.omerfpekgoz.stok.project.ui;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import com.omerfpekgoz.stok.project.models.Category;
import com.omerfpekgoz.stok.project.models.Products;
import com.omerfpekgoz.stok.project.models.Staff;
import com.omerfpekgoz.stok.project.models.UserRoleTypes;
import com.omerfpekgoz.stok.project.utils.CourseUtils;
import com.omerfpekgoz.stok.project.utils.dao.DbServicessBase;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.persistence.EnumType;
import javax.persistence.Id;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.event.ActionListener;
import java.util.List;
import java.awt.event.ActionEvent;
import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Toolkit;

public class AddStaffScreen extends JFrame {
	private JPanel panel;
	private JLabel lblAdsoyad;
	private JTextField txtPersonelAdi;
	private JLabel lblifre;
	private JTextField txtSifre;
	private JLabel lblEmail;
	private JTextField txtEmail;
	private JLabel lblTelno;
	private JTextField txtTelno;
	private JLabel lblYetki;
	private JComboBox cmbYetki;
	private JButton btnKaydet;
	private JButton btnSil;
	private JButton btnGuncelle;
	private JButton btnTemizle;
	private JButton btnCikis;
	private JScrollPane scrollPane;
	private JTable tblPersonel;
	private JLabel lblMesaj;
	private Integer selectedStaffId;
	public AddStaffScreen() {
		setIconImage(Toolkit.getDefaultToolkit().getImage("images\\kullanýcýislemleri.png"));
		setTitle("Kullan\u0131c\u0131 \u0130\u015Flemleri");
		initialize();
		
	}

	private void initialize() {
		getContentPane().setLayout(null);
		getContentPane().add(getPanel());
		getContentPane().add(getBtnCikis());
		getContentPane().add(getScrollPane());
		getContentPane().add(getLblMesaj());
		setBounds(-8, 20, 1370, 650);

		personelTablosuGoster();
		
		
	}
	DefaultTableModel model=new DefaultTableModel();
	

	public void personelTablosuGoster() {
	
	DbServicessBase<Staff> dao=new DbServicessBase<Staff>();
	Staff temp=new Staff();
	List<Staff> personel_listesi=dao.getAllRows(temp);
	
	String [] columnNames= {"ID","AD-SOYAD","ÞÝFRE","E-MAÝL","TEL. NO.","YETKÝ"};
	Object [][] data=new Object [personel_listesi.size()][columnNames.length];
	
	
	for (int i = 0; i < personel_listesi.size(); i++) {
		
		data[i][0]=CourseUtils.getValue(personel_listesi.get(i).getId());
		data[i][1]=CourseUtils.getValue(personel_listesi.get(i).getNameSurname());
		data[i][2]=CourseUtils.getValue(personel_listesi.get(i).getPassword());
		data[i][3]=CourseUtils.getValue(personel_listesi.get(i).getEmail());
		data[i][4]=CourseUtils.getValue(personel_listesi.get(i).getPhoneNumber());
		data[i][5]=CourseUtils.getValue(personel_listesi.get(i).getRole());
		
		
	}
	
	model=new DefaultTableModel(data,columnNames);
	tblPersonel.setModel(model);
	
	DefaultComboBoxModel roleType = new  DefaultComboBoxModel(UserRoleTypes.values());
	cmbYetki.setModel(roleType);
	
	}
	private JPanel getPanel() {
		if (panel == null) {
			panel = new JPanel();
			panel.setBorder(new TitledBorder(null, "Personel Bilgileri", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			panel.setBounds(10, 30, 299, 558);
			panel.setLayout(null);
			panel.add(getLblAdsoyad());
			panel.add(getTxtPersonelAdi());
			panel.add(getLblifre());
			panel.add(getTxtSifre());
			panel.add(getLblEmail());
			panel.add(getTxtEmail());
			panel.add(getLblTelno());
			panel.add(getTxtTelno());
			panel.add(getLblYetki());
			panel.add(getCmbYetki());
			panel.add(getBtnKaydet());
			panel.add(getBtnSil());
			panel.add(getBtnGuncelle());
			panel.add(getBtnTemizle());
		}
		return panel;
	}
	private JLabel getLblAdsoyad() {
		if (lblAdsoyad == null) {
			lblAdsoyad = new JLabel("Ad-Soyad:");
			lblAdsoyad.setBounds(20, 60, 75, 14);
		}
		return lblAdsoyad;
	}
	private JTextField getTxtPersonelAdi() {
		if (txtPersonelAdi == null) {
			txtPersonelAdi = new JTextField();
			txtPersonelAdi.setBounds(116, 57, 127, 20);
			txtPersonelAdi.setColumns(10);
		}
		return txtPersonelAdi;
	}
	private JLabel getLblifre() {
		if (lblifre == null) {
			lblifre = new JLabel("\u015Eifre:");
			lblifre.setBounds(20, 118, 75, 14);
		}
		return lblifre;
	}
	private JTextField getTxtSifre() {
		if (txtSifre == null) {
			txtSifre = new JTextField();
			txtSifre.setColumns(10);
			txtSifre.setBounds(116, 115, 127, 20);
		}
		return txtSifre;
	}
	private JLabel getLblEmail() {
		if (lblEmail == null) {
			lblEmail = new JLabel("E-Mail:");
			lblEmail.setBounds(20, 176, 75, 14);
		}
		return lblEmail;
	}
	private JTextField getTxtEmail() {
		if (txtEmail == null) {
			txtEmail = new JTextField();
			txtEmail.setColumns(10);
			txtEmail.setBounds(116, 173, 127, 20);
		}
		return txtEmail;
	}
	private JLabel getLblTelno() {
		if (lblTelno == null) {
			lblTelno = new JLabel("Tel.No.:");
			lblTelno.setBounds(20, 244, 75, 14);
		}
		return lblTelno;
	}
	private JTextField getTxtTelno() {
		if (txtTelno == null) {
			txtTelno = new JTextField();
			txtTelno.setColumns(10);
			txtTelno.setBounds(116, 241, 127, 20);
		}
		return txtTelno;
	}
	private JLabel getLblYetki() {
		if (lblYetki == null) {
			lblYetki = new JLabel("Yetki:");
			lblYetki.setBounds(20, 307, 75, 14);
		}
		return lblYetki;
	}
	private JComboBox getCmbYetki() {
		if (cmbYetki == null) {
			cmbYetki = new JComboBox();
			cmbYetki.setBounds(116, 304, 127, 20);
		}
		return cmbYetki;
	}
	private JButton getBtnKaydet() {
		if (btnKaydet == null) {
			btnKaydet = new JButton("Kaydet");
			btnKaydet.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					DbServicessBase<Staff> dao=new DbServicessBase<Staff>();
					Staff newStaff=new Staff();
					newStaff.setNameSurname(txtPersonelAdi.getText().toString());
					newStaff.setPassword(txtSifre.getText().toString());
					newStaff.setEmail(txtEmail.getText().toString());
					newStaff.setPhoneNumber(txtTelno.getText().toString());
					newStaff.setRole((UserRoleTypes) cmbYetki.getModel().getSelectedItem());
				
					
					if (dao.save(newStaff)) {
						lblMesaj.setText("Personel Baþarý Ýle Kaydedildi");
						personelTablosuGoster();
						
					}
					else {
						lblMesaj.setText("Personel Kaydedilemedi");
					}
				}
			});
			btnKaydet.setIcon(new ImageIcon("images\\ekle4.png"));
			btnKaydet.setBounds(10, 387, 127, 47);
		}
		return btnKaydet;
	}
	private JButton getBtnSil() {
		if (btnSil == null) {
			btnSil = new JButton("Sil");
			btnSil.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					DbServicessBase<Staff> dao=new DbServicessBase<Staff>();
					Staff deletedStaff=new Staff();
					deletedStaff.setId(selectedStaffId);
					if (dao.delete(deletedStaff)) {
						lblMesaj.setText("Personel Baþarý Ýle Silindi");
						personelTablosuGoster();
					}
					else {
						lblMesaj.setText("Personel Silinemedi");

					}
				}
			});
			btnSil.setIcon(new ImageIcon("images\\delete.png"));
			btnSil.setBounds(162, 387, 127, 47);
		}
		return btnSil;
	}
	private JButton getBtnGuncelle() {
		if (btnGuncelle == null) {
			btnGuncelle = new JButton("G\u00FCncelle");
			btnGuncelle.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					DbServicessBase<Staff> dao=new DbServicessBase<Staff>();
					Staff updatedStaff=new Staff();
					updatedStaff.setId(selectedStaffId);
					updatedStaff.setNameSurname(txtPersonelAdi.getText().toString());
					updatedStaff.setPassword(txtSifre.getText().toString());
					updatedStaff.setEmail(txtEmail.getText().toString());
					updatedStaff.setPhoneNumber(txtTelno.getText().toString());
					updatedStaff.setRole( (UserRoleTypes) cmbYetki.getModel().getSelectedItem());
				
					
					if (dao.update(updatedStaff)) {
						lblMesaj.setText("Personel Baþarý Ýle Güncellendi");
						personelTablosuGoster();
						
					}
					else {
						lblMesaj.setText("Personel Güncellenemedi");
					}
				}
			});
			btnGuncelle.setIcon(new ImageIcon("images\\guncelle2.png"));
			btnGuncelle.setBounds(10, 467, 127, 47);
		}
		return btnGuncelle;
	}
	private JButton getBtnTemizle() {
		if (btnTemizle == null) {
			btnTemizle = new JButton("Temizle");
			btnTemizle.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					txtEmail.setText("");
					txtPersonelAdi.setText("");
					txtSifre.setText("");
					txtTelno.setText("");
					lblMesaj.setText("");
					cmbYetki.getModel().setSelectedItem(null);
				}
			});
			btnTemizle.setIcon(new ImageIcon("images\\temizle.png"));
			btnTemizle.setBounds(162, 467, 127, 47);
		}
		return btnTemizle;
	}
	private JButton getBtnCikis() {
		if (btnCikis == null) {
			btnCikis = new JButton("\u00C7\u0131k\u0131\u015F");
			btnCikis.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					AddStaffScreen.this.dispose();
				}
			});
			btnCikis.setIcon(new ImageIcon("images\\cikis.png"));
			btnCikis.setBounds(1163, 14, 113, 47);
		}
		return btnCikis;
	}
	private JScrollPane getScrollPane() {
		if (scrollPane == null) {
			scrollPane = new JScrollPane();
			scrollPane.setBounds(368, 89, 908, 479);
			scrollPane.setViewportView(getTblPersonel());
		}
		return scrollPane;
	}
	private JTable getTblPersonel() {
		if (tblPersonel == null) {
			tblPersonel = new JTable();
			tblPersonel.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent arg0) {
					int row=tblPersonel.getSelectedRow();
					
					selectedStaffId=Integer.valueOf(tblPersonel.getValueAt(row, 0).toString());
					
					txtPersonelAdi.setText(tblPersonel.getValueAt(row,1).toString());
					txtSifre.setText(tblPersonel.getValueAt(row, 2).toString());
					txtEmail.setText(tblPersonel.getValueAt(row, 3).toString());
					txtTelno.setText(tblPersonel.getValueAt(row, 4).toString());
					
					
					
					Staff staff=new Staff();
					
					String p=staff.toString();
					String u=(String) tblPersonel.getValueAt(row, 5);
					String[] p1 =p.split("-");
					staff.setId(Integer.valueOf(p1[0]));
					staff.setNameSurname(p1[1]);
					staff.setRole(UserRoleTypes.valueOf(u));
					cmbYetki.getModel().setSelectedItem(staff.getRole());
				}
			});
		}
		return tblPersonel;
	}
	private JLabel getLblMesaj() {
		if (lblMesaj == null) {
			lblMesaj = new JLabel("");
			lblMesaj.setForeground(Color.RED);
			lblMesaj.setBackground(Color.RED);
			lblMesaj.setBounds(368, 579, 525, 21);
		}
		return lblMesaj;
	}
}
