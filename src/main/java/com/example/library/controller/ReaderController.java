package com.example.library.controller;

import com.example.library.dto.ReaderDTO;
import com.example.library.service.ReaderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/readers")
@RequiredArgsConstructor
public class ReaderController {

    private final ReaderService readerService;

    @GetMapping("/{id}")
    public ResponseEntity<ReaderDTO> getReaderById(@PathVariable Long id) {
        ReaderDTO readerDTO = readerService.getReaderById(id);
        return ResponseEntity.ok(readerDTO);
    }

    @PostMapping
    public ResponseEntity<ReaderDTO> createReader(@RequestBody ReaderDTO readerDTO) {
        ReaderDTO createdReader = readerService.saveReader(readerDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdReader);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ReaderDTO> updateReader(@PathVariable Long id, @RequestBody ReaderDTO readerDTO) {
        ReaderDTO updatedReader = readerService.updateReader(id, readerDTO);
        return ResponseEntity.ok(updatedReader);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteReader(@PathVariable Long id) {
        readerService.deleteReader(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<List<ReaderDTO>> getAllReaders() {
        List<ReaderDTO> readers = readerService.getAllReaders();
        return ResponseEntity.ok(readers);
    }
}
