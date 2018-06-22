package com.lz.rabbitmq_fanout;


import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

public class SenderFanount {
	
	private static final String EXCHANGE_NAME = "logs";
	public static void main(String[] args) throws Exception, Exception {
		
		//建立工厂
		ConnectionFactory connF = new ConnectionFactory();
		//连接本地ip
		connF.setHost("localhost");
		//创建一个新的连接
		Connection conn = connF.newConnection();
		//创建一个通信通道
		Channel channel = conn.createChannel();
		//声明交换机
		channel.exchangeDeclare(EXCHANGE_NAME, "fanout");
		//发送消息
		for (int i = 0; i <= 2; i++) {
			String message = "我是发送者：00"+i+"号";
			// message.getBytes() 网络传输把String转换成字节
			channel.basicPublish(EXCHANGE_NAME, "", null, message.getBytes());
			System.out.println("已发送" +message+"成功");
		}
		channel.close();
		conn.close();
	}
}
