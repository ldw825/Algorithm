package com.kent.algorithm;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Kent
 * @date 2018/09/02
 * @describtion ��֪2ԪǮ������һƿ�ƣ�2����ƿ����4��ƿ�ǿ��Ի�һƿ�¾ƣ�������10ԪǮ���ܹ����Ժȶ���ƿ�ơ�
 * @algorithm ����ơ���ƿ��ƿ������ʵ�����Լ����ǵĹ�������ִ�кȾƺͶһ��Ĳ���������ÿ�β������������ֱ���������ܼ�������ʱ��ӡ���յ�������
 */

public class ExchangeWine {

    public static final int INIT_MONEY = 10;
    public static final int WINE_PRICE = 2;
    public static final int BOTTLE_COUNT_PER_WINE = 2;
    public static final int LID_COUNT_PER_WINE = 4;

    public static void main(String[] args) {
        new Manager().start();
    }

}

/**
 * ����
 */
class Wine {
    public Wine() {
        System.out.println("�õ�1ƿ��");
    }
}

/**
 * ��ƿ��
 */
class Bottle {
    public Bottle() {
        System.out.println("����1����ƿ");
    }
}

/**
 * ƿ����
 */
class Lid {
    public Lid() {
        System.out.println("����1��ƿ��");
    }
}

/**
 * �ƹ�����
 */
class WineKeeper {
    private List<Wine> wines = new ArrayList<>();
    private OnDrinkListener listener;

    public WineKeeper(int wineCount, OnDrinkListener listener) {
        if (wineCount > 0) {
            for (int i = 0; i < wineCount; i++) {
                wines.add(new Wine());
            }
        }
        this.listener = listener;
    }

    public boolean drinkAll() {
        int count = wines.size();
        if (count == 0) {
            return false;
        }
        wines.clear();
        System.out.println("���ֺ���" + count + "ƿ��");
        System.out.println("--------------------");
        if (listener != null) {
            listener.onDrink(count);
        }
        return true;
    }

    public void addWines(int count) {
        if (count > 0) {
            for (int i = 0; i < count; i++) {
                wines.add(new Wine());
            }
        }
    }

}

/**
 * ��ƿ������
 */
class BottleKeeper {
    private List<Bottle> bottles = new ArrayList<>();
    private OnExchangeListener listener;

    public BottleKeeper(OnExchangeListener listener) {
        this.listener = listener;
    }

    public void addBottles(int count) {
        if (count > 0) {
            for (int i = 0; i < count; i++) {
                bottles.add(new Bottle());
            }
        }
    }

    public int getCount() {
        return bottles.size();
    }

    public void tryExchange() {
        int count = bottles.size();
        if (count < ExchangeWine.BOTTLE_COUNT_PER_WINE) {
            return;
        }
        int mod = count % ExchangeWine.BOTTLE_COUNT_PER_WINE;
        int cost = count - mod;
        int newWineCount = cost / ExchangeWine.BOTTLE_COUNT_PER_WINE;
        for (int i = count - 1; i >= mod; i--) {
            bottles.remove(i);
        }
        System.out.println("������" + cost + "����ƿ");
        if (listener != null) {
            listener.onExchange(newWineCount);
        }
    }
}

/**
 * ƿ�ǹ�����
 */
class LidKeeper {

    private List<Lid> lids = new ArrayList<>();
    private OnExchangeListener listener;

    public LidKeeper(OnExchangeListener listener) {
        this.listener = listener;
    }

    public void addLids(int count) {
        if (count > 0) {
            for (int i = 0; i < count; i++) {
                lids.add(new Lid());
            }
        }
    }

    public int getCount() {
        return lids.size();
    }

    public void tryExchange() {
        int count = lids.size();
        if (count < ExchangeWine.LID_COUNT_PER_WINE) {
            return;
        }
        int mod = count % ExchangeWine.LID_COUNT_PER_WINE;
        int cost = count - mod;
        int newWineCount = cost / ExchangeWine.LID_COUNT_PER_WINE;
        for (int i = count - 1; i >= mod; i--) {
            lids.remove(i);
        }
        System.out.println("������" + cost + "��ƿ��");
        if (listener != null) {
            listener.onExchange(newWineCount);
        }
    }

}

/**
 * ����һƿ�ƵĻص��ӿ�
 */
interface OnDrinkListener {
    /**
     * @param wineCount
     *            �ȵľƵ�����
     */
    void onDrink(int wineCount);
}

/**
 * �һ����¾ƵĻص��ӿ�
 */
interface OnExchangeListener {
    /**
     * @param wineCount
     *            �ɹ��һ����¾Ƶ�����
     */
    void onExchange(int wineCount);
}

/**
 * ͳ��һ�������
 */
class Manager implements OnDrinkListener, OnExchangeListener {

    private WineKeeper wineKeeper;
    private BottleKeeper bottleKeeper;
    private LidKeeper lidKeeper;
    private int drinkCount;

    public Manager() {
        int initWineCount = ExchangeWine.INIT_MONEY / ExchangeWine.WINE_PRICE;
        wineKeeper = new WineKeeper(initWineCount, this);
        bottleKeeper = new BottleKeeper(this);
        lidKeeper = new LidKeeper(this);
    }

    public void start() {
        wineKeeper.drinkAll();
    }

    @Override
    public void onDrink(int wineCount) {
        drinkCount += wineCount;
        bottleKeeper.addBottles(wineCount);
        lidKeeper.addLids(wineCount);
        bottleKeeper.tryExchange();
        lidKeeper.tryExchange();
        if (!wineKeeper.drinkAll()) {
            System.out.println("Game over,�ܹ�����" + drinkCount + "ƿ�ƣ�ʣ��" + bottleKeeper.getCount() + "����ƿ��"
                    + lidKeeper.getCount() + "��ƿ��");
        }
    }

    @Override
    public void onExchange(int wineCount) {
        wineKeeper.addWines(wineCount);
    }

}
