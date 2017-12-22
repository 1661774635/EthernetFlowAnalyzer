package MyCaptureNet;

import java.util.ArrayList;

import javax.swing.JOptionPane;

import org.jnetpcap.Pcap;
import org.jnetpcap.PcapIf;

/**
 * ץ���Ĺ����࣬ƫ�ڵײ��ץ��������jnetpcap�Ľӿ� ���������б�Ļ�ȡ�����Ĳ���ץ�������ֹͣ
 * 
 * @author ������
 *
 */
public class MyCapUtil extends Thread {
	private static boolean flag = true;//�����豸ʹ�ñ�־λ
	public static int number = 2;
	private static StringBuilder errbuf = new StringBuilder();// �洢������Ϣ

	/**
	 * ���ڻ�ȡ�豸������������ ���ִ���ο�jnetpcap��������1
	 * 
	 * @return Arrayist�������豸�б�
	 */
	public static ArrayList<PcapIf> CaptureNet() {
		//System.out.println("MyCapUtil.CaptureNet() is start");
		MyCapUtil.flag = false;  //�Ƿ�ץ���ı�־λ��true��������ץ����false ����ֹͣץ��

		ArrayList<PcapIf> alldevs = new ArrayList<PcapIf>();// �洢�������������豸
        /* ������������˿�����pcap_open_live()�򿪵����������豸
        * ����б��е�Ԫ�ض��� pcap_if_t��
        * name һ��ָ���豸���ֵ�ָ�룻
        * adderess ��һ���ӿڵĵ�ַ�б�ĵ�һ��Ԫ�ص�ָ�룻
        * flags һ��PCAP_IF_LOOPBACK��ǽӿ��Ƿ���loopback��
        * ʧ�ܷ���-1���ɹ�����0
        */ 
		int r = Pcap.findAllDevs(alldevs, errbuf);// �����豸�ϵ�����������
		// �����쳣�����rΪ1�����б�Ϊ��
		if (r == Pcap.NOT_OK || alldevs.isEmpty()) {
			// ����������Ϣ��ʾ��
			JOptionPane.showMessageDialog(null, errbuf.toString(), "��ȡ�����豸����", JOptionPane.ERROR_MESSAGE);
			return null;
		}
		//System.out.println("MyCapUtil.CaptureNet() is end");
		return alldevs;
	}
	
	/**
	 * ѡȡ�����豸���Ҳ�׽���ݰ�
	 * ���ִ���ο�jnetpcap��������1
	 * @param alldevs �豸�б�
	 */
	public static void CapturePacket(ArrayList<PcapIf> alldevs){
		
		MyCapUtil.flag = true;
		PcapIf device = alldevs.get(number);
		
		//��ѡ�е������豸
		int snaplen = Pcap.DEFAULT_SNAPLEN;//��ȡĬ�ϳ���Ϊ65535
		int flags = Pcap.MODE_PROMISCUOUS;//��ȡģʽΪ����ģʽ
		int timeout = 10 * 1000;//��ʱ����Ϊ10seconds		
		
        // openlive���������������һ����ָ�������豸�йصģ���Ծ�Ĳ����� 

        // ������snaplenָ�����ǿ��Բ��������byte����
        // ��� snaplen��ֵ �� ���ǲ���İ��Ĵ�СҪС�Ļ���
        // ��ôֻ��snaplen��С�����ݻᱻ������packet data����ʽ�ṩ��
        // IPЭ����16λ����ʾIP�����ݰ����ȣ�������󳤶���65535�ĳ���
        // ������ȶ��ڴ�������������㹻����ȫ�������ݰ���

        // ������flags promiscָ���˽ӿ���promiscģʽ�ģ�Ҳ���ǻ���ģʽ��
        // ����ģʽ���������ֹ���ģʽ֮һ���Ƚ���ֱ��ģʽ��
        // ֱ��ģʽֻ����mac��ַ���Լ���֡��
        // ���ǻ���ģʽ���������������еģ�����������֡���ﵽ��������Ϣ���Ӳ�׽��Ŀ��

        // ������timeout �������ʹ�ò��񱨺�ȴ�һ����ʱ�䣬�������������ݰ���
        // Ȼ��һ�β�����������������������е�ƽ̨��֧�֣���֧�ֵĻ��Զ������������

        // ������errbuf pcap_open_live()ʧ�ܷ���NULL�Ĵ�����Ϣ�����߳ɹ�ʱ��ľ�����Ϣ   
		Pcap pcap = Pcap.openLive(device.getName(), snaplen,flags, timeout, errbuf);
		if(pcap == null){
			JOptionPane.showMessageDialog(null, errbuf.toString(),"��׽���ݰ�����",JOptionPane.ERROR_MESSAGE);
			return;
		}
		
		//��ȡ���ݰ��󽻸�handler����,����һ��packet handler ����������libpcap loop�н������ݰ�
		MyPacketMatch packMatch = MyPacketMatch.getInstance();
		MyPcapHandler<Object> myhandler = new MyPcapHandler<Object>();
		
		//�����ץ��
		while(MyCapUtil.flag){
			//��handler����loop�в�������ץȡ1����
			pcap.loop(1, myhandler,"/njnetpcap");
		}		
		//�ر�pcap
		pcap.close();
	}
	
	/**
	 * ֹͣץ��
	 */
	public static void stopCapturePacket(){
		MyCapUtil.flag = false;
	}
	
	/**
	 * ��ռ�¼
	 */
	public static void clearPacket(){
		MyPacketMatch.numberOfPacket=0;		
		MyPacketMatch.numberOfArp=0;
		MyPacketMatch.numberOfTcp=0;
		MyPacketMatch.numberOfUdp=0;
		MyPacketMatch.numberOfIcmp=0;
		MyPacketMatch.numberOfWideSpread=0;
		MyPacketMatch.hm.clear();
		MainWin.lItems.clear();	
		Listener.jta_totalWord.setText("");
	}
	
	public void run(){
		MyCapUtil.CapturePacket(MyCapUtil.CaptureNet());
	}
}
