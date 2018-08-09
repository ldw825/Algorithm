package com.kent.algorithm;

/**
 * @author Kent
 * @date 2018/08/09
 * @describtion 数组中有若干个0，要把这些0全部放到数组的后面。要求不能使用新数组来选择保存，并满足性能要求。
 * @alorithm 1.根据要求，只能考虑移动数组的做法，因为数组的前面可能要删除数据，后面要增加数据，所以数组遍历的方向应该从后往前遍历。2.从后往前遍历时，先找到第一个非0的数据，记下其位置，并把它前面的一个作为0来处理(实际可能是非0)，
 *           这个位置是算法的起点位置。如果这个位置数据是0，并且和后面的非0相邻，表明这个0后面只有一个非0，则和这个非0交换位置，同时这个非0的位置向前移一个；如果这个0后面有多个非0，则把0移到所有非0的后面。3.算法起点位置继续往前遍历，直到数组结束。
 *
 */

public class MoveZerosBack {

    private static final int[] ARRAY = { 0, 3, 9, 11, 0, 0, 6, 7, 0 };

    public static void main(String[] args) {
        new MoveZerosBack().startMove(ARRAY);
    }

    private void startMove(int[] a) {
        System.out.println("before move, a=" + appendArray(a));
        move(a);
        System.out.println("after move, a=" + appendArray(a));
    }

    private void move(int[] a) {
        final int N = a.length;
        // 从后往前找到的非0的位置，初始值为数组的最后一个
        int posIndex = N - 1;
        // 非0位置前面的一个
        int zeroIndex = -1;
        // 从后往前找第一个非0位置
        for (int i = N - 1; i >= 0; i--) {
            if (a[i] == 0) {
                continue;
            } else {
                posIndex = i;
                zeroIndex = i - 1;
                break;
            }
        }

        // 如果非0位置为0，则表示整个数组都是正数，不需要移动
        if (zeroIndex == -1) {
            return;
        }
        // 如果非0位置为1，表示只有第一个数据是0，把它移到数组的最后即可
        if (zeroIndex == 0) {
            if (a[0] != 0) {
                return;
            }
            for (int i = 0; i < N - 1; i++) {
                a[i] = a[i + 1];
            }
            a[N - 1] = 0;
        }

        // 从第一个0(可能实际是非0)开始往前遍历
        for (int i = zeroIndex; i >= 0; i--) {
            if (a[i] == 0) { // 只处理0的情况，如果不是0，则i不断往前，这样和最后一个非0之间就会存在多个非0
                if ((i + 1) == posIndex) { // 表明找到的0和后面的一个非0是相邻的情况，直接交换两者就可以
                    a[i] = a[posIndex];
                    a[posIndex] = 0;
                } else {// 找到的0和最后面的非0间隔了多个非0，把0移动最后一个非0的后面
                    for (int j = i; j < posIndex; j++) {
                        a[j] = a[j + 1];
                    }
                    a[posIndex] = 0;
                }
                posIndex--; // 不管找到的0和最后面的非0是否相邻，每遍历一次，非0的位置都要向前移一位
            }
        }
    }

    private String appendArray(int[] a) {
        StringBuilder builder = new StringBuilder();
        builder.append("[");
        for (int i = 0; i < a.length; i++) {
            builder.append(a[i]).append(",");
        }
        builder.setLength(builder.length() - 1);
        builder.append("]");
        return builder.toString();
    }

}
