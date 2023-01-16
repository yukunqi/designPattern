package com.backup;

import org.apache.commons.lang3.RandomStringUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * @description:
 * @author: KunQi Yu
 * @date: 2023-01-16 11:34
 **/
class SnapshotBackUpDataSourceTest {


    @Test
    public void compareRevertAndOriginal_should_be_theSame(){
        DataObject dataObject = new DataObject();
        String s = RandomStringUtils.randomAlphabetic(3);
        dataObject.append(s);
        String expected = dataObject.getText();

        try {
            Thread.sleep(2000L);
        } catch (InterruptedException interruptedException) {
            interruptedException.printStackTrace();
        }

        dataObject.close();

        DataObject newOne = new DataObject();
        newOne.initByVersion(System.currentTimeMillis());
        String actual = newOne.getText();
        newOne.close();
        Assertions.assertEquals(expected,actual);
    }

    @Test
    public void concurrentTestWith_append_and_revert_should_no_affect(){

    }

    @Test
    public void revertOnTargetVersion(){//fixme 按照版本进行恢复的逻辑还有问题
        DataObject dataObject = new DataObject();
        dataObject.initByVersion(1673846384607L);
        String actual = dataObject.getText();
        System.out.println(actual);
    }
}