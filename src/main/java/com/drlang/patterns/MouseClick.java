package com.drlang.patterns;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public interface MouseClick extends MouseListener {
    @Override
    void mouseEntered(MouseEvent e);

    @Override
    void mouseExited(MouseEvent e);

    @Override
    default void mousePressed(MouseEvent e) {
    }

    @Override
    default void mouseReleased(MouseEvent e) {
    }
}
