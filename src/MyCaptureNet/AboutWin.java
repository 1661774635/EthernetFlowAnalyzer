package MyCaptureNet;
import javax.swing.JOptionPane;
/**
 * ����������������ʵ����
 * @author ������
 *
 */
public class AboutWin {
	String str = "�����������winpcap����̫������������\n" + "�汾��v1.0.0\n" + "���ߣ�mybichu";

	// ��ʾ
	public void showAboutWin() {
		// ����
		JOptionPane.showMessageDialog( MainWin.mainFrame, str,"�������",JOptionPane.INFORMATION_MESSAGE);
	}
	
}
