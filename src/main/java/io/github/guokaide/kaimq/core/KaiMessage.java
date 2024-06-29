package io.github.guokaide.kaimq.core;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

/**
 * kai mq message model.
 *
 * @param <T>
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class KaiMessage<T> {
    private Long id;
    private T body;
    private Map<String, String> headers; // 属性：系统属性以及业务属性
}
