package MyCaptureNet;

import org.jnetpcap.packet.PcapPacket;
import org.jnetpcap.packet.PcapPacketHandler;

/**
 * ʵ����PcapPacketHandler�Ľӿڣ���дnextPacket����
 * handler��һ�������������ص��Ľӿڣ�������һ���µ�packet�����ʱ�򣬻��֪ͨ
 * @author ������
 *
 */
public class MyPcapHandler<Object> implements PcapPacketHandler<Object>{

	@Override
	public void nextPacket(PcapPacket packet, Object user) {
		//�����ݰ��Ĵ���
		MyPacketMatch mpm = MyPacketMatch.getInstance();
		
		mpm.handlePacket(packet);
	}
	

}
