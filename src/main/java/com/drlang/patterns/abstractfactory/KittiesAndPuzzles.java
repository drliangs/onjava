package com.drlang.patterns.abstractfactory;

public class KittiesAndPuzzles extends GameElementFactory {

    public KittiesAndPuzzles() {
        player = Kitty::new;
        obstacle = Puzzle::new;
    }

    private static class Melee extends GameElementFactory {
        public Melee() {
            player = Fighter::new;
            obstacle = Weapon::new;
        }
    }

    private static class GameEnvironment {
        private Player p;
        private Obstacle ob;

        public GameEnvironment(GameElementFactory factory) {
            p = factory.player.get();
            ob = factory.obstacle.get();
        }

        public void play() {
            p.interactWith(ob);
        }

        public static void main(String[] args) {
            GameElementFactory kp = new KittiesAndPuzzles();
            GameElementFactory ml = new Melee();
            GameEnvironment g1 = new GameEnvironment(kp);
            GameEnvironment g2 = new GameEnvironment(ml);
            g1.play();
            g2.play();
        }
    }


}

