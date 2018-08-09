package com.kent.algorithm;

/**
 * @author Kent
 * @date 2018/08/09
 * @describtion n个人围成一圈，编号1~n。从第一个人开始，依次从1开始报数，报到m的人出局，然后从出局者的下一个重新开始报数，直到剩下最后一个，求该幸存者的编号。
 * @algorithm 使用循环链表把这n个人串联起来，每个人对应一个结点，保存自己的编号以及下一个人的引用。当链表的个数大于1时，循环淘汰报m的人，直到剩下一个人为止。
 */

import java.util.Scanner;

public class CountOffWash {

    public static void main(String[] args) {
        // 通过Scanner输入n和m
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter n and m:");
        int n = scanner.nextInt();
        int m = scanner.nextInt();
        scanner.close();
        CountOffWash wash = new CountOffWash();
        System.out.println("The last num is:" + wash.getLastNum(n, m));
    }

    /**
     * 得到最后幸存者的编号
     * 
     * @param n
     *            结点个数n是固定值
     * @param m
     *            间隔m是固定值
     * @return 幸存者的编号
     */
    private int getLastNum(final int n, final int m) {
        // 幸存者的结点
        Node last = null;
        // 先初始化链表，得到头结点
        Node start = initNodes(n);
        // 根据剩下的个数来决定是否需要继续淘汰
        int totalNum = n;
        while (totalNum > 1) {
            // 根据第一个报数的人，得到报m的人
            Node[] remove = getNodeby(start, m);
            // 报m的前面那个
            Node prev = remove[0];
            // 报m的人
            Node target = remove[1];
            // 把前面那个保存在last，因为前面那个肯定不会被移除
            last = prev;
            // 从报m的下一个重新开始报，改变start结点
            start = target.next;
            // 报m的前面那个，它的下一个变成target.next，也即是新的start
            prev.next = start;
            // 移除报m的结点的next引用，防止内存泄露
            target.next = null;
            // 结点总数减1
            totalNum--;
            // 打印本轮淘汰的结点的编号
            System.out.println("wash out:" + target.num);
        }
        if (last != null) {
            return last.num;
        }
        return -1;
    }

    /**
     * 初始化链表，为每个结点编码，并串联起来
     * 
     * @param n
     *            结点个数n是固定值
     * @return 头结点
     */
    private Node initNodes(final int n) {
        final Node head = new Node();
        head.num = 1;
        Node prev = head;
        for (int i = 2; i <= n; i++) {
            Node node = new Node();
            node.num = i;
            prev.next = node;
            prev = node;
        }
        prev.next = head;
        printNodes(head);
        return head;
    }

    /**
     * 得到下一个要移除的结点，即报m的人的结点
     * 
     * @param start
     *            开始报数的结点
     * @param m
     *            间隔m是固定值
     * @return 报m的结点，以及前一个结点，移除结点时会用到
     */
    private Node[] getNodeby(Node start, final int m) {
        Node prev = null;
        Node result = start;
        for (int i = 0; i < m - 1; i++) {
            prev = result;
            result = prev.next;
        }
        return new Node[] { prev, result };
    }

    /**
     * 从头结点开始依次打印每个结点的编号
     * 
     * @param head
     *            链表的头结点
     */
    private void printNodes(final Node head) {
        StringBuilder builder = new StringBuilder();
        Node start = head;
        do {
            builder.append(start.num).append("->");
            start = start.next;
        } while (start != head);
        builder.setLength(builder.length() - "->".length());
        System.out.println("LinkedList:" + builder);
    }

    /**
     * 每个人对应的结点结构
     * 
     * @member num 每个人对应的编号
     * @mebmer next 下一个人的引用
     */
    class Node {
        int num;
        Node next;
    }

}
