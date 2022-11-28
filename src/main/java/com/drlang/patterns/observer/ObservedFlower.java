package com.drlang.patterns.observer;

import java.util.Observable;
import java.util.Observer;

class Flower {
    private boolean isOpen = false;
    private boolean alreadyOpen = false;
    private boolean alreadyClosed = false;

    Observable opening = new Observable() {
        @Override
        public void notifyObservers() {
            if (isOpen && !alreadyOpen) {
                setChanged();
                super.notifyObservers();
                alreadyOpen = true;
            }
        }
    };
    Observable closing = new Observable() {
        @Override
        public void notifyObservers() {
            if (!isOpen && !alreadyClosed) {
                setChanged();
                super.notifyObservers();
                alreadyClosed = true;
            }
        }
    };

    public void open() {
        isOpen = true;
        opening.notifyObservers();
        alreadyClosed = false;
    }

    public void close() {
        isOpen = false;
        closing.notifyObservers();
        alreadyClosed = true;
    }
}

class Bee {
    private String id;

    public Bee(String id) {
        this.id = id;
    }

    public final Observer whenOpened = (ab, a) -> System.out.println("Bee " + id + "'s breakfast time!");
    public final Observer whenClosed = (ab, a) -> System.out.println("Bee " + id + "'is bed time!");

}

class Hummingbird {
    private String id;

    public Hummingbird(String id) {
        this.id = id;
    }

    public final Observer whenOpened = (ab, a) -> System.out.println("Hummingbird  " + id + "'s breakfast time!");
    public final Observer whenClosed = (ab, a) -> System.out.println("Hummingbird " + id + "'is bed time!");

}

public class ObservedFlower {
    public static void main(String[] args) {

        Flower f = new Flower();
        Bee ba = new Bee("A"), bb = new Bee("B");
        Hummingbird ha = new Hummingbird("A"), hb = new Hummingbird("B");
        f.opening.addObserver(ha.whenOpened);
        f.opening.addObserver(hb.whenOpened);
        f.opening.addObserver(ba.whenOpened);
        f.opening.addObserver(bb.whenOpened);
        f.closing.addObserver(ha.whenClosed);
        f.closing.addObserver(hb.whenClosed);
        f.closing.addObserver(ba.whenClosed);
        f.closing.addObserver(bb.whenClosed);
//        f.opening
        f.opening.deleteObserver(hb.whenOpened);
        f.open();
        f.open();
        System.out.println("-------------------");
        f.closing.deleteObserver(ba.whenClosed);
        f.close();
        System.out.println("###################");
        f.close();
    }



}
