package net.jamesmcmahon.memorytest;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

import static java.lang.Runtime.getRuntime;
import static net.jamesmcmahon.memorytest.Constants.GIGABYTE;
import static net.jamesmcmahon.memorytest.Constants.MEGABYTE;
import static net.jamesmcmahon.memorytest.Stats.printMemoryStats;

@Controller
@RequestMapping("memory")
public class UseMemoryController {
    @GetMapping("eat-memory")
    public ResponseEntity<Float> eatMemory(
            @RequestParam("steps") int steps,
            @RequestParam("mbs") int mbs) {
        UseMemoryController.allocateBytes(steps, mbs);
        return ResponseEntity.ok().body(getRuntime().freeMemory() / (float) GIGABYTE);
    }

    @GetMapping("print-memory")
    public ResponseEntity printMemory() {
        printMemoryStats();
        return ResponseEntity.ok().build();
    }

    private static List<byte[]> allocateBytes(int steps, int megabytes) {
        var list = new ArrayList<byte[]>();
        for (int i = 0; i < steps; i++) {
            System.out.println(
                    "Free Memory: " +
                            getRuntime().freeMemory() / (float) GIGABYTE +
                            "GB"
            );
            list.add(new byte[megabytes * MEGABYTE]);
        }
        return list;
    }
}
