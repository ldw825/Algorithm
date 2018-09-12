package com.kent.algorithm;

/**
 * @author Kent
 * @date 2018/09/13
 * @describtion ��֪һ�����ӳ�����3�����Ժ󣨰���������һ�������ӣ�������Ҳ����������ɣ��������е����Ӷ���������ÿ�������ӵ������Ƕ��١�
 * @algorithm ���һ�����Ӷ��࣬�������һ�����������ı�����ÿ���¼ӣ������ҵ����ڵ��ڣ�ʱ���Բ���һ�������Ӷԡ���
 */

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

public class RabitPropagate {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("�����n���£�");
        int n = scanner.nextInt();
        scanner.close();
        RabitFactory factory = new RabitFactory();
        factory.getCountByMonth(n);
    }

}

class RabitPair {

    /**
     * ���ӳ������n����
     */
    private int months;

    public boolean tryPropagate() {
        months++;
        if (months < 3) {
            return false;
        }
        return true;
    }

}

class RabitFactory {
    private List<RabitPair> rabitPairs;

    public RabitFactory() {
        rabitPairs = new ArrayList<>();
        rabitPairs.add(new RabitPair());
    }

    public void getCountByMonth(int month) {
        if (month <= 0) {
            return;
        }

        for (int i = 1; i <= month; i++) {
            List<RabitPair> newRabits = null;
            Iterator<RabitPair> iterator = rabitPairs.iterator();
            while (iterator.hasNext()) {
                RabitPair rabitPair = iterator.next();
                boolean res = rabitPair.tryPropagate();
                if (res) {
                    if (newRabits == null) {
                        newRabits = new ArrayList<>();
                    }
                    newRabits.add(new RabitPair());
                }
            }
            if (newRabits != null) {
                rabitPairs.addAll(newRabits);
            }
        }

        System.out.println("��" + month + "���£�����" + rabitPairs.size() + "������");

    }

}
