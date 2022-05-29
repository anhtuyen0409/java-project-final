package ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import service.TaiKhoanService;



public class DoiMatKhauUI extends JFrame{
	JTextField txtTenDangNhap;
	JPasswordField txtMatKhauCu, txtMatKhauMoi;
	JButton btnXacNhan, btnTroVe;
	public DoiMatKhauUI(String title){
		super(title);
		addControls();
		addEvents();
	}

	private void addEvents() {
		btnXacNhan.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				xuLyDoiMatKhau();
			}
		});
		btnTroVe.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				xuLyTroVe();
			}
		});
	}

	protected void xuLyTroVe() {
		dispose();
	}

	protected void xuLyDoiMatKhau() {
		TaiKhoanService tkService = new TaiKhoanService();
		if(tkService.doiMatKhau(txtTenDangNhap.getText(), txtMatKhauCu.getText(), txtMatKhauMoi.getText()) > 0){
			JOptionPane.showMessageDialog(null, "Đổi mật khẩu thành công!");
			txtTenDangNhap.setText("");
			txtMatKhauCu.setText("");
			txtMatKhauMoi.setText("");
		}
		else{
			JOptionPane.showMessageDialog(null, "Đổi mật khẩu thất bại!");
		}
	}

	private void addControls() {
		Container con = getContentPane();
		con.setLayout(new BorderLayout());
		
		JPanel pnTop = new JPanel();
		pnTop.setLayout(new FlowLayout(FlowLayout.CENTER));
		JLabel lblTieuDe = new JLabel("ĐỔI MẬT KHẨU");
		lblTieuDe.setForeground(Color.BLUE);
		lblTieuDe.setFont(new Font("", Font.BOLD, 20));
		pnTop.add(lblTieuDe);
		con.add(pnTop, BorderLayout.NORTH);
		
		JPanel pnBottom = new JPanel();
		con.add(pnBottom, BorderLayout.CENTER);
		pnBottom.setLayout(new BoxLayout(pnBottom, BoxLayout.Y_AXIS));
		
		JPanel pnTenDangNhap = new JPanel();
		pnTenDangNhap.setLayout(new FlowLayout(FlowLayout.LEFT));
		JLabel lblTenDangNhap = new JLabel("Tên đăng nhập: ");
		lblTenDangNhap.setFont(new Font("", Font.PLAIN, 15));
		txtTenDangNhap = new JTextField(25);
		pnTenDangNhap.add(lblTenDangNhap);
		pnTenDangNhap.add(txtTenDangNhap);
		pnBottom.add(pnTenDangNhap);
		
		JPanel pnMatKhauCu = new JPanel();
		pnMatKhauCu.setLayout(new FlowLayout(FlowLayout.LEFT));
		JLabel lblMatKhauCu = new JLabel("Nhập mật khẩu cũ: ");
		lblMatKhauCu.setFont(new Font("", Font.PLAIN, 15));
		txtMatKhauCu = new JPasswordField(25);
		pnMatKhauCu.add(lblMatKhauCu);
		pnMatKhauCu.add(txtMatKhauCu);
		pnBottom.add(pnMatKhauCu);
		
		JPanel pnMatKhauMoi = new JPanel();
		pnMatKhauMoi.setLayout(new FlowLayout(FlowLayout.LEFT));
		JLabel lblMatKhauMoi = new JLabel("Nhập mật khẩu mới: ");
		lblMatKhauMoi.setFont(new Font("", Font.PLAIN, 15));
		txtMatKhauMoi = new JPasswordField(25);
		pnMatKhauMoi.add(lblMatKhauMoi);
		pnMatKhauMoi.add(txtMatKhauMoi);
		pnBottom.add(pnMatKhauMoi);
		
		JPanel pnControls = new JPanel();
		pnControls.setLayout(new FlowLayout());
		btnXacNhan = new JButton("Xác nhận");
		btnXacNhan.setIcon(new ImageIcon("images/confirm.png"));
		btnTroVe = new JButton("Trở về");
		btnTroVe.setIcon(new ImageIcon("images/return3.png"));
		pnControls.add(btnXacNhan);
		pnControls.add(btnTroVe);
		con.add(pnControls, BorderLayout.SOUTH);
		
		lblTenDangNhap.setPreferredSize(lblMatKhauMoi.getPreferredSize());
		lblMatKhauCu.setPreferredSize(lblMatKhauMoi.getPreferredSize());
	}
	public void showWindows(){
		this.setSize(480, 250);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.setResizable(false);
		this.setLocationRelativeTo(null);
		this.setVisible(true);

	}
}
