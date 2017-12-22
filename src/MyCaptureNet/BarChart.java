package MyCaptureNet;

import java.awt.BorderLayout;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;

/**
 * ������״ͼ����
 * @author ������
 *
 */
public class BarChart {
	//��״ͼ����
	public static JFreeChart barChart = ChartFactory.createBarChart("���ݰ�ͳ�ƽ��", "���ݰ�����", "����", createDataset(),
			PlotOrientation.HORIZONTAL, true, true, false);
	
	/**
	 * ���ݼ�
	 * @return dataset
	 */
	private static CategoryDataset createDataset() {
		final String tcp = "TCP";
		final String udp = "UDP";
		final String arp = "ARP";
		final String icmp = "ICMP";
		final String widespread = "�㲥��";
		final String number = "������";
		final DefaultCategoryDataset dataset = new DefaultCategoryDataset();

		dataset.addValue(MyPacketMatch.numberOfTcp, tcp, number);
		dataset.addValue(MyPacketMatch.numberOfUdp, udp, number);
		dataset.addValue(MyPacketMatch.numberOfArp, arp, number);
		dataset.addValue(MyPacketMatch.numberOfIcmp, icmp, number);
		dataset.addValue(MyPacketMatch.numberOfWideSpread, widespread, number);
		

		return dataset;
	}

	/**
	 * ��ʾͼ��
	 */
	public void showChart() {
		ChartPanel myChart = new ChartPanel(barChart);
		MainWin.jp_tuxingArea.setLayout(new java.awt.BorderLayout()); //border����
		MainWin.jp_tuxingArea.add(myChart,BorderLayout.CENTER);
		MainWin.jp_tuxingArea.validate();  //����Ϊ��Ч
	}
}
