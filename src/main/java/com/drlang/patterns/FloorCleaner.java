package com.drlang.patterns;

public class FloorCleaner {
    public FloorCleaner() {
    }

    private Attachment attachment = new Vacuum();
    public void change(Attachment attachment){
        this.attachment = attachment;
    }
    public void clear(){
        attachment.action();
    }


    private static class Vacuum implements Attachment{

        @Override
        public void action() {
            System.out.println("Vacuuming");
        }
    }
    private static class Mop implements Attachment{

        @Override
        public void action() {
            System.out.println("Mopping");
        }
    }
    private static class CleanTheFloor{
        public static void main(String[] args) {
            FloorCleaner floorCleaner = new FloorCleaner();
            floorCleaner.clear();
            floorCleaner.change(new Mop());
            floorCleaner.clear();
        }
    }
}
