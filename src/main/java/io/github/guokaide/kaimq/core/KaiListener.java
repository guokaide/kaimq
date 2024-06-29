package io.github.guokaide.kaimq.core;

/**
 * Message Listener.
 */
public interface KaiListener<T> {
    void onMessage(KaiMessage<T> message);

}
