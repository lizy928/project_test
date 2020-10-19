package com.dlion.testproject.kafka;

import lombok.Data;

/**
 * @author lzy
 * @date 2020/10/16
 */
@Data
public class Book {

    private Long id;
    private String name;

    public Book() {
    }

    public Book(Long id, String name) {
        this.id = id;
        this.name = name;
    }
}
