package com.drlang.patterns;

import com.drlang.patterns.trash.Trash;

import java.util.ArrayList;

public class TrashSorter extends ArrayList<ArrayList<Trash>> {
    @Override
    public boolean add(ArrayList<Trash> trashes) {
        return super.add(trashes);
    }
}
