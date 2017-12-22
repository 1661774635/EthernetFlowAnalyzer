package MyCaptureNet;

import java.util.HashMap;

import org.jnetpcap.packet.PcapPacket;
import org.jnetpcap.protocol.network.Arp;
import org.jnetpcap.protocol.network.Icmp;
import org.jnetpcap.protocol.network.Ip4;
import org.jnetpcap.protocol.tcpip.Tcp;
import org.jnetpcap.protocol.tcpip.Udp;

/**
 * ���ݰ�������,���Ϊ����ģʽ
 * 
 * @author ������
 *
 */
public class MyPacketMatch {

	public static HashMap<Integer, String> hm = new HashMap<Integer, String>();// �洢��׽�������ݰ�
	public static int numberOfPacket = 0;// ���ݰ�����
	private static MyPacketMatch mpm; // ������

	private Icmp icmp = new Icmp();// ����icmp���ݰ�
	private Tcp tcp = new Tcp();// ����tcp���ݰ�
	private Udp udp = new Udp();// ����udp���ݰ�
	private Arp arp = new Arp();// ����arp��
	private Ip4 ip4 = new Ip4();// ����IPv4����Ϣ����Ҫ������ȡ�㲥��Ϣ

	public static double totalOfIcmp = 0; // ͳ��icmp��������
	public static double totalOfTcp = 0; // ͳ��tcp��������
	public static double totalOfUdp = 0; // ͳ��udp��������
	public static double totalOfArp = 0; // ͳ��arp��������
	public static double totalOfIp = 0; // ͳ��ip��������
	public static double totalOfSpread = 0; // ͳ�ƹ㲥��������

	public static int numberOfWideSpread = 0;// ͳ�ƹ㲥������
	public static int numberOfTcp = 0;// ͳ��tcp������
	public static int numberOfUdp = 0;// ͳ��udp������
	public static int numberOfIcmp = 0;// ͳ��icmp������
	public static int numberOfArp = 0;// ͳ��arp������

	/**
	 * ������ ��ȡMyPacketMatch��ʵ��
	 * 
	 * @return MyPacketMatch��ʵ��
	 */
	public static MyPacketMatch getInstance() {
		if (mpm == null) {
			mpm = new MyPacketMatch();
		}
		return mpm;
	}

	/**
	 * Ϊ���ݰ�����(tcp��,udp��,arp��,icmp��,�㲥��)
	 * 
	 * @param packet
	 *            һ��pcap�����ݱ�
	 */
	public void handlePacket(PcapPacket packet) {
		MyPacketMatch.totalOfIp += packet.getTotalSize() / (1024.0 * 1024.0);// ��λ��MB

		if (packet.hasHeader(icmp)) {
			handleIcmp(packet);
		}
		if (packet.hasHeader(arp)) {
			handleArp(packet);
		}
		if (packet.hasHeader(tcp)) {
			handleTcp(packet);
		}
		if (packet.hasHeader(udp)) {
			handleUdp(packet);
		}
		if (packet.hasHeader(ip4)) {
			handleIp4(packet);
		}

	}

	/**
	 * �㲥���Ĵ����� ���޹㲥 ������·�ɷ��ͣ����ᱻ�͵���ͬ����������ϵ���������
	 * IP��ַ�������ֶκ������ֶ�ȫΪ1���ǵ�ַ255.255.255.255
	 * 
	 * @param packet
	 */
	private void handleIp4(PcapPacket packet) {
		packet.getHeader(ip4);
		if (MyPacketMatch.int2Ip(ip4.destinationToInt()).equals("255.255.255.255")) {
			// System.out.println("�յ���һ���㲥��");
			MainWin.lItems.add(numberOfPacket, "�㲥���ݰ�");

			hm.put(numberOfPacket, "�㲥���ݰ�\n" + ip4.toString());

			numberOfWideSpread++;

			totalOfSpread += (ip4.getLength() / 1024.0); // ��λ��KB

			numberOfPacket++;
		}
	}

	/**
	 * tcp���Ĵ�����
	 * 
	 * @param packet
	 */
	private void handleTcp(PcapPacket packet) {
		packet.getHeader(tcp);
		// System.out.println(tcp.toString());
		hm.put(numberOfPacket, packet.toString());

		MainWin.lItems.add(numberOfPacket, "TCP Packet");

		numberOfTcp++;

		totalOfTcp += tcp.getLength() / 1024.0; // ��λΪKB

		numberOfPacket++;

	}

	/**
	 * udp���Ĵ�����
	 * 
	 * @param packet
	 */
	private void handleUdp(PcapPacket packet) {
		packet.getHeader(udp);
		// System.out.println("udp��Դ�˿�"+udp.toString());
		hm.put(numberOfPacket, packet.toString());

		MainWin.lItems.add(numberOfPacket, "UDP Packet");

		numberOfUdp++;

		totalOfUdp += udp.getLength() / 1024.0; // ��λתΪKB

		numberOfPacket++;
	}

	/**
	 * arp���Ĵ�����
	 * 
	 * @param packet
	 */
	private void handleArp(PcapPacket packet) {
		packet.getHeader(arp);
		// System.out.println("arp:"+arp.toString());
		hm.put(numberOfPacket, arp.toString());

		MainWin.lItems.add(numberOfPacket, "ARP Packet");

		numberOfArp++;

		totalOfArp += arp.getLength() / 1024.0;

		numberOfPacket++;
	}

	/**
	 * icmp���Ĵ�����
	 * 
	 * @param icmp
	 */
	private void handleIcmp(PcapPacket packet) {
		packet.getHeader(icmp);
		// System.out.println("icmp:"+icmp.toString());
		hm.put(numberOfPacket, icmp.toString());

		MainWin.lItems.add(numberOfPacket, "ICMP Packet");

		numberOfIcmp++;

		totalOfIcmp += icmp.getLength() / 1024.0;

		numberOfPacket++;
	}

	/**
	 * ��int����ת��ΪIP��ַ
	 * 
	 * @param ipInt
	 * @return IP-String
	 */
	public static String int2Ip(int ipInt) {
		return new StringBuilder().append(((ipInt >> 24) & 0xff)).append('.').append((ipInt >> 16) & 0xff).append('.')
				.append((ipInt >> 8) & 0xff).append('.').append((ipInt & 0xff)).toString();
	}

}
