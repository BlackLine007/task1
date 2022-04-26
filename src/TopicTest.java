import javax.jms.Connection;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.MessageListener;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.jms.Topic;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.command.ActiveMQTopic;

public class TopicTest {
    public static void main(String[] args) throws Exception {
        ActiveMQConnectionFactory factory = new ActiveMQConnectionFactory(
                "tcp://localhost:61616");

        Connection connection = factory.createConnection();
        connection.start();

        // создать тему
        Topic topic = new ActiveMQTopic("testTopic");
        Session session = connection.createSession(false,
                Session.AUTO_ACKNOWLEDGE);

        // Зарегистрированный потребитель 1
        MessageConsumer comsumer1 = session.createConsumer(topic);
        comsumer1.setMessageListener(new MessageListener() {
            public void onMessage(Message m) {
                try {
                    System.out.println("Первый потребитель получил "
                            + ((TextMessage) m).getText());
                } catch (JMSException e) {
                    e.printStackTrace();
                }
            }
        });

        // Зарегистрированный потребитель 2
        MessageConsumer comsumer2 = session.createConsumer(topic);
        comsumer2.setMessageListener(new MessageListener() {
            public void onMessage(Message m) {
                try {
                    System.out.println("Второй потребитель получил "
                            + ((TextMessage) m).getText());
                } catch (JMSException e) {
                    e.printStackTrace();
                }
            }

        });

        // Создать продюсера и отправить несколько сообщений.
        MessageProducer producer = session.createProducer(topic);
        for (int i = 1; i <= 10; i++) {
            System.out.println("Отправитель отправил сообщение " + i);
            producer.send(session.createTextMessage("сообщение " + i));
        }
    }

}