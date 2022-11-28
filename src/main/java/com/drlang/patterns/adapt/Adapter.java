package com.drlang.patterns.adapt;


class WhatIHava{
    public void g(){};
    public void h(){};
}

interface WhatIWant{
    void f();
}
class ProxyAdapter implements WhatIWant{
    private final WhatIHava whatIHava;

    public ProxyAdapter(WhatIHava whatIHava) {
        this.whatIHava = whatIHava;
    }

    @Override
    public void f() {
        whatIHava.g();;
        whatIHava.h();
    }
}
class WhatIUse{
    public void op(WhatIHava wiw){
            wiw.h();
    }
}
class WhatIUse2 extends WhatIUse{
    @Override
    public void op(WhatIHava wiw) {
        new ProxyAdapter(wiw).f();
    }
}
class WhatIHave2 extends WhatIHava implements WhatIWant{
    @Override
    public void f() {
        g();
        h();
    }
}

class WhatIHave3 extends WhatIHava {
    private class InnerAdapter implements WhatIWant{
        @Override
        public void f() {
            g();
            h();
        }
    }
    public WhatIWant whatIWant(){
        return new InnerAdapter();
    }
}

public class Adapter {
    public static void main(String[] args) {
        WhatIUse whatIUse = new WhatIUse();
        WhatIHava whatIHava = new WhatIHava();
        WhatIWant adapt = new ProxyAdapter(whatIHava);
//        whatIUse.op(adapt);
    }
}
