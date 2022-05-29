package ui;

import io.FileFactory;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.Vector;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

import model.DangNhapModel;
import model.NhanVienModel;
import service.DangNhapService;
import service.NhanVienService;

public class DangNhapUI extends JFrame{
	JTextField txtUserName;
	JPasswordField txtPassword;
	
	JCheckBox chkSave, chkQuenMatKhau;
	
	JButton btnLogin,btnExit, btnDoiMatKhau;

	public DangNhapUI(String title){
		super(title);
		addControls();
		addEvents();
		hienThiLaiThongTinDangNhap();
	}
	private void hienThiLaiThongTinDangNhap() {
		File f = new File("login.data");
		if(f.exists())
		{
			Object data = FileFactory.readData("login.data");
			if(data!=null)
			{
				DangNhapModel dn = (DangNhapModel) data;
				txtUserName.setText(dn.getTenDangNhap());
				txtPassword.setText(dn.getMatKhau());
				chkSave.setSelected(true);
			}
		}
	}
	public void addControls(){
		Container con = getContentPane();
		con.setLayout(new BorderLayout());
		JPanel pnNorth = new JPanel();
		con.add(pnNorth,BorderLayout.NORTH);
		JPanel pnCenter = new JPanel();
		con.add(pnCenter,BorderLayout.CENTER);
		JPanel pnSouth = new JPanel();
		con.add(pnSouth,BorderLayout.SOUTH);
		
		JLabel lblTitle = new JLabel("Đăng nhập hệ thống");
		lblTitle.setFont(new Font("tahoma",Font.BOLD,20));
		lblTitle.setForeground(Color.RED);
		pnNorth.add(lblTitle);
		
		pnCenter.setLayout(new BorderLayout());
		JPanel pnImage = new JPanel();
		pnImage.setPreferredSize(new Dimension(200, 0));
		JLabel lblIcon = new JLabel(new ImageIcon("images/login2.png"));
		pnImage.add(lblIcon);
		pnCenter.add(pnImage,BorderLayout.WEST);
		
		JPanel pnUser = new JPanel();
		pnUser.setLayout(new BoxLayout(pnUser, BoxLayout.Y_AXIS));
		pnCenter.add(pnUser,BorderLayout.CENTER);
		
		JPanel pnUserName = new JPanel();
		pnUserName.setLayout(new FlowLayout(FlowLayout.LEFT));
		JLabel lblUserName = new JLabel("Tài khoản:");
		lblUserName.setFont(new Font("tahoma",Font.TRUETYPE_FONT,15));
		txtUserName = new JTextField(20);
		txtUserName.setPreferredSize(new Dimension(0, 25));
		pnUserName.add(lblUserName);
		pnUserName.add(txtUserName);
		pnUser.add(pnUserName);
		
		JPanel pnPassword = new JPanel();
		pnPassword.setLayout(new FlowLayout(FlowLayout.LEFT));
		JLabel lblPassword = new JLabel("Mật khẩu:");
		lblPassword.setFont(new Font("tahoma",Font.ROMAN_BASELINE,15));
		txtPassword = new JPasswordField(20);
		txtPassword.setPreferredSize(new Dimension(0, 25));
		pnPassword.add(lblPassword);
		pnPassword.add(txtPassword);
		pnUser.add(pnPassword);
		
		JPanel pnSave = new JPanel();
		pnSave.setLayout(new FlowLayout());
		chkSave = new JCheckBox("Lưu thông tin đăng nhập");
		chkSave.setFont(new Font("", Font.ITALIC, 15));
		chkSave.setForeground(Color.BLUE);
		pnSave.add(chkSave, FlowLayout.LEFT);
		pnUser.add(pnSave);
		
		JPanel pnQuenMatKhau = new JPanel();
		pnQuenMatKhau.setLayout(new FlowLayout());
		chkQuenMatKhau = new JCheckBox("Quên mật khẩu?");
		chkQuenMatKhau.setFont(new Font("", Font.ITALIC, 15));
		chkQuenMatKhau.setForeground(Color.BLUE);
		pnQuenMatKhau.add(chkQuenMatKhau, FlowLayout.LEFT);
		pnUser.add(pnQuenMatKhau);
		
		btnLogin = new JButton("Đăng nhập");
		btnLogin.setPreferredSize(new Dimension(135, 40));
		btnLogin.setFont(new Font("", Font.BOLD, 14));
		btnDoiMatKhau = new JButton("Đổi mật khẩu");
		btnDoiMatKhau.setPreferredSize(new Dimension(160, 40));
		btnDoiMatKhau.setFont(new Font("", Font.BOLD, 14));
		btnExit= new JButton("Thoát");
		btnExit.setPreferredSize(new Dimension(100, 40));
		btnExit.setFont(new Font("", Font.BOLD, 14));
		pnSouth.add(btnLogin);
		pnSouth.add(btnDoiMatKhau);
		pnSouth.add(btnExit);
		
		TitledBorder borderUser=
				new TitledBorder(BorderFactory.createLineBorder(Color.RED),
						"Thông tin đăng nhập");
		borderUser.setTitleColor(Color.BLUE);
		borderUser.setTitleFont(new Font("", Font.PLAIN, 15));
		pnUser.setBorder(borderUser);
		pnImage.setBorder(BorderFactory.createLineBorder(Color.RED));
		pnCenter.setBorder(BorderFactory.createLineBorder(Color.BLUE));
		
		btnLogin.setIcon(new ImageIcon("images/action.png"));
		btnExit.setIcon(new ImageIcon("images/exit.png"));
		btnDoiMatKhau.setIcon(new ImageIcon("images/changepass.png"));
		lblPassword.setPreferredSize(lblUserName.getPreferredSize());
		chkQuenMatKhau.setPreferredSize(chkSave.getPreferredSize());
	}
	public void addEvents(){
		btnLogin.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				xuLyDangNhap();
			}
		});
		btnExit.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		btnDoiMatKhau.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				xuLyDoiMatKhau();
			}
		});
	}
	protected void xuLyDoiMatKhau() {
		DoiMatKhauUI ui = new DoiMatKhauUI("Đổi mật khẩu");
		ui.showWindows();
	}

	protected void xuLyDangNhap() {
		DangNhapService dnService = new DangNhapService();
		DangNhapModel dnModel;
		dnModel = dnService.loginQLKho(txtUserName.getText(), txtPassword.getText());
		if(dnModel != null){
			if(chkSave.isSelected())
			{
				FileFactory.saveData(dnModel, "login.data");
			}
			else
			{
				FileFactory.saveData(null, "login.data");
			}
			dispose(); 
			BoPhanQuanLyKhoUI ui = new BoPhanQuanLyKhoUI("Bộ phận quản lý kho");
			ui.showWindows();
			
		}
		else{
			dnModel = dnService.loginQLBanHang(txtUserName.getText(), txtPassword.getText());
			if(dnModel != null){
				if(chkSave.isSelected())
				{
					FileFactory.saveData(dnModel, "login.data");
				}
				else
				{
					FileFactory.saveData(null, "login.data");
				}
				dispose(); 
				BoPhanBanHangUI ui = new BoPhanBanHangUI("Bộ phận bán hàng");
				ui.showWindows();
				
			}
			else{
				dnModel = dnService.login(txtUserName.getText(), txtPassword.getText());
				if(dnModel != null){
					//JOptionPane.showMessageDialog(null, "Đăng nhập thành công!");
					if(chkSave.isSelected())
					{
						FileFactory.saveData(dnModel, "login.data");
					}
					else
					{
						FileFactory.saveData(null, "login.data");
					}
					dispose(); //mất form cũ
					DanhSachChucNangUI dscn = new DanhSachChucNangUI("Hệ thống cửa hàng điện thoại");
					dscn.showWindows();
				}
				else{
					JOptionPane.showMessageDialog(null, "Đăng nhập thất bại!");
				}
			}
		}
		
	}
	public String layTenDangnhap(){
		return txtUserName.getText();
	}
	
	public void showWindows(){
		this.setSize(550, 300);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.setResizable(false);
		this.setLocationRelativeTo(null);
		this.setVisible(true);
	}
}
