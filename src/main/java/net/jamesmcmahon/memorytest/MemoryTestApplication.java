package net.jamesmcmahon.memorytest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import static java.lang.Runtime.getRuntime;
import static net.jamesmcmahon.memorytest.Constants.GIGABYTE;

@SpringBootApplication
public class MemoryTestApplication {
    public static void main(String[] args) {
        SpringApplication.run(MemoryTestApplication.class, args);
        printMemoryStats();
    }

    private static void printMemoryStats() {
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
