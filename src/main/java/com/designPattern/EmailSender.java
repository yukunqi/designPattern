package com.designPattern;

public class EmailSender {

    public void send(String address,String content){
        System.out.printf("sending email to %s content is %s%n",address,content);
    }

}
