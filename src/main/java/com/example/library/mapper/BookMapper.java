package com.example.library.mapper;
import com.example.library.dto.BookDTO;
import com.example.library.model.Book;
import org.mapstruct.Mapper;


@Mapper(componentModel = "spring")
public interface BookMapper {
    BookDTO toDto(Book book);
    Book toEntity(BookDTO bookDTO);
}