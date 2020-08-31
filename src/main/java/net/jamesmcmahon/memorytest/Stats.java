package net.jamesmcmahon.memorytest;

import static java.lang.Runtime.getRuntime;
import static net.jamesmcmahon.memorytest.Constants.GIGABYTE;

public class Stats {
    public static void printMemoryStats() {
        System.out.println("######## Memory Stats ########");
        System.out.println("Used Memory:"
                + (getRuntime().totalMemory() - getRuntime().freeMemory()) / (float) GIGABYTE);
        System.out.println("Free Memory:"
                + getRuntime().freeMemory() / (float) GIGABYTE);
        System.out.println("Total Memory:" + getRuntime().totalMemory() / (float) GIGABYTE);
        System.out.println("Max Memory:" + getRuntime().maxMemory() / (float) GIGABYTE);
        System.out.println("##############################");
    }
}
