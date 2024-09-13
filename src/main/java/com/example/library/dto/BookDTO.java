package com.example.library.dto;

import lombok.Value;

import java.io.Reader;
import java.io.Serializable;
import java.util.List;

/**
 * DTO for {@link com.example.library.model.Book}
 */
@Value
public class BookDTO implements Serializable {
    Long identifiant;
    String title;
    List<List<Reader>> listreaders;
}