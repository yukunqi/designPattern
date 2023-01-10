package com.backup;

/**
 * @description:
 * @author: KunQi Yu
 * @date: 2023-01-05 23:10
 **/
public class DataObject {

    private final StringBuilder text = new StringBuilder();

    public DataObject() {
    }

    public String getText() {
        return text.toString();
    }

    public void append(String input) {
        text.append(input);

    }
}
