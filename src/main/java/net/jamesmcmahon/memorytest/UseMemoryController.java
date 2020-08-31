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

@Controller
@RequestMapping("memory")
public class UseMemoryController {
    @GetMapping("eat-memory")
    public ResponseEntity<Float> eatMemory(
            @RequestParam("chunks") int chunks,
            @RequestParam("size") int size) {
        UseMemoryController.allocateBytes(chunks, size);
        return ResponseEntity.ok().body(getRuntime().freeMemory() / (float) GIGABYTE);
    }

    private static List<byte[]> allocateBytes(int chunks, int chunkSize) {
        var list = new ArrayList<byte[]>();
        for (int i = 0; i < chunks; i++) {
            System.out.println(
                    "Free Memory: " +
                            getRuntime().freeMemory() / (float) GIGABYTE +
                            "GB"
            );
            list.add(new byte[chunkSize * MEGABYTE]);
        }
        return list;
    }
}
