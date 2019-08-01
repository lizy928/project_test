package com.dlion.testproject.model;

import lombok.Data;

import java.util.Date;

/**
 * @author 李正元
 * @date 2019/8/1
 */
@Data
public class WeiBoMessage {

    private String type;

    private Long receiver_id;

    private Long sender_id;

    private Date created_at;

    private String text;

    private String data;

}
