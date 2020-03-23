package com.omerfpekgoz.stok.project.ui;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

import com.omerfpekgoz.stok.project.utils.CourseUtils;

public class MainScreen extends JFrame {
	private JMenuBar menuBar;
	private JMenu mnNewMenu;
	private JTabbedPane MainTabbedPane;
	private JPanel satiþPanel;
	private JPanel stokPanel;
	private JPanel urunPanel;
	private JPanel MüsteriPanel;
	private JButton btnGeri;
	private JTabbedPane tabbedPane;
	private JMenu mnGeri;
	private JMenu mnRaporlama;
	
	public MainScreen() {
		initialize();
	}

	private void initialize() {
		setTitle("Sat\u0131\u0131\u015F ve Stok Program\u0131  ///"+CourseUtils.loginedUser.getNameSurname() + " - "
				+ CourseUtils.loginedUser.getRole());
		setBounds(-10,0, 1382, 732);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		getContentPane().setLayout(null);
		getContentPane().add(getMenuBar_1());
		getContentPane().add(getMainTabbedPane());
		
		
	}
	
	private JMenuBar getMenuBar_1() {
		if (menuBar == null) {
			menuBar = new JMenuBar();
			menuBar.setBounds(0, 0, 1366, 37);
			menuBar.add(getMnNewMenu());
			menuBar.add(getMnGeri());
			menuBar.add(getMnRaporlama());
		}
		return menuBar;
	}
	private JMenu getMnNewMenu() {
		if (mnNewMenu == null) {
			mnNewMenu = new JMenu("Kullan\u0131c\u0131 \u0130\u015Flemleri");
			mnNewMenu.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent arg0) {
					new AddStaffScreen().setVisible(true);
				}
			});
			
			mnNewMenu.setIcon(new ImageIcon("images\\kullan\u0131c\u0131islemleri.png"));
		}
		return mnNewMenu;
	}
	private JTabbedPane getMainTabbedPane() {
		if (MainTabbedPane == null) {
			MainTabbedPane = new JTabbedPane(JTabbedPane.TOP);
			MainTabbedPane.setBounds(0, 36, 1366, 657);
			MainTabbedPane.addTab("Satýþ Ýþlemleri", new ImageIcon("images\\satýþiþlemleri2.png"), new SalesScreen().getContentPane(), null);
			MainTabbedPane.addTab("Stok Ýþlemleri", new ImageIcon("images\\stok2.png"), new StockScreen().getContentPane(),null);
			MainTabbedPane.addTab("\u00DCr\u00FCn \u0130\u015Flemleri", new ImageIcon("images\\ürünler.png"),new AddProductandCategoryScreen().getContentPane(), null);
			MainTabbedPane.addTab("M\u00FC\u015Fteri \u0130\u015Flemleri", new ImageIcon("images\\müþteriler.png"),new AddCustomerScreen().getContentPane(), null);
			
		}
		return MainTabbedPane;
	}
	private JPanel getSatiþPanel() {
		if (satiþPanel == null) {
			satiþPanel = new JPanel();
			satiþPanel.setLayout(null);
			
			
		}
		return satiþPanel;
	}
	private JPanel getStokPanel() {
		if (stokPanel == null) {
			stokPanel = new JPanel();
			stokPanel.setLayout(null);
		}
		return stokPanel;
	}
	private JPanel getUrunPanel() {
		if (urunPanel == null) {
			urunPanel = new JPanel();
		}
		return urunPanel;
	}
	private JPanel getMüsteriPanel() {
		if (MüsteriPanel == null) {
			MüsteriPanel = new JPanel();
		}
		return MüsteriPanel;
	}
	
	private JTabbedPane getTabbedPane() {
		if (tabbedPane == null) {
			tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		}
		return tabbedPane;
	}
	private JMenu getMnGeri() {
		if (mnGeri == null) {
			mnGeri = new JMenu("Kullan\u0131c\u0131 Giri\u015Fi");
			mnGeri.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent arg0) {
					MainScreen.this.dispose();
					new LoginScreen().setVisible(true);
				}
			});
			mnGeri.setIcon(new ImageIcon("images\\kullanýcýgirisi.png"));
		}
		return mnGeri;
	}
	private JMenu getMnRaporlama() {
		if (mnRaporlama == null) {
			mnRaporlama = new JMenu("Raporlama");
			mnRaporlama.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent arg0) {
					new ReportScreen().setVisible(true);
				}
			});
			mnRaporlama.setIcon(new ImageIcon("images\\rapor2.png"));
		}
		return mnRaporlama;
	}
}
