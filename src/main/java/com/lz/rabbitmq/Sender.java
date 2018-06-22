package com.lz.rabbitmq;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

public class Sender {
	
	public static void main(String[] args) throws Exception, Exception {
		//创建一个工厂
		ConnectionFactory factory  = new ConnectionFactory();
		//默认连接主机名
		factory .setHost("localhost");
		//创建连接
		Connection conn = factory .newConnection();
		//创建信息管道
		String queue_name = "hello2";
		Channel channel = conn.createChannel();
		//创建队列
		channel.queueDeclare(queue_name, false, false, false, null);
		//进行信息声明
		String message = "hello Recever I'm Ren222";
		//发送消息
		channel.basicPublish("", queue_name, null, message.getBytes());
		System.out.println("发送"+message+"成功");
		//关闭通道
		channel.close();
		//关闭连接
		conn.close();
	}
}
