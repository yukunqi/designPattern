package com.backup;

import org.apache.commons.lang3.RandomStringUtils;

/**
 * @description:
 * @author: KunQi Yu
 * @date: 2023-01-13 10:54
 **/
public class ApplicationDemo {

    public static void main(String[] args) {
        DataObject dataObject = new DataObject();
        int n = 30;

        for (int i = 0; i < n; i++) {
            String s = RandomStringUtils.randomAlphabetic(3);
            System.out.printf("appending %s to dataStorage",s);
            dataObject.append(s);
            System.out.printf("current dataStorage :%s\n", dataObject.getText());
        }

        String old = dataObject.getText();
        System.out.printf("final dataStorage text is :\n%s\n",old);

        try {
            System.out.println("sleep for 2s in order to make sure increase back up finished");
            Thread.sleep(2000L);
            dataObject.close();
        } catch (InterruptedException interruptedException) {
            interruptedException.printStackTrace();
        }

        DataObject newDataObject = new DataObject();
        newDataObject.initByVersion(System.currentTimeMillis());

        String newStr = newDataObject.getText();
        System.out.printf("recovered from backup print:\n%s\n",newStr);

        System.out.printf("compare two string result : %s\n",newStr.equals(old));

        newDataObject.close();
    }
}
