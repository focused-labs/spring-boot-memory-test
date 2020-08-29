package net.jamesmcmahon.memorytest;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("memory")
public class UseMemoryController {
    public static final int CHUNK_MULTIPLIER = 1_000_000;

    @GetMapping("eat-memory")
    public ResponseEntity<Long> eatMemory(
            @RequestParam("chunks") int chunks,
            @RequestParam("size") int size)
    {
        UseMemoryController.allocateBytes(chunks, size);
        return ResponseEntity.ok().body(Runtime.getRuntime().freeMemory());
    }

    private static List<byte[]> allocateBytes(int chunks, int chunkSize) {
        var list = new ArrayList<byte[]>();
        for (int i = 0; i < chunks; i++) {
            System.out.println("free memory: " + Runtime.getRuntime().freeMemory());
            list.add(new byte[chunkSize * CHUNK_MULTIPLIER]);
        }
        return list;
    }
}
