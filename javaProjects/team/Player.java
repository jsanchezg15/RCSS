package team;

import java.io.FileWriter;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

import message.InitMessage;
import message.Message;
import message.SeeMessage;
import parser.Parser;

public class Player extends Thread {
	
	String         TEAM;
	char           SIDE;
	int            NUM;
	int            PORT;
	InetAddress    HOST;
	DatagramSocket sck;
	String         init_cmd;

	public Player(String team) {
		try {
			this.TEAM = team;
			this.PORT = 6000;
			this.HOST = InetAddress.getByName("127.0.0.1");
			this.sck  = new DatagramSocket();
			this.init_cmd  = "(init " + this.TEAM + ")";
		}
		catch(IOException e) {
			if(this.sck != null) this.sck.close();
		}
	}

	public void run() {
		try {
			this.send(this.init_cmd);

			String  str = this.receive();
			Message msg = Parser.parse(str);
			String  cmd = this.processMsg(msg);

			this.send(cmd);

			FileWriter file = new FileWriter("data " + this.TEAM + this.NUM + ".txt");
			file.write(cmd + "\n\n");

			try {
				while(true) {
					str = this.receive();
					msg = Parser.parse(str);

					if(msg == null || msg.TURN == 0)
						continue;

					cmd = this.processMsg(msg);
					
					file.write(str + "\n\n" + cmd + "\n\n");

					if(cmd.equals("null"))
						continue;

					this.send(cmd);
				}
			}
			catch(Exception e) {
				if(file != null)
					file.close();
			}
		} 
		catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			this.sck.close();			
		}
	}

	private void send(String cmd) throws IOException {
		
		byte[]         msg = cmd.getBytes();
		DatagramPacket out = new DatagramPacket(msg, msg.length, HOST, PORT);

		this.sck.send(out);
	}

	private String receive() throws IOException {
		
		byte[]         msg = new byte[1000];
		DatagramPacket in  = new DatagramPacket(msg, msg.length);

		this.sck.receive(in);

		return new String(in.getData());
	}

	private String processMsg(Message msg) {

		try {
			if(msg.TYPE.equals("SEE_MESSAGE")) 
				return this.processSeeMSG((SeeMessage) msg);
			
			else if(msg.TYPE.equals("INIT_MESSAGE"))
				return this.processInitMsg((InitMessage) msg);
	
			return "null";
		}
		catch(Exception e) {
			e.printStackTrace();
			return "null";
		}
	}

	private String processSeeMSG(SeeMessage msg) {
	
		if(msg.ball.equals("NOT_FOUND"))
			return "(turn 60)";
	
		String[] words = msg.ball.split(" ");
				
		Float    dist  = Float.parseFloat(words[1]);
		Integer  angle = Integer.parseInt(words[2].replace(")", ""));
	
		if(Math.abs(angle) > 20)
			return "(turn " + angle + ")";
	
		if(dist < 1)
			return "(kick 50 0)";
	
		return "(dash 80)";	
	}

	private String processInitMsg(InitMessage msg) {

		this.SIDE = msg.SIDE;
		this.NUM  = msg.NUMBER;

		switch(this.NUM) {
			case 1:  return "(move -40   0)";
			case 2:  return "(move -30  24)";
			case 3:  return "(move -30   8)";
			case 4:  return "(move -30  -8)";
			case 5:  return "(move -30 -24)";
			case 6:  return "(move -20  24)";
			case 7:  return "(move -20   8)";
			case 8:  return "(move -20  -8)";
			case 9:  return "(move -20 -24)";
			case 10: return "(move -10   8)";
			case 11: return "(move -10  -8)";
			default: return "(move 0 0)";
		}
	}

}
