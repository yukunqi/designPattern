package com.iterator.reference;

import java.util.Iterator;

/**
 * @description:
 * @author: KunQi Yu
 * @date: 2023-01-04 13:58
 **/
public class Application {

    public static void main(String[] args) {

        WeakSnapshotArrayList<Integer> list = new WeakSnapshotArrayList<>(10);
        list.add(3);
        list.add(8);
        list.add(2);

        Iterator<Integer> iter1 = list.iterator();//snapshot: 3, 8, 2
        list.remove(new Integer(2));//list：3, 8
        Iterator<Integer> iter2 = list.iterator();//snapshot: 3, 8
        list.remove(new Integer(3));//list：8
        Iterator<Integer> iter3 = list.iterator();//snapshot: 3

        // 输出结果：3 8 2
        while (iter1.hasNext()) {
            System.out.print(iter1.next() + " ");
        }
        System.out.println();

        // 输出结果：3 8
        while (iter2.hasNext()) {
            System.out.print(iter2.next() + " ");
        }
        System.out.println();

        // 输出结果：8
        while (iter3.hasNext()) {
            System.out.print(iter3.next() + " ");
        }

        System.out.println();

        System.out.println("by now elements doesn't actually remove");
        list.printf();

        function(list);
    }


    private static void function(WeakSnapshotArrayList<Integer> list){
        System.gc();
        try {
            //休眠一下，在运行的时候加上虚拟机参数-XX:+PrintGCDetails，输出gc信息，确定gc发生了。
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("ready to remove a element from list");
        list.remove(new Integer(8));
        System.out.println("by now elements should be all actually removed from the list because no var reference iterator");
        list.printf();
    }
}
