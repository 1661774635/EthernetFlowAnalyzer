package MyCaptureNet;

import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.Toolkit;

import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.JComboBox;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import java.awt.Font;
import javax.swing.JMenuItem;
import javax.swing.KeyStroke;
import javax.swing.UIManager;
import javax.swing.border.Border;

import java.awt.event.*;
import java.util.ArrayList;

import javax.swing.JScrollPane;
import javax.swing.JPanel;
import javax.swing.JTextPane;
import java.awt.Color;
import javax.swing.JList;
import javax.swing.JTextArea;
import javax.swing.border.MatteBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import org.jnetpcap.PcapIf;
import javax.swing.JLabel;
import java.awt.BorderLayout;

/**
 * ���������
 * 
 * @author ������
 *
 */
public class MainWin {

	public static JFrame mainFrame;
	public static DefaultListModel lItems = new DefaultListModel();
	private JList list = new JList(lItems);
	public static JPanel jp_tuxingArea;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainWin window = new MainWin();
					window.mainFrame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public MainWin() {
		try {
			UIManager.setLookAndFeel("com.birosoft.liquid.LiquidLookAndFeel");
		} catch (Exception e) {
			System.out.println("ˮ��Ƥ��û���ҵ��������");
			e.printStackTrace();
		}
		
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		mainFrame = new JFrame(); // ������
		Listener li = new Listener();// ������
		mainFrame.getContentPane().setBackground(Color.WHITE);
		mainFrame.setResizable(false);
		mainFrame.setIconImage(Toolkit.getDefaultToolkit()
				.getImage("imgs\\5.jpg"));
		mainFrame.setTitle("WinPcap\u6D41\u91CF\u5206\u6790\u5668(designed by mybichu)");
		mainFrame.setAlwaysOnTop(true);
		mainFrame.setBounds(100, 100, 900, 700);
		mainFrame.setLocationRelativeTo(null);
		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mainFrame.getContentPane().setLayout(null);

		// �˵���
		JMenuBar menuBar = new JMenuBar();
		menuBar.setBounds(0, 0, 900, 30);
		mainFrame.getContentPane().add(menuBar);

		// ��ץ�����˵�
		JMenu menu0 = new JMenu("\u6293\u5305(Z)");
		menu0.setMnemonic('Z');
		menu0.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 12));
		menuBar.add(menu0);

		// ����ʼץ�����˵���
		JMenuItem mi_startCap = new JMenuItem("\u5F00\u59CB\u6293\u5305(B)");
		mi_startCap.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 12));
		mi_startCap.setMnemonic('B');
		mi_startCap.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_B, InputEvent.CTRL_MASK));
		menu0.add(mi_startCap);
		mi_startCap.setActionCommand("mi_startCap");
		mi_startCap.addActionListener(li);

		// ������ץ�����˵���
		JMenuItem mi_endCap = new JMenuItem("\u7ED3\u675F\u6293\u5305(E)");
		mi_endCap.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 12));
		mi_startCap.setMnemonic('E');
		mi_endCap.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_E, InputEvent.CTRL_MASK));
		menu0.add(mi_endCap);
		mi_endCap.setActionCommand("mi_endCap");
		mi_endCap.addActionListener(li);

		// ����ռ�¼���˵���
		JMenuItem mi_clean = new JMenuItem("\u6E05\u7A7A\u8BB0\u5F55(C)");
		mi_clean.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 12));
		mi_startCap.setMnemonic('C');
		mi_clean.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C, InputEvent.CTRL_MASK));
		menu0.add(mi_clean);
		mi_clean.setActionCommand("mi_clean");
		mi_clean.addActionListener(li);

		// ��ͳ�ƽ�����˵�
		JMenu menu1 = new JMenu("\u7EDF\u8BA1\u7ED3\u679C(X)");
		menu1.setMnemonic('X');
		menu1.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 12));
		menuBar.add(menu1);

		// ��ͼ����ʾ���˵���
		JMenuItem mi_tuxing = new JMenuItem("\u56FE\u5F62\u663E\u793A(T)");
		mi_tuxing.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 12));
		mi_startCap.setMnemonic('T');
		mi_tuxing.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_T, InputEvent.CTRL_MASK));
		menu1.add(mi_tuxing);
		mi_tuxing.setActionCommand("mi_tuxing");
		mi_tuxing.addActionListener(li);

		// �����������˵���
		JMenuItem mi_save = new JMenuItem("\u4FDD\u5B58\u7ED3\u679C(S)");
		mi_save.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 12));
		mi_startCap.setMnemonic('S');
		mi_save.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, InputEvent.CTRL_MASK));
		menu1.add(mi_save);
		mi_save.setActionCommand("mi_save");
		mi_save.addActionListener(li);

		// ���������˵�
		JMenu menu2 = new JMenu("\u5E2E\u52A9(H)");
		menu2.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 12));
		menu2.setMnemonic('H');
		menuBar.add(menu2);

		// ������������˵���
		JMenuItem mi_about = new JMenuItem("\u5173\u4E8E\u8F6F\u4EF6(A)");
		mi_about.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 12));
		mi_startCap.setMnemonic('A');
		mi_about.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_A, InputEvent.CTRL_MASK));
		menu2.add(mi_about);
		mi_about.setActionCommand("mi_about");
		mi_about.addActionListener(li);

		// ���󲿷ֵ���ʾ�������
		JScrollPane jsp_1 = new JScrollPane();
		jsp_1.setBounds(0, 58, 169, 387);
		mainFrame.getContentPane().add(jsp_1);

		// �м䲿�ֵġ���ϸ��Ϣ������ʾ��
		JLabel label = new JLabel("<html>��<br/>ϸ<br/>��<br/>Ϣ<br/></html>");
		label.setFont(new Font("Microsoft JhengHei", Font.PLAIN, 15));
		label.setBounds(177, 155, 18, 112);
		mainFrame.getContentPane().add(label);

		// ���Ҳ��ֵ���ʾ�Ĺ�����
		JScrollPane jsp_2 = new JScrollPane();
		jsp_2.setBounds(194, 58, 675, 387);
		mainFrame.getContentPane().add(jsp_2);

		// ���Ҳ��ֵ��ı���
		JTextArea jta_detailInfo = new JTextArea();
		jta_detailInfo.setEditable(false);
		jsp_2.setViewportView(jta_detailInfo);

		// ���󲿷ֵ���ʾ�б�
		// DefaultListModel lItems=new DefaultListModel();
		// JList list = new JList(lItems);
		jsp_1.setViewportView(list);
		@SuppressWarnings("unused")
		Border brd = BorderFactory.createMatteBorder(1, 1, 2, 2, Color.black);
		list.setBorder(new MatteBorder(1, 1, 2, 2, (Color) new Color(152, 251, 152)));
		list.addListSelectionListener(new ListSelectionListener() {// ���list�������Ҳ�����ʾ������Ϣ
			@Override
			public void valueChanged(ListSelectionEvent e) {
				jta_detailInfo.setText("");
				jta_detailInfo.append((String) MyPacketMatch.hm.get(list.getSelectedIndex()));// ��hashmapȡ����
			}
		});

		// ��ʾ����ѡ�������豸������textPane
		JTextPane textPane = new JTextPane();
		textPane.setForeground(Color.BLACK);
		textPane.setEnabled(false);
		textPane.setFont(new Font("����", Font.BOLD | Font.ITALIC, 20));
		textPane.setEditable(false);
		textPane.setText("\u8BF7\u9009\u62E9\u7F51\u7EDC\u8BBE\u5907\uFF1A");
		textPane.setBounds(0, 27, 169, 25);
		mainFrame.getContentPane().add(textPane);

		// �����豸�������б�
		JComboBox comboBox = new JComboBox();
		comboBox.setBackground(new Color(255, 239, 213));
		comboBox.setBounds(194, 30, 357, 21);
		mainFrame.getContentPane().add(comboBox);

		// �����б�ļ������¼�
		comboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String net = (String) comboBox.getSelectedItem();
				ArrayList<PcapIf> alldevs = MyCapUtil.CaptureNet();
				int i = 0;
				// System.out.println(net);
				for (PcapIf device : alldevs) {
					if (net.equals(device.getDescription())) {
						MyCapUtil.number = i;
						// System.out.println(CaptureUtil.number+":"+device.getDescription());
						MyCapUtil.stopCapturePacket();
					}
					i++;
				}
			}
		});

		// �����б���������豸
		ArrayList<PcapIf> alldevs = MyCapUtil.CaptureNet();
		for (PcapIf device : alldevs) {
			comboBox.addItem(device.getDescription());
		}

		// �²��ִ����ʾͳ�ƽ������壬�����󲿷ֵ�������������Ҳ��ֵ�ͼ������
		JPanel jp_showArea = new JPanel();
		jp_showArea.setBackground(new Color(175, 238, 238));
		jp_showArea.setBounds(0, 455, 869, 206);
		mainFrame.getContentPane().add(jp_showArea);
		jp_showArea.setLayout(null);

		// ���󲿷ֵ������������ֵķ�ʽ��ʾͳ�ƽ��
		JPanel jp_wordArea = new JPanel();
		jp_wordArea.setBounds(40, 10, 320, 186);
		jp_showArea.add(jp_wordArea);
		jp_wordArea.setLayout(null);

		// �ı���
		JTextArea textArea_1 = new JTextArea();
		textArea_1.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 15));
		textArea_1.setEditable(false);
		textArea_1.setBounds(0, 0, 339, 186);
		jp_wordArea.add(textArea_1);
		li.setJta_totalWord(textArea_1);

		// ���Ҳ��ֵ�ͼ������ͼ�εķ�ʽ��ʾͳ�ƽ��
		jp_tuxingArea = new JPanel();
		jp_tuxingArea.setBounds(374, 10, 485, 186);
		jp_showArea.add(jp_tuxingArea);
		jp_tuxingArea.setLayout(new BorderLayout(0, 0));

		// �����ǻ�ͼ��Ĳ���
//		 ChartPanel chartPanel = new ChartPanel( barChart );        
//	     chartPanel.setPreferredSize(new java.awt.Dimension( 560 , 367 ) ); 
//	     jp_tuxingArea.add(chartPanel);
	

		// �����󲿷ֵġ�ͳ������������ʾ
		JLabel lblNewLabel = new JLabel("<html>ͳ<br/>��<br/>��<br/></html>");
		lblNewLabel.setFont(new Font("����", Font.BOLD | Font.ITALIC, 20));
		lblNewLabel.setBounds(10, 10, 26, 186);
		jp_showArea.add(lblNewLabel);

	}
}
