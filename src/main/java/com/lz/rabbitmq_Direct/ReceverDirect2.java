package com.lz.rabbitmq_Direct;

import java.util.concurrent.TimeoutException;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.QueueingConsumer;
import com.rabbitmq.client.QueueingConsumer.Delivery;

public class ReceverDirect2 {
	
	private final static String DIRECT_EXCHANGE = "direct_exchange";
	private final static String DIRECT_QUEUE = "direct_exchange";
	public static void main(String[] args) throws Exception, TimeoutException {

		ConnectionFactory connF = new ConnectionFactory();
		connF.setHost("localhost");
		Connection conn = connF.newConnection();
		Channel channel = conn.createChannel();
		
		//声明队列
		channel.queueDeclare(DIRECT_QUEUE, false, false, false, null);
		//绑定队列
		channel.queueBind(DIRECT_QUEUE, DIRECT_EXCHANGE, "insert");
		channel.queueBind(DIRECT_QUEUE, DIRECT_EXCHANGE, "update");
		//创建消费者
		QueueingConsumer cousember = new QueueingConsumer(channel);
		channel.basicConsume(DIRECT_QUEUE, true, cousember);
		while(true){
			QueueingConsumer.Delivery nextDelivery = cousember.nextDelivery();
			String message = new String(nextDelivery.getBody());
			System.out.println("消费的内容为：" + message);
		}
	}
}
