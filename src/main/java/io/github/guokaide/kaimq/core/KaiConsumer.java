package io.github.guokaide.kaimq.core;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
public class KaiConsumer<T> {

    private KaiBroker broker;
    private String topic;
    private KaiMq mq;

    public KaiConsumer(KaiBroker broker) {
        this.broker = broker;
    }

    public void subscribe(String topic) {
        this.topic = topic;
        this.mq = broker.find(topic);
    }

    public KaiMessage<T> poll(long timeout) {
        return mq.poll(timeout);
    }

    public void listen(KaiListener<T> listener) {
        mq.addListener(listener);
    }

}
