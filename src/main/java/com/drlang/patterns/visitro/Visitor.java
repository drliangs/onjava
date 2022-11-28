package com.drlang.patterns.visitro;

public interface Visitor {
    void visit(Gladiolus g);

    void visit(Ranunculus r);

    void visit(Chrysanthemum c);
}
