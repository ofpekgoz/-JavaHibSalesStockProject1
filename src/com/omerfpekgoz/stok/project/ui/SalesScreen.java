package com.omerfpekgoz.stok.project.ui;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JComboBox;

import com.omerfpekgoz.stok.project.models.Category;
import com.omerfpekgoz.stok.project.models.Customer;
import com.omerfpekgoz.stok.project.models.Products;
import com.omerfpekgoz.stok.project.models.Sales;
import com.omerfpekgoz.stok.project.models.Staff;
import com.omerfpekgoz.stok.project.models.Stock;
import com.omerfpekgoz.stok.project.models.UserRoleTypes;
import com.omerfpekgoz.stok.project.utils.CourseUtils;
import com.omerfpekgoz.stok.project.utils.dao.DbServicessBase;
import com.toedter.calendar.JDateChooser;
import javax.swing.JTable;
import java.awt.Color;

import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
import java.util.Date;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.ParseException;
import java.text.SimpleDateFormat;


public class SalesScreen extends JFrame{
	private JPanel panel;
	private JLabel lblrnAd;
	private JComboBox cmbUrunAdi;
	private JLabel lblrnKategori;
	private JComboBox cmbUrunKategori;
	private JLabel lblAdet;
	private JTextField txtSatisAdet;
	private JLabel lblSatTarihi;
	private JDateChooser dateSatiþTarihi;
	private JLabel lblSatYapanPersonel;
	private JComboBox cmbPersonel;
	private JLabel lblMteriAdsoyad;
	private JComboBox cmbMusteri;
	private JButton btnSatYap;
	private JButton btnYenile;
	private JLabel lblMesaj;
	private JScrollPane scrollPane;
	private JTable tblSatislarTablosu;
	private JLabel lblStokAdedi;
	private JTextField txtStokAdedi;
	private JButton btnSatisSil;
	private JButton btnSatisGuncelle;
	private Integer selectedSalesId;

	public SalesScreen() {
		initialize();
	}

	private void initialize() {
		setTitle("Sat\u0131\u015F \u0130\u015Flemleri");
		setBounds(-10, 20, 1370, 650);
		getContentPane().setLayout(null);
		getContentPane().add(getPanel());
		getContentPane().add(getLblMesaj());
		getContentPane().add(getScrollPane());
		satisTablosuGoster();
		

		DbServicessBase<Customer> dao=new DbServicessBase<Customer>();
		List<Customer> customers=dao.getAllRows(new Customer());
		DefaultComboBoxModel model=new DefaultComboBoxModel(customers.toArray());
		cmbMusteri.setModel(model);
		DbServicessBase<Products> dao1=new DbServicessBase<Products>();
		List<Products> products=dao1.getAllRows(new Products());
		 model=new DefaultComboBoxModel(products.toArray());
		cmbUrunAdi.setModel(model);
		DbServicessBase<Category> dao2=new DbServicessBase<Category>();
		List<Category> categories=dao2.getAllRows(new Category());
		 model=new DefaultComboBoxModel(categories.toArray());
		cmbUrunKategori.setModel(model);
		DbServicessBase<Staff> dao3=new DbServicessBase<Staff>();
		List<Staff> staffs=dao3.getAllRows(new Staff());
		model=new DefaultComboBoxModel(staffs.toArray());
		cmbPersonel.setModel(model);
		
		cmbMusteri.setSelectedItem(null);
		cmbPersonel.setSelectedItem(null);
		cmbUrunAdi.setSelectedItem(null);
		cmbUrunKategori.setSelectedItem(null);
		txtSatisAdet.setText("");
		txtStokAdedi.setText("");
		dateSatiþTarihi.setDate(null);
		lblMesaj.setText("");
		
	
	}
	Integer stokAdet;
	public void stokAdetYazdýrma() {

		DbServicessBase<Stock> daoStock=new DbServicessBase<Stock>();
		Stock stock=new Stock();
		
		Products stokProducts=((Products) cmbUrunAdi.getModel().getSelectedItem());
		stock.setProducts(stokProducts);
		List<Stock> stocklist=daoStock.search(stock);
		if (stocklist.size()>0) {
			for (int i = 0; i <stocklist.size(); i++) {
				txtStokAdedi.setText(String.valueOf(stocklist.get(0).getStockPiece()));
			}
			 stokAdet=Integer.valueOf(txtStokAdedi.getText());	
		}
		else {
			txtStokAdedi.setText("");
		}
		
	}
	
	public void stokAdetAzaltma(int newStockPiece) {

		DbServicessBase<Stock> daoStock=new DbServicessBase<Stock>();
		Stock stock=new Stock();
		
		Products stokProducts=((Products) cmbUrunAdi.getModel().getSelectedItem());
		stock.setProducts(stokProducts);
		List<Stock> stocklist=daoStock.search(stock);
		if (stocklist.size()>0) {
			for (int i = 0; i <stocklist.size(); i++) {
				stocklist.get(i).setStockPiece(newStockPiece);
				txtStokAdedi.setText(String.valueOf(newStockPiece));
			}
			
		}
		else {
			txtStokAdedi.setText("");
		}
		
	}
DefaultTableModel model=new DefaultTableModel();

	public void satisTablosuGoster() {
	
	DbServicessBase<Sales> dao=new DbServicessBase<Sales>();
	Sales temp=new Sales();
	Stock stock=new Stock();
	List<Sales> satis_listesi=dao.getAllRows(temp);
	
	String [] columnNames= {"ID","MÜÞTERÝ AD-SOYAD","ÜRÜN ADI","KATEGORÝ","ÜRÜN SATIÞ FÝYATI","ÜRÜN SATIÞ ADEDÝ","SATIÞ TARÝHÝ","SATIÞ YAPAN PERSONEL","STOK ADEDÝ"};
	Object [][] data=new Object [satis_listesi.size()][columnNames.length];
	stokAdetYazdýrma();
	
	for (int i = 0; i < satis_listesi.size(); i++) {
		
		data[i][0]=CourseUtils.getValue(satis_listesi.get(i).getId());
		data[i][1]=CourseUtils.getValue(satis_listesi.get(i).getCustomer());
		data[i][2]=CourseUtils.getValue(satis_listesi.get(i).getProducts());
		data[i][3]=CourseUtils.getValue(satis_listesi.get(i).getProducts().getCategory());
		data[i][4]=CourseUtils.getValue(satis_listesi.get(i).getProducts().getPrice());
		data[i][5]=CourseUtils.getValue(satis_listesi.get(i).getSalesPiece());
		data[i][6]=CourseUtils.getValue(satis_listesi.get(i).getSalesDate());
		data[i][7]=CourseUtils.getValue(satis_listesi.get(i).getStaff());
		//data[i][8]=CourseUtils.getValue(stokAdet);
		
	}
	
	
	
	model=new DefaultTableModel(data,columnNames);
	tblSatislarTablosu.setModel(model);
	}
	private JPanel getPanel() {
		if (panel == null) {
			panel = new JPanel();
			panel.setBorder(new TitledBorder(null, "Sat\u0131\u015F \u0130\u015Flemleri", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			panel.setBounds(0, 11, 315, 589);
			panel.setLayout(null);
			panel.add(getLblrnAd());
			panel.add(getCmbUrunAdi());
			panel.add(getLblrnKategori());
			panel.add(getCmbUrunKategori());
			panel.add(getLblAdet());
			panel.add(getTxtSatisAdet());
			panel.add(getLblSatTarihi());
			panel.add(getDateSatiþTarihi());
			panel.add(getLblSatYapanPersonel());
			panel.add(getCmbPersonel());
			panel.add(getLblMteriAdsoyad());
			panel.add(getCmbMusteri());
			panel.add(getBtnSatYap());
			panel.add(getBtnYenile());
			panel.add(getLblStokAdedi());
			panel.add(getTxtStokAdedi());
			panel.add(getBtnSatisSil());
			panel.add(getBtnSatisGuncelle());
		}
		return panel;
	}
	private JLabel getLblrnAd() {
		if (lblrnAd == null) {
			lblrnAd = new JLabel("\u00DCr\u00FCn Ad\u0131:");
			lblrnAd.setBounds(84, 42, 70, 14);
		}
		return lblrnAd;
	}
	private JComboBox getCmbUrunAdi() {
		if (cmbUrunAdi == null) {
			cmbUrunAdi = new JComboBox();
			cmbUrunAdi.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					stokAdetYazdýrma();
				}
			});
			cmbUrunAdi.setBounds(156, 39, 138, 20);
		}
		return cmbUrunAdi;
	}
	private JLabel getLblrnKategori() {
		if (lblrnKategori == null) {
			lblrnKategori = new JLabel("\u00DCr\u00FCn Kategori:");
			lblrnKategori.setBounds(59, 91, 82, 14);
		}
		return lblrnKategori;
	}
	private JComboBox getCmbUrunKategori() {
		if (cmbUrunKategori == null) {
			cmbUrunKategori = new JComboBox();
			cmbUrunKategori.setBounds(156, 88, 138, 20);
		}
		return cmbUrunKategori;
	}
	private JLabel getLblAdet() {
		if (lblAdet == null) {
			lblAdet = new JLabel("Sat\u0131\u015F Yap\u0131lacak Adet:");
			lblAdet.setBounds(18, 190, 144, 17);
		}
		return lblAdet;
	}
	private JTextField getTxtSatisAdet() {
		if (txtSatisAdet == null) {
			txtSatisAdet = new JTextField();
			txtSatisAdet.setBounds(156, 188, 138, 20);
			txtSatisAdet.setColumns(10);
		}
		return txtSatisAdet;
	}
	private JLabel getLblSatTarihi() {
		if (lblSatTarihi == null) {
			lblSatTarihi = new JLabel("Sat\u0131\u015F Tarihi:");
			lblSatTarihi.setBounds(72, 239, 69, 14);
		}
		return lblSatTarihi;
	}
	private JDateChooser getDateSatiþTarihi() {
		if (dateSatiþTarihi == null) {
			dateSatiþTarihi = new JDateChooser();
			dateSatiþTarihi.setBounds(156, 239, 138, 20);
			dateSatiþTarihi.setDateFormatString("yyyy-MM-dd");
		}
		return dateSatiþTarihi;
	}
	private JLabel getLblSatYapanPersonel() {
		if (lblSatYapanPersonel == null) {
			lblSatYapanPersonel = new JLabel("Sat\u0131\u015F Yapan Personel:");
			lblSatYapanPersonel.setBounds(30, 298, 132, 14);
		}
		return lblSatYapanPersonel;
	}
	private JComboBox getCmbPersonel() {
		if (cmbPersonel == null) {
			cmbPersonel = new JComboBox();
			cmbPersonel.setBounds(156, 295, 138, 20);
		}
		return cmbPersonel;
	}
	private JLabel getLblMteriAdsoyad() {
		if (lblMteriAdsoyad == null) {
			lblMteriAdsoyad = new JLabel("M\u00FC\u015Fteri Ad-Soyad:");
			lblMteriAdsoyad.setBounds(41, 348, 144, 14);
		}
		return lblMteriAdsoyad;
	}
	private JComboBox getCmbMusteri() {
		if (cmbMusteri == null) {
			cmbMusteri = new JComboBox();
			cmbMusteri.setBounds(156, 345, 138, 20);
		}
		return cmbMusteri;
	}
	private JButton getBtnSatYap() {
		if (btnSatYap == null) {
			btnSatYap = new JButton("Sat\u0131\u015F Yap");
			btnSatYap.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					DbServicessBase<Sales> dao=new DbServicessBase<Sales>();
					
					Sales addedSales=new Sales();
					Stock stock=new Stock();
					stock.setProducts((Products) cmbUrunAdi.getModel().getSelectedItem());
					
					addedSales.setProducts((Products) cmbUrunAdi.getModel().getSelectedItem());
					addedSales.getProducts().setCategory((Category) cmbUrunKategori.getModel().getSelectedItem());
					addedSales.setSalesPiece(Integer.valueOf(txtSatisAdet.getText().toString()));
					addedSales.setSalesDate(dateSatiþTarihi.getDate());
					addedSales.setStaff((Staff) cmbPersonel.getModel().getSelectedItem());
					addedSales.setCustomer((Customer) cmbMusteri.getModel().getSelectedItem());
					if (stokAdet<addedSales.getSalesPiece()) {
						
						lblMesaj.setText("Bu ürün satýþ yapýlamaz(Ürün Stok Adedi Satýþ Adedinden Azdýr)");
					}
					else {
						
						if (dao.save(addedSales)) {
							lblMesaj.setText("Satýþ Baþarý ile Gerçekleþti");
							satisTablosuGoster();
							stock.setStockPiece(stokAdet-addedSales.getSalesPiece());
							stokAdetAzaltma(stock.getStockPiece());
							JOptionPane.showMessageDialog(SalesScreen.this, addedSales.getProducts().getName()+" Ürününden Kalan Adet: "+stock.getStockPiece());
							
						}
						else {
							lblMesaj.setText("Satýþ Gerçekleþemedi");
						}
						
					}
				}
			});
			btnSatYap.setIcon(new ImageIcon("images\\satisyap.png"));
			btnSatYap.setBounds(10, 405, 131, 46);
		}
		return btnSatYap;
	}
	private JButton getBtnYenile() {
		if (btnYenile == null) {
			btnYenile = new JButton("Yenile");
			btnYenile.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					cmbMusteri.setSelectedItem(null);
					cmbPersonel.setSelectedItem(null);
					cmbUrunAdi.setSelectedItem(null);
					cmbUrunKategori.setSelectedItem(null);
					txtSatisAdet.setText("");
					dateSatiþTarihi.setDate(null);
					lblMesaj.setText("");
					txtStokAdedi.setText("");
				}
			});
			btnYenile.setIcon(new ImageIcon("images\\guncelle.png"));
			btnYenile.setBounds(156, 405, 138, 46);
		}
		return btnYenile;
	}
	private JLabel getLblMesaj() {
		if (lblMesaj == null) {
			lblMesaj = new JLabel("");
			lblMesaj.setForeground(Color.RED);
			lblMesaj.setBackground(Color.RED);
			lblMesaj.setBounds(337, 577, 692, 23);
		}
		return lblMesaj;
	}
	private JScrollPane getScrollPane() {
		if (scrollPane == null) {
			scrollPane = new JScrollPane();
			scrollPane.setBounds(337, 47, 993, 525);
			scrollPane.setViewportView(getTblSatislarTablosu());
		}
		return scrollPane;
	}
	private JTable getTblSatislarTablosu() {
		if (tblSatislarTablosu == null) {
			tblSatislarTablosu = new JTable();
			tblSatislarTablosu.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent arg0) {
					int row=tblSatislarTablosu.getSelectedRow();
					selectedSalesId=Integer.valueOf(tblSatislarTablosu.getValueAt(row, 0).toString());
					String  s = (String)tblSatislarTablosu.getValueAt(row, 1);
					String[] s1 = s.split("-");
					Customer c = new Customer();
					c.setId(Integer.valueOf(s1[0]));
					c.setNameSurname(s1[1]);
					
					cmbMusteri.getModel().setSelectedItem(c);
					
					String  n = (String)tblSatislarTablosu.getValueAt(row, 2);
					String[] n1 = n.split("-");
					Products p = new Products();
					p.setId(Integer.parseInt(n1[0]));
					p.setName(n1[1]);
					cmbUrunAdi.getModel().setSelectedItem(p);
					
					String  a = (String)tblSatislarTablosu.getValueAt(row, 3);
					String[] a1 = a.split("-");
					Category k = new Category();
					k.setId(Integer.parseInt(a1[0]));
					k.setName(a1[1]);
					cmbUrunKategori.getModel().setSelectedItem(k);
					txtSatisAdet.setText((String) tblSatislarTablosu.getValueAt(row, 5));
					
					
					String  m = (String)tblSatislarTablosu.getValueAt(row, 7);
					String[] m1 = m.split("-");
					Staff l=new Staff();
					l.setId(Integer.valueOf(m1[0]));
					l.setNameSurname(m1[1]);
					l.setRole(UserRoleTypes.valueOf(m1[2]));
					
					cmbPersonel.getModel().setSelectedItem(l);
					try {
						Date date = new SimpleDateFormat("yyyy-MM-dd").parse((String)  tblSatislarTablosu.getValueAt(row, 6));
						dateSatiþTarihi.setDate(date);
					} catch (ParseException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
					
				}
			});
		}
		return tblSatislarTablosu;
	}
	private JLabel getLblStokAdedi() {
		if (lblStokAdedi == null) {
			lblStokAdedi = new JLabel("Stok Adedi:");
			lblStokAdedi.setBounds(59, 141, 82, 14);
		}
		return lblStokAdedi;
	}
	private JTextField getTxtStokAdedi() {
		if (txtStokAdedi == null) {
			txtStokAdedi = new JTextField();
			txtStokAdedi.setEnabled(false);
			txtStokAdedi.setText("");
			txtStokAdedi.setColumns(10);
			txtStokAdedi.setBounds(156, 138, 138, 20);
		}
		return txtStokAdedi;
	}
	private JButton getBtnSatisSil() {
		if (btnSatisSil == null) {
			btnSatisSil = new JButton("Sat\u0131\u015F Sil");
			btnSatisSil.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
			DbServicessBase<Sales> dao=new DbServicessBase<Sales>();
					
					Sales deletedSales=new Sales();
					deletedSales.setId(selectedSalesId);
					deletedSales.setProducts((Products) cmbUrunAdi.getModel().getSelectedItem());
					deletedSales.getProducts().setCategory((Category) cmbUrunKategori.getModel().getSelectedItem());
					deletedSales.setSalesPiece(Integer.valueOf(txtSatisAdet.getText().toString()));
					deletedSales.setSalesDate(dateSatiþTarihi.getDate());
					deletedSales.setStaff((Staff) cmbPersonel.getModel().getSelectedItem());
					deletedSales.setCustomer((Customer) cmbMusteri.getModel().getSelectedItem());
					
						
						if (dao.delete(deletedSales)) {
							lblMesaj.setText("Satýþ Bilgileri Silindi ");
							satisTablosuGoster();
						}
						else {
							lblMesaj.setText("Satýþ Bilgileri Silinemedi");
						}
						
					
				}
			});
			btnSatisSil.setIcon(new ImageIcon("images\\iptal.png"));
			btnSatisSil.setBounds(10, 490, 131, 46);
		}
		return btnSatisSil;
	}
	private JButton getBtnSatisGuncelle() {
		if (btnSatisGuncelle == null) {
			btnSatisGuncelle = new JButton("Sat\u0131\u015F G\u00FCncelle");
			btnSatisGuncelle.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					DbServicessBase<Sales> dao=new DbServicessBase<Sales>();
					
					Sales updatedSales=new Sales();
					updatedSales.setId(selectedSalesId);
					updatedSales.setProducts((Products) cmbUrunAdi.getModel().getSelectedItem());
					updatedSales.getProducts().setCategory((Category) cmbUrunKategori.getModel().getSelectedItem());
					updatedSales.setSalesPiece(Integer.valueOf(txtSatisAdet.getText().toString()));
					updatedSales.setSalesDate(dateSatiþTarihi.getDate());
					updatedSales.setStaff((Staff) cmbPersonel.getModel().getSelectedItem());
					updatedSales.setCustomer((Customer) cmbMusteri.getModel().getSelectedItem());
					if (stokAdet<updatedSales.getSalesPiece()) {
						
						lblMesaj.setText("Bu Ürünün Satýþ Fiyatý Güncellenemez(Ürün Stok Adedi Satýþ Adedinden Azdýr)");
					}
					else {
						
						if (dao.update(updatedSales)) {
							lblMesaj.setText("Satýþ Bilgileri Güncellendi ");
							satisTablosuGoster();
						}
						else {
							lblMesaj.setText("Satýþ Bilgileri Güncellenemedi");
						}
						
					}
				}
			});
			btnSatisGuncelle.setIcon(new ImageIcon("images\\guncelle2.png"));
			btnSatisGuncelle.setBounds(156, 490, 149, 46);
		}
		return btnSatisGuncelle;
	}
}
