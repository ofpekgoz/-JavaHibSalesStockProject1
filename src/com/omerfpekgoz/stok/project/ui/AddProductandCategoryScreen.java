package com.omerfpekgoz.stok.project.ui;

import java.awt.Color;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputMethodEvent;
import java.awt.event.InputMethodListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;


import com.omerfpekgoz.stok.project.models.Category;
import com.omerfpekgoz.stok.project.models.Products;
import com.omerfpekgoz.stok.project.utils.CourseUtils;
import com.omerfpekgoz.stok.project.utils.dao.DbServicessBase;
import com.toedter.calendar.JDateChooser;
import javax.swing.JScrollPane;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class AddProductandCategoryScreen extends JFrame {
	private JLabel lblrnAd;
	private JTextField txtUrunAdi;
	private JLabel lblrnKategori;
	private JLabel lblFiyat;
	private JTextField txtFiyat;
	private JLabel lblEklemeTarihi;
	private JDateChooser dateUrunEklemeTarihi;
	private JButton btnEkle;
	private JLabel lblAdet;
	private JTextField txtAdet;
	private JPanel panel;
	private JButton btnSil;
	private JButton btnGuncelle;
	private JButton btnTemizle;
	private JLabel lblKategoriAd;
	private JTextField txtKategoriAdi;
	private JButton btnKategoriEkle;
	private JLabel lblMesaj;
	private JComboBox cmbUrunKategori;
	private String gunEkleme;
	private JScrollPane scrollPane;
	private Integer selectedProductId;
	private JTable tblUrunlerTablosu;
	public AddProductandCategoryScreen() {
		
		initialize();
		
	}

	private void initialize() {
		setTitle("\u00DCr\u00FCn Ekle/Sil/G\u00FCncelle");
		setIconImage(Toolkit.getDefaultToolkit().getImage("images\\ürünler.png"));
		setBounds(-10, 20, 1370, 650);
		getContentPane().setLayout(null);
		getContentPane().add(getPanel());
		getContentPane().add(getLblKategoriAd());
		getContentPane().add(getTxtKategoriAdi());
		getContentPane().add(getBtnKategoriEkle());
		getContentPane().add(getLblMesaj());
		getContentPane().add(getScrollPane());
		
		urunTablosuGoster();
		
		DbServicessBase<Category> dao=new DbServicessBase<Category>();
		List<Category> categories=dao.getAllRows(new Category());
		DefaultComboBoxModel model=new DefaultComboBoxModel(categories.toArray());
		cmbUrunKategori.setModel(model);
		
		txtAdet.setText("");
		txtFiyat.setText("");
		txtKategoriAdi.setText("");
		txtUrunAdi.setText("");
		dateUrunEklemeTarihi.setDate(null);
		cmbUrunKategori.setSelectedItem(null);
		
		
		
	}
	DefaultTableModel model=new DefaultTableModel();
	

	public void urunTablosuGoster() {
	
	DbServicessBase<Products> dao=new DbServicessBase<Products>();
	Products temp=new Products();
	List<Products> urun_listesi=dao.getAllRows(temp);
	
	String [] columnNames= {"ID","KATEGORÝ","ÜRÜN ADI","ÜRÜN ADEDÝ","ÜRÜN FÝYATI","EKLEME TARÝHÝ"};
	Object [][] data=new Object [urun_listesi.size()][columnNames.length];
	
	
	for (int i = 0; i < urun_listesi.size(); i++) {
		
		data[i][0]=CourseUtils.getValue(urun_listesi.get(i).getId());
		data[i][1]=CourseUtils.getValue(urun_listesi.get(i).getCategory());
		data[i][2]=CourseUtils.getValue(urun_listesi.get(i).getName());
		data[i][3]=CourseUtils.getValue(urun_listesi.get(i).getPiece());
		data[i][4]=CourseUtils.getValue(urun_listesi.get(i).getPrice());
		data[i][5]=CourseUtils.getValue(urun_listesi.get(i).getDate());
		
		
	}
	
	model=new DefaultTableModel(data,columnNames);
	tblUrunlerTablosu.setModel(model);
	}
	private JLabel getLblrnAd() {
		if (lblrnAd == null) {
			lblrnAd = new JLabel("\u00DCr\u00FCn Ad\u0131:");
			lblrnAd.setBounds(80, 41, 55, 14);
		}
		return lblrnAd;
	}
	private JTextField getTxtUrunAdi() {
		if (txtUrunAdi == null) {
			txtUrunAdi = new JTextField();
			txtUrunAdi.setBounds(154, 38, 127, 20);
			txtUrunAdi.setColumns(10);
		}
		return txtUrunAdi;
	}
	private JLabel getLblrnKategori() {
		if (lblrnKategori == null) {
			lblrnKategori = new JLabel("\u00DCr\u00FCn Kategori:");
			lblrnKategori.setBounds(63, 87, 81, 14);
		}
		return lblrnKategori;
	}
	private JLabel getLblFiyat() {
		if (lblFiyat == null) {
			lblFiyat = new JLabel("Fiyat:");
			lblFiyat.setBounds(105, 136, 55, 14);
		}
		return lblFiyat;
	}
	private JTextField getTxtFiyat() {
		if (txtFiyat == null) {
			txtFiyat = new JTextField();
			txtFiyat.setBounds(154, 133, 127, 20);
			txtFiyat.setColumns(10);
		}
		return txtFiyat;
	}
	private JLabel getLblEklemeTarihi() {
		if (lblEklemeTarihi == null) {
			lblEklemeTarihi = new JLabel("Ekleme Tarihi:");
			lblEklemeTarihi.setBounds(63, 237, 81, 14);
			
		}
		return lblEklemeTarihi;
	}
	private JDateChooser getDateUrunEklemeTarihi() {
		if (dateUrunEklemeTarihi == null) {
			dateUrunEklemeTarihi = new JDateChooser();
			dateUrunEklemeTarihi.setBounds(154, 231, 137, 20);
			dateUrunEklemeTarihi.setDateFormatString("yyyy-MM-dd");
		}
		return dateUrunEklemeTarihi;
	}
	private JButton getBtnEkle() {
		if (btnEkle == null) {
			btnEkle = new JButton("\u00DCr\u00FCn Kaydet");
			btnEkle.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					DbServicessBase<Products> dao=new DbServicessBase<Products>();
					Products newProducts=new Products();
					newProducts.setName(txtUrunAdi.getText().toString());
					newProducts.setCategory((Category) cmbUrunKategori.getModel().getSelectedItem());
					newProducts.setPiece(Integer.valueOf(txtAdet.getText()));
					newProducts.setPrice(Float.valueOf(getTxtFiyat().getText()));
					newProducts.setDate(getDateUrunEklemeTarihi().getDate());
					
					if (dao.save(newProducts)) {
						lblMesaj.setText("Ürün Kaydedildi");
						urunTablosuGoster();
					}
					else {
						lblMesaj.setText("Ürün Kaydedilemedi");
					}
				}
			});
			btnEkle.setIcon(new ImageIcon("images\\ekle4.png"));
			btnEkle.setBounds(10, 303, 150, 36);
		}
		return btnEkle;
	}
	private JLabel getLblAdet() {
		if (lblAdet == null) {
			lblAdet = new JLabel("Adet:");
			lblAdet.setBounds(105, 184, 44, 14);
		}
		return lblAdet;
	}
	private JTextField getTxtAdet() {
		if (txtAdet == null) {
			txtAdet = new JTextField();
			txtAdet.setBounds(154, 181, 127, 20);
			txtAdet.setColumns(10);
		}
		return txtAdet;
	}
	private JPanel getPanel() {
		if (panel == null) {
			panel = new JPanel();
			panel.setBorder(new TitledBorder(null, "\u00DCr\u00FCn Bilgileri", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			panel.setBounds(27, 103, 341, 450);
			panel.setLayout(null);
			panel.add(getLblrnAd());
			panel.add(getTxtUrunAdi());
			panel.add(getLblrnKategori());
			panel.add(getTxtFiyat());
			panel.add(getLblFiyat());
			panel.add(getTxtAdet());
			panel.add(getLblAdet());
			panel.add(getDateUrunEklemeTarihi());
			panel.add(getLblEklemeTarihi());
			panel.add(getBtnEkle());
			panel.add(getBtnSil());
			panel.add(getBtnGuncelle());
			panel.add(getBtnTemizle());
			panel.add(getCmbUrunKategori());
		}
		return panel;
	}
	private JButton getBtnSil() {
		if (btnSil == null) {
			btnSil = new JButton("\u00DCr\u00FCn Sil");
			btnSil.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					DbServicessBase<Products> dao=new DbServicessBase<Products>();
					Products deletedProducts=new Products();
					int row=tblUrunlerTablosu.getSelectedRow();
					deletedProducts.setId(selectedProductId);
					
					
					if (dao.delete(deletedProducts)) {
						lblMesaj.setText("Ürün Baþarý ile Silindi");
						urunTablosuGoster();
					}
					else {
						lblMesaj.setText("Ürün Silinemedi");
					}
					
				}
			});
			btnSil.setIcon(new ImageIcon("images\\delete.png"));
			btnSil.setBounds(194, 303, 137, 36);
		}
		return btnSil;
	}
	private JButton getBtnGuncelle() {
		if (btnGuncelle == null) {
			btnGuncelle = new JButton("\u00DCr\u00FCn G\u00FCncelle");
			btnGuncelle.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					
					DbServicessBase<Products> dao=new DbServicessBase<Products>();
					Products updatedProducts=new Products();
					
					updatedProducts.setId(selectedProductId);
					updatedProducts.setCategory((Category) cmbUrunKategori.getModel().getSelectedItem());
					updatedProducts.setName(txtUrunAdi.getText());
					updatedProducts.setPiece(Integer.valueOf(txtAdet.getText()));
					updatedProducts.setPrice(Float.valueOf(getTxtFiyat().getText()));
					updatedProducts.setDate(getDateUrunEklemeTarihi().getDate());
					
					if (dao.update(updatedProducts)) {
						lblMesaj.setText("Ürün Baþarý ile Güncellendi");
						urunTablosuGoster();
					}
					else {
						lblMesaj.setText("Ürün Güncellenemedi");

					}
				}
			});
			btnGuncelle.setIcon(new ImageIcon("images\\guncelle2.png"));
			btnGuncelle.setBounds(10, 372, 150, 36);
		}
		return btnGuncelle;
	}
	private JButton getBtnTemizle() {
		if (btnTemizle == null) {
			btnTemizle = new JButton("Temizle");
			btnTemizle.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					txtAdet.setText("");
					txtFiyat.setText("");
					txtKategoriAdi.setText("");
					txtUrunAdi.setText("");
					dateUrunEklemeTarihi.setDate(null);
					cmbUrunKategori.setSelectedItem(null);
				}
			});
			btnTemizle.setIcon(new ImageIcon("images\\temizle.png"));
			btnTemizle.setBounds(194, 372, 137, 36);
		}
		return btnTemizle;
	}
	private JLabel getLblKategoriAd() {
		if (lblKategoriAd == null) {
			lblKategoriAd = new JLabel("Kategori Ad\u0131:");
			lblKategoriAd.setBounds(33, 35, 100, 14);
		}
		return lblKategoriAd;
	}
	private JTextField getTxtKategoriAdi() {
		if (txtKategoriAdi == null) {
			txtKategoriAdi = new JTextField();
			txtKategoriAdi.setColumns(10);
			txtKategoriAdi.setBounds(140, 32, 127, 20);
		}
		return txtKategoriAdi;
	}
	private JButton getBtnKategoriEkle() {
		if (btnKategoriEkle == null) {
			btnKategoriEkle = new JButton("Kategori Ekle");
			btnKategoriEkle.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					DbServicessBase<Category> dao=new DbServicessBase<Category>();
					Category newcategory=new Category();
					
					newcategory.setName(txtKategoriAdi.getText());
					if (dao.save(newcategory)) {
						lblMesaj.setText("Kategori Oluþturuldu");
					}
					else {
						lblMesaj.setText("Kategori Oluþturulamadý");
					}
				}
			});
			btnKategoriEkle.setIcon(new ImageIcon("images\\addcategory.png"));
			btnKategoriEkle.setBounds(88, 60, 164, 32);
		}
		return btnKategoriEkle;
	}
	private JLabel getLblMesaj() {
		if (lblMesaj == null) {
			lblMesaj = new JLabel("");
			lblMesaj.setForeground(Color.RED);
			lblMesaj.setBackground(Color.RED);
			lblMesaj.setBounds(415, 569, 703, 20);
		}
		return lblMesaj;
	}
	private JComboBox getCmbUrunKategori() {
		if (cmbUrunKategori == null) {
			cmbUrunKategori = new JComboBox();
			cmbUrunKategori.setBounds(154, 84, 127, 20);
		}
		return cmbUrunKategori;
	}
	private JScrollPane getScrollPane() {
		if (scrollPane == null) {
			scrollPane = new JScrollPane();
			scrollPane.setBounds(415, 35, 905, 518);
			scrollPane.setViewportView(getTblUrunlerTablosu());
		}
		return scrollPane;
	}
	private JTable getTblUrunlerTablosu() {
		if (tblUrunlerTablosu == null) {
			tblUrunlerTablosu = new JTable();
			tblUrunlerTablosu.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent arg0) {
					int row=tblUrunlerTablosu.getSelectedRow();
					selectedProductId=Integer.valueOf(tblUrunlerTablosu.getValueAt(row, 0).toString());
					String  s = (String)tblUrunlerTablosu.getValueAt(row, 1);
					String[] s1 = s.split("-");
					Category c = new Category();
					c.setId(Integer.parseInt(s1[0]));
					c.setName(s1[1]);
					cmbUrunKategori.getModel().setSelectedItem(c);
					txtUrunAdi.setText((String) tblUrunlerTablosu.getValueAt(row,2));
					txtAdet.setText(tblUrunlerTablosu.getValueAt(row, 3).toString());
					txtFiyat.setText(tblUrunlerTablosu.getValueAt(row, 4).toString());
					try {
						Date date=new SimpleDateFormat("yyyy-MM-dd").parse((String) tblUrunlerTablosu.getValueAt(row, 5));
						dateUrunEklemeTarihi.setDate(date);
					} catch (ParseException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
				}
			});
		}
		return tblUrunlerTablosu;
	}
}
