package com.kent.algorithm;

/**
 * @author Kent
 * @date 2018/09/13
 * @describtion 已知一对兔子出生第3个月以后（包含）会生一对新兔子，新兔子也按照这个规律，假如所有的兔子都不死，求每个月兔子的总数是多少。
 * @algorithm 设计一个兔子对类，里面包含一个出生月数的变量，每个月加１，并且当大于等于３时可以产生一个新兔子对。。
 */

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

public class RabitPropagate {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("输入第n个月：");
        int n = scanner.nextInt();
        scanner.close();
        RabitFactory factory = new RabitFactory();
        factory.getCountByMonth(n);
    }

}

class RabitPair {

    /**
     * 兔子出生后第n个月
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

        System.out.println("第" + month + "个月，共有" + rabitPairs.size() + "对兔子");

    }

}
