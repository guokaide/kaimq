package io.github.guokaide.kaimq.core;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
public class KaiProducer {

    private KaiBroker broker;

    public boolean send(String topic, KaiMessage message) {
        KaiMq mq = broker.find(topic);
        return mq.send(message);
    }
}
