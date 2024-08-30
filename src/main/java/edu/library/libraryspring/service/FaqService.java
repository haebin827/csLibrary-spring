package edu.library.libraryspring.service;

import edu.library.libraryspring.dto.BookDTO;
import edu.library.libraryspring.dto.FaqDTO;
import edu.library.libraryspring.dto.PageRequestDTO;
import edu.library.libraryspring.dto.PageResponseDTO;

import java.util.List;

public interface FaqService {

    void register(FaqDTO faqDTO);

    List<FaqDTO> getAll();

    PageResponseDTO<FaqDTO> getList(PageRequestDTO pageRequestDTO);

    FaqDTO getOne(Long f_id);

    void remove(Long f_id);

    void modify(FaqDTO faqDTO);
}
