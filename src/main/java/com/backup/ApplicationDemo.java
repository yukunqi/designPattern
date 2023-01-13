package com.backup;

/**
 * @description:
 * @author: KunQi Yu
 * @date: 2023-01-13 10:54
 **/
public class ApplicationDemo {

    public static void main(String[] args) {
        DataObject dataObject = new DataObject();

        dataObject.append("hello");
        System.out.printf("current print:%s\n", dataObject.getText());
        dataObject.append("world");
        System.out.printf("current print:%s\n",dataObject.getText());


        dataObject.close();
        try {
            System.out.println("sleep for 5s in order to make sure increase back up finished");
            Thread.sleep(5000L);
        } catch (InterruptedException interruptedException) {
            interruptedException.printStackTrace();
        }


        dataObject = new DataObject();
        dataObject.initByVersion(System.currentTimeMillis());
        System.out.printf("recovered from backup print:%s\n",dataObject.getText());
    }
}
