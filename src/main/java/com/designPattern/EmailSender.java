package com.designPattern;

public class EmailSender {

    public void send(String address,String content){
        System.out.println(String.format("sending email to %s content is %s",address,content));
    }

}
