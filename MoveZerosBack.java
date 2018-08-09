package com.kent.algorithm;

/**
 * @author Kent
 * @date 2018/08/09
 * @describtion �����������ɸ�0��Ҫ����Щ0ȫ���ŵ�����ĺ��档Ҫ����ʹ����������ѡ�񱣴棬����������Ҫ��
 * @alorithm 1.����Ҫ��ֻ�ܿ����ƶ��������������Ϊ�����ǰ�����Ҫɾ�����ݣ�����Ҫ�������ݣ�������������ķ���Ӧ�ôӺ���ǰ������2.�Ӻ���ǰ����ʱ�����ҵ���һ����0�����ݣ�������λ�ã�������ǰ���һ����Ϊ0������(ʵ�ʿ����Ƿ�0)��
 *           ���λ�����㷨�����λ�á�������λ��������0�����Һͺ���ķ�0���ڣ��������0����ֻ��һ����0����������0����λ�ã�ͬʱ�����0��λ����ǰ��һ����������0�����ж����0�����0�Ƶ����з�0�ĺ��档3.�㷨���λ�ü�����ǰ������ֱ�����������
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
        // �Ӻ���ǰ�ҵ��ķ�0��λ�ã���ʼֵΪ��������һ��
        int posIndex = N - 1;
        // ��0λ��ǰ���һ��
        int zeroIndex = -1;
        // �Ӻ���ǰ�ҵ�һ����0λ��
        for (int i = N - 1; i >= 0; i--) {
            if (a[i] == 0) {
                continue;
            } else {
                posIndex = i;
                zeroIndex = i - 1;
                break;
            }
        }

        // �����0λ��Ϊ0�����ʾ�������鶼������������Ҫ�ƶ�
        if (zeroIndex == -1) {
            return;
        }
        // �����0λ��Ϊ1����ʾֻ�е�һ��������0�������Ƶ��������󼴿�
        if (zeroIndex == 0) {
            if (a[0] != 0) {
                return;
            }
            for (int i = 0; i < N - 1; i++) {
                a[i] = a[i + 1];
            }
            a[N - 1] = 0;
        }

        // �ӵ�һ��0(����ʵ���Ƿ�0)��ʼ��ǰ����
        for (int i = zeroIndex; i >= 0; i--) {
            if (a[i] == 0) { // ֻ����0��������������0����i������ǰ�����������һ����0֮��ͻ���ڶ����0
                if ((i + 1) == posIndex) { // �����ҵ���0�ͺ����һ����0�����ڵ������ֱ�ӽ������߾Ϳ���
                    a[i] = a[posIndex];
                    a[posIndex] = 0;
                } else {// �ҵ���0�������ķ�0����˶����0����0�ƶ����һ����0�ĺ���
                    for (int j = i; j < posIndex; j++) {
                        a[j] = a[j + 1];
                    }
                    a[posIndex] = 0;
                }
                posIndex--; // �����ҵ���0�������ķ�0�Ƿ����ڣ�ÿ����һ�Σ���0��λ�ö�Ҫ��ǰ��һλ
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
