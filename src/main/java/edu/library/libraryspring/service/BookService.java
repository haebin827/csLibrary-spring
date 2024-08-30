package edu.library.libraryspring.service;

import edu.library.libraryspring.dto.BookDTO;
import edu.library.libraryspring.dto.PageRequestDTO;
import edu.library.libraryspring.dto.PageResponseDTO;

import java.util.List;

public interface BookService {

    void register(BookDTO bookDTO);

    List<BookDTO> getAll();

    PageResponseDTO<BookDTO> getList(PageRequestDTO pageRequestDTO);

    BookDTO getOne(Long b_no);

    void remove(Long b_no);

    void modify(BookDTO bookDTO);
}
