package io.github.guokaide.kaimq.core;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.SneakyThrows;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * topic message storage queue.
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
public class KaiMq {

    private String topic;

    private LinkedBlockingQueue<KaiMessage> queue = new LinkedBlockingQueue<>();

    private List<KaiListener<?>> listeners = new ArrayList<>();

    public KaiMq(String topic) {
        this.topic = topic;
    }

    public boolean send(KaiMessage message) {
        boolean offered = this.queue.offer(message);
        this.listeners.forEach(listener -> listener.onMessage(message));
        return offered;
    }

    // 拉模式
    @SneakyThrows
    public <T> KaiMessage<T> poll(long timeout) {
        return this.queue.poll(timeout, TimeUnit.MILLISECONDS);
    }

    // 推模式
    public <T> void addListener(KaiListener<T> listener) {
        this.listeners.add(listener);
    }
}
