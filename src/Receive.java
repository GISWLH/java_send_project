import javax.swing.*;
import java.awt.*;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketAddress;
import java.awt.Frame;
import java.awt.Menu;
import java.awt.MenuBar;
import java.awt.MenuItem;
import javax.swing.JLabel;
public class Receive {

    public static void main(String[] args) {
        try {
            InetAddress ip = InetAddress.getLocalHost();
            int port = 8888;

            // 创建接收方的套接字,并制定端口号和IP地址
            DatagramSocket getSocket = new DatagramSocket(port, ip);

            // 确定数据报接受的数据的数组大小
            byte[] buf = new byte[1024];

            // 创建接受类型的数据报，数据将存储在buf中
            DatagramPacket getPacket = new DatagramPacket(buf, buf.length);

            // 通过套接字接收数据
            getSocket.receive(getPacket); //有问题



            // 解析发送方传递的消息，并打印
            String getMes = new String(buf, 0, getPacket.getLength());

            System.out.println("对方发送的消息：" + getMes);

            // 通过数据报得到发送方的IP和端口号，并打印
            InetAddress sendIP = getPacket.getAddress();
            int sendPort = getPacket.getPort();

            System.out.println("对方的IP地址是：" + sendIP.getHostAddress());
            System.out.println("对方的端口号是：" + sendPort);

            // 通过数据报得到发送方的套接字地址
            SocketAddress sendAddress = getPacket.getSocketAddress();

            // 确定要反馈发送方的消息内容，并转换为字节数组
            String feedback = "收到了！";
            byte[] backBuf = feedback.getBytes();

            // 创建发送类型的数据报
            DatagramPacket sendPacket = new DatagramPacket(backBuf,
                    backBuf.length, sendAddress);

            // 通过套接字发送数据
            getSocket.send(sendPacket);

            // 关闭套接字
            getSocket.close();

            //窗口自定
            JFrame frame = new JFrame("Title for your window");

            JPanel panel = new JPanel();

            JLabel l = new JLabel();
            ImageIcon icon = new ImageIcon("img_1.png");     //在此直接创建对象,注意目录之间的分隔符是双斜线
            l.setIcon(icon);
            l.setBounds(10, 10, icon.getIconWidth(),icon.getIconHeight());
            panel.add(l,new Integer(Integer.MIN_VALUE));


            JLabel label = new JLabel("对方发送的消息：" + getMes );
            JLabel label1 = new JLabel("对方的IP地址是：" + sendIP.getHostAddress());
            JLabel label2 = new JLabel("对方的端口号是：" + sendPort);
            JLabel label_kong = new JLabel("           ");
            JLabel label3 = new JLabel("收到了！");
            Font f = new Font("宋体",Font.PLAIN,16);
            label3.setFont(f);
            label3.setForeground(Color.red);
            Container con = frame.getContentPane();    //用getContentPane()方法获得JFrame的内容面板
            con.setBackground(Color.blue);    //设置背景颜色为蓝色

            //距离屏幕左边100个像素，上边100个像素，窗口的宽是300，高是300
            frame.setBounds(100,100,300,300);    //设置窗口1在屏幕上的位置及大小
            frame.setVisible(true);
            panel.add(label);
            panel.add(label1);
            panel.add(label2);
            panel.add(label_kong);
            panel.add(label3);
            frame.add(panel);
            //Menu无法直接添加到容器中，只能直接添加到菜单容器中
            MenuBar mb = new MenuBar(); //创建菜单容器
            frame.setMenuBar(mb);
            //添加菜单
            Menu m1 = new Menu("File");
            Menu m2 = new Menu("Edit");
            Menu m3 = new Menu("Help");
            mb.add(m1);
            mb.add(m2);
            mb.add(m3);

            //添加菜单项
            MenuItem mi1 = new MenuItem("Save");
            MenuItem mi2 = new MenuItem("Load");
            MenuItem mi3 = new MenuItem("Quit");
            m1.add(mi1);
            m1.add(mi2);
            m1.addSeparator(); //添加分隔线
            m1.add(mi3);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
