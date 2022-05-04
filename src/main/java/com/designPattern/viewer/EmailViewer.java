package com.designPattern.viewer;

import com.designPattern.EmailSender;
import com.designPattern.format.EmailStatFormat;
import com.designPattern.format.StatFormat;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class EmailViewer implements StatViewer {
    private EmailSender emailSender;
    private List<String> toAddresses = new ArrayList<>();

    /**
     * 这里正确的形式可能是依赖注入，而不是自己实例化。但是email应该默认就是email类型的format 所以直接初始化成默认的了
     */
    private StatFormat format = new EmailStatFormat();

    public EmailViewer() {
        this.emailSender = new EmailSender(/*省略参数*/);
    }

    public void setFormat(StatFormat format) {
        this.format = format;
    }

    public EmailViewer(EmailSender emailSender) {
        this.emailSender = emailSender;
    }

    public void addToAddress(String address) {
        toAddresses.add(address);
    }

    @Override
    public void output(Map requestStats, long startTimeInMillis, long endTimeInMills) {
        // format the requestStats to HTML style.
        String email = format.format(requestStats);
        // send it to email toAddresses.
        for (String address : toAddresses){
            emailSender.send(address,email);
        }
    }
}
