package com.learnings.books.config;

import org.apache.activemq.artemis.jms.client.ActiveMQConnectionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.support.converter.MappingJackson2MessageConverter;
import org.springframework.jms.support.converter.MessageConverter;
import org.springframework.jms.support.converter.SimpleMessageConverter;

import jakarta.jms.ConnectionFactory;
import jakarta.jms.JMSException;

@Configuration
@EnableJms
public class JMSConfig {
	@Bean
	public ActiveMQConnectionFactory connectionFactory() throws JMSException {
		ActiveMQConnectionFactory factory = new ActiveMQConnectionFactory();
		factory.setBrokerURL("tcp://localhost:61616"); // Set the Artemis broker URL
		factory.setUser("username");
		factory.setPassword("password");
		return factory;
	}

	/*
	 * @Bean public JmsTemplate jmsTemplate() throws JMSException { return new
	 * JmsTemplate(connectionFactory()); }
	 */
	public JmsTemplate jmsTemplate(ConnectionFactory connectionFactory, MessageConverter messageConverter) {
		JmsTemplate jmsTemplate = new JmsTemplate();
		jmsTemplate.setConnectionFactory(connectionFactory);
		jmsTemplate.setMessageConverter(messageConverter);
		return jmsTemplate;
	}

	@Bean
	public MessageConverter messageConverter() {
		return new MappingJackson2MessageConverter();
//		return new SimpleMessageConverter();
	}
}
