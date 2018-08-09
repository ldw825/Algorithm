package com.kent.algorithm;

/**
 * @author Kent
 * @date 2018/08/09
 * @describtion n����Χ��һȦ�����1~n���ӵ�һ���˿�ʼ�����δ�1��ʼ����������m���˳��֣�Ȼ��ӳ����ߵ���һ�����¿�ʼ������ֱ��ʣ�����һ��������Ҵ��ߵı�š�
 * @algorithm ʹ��ѭ���������n���˴���������ÿ���˶�Ӧһ����㣬�����Լ��ı���Լ���һ���˵����á�������ĸ�������1ʱ��ѭ����̭��m���ˣ�ֱ��ʣ��һ����Ϊֹ��
 */

import java.util.Scanner;

public class CountOffWash {

    public static void main(String[] args) {
        // ͨ��Scanner����n��m
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter n and m:");
        int n = scanner.nextInt();
        int m = scanner.nextInt();
        scanner.close();
        CountOffWash wash = new CountOffWash();
        System.out.println("The last num is:" + wash.getLastNum(n, m));
    }

    /**
     * �õ�����Ҵ��ߵı��
     * 
     * @param n
     *            ������n�ǹ̶�ֵ
     * @param m
     *            ���m�ǹ̶�ֵ
     * @return �Ҵ��ߵı��
     */
    private int getLastNum(final int n, final int m) {
        // �Ҵ��ߵĽ��
        Node last = null;
        // �ȳ�ʼ�������õ�ͷ���
        Node start = initNodes(n);
        // ����ʣ�µĸ����������Ƿ���Ҫ������̭
        int totalNum = n;
        while (totalNum > 1) {
            // ���ݵ�һ���������ˣ��õ���m����
            Node[] remove = getNodeby(start, m);
            // ��m��ǰ���Ǹ�
            Node prev = remove[0];
            // ��m����
            Node target = remove[1];
            // ��ǰ���Ǹ�������last����Ϊǰ���Ǹ��϶����ᱻ�Ƴ�
            last = prev;
            // �ӱ�m����һ�����¿�ʼ�����ı�start���
            start = target.next;
            // ��m��ǰ���Ǹ���������һ�����target.next��Ҳ�����µ�start
            prev.next = start;
            // �Ƴ���m�Ľ���next���ã���ֹ�ڴ�й¶
            target.next = null;
            // ���������1
            totalNum--;
            // ��ӡ������̭�Ľ��ı��
            System.out.println("wash out:" + target.num);
        }
        if (last != null) {
            return last.num;
        }
        return -1;
    }

    /**
     * ��ʼ������Ϊÿ�������룬����������
     * 
     * @param n
     *            ������n�ǹ̶�ֵ
     * @return ͷ���
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
     * �õ���һ��Ҫ�Ƴ��Ľ�㣬����m���˵Ľ��
     * 
     * @param start
     *            ��ʼ�����Ľ��
     * @param m
     *            ���m�ǹ̶�ֵ
     * @return ��m�Ľ�㣬�Լ�ǰһ����㣬�Ƴ����ʱ���õ�
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
     * ��ͷ��㿪ʼ���δ�ӡÿ�����ı��
     * 
     * @param head
     *            �����ͷ���
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
     * ÿ���˶�Ӧ�Ľ��ṹ
     * 
     * @member num ÿ���˶�Ӧ�ı��
     * @mebmer next ��һ���˵�����
     */
    class Node {
        int num;
        Node next;
    }

}
