package com.lz.rabbitmq_fanout;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.QueueingConsumer;
import com.rabbitmq.client.QueueingConsumer.Delivery;

public class RececerFanout {
	
	private static final String EXCHANGE_NAME = "logs";
	
	public static void main(String[] args) throws IOException, Exception {
		
		ConnectionFactory connF = new ConnectionFactory();
		connF.setHost("localhost");
		Connection conn = connF.newConnection();
		Channel channel = conn.createChannel();
		channel.exchangeDeclare(EXCHANGE_NAME,"fanout");
		//声明队列
		String queueName = "fanount_queue";
		//队列名，服务重启时是否能存活，是否为专用队列，断开连接是否自动删除，当没有任何消费者时自动是否自动删除该队列，null
		channel.queueDeclare(queueName, false, false, false, null);
		//队列和交换机绑定
		channel.queueBind(queueName, EXCHANGE_NAME,"");
		//创建消费者
		QueueingConsumer consumer = new QueueingConsumer(channel);
		//指定消费队列
		channel.basicConsume(queueName, true, consumer);
		
		while(true){
			QueueingConsumer.Delivery delivery = consumer.nextDelivery();
			//接受数据进行转换为字符砖
			byte[] body = delivery.getBody();
			String message = new String(body);
			System.out.println("接收数据："+message+"成功");
		}
	}
}
