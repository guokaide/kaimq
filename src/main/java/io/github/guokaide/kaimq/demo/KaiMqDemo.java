package io.github.guokaide.kaimq.demo;

import io.github.guokaide.kaimq.core.KaiBroker;
import io.github.guokaide.kaimq.core.KaiConsumer;
import io.github.guokaide.kaimq.core.KaiMessage;
import io.github.guokaide.kaimq.core.KaiProducer;

import java.io.IOException;

public class KaiMqDemo {
    public static void main(String[] args) throws IOException {
        String topic = "kai.order";

        KaiBroker broker = new KaiBroker();
        broker.createTopic(topic);

        KaiProducer producer = broker.createProducer();
        KaiConsumer<Order> consumer = broker.createConsumer(topic);
        consumer.listen((message) -> {
            System.out.println(" onMessage: " + message);
        });

        for (int i = 0; i < 10; i++) {
            Order order = new Order(i, "item: " + i);
            producer.send(topic, new KaiMessage<>((long) i, order, null));
        }

        for (int i = 0; i < 10; i++) {
            KaiMessage<Order> message = consumer.poll(1000);
            System.out.println(message);
        }

        long id = 11;
        while (true) {
            char c = (char) System.in.read();
            if (c == 'q') {
                break;
            }
            if (c == 'p') {
                Order order = new Order(id, "item: " + id);
                producer.send(topic, new KaiMessage<>(id++, order, null));
                System.out.println(" send ok: " + order);
            }
            if (c == 'c') {
                KaiMessage<Order> message = consumer.poll(1000);
                System.out.println(" poll ok: " + message);
            }
            if (c == 'a') {
                for (int i = 0; i < 10; i++) {
                    Order order = new Order(id, "item: " + id);
                    producer.send(topic, new KaiMessage<>(id++, order, null));
                    System.out.println(" send ok: " + order);
                }
            }
        }
    }
}
