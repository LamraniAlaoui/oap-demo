package com.example.library.service;


import com.example.library.dto.ReaderDTO;
import com.example.library.exceptions.DataNotFoundException;
import com.example.library.mapper.ReaderMapper;
import com.example.library.model.Reader;
import com.example.library.repository.ReaderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ReaderService {

    private final ReaderRepository readerRepository;
    private final ReaderMapper readerMapper;

    @Cacheable(value = "readers", key = "#id")
    public ReaderDTO getReaderById(Long id) {
        Reader reader = readerRepository.findById(id)
                .orElseThrow(() -> new DataNotFoundException("Reader not found with id: " + id));
        return readerMapper.toDto(reader);
    }

    @Transactional
    public ReaderDTO saveReader(ReaderDTO readerDTO) {
        Reader reader = readerMapper.toEntity(readerDTO);
        Reader savedReader = readerRepository.save(reader);
        return readerMapper.toDto(savedReader);
    }

    @Transactional
    public ReaderDTO updateReader(Long id, ReaderDTO readerDTO) {
        Reader existingReader = readerRepository.findById(id)
                .orElseThrow(() -> new DataNotFoundException("Reader not found with id: " + id));

        existingReader.setName(readerDTO.getName());

        Reader updatedReader = readerRepository.save(existingReader);
        return readerMapper.toDto(updatedReader);
    }

    @Transactional
    public void deleteReader(Long id) {
        Reader reader = readerRepository.findById(id)
                .orElseThrow(() -> new DataNotFoundException("Reader not found with id: " + id));
        readerRepository.delete(reader);
    }

    @Cacheable(value = "readers")
    public List<ReaderDTO> getAllReaders() {
        List<Reader> readers = readerRepository.findAll();
        return readers.stream()
                .map(readerMapper::toDto)
                .collect(Collectors.toList());
    }
}
