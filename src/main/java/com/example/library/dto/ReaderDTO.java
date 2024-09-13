package com.example.library.dto;

import lombok.Value;

import java.io.Serializable;
import java.util.List;

/**
 * DTO for {@link com.example.library.model.Reader}
 */
@Value
public class ReaderDTO implements Serializable {
    Long identifiant;
    String name;
    List<BookDTO> listbooks;
}