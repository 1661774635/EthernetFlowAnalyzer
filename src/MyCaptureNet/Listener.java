package MyCaptureNet;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;

import javax.swing.JList;
import javax.swing.JTextArea;

/**
 * ������������������Ĳ˵�������б��
 * 
 * @author ������
 *
 */
public class Listener implements ActionListener {
	private String cmd;
	private static String message;
	public static JTextArea jta_totalWord;
	private JTextArea jta_detailInfo;
	private JList list;
	private DecimalFormat df = new DecimalFormat("0.0000");

	public void setJta_totalWord(JTextArea jta_totalWord) {
		this.jta_totalWord = jta_totalWord;
	}

	public JTextArea getJta_totalWord() {
		return jta_totalWord;
	}

	public void setList(JList list) {
		this.list = list;
	}

	public JList getList() {
		return list;
	}

	public void setJta_detailInfo(JTextArea jta_detailInfo) {
		this.jta_detailInfo = jta_detailInfo;
	}

	public JTextArea getJta_detailInfo() {
		return jta_detailInfo;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		cmd = e.getActionCommand();
		if ("mi_startCap".equals(cmd)) { // ��ʼץ��
			// ����ץ���߳�
			(new MyCapUtil()).start();
		} else if ("mi_endCap".equals(cmd)) {// ֹͣץ��
			MyCapUtil.stopCapturePacket();
			jta_totalWord.setText("");
			message = "Tcp:\t" + MyPacketMatch.numberOfTcp + "��\t" + df.format(MyPacketMatch.totalOfTcp) + "KB\n"
					+ "Udp:\t" + MyPacketMatch.numberOfUdp + "��\t" + df.format(MyPacketMatch.totalOfUdp) + "KB\n"
					+ "Icmp:\t" + MyPacketMatch.numberOfIcmp + "��\t" + df.format(MyPacketMatch.totalOfIcmp) + "KB\n"
					+ "Arp:\t" + MyPacketMatch.numberOfArp + "��\t" + df.format(MyPacketMatch.totalOfArp) + "KB\n"
					+ "�㲥��:\t" + MyPacketMatch.numberOfWideSpread + "��\t" + df.format(MyPacketMatch.totalOfSpread)
					+ "KB\n" + "������:\t" + MyPacketMatch.numberOfPacket + "��\t" + df.format(MyPacketMatch.totalOfIp)
					+ "MB";
			jta_totalWord.append(message);
		} else if ("mi_clean".equals(cmd)) { // ��ռ�¼
			MyCapUtil.clearPacket();
		} else if ("mi_about".equals(cmd)) { // �������
			AboutWin aw = new AboutWin();
			aw.showAboutWin();
		} else if ("mi_save".equals(cmd)) { // ���������ļ�
			SaveFile sf = new SaveFile();
			sf.saveFile(MainWin.mainFrame, jta_totalWord.getText());
		} else if ("mi_tuxing".equals(cmd)) { // ͼ����ʾ
			BarChart bc = new BarChart();
			bc.showChart();
		}
	}

}
