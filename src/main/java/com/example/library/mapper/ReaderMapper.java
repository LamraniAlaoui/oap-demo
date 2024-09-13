package com.example.library.mapper;


import com.example.library.dto.ReaderDTO;
import com.example.library.model.Reader;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ReaderMapper {
    ReaderDTO toDto(Reader reader);
    Reader toEntity(ReaderDTO readerDTO);
}