package com.example.library.service;


import com.example.library.dto.BookDTO;
import com.example.library.exceptions.DataNotFoundException;
import com.example.library.mapper.BookMapper;
import com.example.library.model.Book;
import com.example.library.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BookService {

    private final BookRepository bookRepository;
    private final BookMapper bookMapper;

    @Cacheable(value = "books", key = "#id")
    public BookDTO getBookById(Long id) {
        Book book = bookRepository.findById(id)
                .orElseThrow(() -> new DataNotFoundException("Book not found with id: " + id));
        return bookMapper.toDto(book);
    }

    @Transactional
    public BookDTO saveBook(BookDTO bookDTO) {
        Book book = bookMapper.toEntity(bookDTO);
        Book savedBook = bookRepository.save(book);
        return bookMapper.toDto(savedBook);
    }

    @Transactional
    public BookDTO updateBook(Long id, BookDTO bookDTO) {
        Book existingBook = bookRepository.findById(id)
                .orElseThrow(() -> new DataNotFoundException("Book not found with id: " + id));

        existingBook.setTitle(bookDTO.getTitle());

        Book updatedBook = bookRepository.save(existingBook);
        return bookMapper.toDto(updatedBook);
    }

    @Transactional
    public void deleteBook(Long id) {
        Book book = bookRepository.findById(id)
                .orElseThrow(() -> new DataNotFoundException("Book not found with id: " + id));
        bookRepository.delete(book);
    }

    @Cacheable(value = "books")
    public List<BookDTO> getAllBooks() {
        List<Book> books = bookRepository.findAll();
        return books.stream()
                .map(bookMapper::toDto)
                .collect(Collectors.toList());
    }
}
