package com.drlang.patterns.abstractfactory;

import java.util.function.Supplier;

public class GameElementFactory {

    Supplier<Obstacle> obstacle;

    Supplier<Player> player;

}
