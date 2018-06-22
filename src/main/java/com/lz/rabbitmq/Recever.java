package com.lz.rabbitmq;


import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.QueueingConsumer;
import com.rabbitmq.client.QueueingConsumer.Delivery;

public class Recever {

	public static void main(String[] args) throws Exception, Exception {
		//创建工厂
		ConnectionFactory connF = new ConnectionFactory();
		//连接服务
		connF.setHost("localhost");
		//创建一个新连接
		Connection conn = connF.newConnection();
		//打开连接通道
		Channel channel = conn.createChannel();
		//声明队列
		String queue_name = "hello2";
		channel.queueDeclare(queue_name, false, false, false, null);
		System.out.println("连接结束。。。。。。。。。。。。。。。。。开始接受");
		//创建消费者
		QueueingConsumer consumer = new QueueingConsumer(channel);
		//指定消费队列
		channel.basicConsume(queue_name, true, consumer);
		while(true){
			//得到消息并且传输
			QueueingConsumer.Delivery delivery  = consumer.nextDelivery();
			String message = new String(delivery.getBody());
			System.out.println("进行消费: " +message);
		}
	}
}
