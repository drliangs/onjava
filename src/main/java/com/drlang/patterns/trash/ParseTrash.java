package com.drlang.patterns.trash;


import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class ParseTrash {
    public static String source = "Trash.dat";

    public static <T extends Trash> void fillBin(String packageName, Fillable<T> bin) {
        DynaFactory factory = new DynaFactory(packageName);
        try {
            Files.lines(Paths.get("trash", source))
                    .filter(line -> line.trim().length() != 0)
                    .filter(line -> !line.startsWith("//"))
                    .forEach(line -> {
                        String type = line.substring(0, line.indexOf(':')).trim();
                        double weight = Double.parseDouble(line.substring(line.indexOf(':') + 1).trim());
                        bin.addTrash(factory.create(new TrashInfo(type, weight)));
                    });
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static <T extends Trash> void fillBin(String packageName, List<T> bin) {
        fillBin(packageName, new FillableList<>(bin));
    }

}

class FillableList<T extends Trash> implements Fillable<T> {
    private final List<T> list;

    public FillableList(List<T> list) {
        this.list = list;
    }

    @Override
    public void addTrash(T t) {
        list.add(t);
    }
}
