package net.jamesmcmahon.memorytest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import static java.lang.Runtime.getRuntime;
import static net.jamesmcmahon.memorytest.Constants.GIGABYTE;
import static net.jamesmcmahon.memorytest.Stats.printMemoryStats;

@SpringBootApplication
public class MemoryTestApplication {
    public static void main(String[] args) {
        SpringApplication.run(MemoryTestApplication.class, args);
        printMemoryStats();
    }
}
