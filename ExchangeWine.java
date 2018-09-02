package com.kent.algorithm;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Kent
 * @date 2018/09/02
 * @describtion 已知2元钱可以买一瓶酒，2个空瓶或者4个瓶盖可以换一瓶新酒，现在有10元钱问总共可以喝多少瓶酒。
 * @algorithm 定义酒、空瓶、瓶盖三个实体类以及它们的管理类来执行喝酒和兑换的操作，保存每次操作后的数量，直到操作不能继续进行时打印最终的数量。
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
 * 酒类
 */
class Wine {
    public Wine() {
        System.out.println("得到1瓶酒");
    }
}

/**
 * 空瓶类
 */
class Bottle {
    public Bottle() {
        System.out.println("产生1个空瓶");
    }
}

/**
 * 瓶盖类
 */
class Lid {
    public Lid() {
        System.out.println("产生1个瓶盖");
    }
}

/**
 * 酒管理类
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
        System.out.println("本轮喝了" + count + "瓶酒");
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
 * 空瓶管理类
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
        System.out.println("消耗了" + cost + "个空瓶");
        if (listener != null) {
            listener.onExchange(newWineCount);
        }
    }
}

/**
 * 瓶盖管理类
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
        System.out.println("消耗了" + cost + "个瓶盖");
        if (listener != null) {
            listener.onExchange(newWineCount);
        }
    }

}

/**
 * 喝完一瓶酒的回调接口
 */
interface OnDrinkListener {
    /**
     * @param wineCount
     *            喝的酒的数量
     */
    void onDrink(int wineCount);
}

/**
 * 兑换完新酒的回调接口
 */
interface OnExchangeListener {
    /**
     * @param wineCount
     *            成功兑换的新酒的数量
     */
    void onExchange(int wineCount);
}

/**
 * 统筹兑换管理类
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
            System.out.println("Game over,总共喝了" + drinkCount + "瓶酒，剩余" + bottleKeeper.getCount() + "个空瓶和"
                    + lidKeeper.getCount() + "个瓶盖");
        }
    }

    @Override
    public void onExchange(int wineCount) {
        wineKeeper.addWines(wineCount);
    }

}
