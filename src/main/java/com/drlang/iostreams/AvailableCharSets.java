package com.drlang.iostreams;

import java.nio.charset.Charset;
import java.util.Iterator;
import java.util.Map;
import java.util.SortedMap;

public class AvailableCharSets {
    public static void main(String[] args) {
        SortedMap<String, Charset> charsets = Charset.availableCharsets();
        for (String csName : charsets.keySet()) {
            System.out.print(csName);
            Iterator<String> aliases = charsets.get(csName).aliases().iterator();
            if (aliases.hasNext()){
                System.out.print(": ");
            }
            while (aliases.hasNext()){
                System.out.print(aliases.next());
                if (aliases.hasNext()){
                    System.out.print(", ");
                }
            }
            System.out.println();
        }
    }
}
