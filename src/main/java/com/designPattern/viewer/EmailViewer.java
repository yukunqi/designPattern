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


    private StatFormat format;

    public EmailViewer() {
        this(new EmailSender(),new EmailStatFormat());
    }

    public EmailViewer(EmailSender emailSender, StatFormat format) {
        this.emailSender = emailSender;
        this.format = format;
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
