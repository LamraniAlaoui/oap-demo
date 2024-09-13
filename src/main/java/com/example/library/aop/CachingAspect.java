package com.example.library.aop;


import com.example.library.dto.BookDTO;
import com.example.library.model.Book;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Aspect
@Component
public class CachingAspect {

    private final Map<Long, BookDTO> bookCache = new HashMap<>();

    @Around("execution(* com.example.library.service.BookService.getBookById(..)) && args(bookId)")
    public Object cacheBook(ProceedingJoinPoint joinPoint, Long bookId) throws Throwable {
        if (bookCache.containsKey(bookId)) {
            System.out.println("Fetching book from cache: " + bookId);
            return bookCache.get(bookId);
        } else {
            BookDTO book = (BookDTO) joinPoint.proceed();
            bookCache.put(bookId, book);
            System.out.println("Caching book: " + bookId);
            return book;
        }
    }

    @After("execution(* com.example.library.service.BookService.saveBook(..))")
    public void invalidateCache(JoinPoint joinPoint) {
        Book book = (Book) joinPoint.getArgs()[0];
        bookCache.remove(book.getId());
        System.out.println("Cache invalidated for book: " + book.getId());
    }
}
