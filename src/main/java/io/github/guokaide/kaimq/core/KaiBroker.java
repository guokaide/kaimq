package io.github.guokaide.kaimq.core;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * KaiMq Broker.
 */
public class KaiBroker {

    private final Map<String, KaiMq> topicMqs = new ConcurrentHashMap<>(64);

    public KaiMq find(String topic) {
        if (!this.topicMqs.containsKey(topic)) {
            throw new IllegalArgumentException("topic: " + topic + "not found");
        }
        return this.topicMqs.get(topic);
    }

    public void createTopic(String topic) {
        this.topicMqs.putIfAbsent(topic, new KaiMq(topic));
    }

    public KaiProducer createProducer() {
        return new KaiProducer(this);
    }

    public KaiConsumer createConsumer() {
        return new KaiConsumer(this);
    }

    public KaiConsumer createConsumer(String topic) {
        KaiConsumer consumer = new KaiConsumer(this);
        consumer.subscribe(topic);
        return consumer;
    }

}
