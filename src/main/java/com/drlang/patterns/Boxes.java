package com.drlang.patterns;

import javax.swing.*;
import java.awt.*;
import java.util.Observable;

public class Boxes extends JFrame {
    Observable notifier = new Observable() {
        @Override
        public void notifyObservers() {
            setChanged();
            super.notifyObservers();
        }
    };

    public Boxes(int gird){
        setTitle("Demonstrates Observer pattern");
        Container cp = getContentPane();
        cp.setLayout(new GridLayout(gird,gird));
        for (int i = 0; i < gird; i++) {
            for (int j = 0; j < gird; j++) {
//                cp.add(new Box(i,j,notifier));
            }
        }
    }
}
