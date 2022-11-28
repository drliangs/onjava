package com.drlang.patterns.trash;

import java.util.ArrayList;
import java.util.List;

public class Bins{
    final List<Trash> bin;
    final List<Aluminum> aluminums = new ArrayList<>();
    final List<Paper> papers = new ArrayList<>();
    final List<Glass> glasses = new ArrayList<>();
    final List<Cradboard> cradboards = new ArrayList<>();
    public Bins(List<Trash> source){
        this.bin = new ArrayList<>(source);
        bin.forEach(t->{
            if (t instanceof Aluminum a){
                aluminums.add(a);
            }
            if (t instanceof Paper p){
                papers.add(p);
            }
            if (t instanceof Glass g){
                glasses.add(g);
            }
            if (t instanceof Cradboard c){
                cradboards.add(c);
            }
        });
    }
    public void show(){
        TrashValue.sum(aluminums,"Aluminum");
        TrashValue.sum(papers,"Paper");
        TrashValue.sum(glasses,"Glass");
        TrashValue.sum(cradboards,"Cardboard");
        TrashValue.sum(bin,"Trash");
    }
}
