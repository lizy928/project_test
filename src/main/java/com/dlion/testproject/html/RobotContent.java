package com.dlion.testproject.html;

import lombok.Data;

import java.util.List;

/**
 * @author 李正元
 * @date 2019/8/27
 */
@Data
public class RobotContent {

    /**
     * 文本-html
     */
    private String text;

    /**
     * 按钮-选项
     */
    private List<String> options;
}
