package com.lz.rabbitmq_Direct;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

public class SenderDirect {
	
	private final static String DIRECT_EXCHANGE = "direct_exchange";
	
	public static void main(String[] args) throws Exception, TimeoutException {
		
		ConnectionFactory connF = new ConnectionFactory();
		connF.setHost("localhost");
		Connection conn = connF.newConnection();
		Channel channel = conn.createChannel();
		
		channel.exchangeDeclare(DIRECT_EXCHANGE, "direct");
		
		//放松消息
		String message = "商品ID, id=1";
		String message2 = "商品ID, id=2";
		channel.basicPublish(DIRECT_EXCHANGE, "delect", null, message.getBytes());
		channel.basicPublish(DIRECT_EXCHANGE, "insert", null, message2.getBytes());
		
		System.out.println("已发送的消息为：" + message);
		System.out.println("已发送的消息为：" + message2);
		channel.close();
		conn.close();
	}
}
