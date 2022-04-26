
import javax.jms.ConnectionFactory;

import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;


public class Main {
    public static ActiveMQConnectionFactory getConnectionFactory() {
        return new ActiveMQConnectionFactory(
                ActiveMQConnection.DEFAULT_USER,
                ActiveMQConnection.DEFAULT_PASSWORD,
                "tcp://localhost:61616");
    }

    public static ActiveMQConnectionFactory getEmbeddedBroker() {
        return new ActiveMQConnectionFactory("vm://localhost?broker.persistent=false");
    }

    public static void main(String[] args) {
      /*  ConnectionFactory connect = getConnectionFactory();
        Sender sender = new Sender(connect);
        sender.connectAndTransfer(Boolean.FALSE, "AUTO_ACKNOWLEDGE", "Queue");
        Consumer consumer = new Consumer(connect);
        consumer.connectAndGet(Boolean.FALSE, "AUTO_ACKNOWLEDGE", "Queue");*/

       /* ConnectionFactory connect = getConnectionFactory();
        Sender sender = new Sender(connect);
        sender.connectAndTransfer(Boolean.FALSE, "CLIENT_ACKNOWLEDGE", "Queue");
        Consumer consumer = new Consumer(connect);
        consumer.connectAndGet(Boolean.FALSE, "CLIENT_ACKNOWLEDGE", "Queue");*/
        long time = System.currentTimeMillis();
        ConnectionFactory connect = getEmbeddedBroker();
        Sender sender = new Sender(connect);
        sender.connectAndTransfer(false, "AUTO_ACKNOWLEDGE", "Queue");
        Consumer consumer = new Consumer(connect);
        consumer.connectAndGet(false, "AUTO_ACKNOWLEDGE", "Queue");
        System.out.println(System.currentTimeMillis() - time);

        long time1 = System.currentTimeMillis();
        ConnectionFactory connect1 = getConnectionFactory();
        Sender sender1 = new Sender(connect1);
        sender.connectAndTransfer(false, "AUTO_ACKNOWLEDGE", "Queue");
        Consumer consumer1 = new Consumer(connect1);
        consumer.connectAndGet(false, "AUTO_ACKNOWLEDGE", "Queue");
        System.out.println(System.currentTimeMillis() - time1);
    }
}
