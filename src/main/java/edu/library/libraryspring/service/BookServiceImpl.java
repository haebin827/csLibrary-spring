package edu.library.libraryspring.service;

import edu.library.libraryspring.config.ModelMapperConfig;
import edu.library.libraryspring.domain.BookVO;
import edu.library.libraryspring.dto.BookDTO;
import edu.library.libraryspring.dto.PageRequestDTO;
import edu.library.libraryspring.dto.PageResponseDTO;
import edu.library.libraryspring.mapper.BookMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Log4j2
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {

    private final BookMapper bm;
    private final ModelMapper mm;
    private final ModelMapperConfig modelMapperConfig;

    @Override
    public void register(BookDTO bookDTO) {

        BookVO bookVO = mm.map(bookDTO, BookVO.class);

        log.info("bookVO: " + bookVO);
        bm.insert(bookVO);
    }

    @Override
    public List<BookDTO> getAll() {

        List<BookDTO> dtoList = bm.selectAll().stream()
                .map(vo -> mm.map(vo, BookDTO.class))
                .collect(Collectors.toList());

        return dtoList;
    }

    @Override
    public BookDTO getOne(Long b_no) {

        BookVO bookVO = bm.selectOne(b_no);
        BookDTO bookDTO = mm.map(bookVO, BookDTO.class);

        return bookDTO;
    }

    @Override
    public void remove(Long b_no) {

        bm.delete(b_no);
    }

    @Override
    public void modify(BookDTO bookDTO) {

        BookVO bookVO = mm.map(bookDTO, BookVO.class);
        bm.update(bookVO);
    }

    @Override
    public PageResponseDTO<BookDTO> getList(PageRequestDTO pageRequestDTO) {

        List<BookVO> voList = bm.selectList(pageRequestDTO);
        List<BookDTO> dtoList = voList.stream()
                .map(vo -> mm.map(vo, BookDTO.class))
                .collect(Collectors.toList());

        int total = bm.getCount(pageRequestDTO);

        PageResponseDTO<BookDTO> pageResponseDTO = PageResponseDTO.<BookDTO>withAll()
                .dtoList(dtoList)
                .total(total)
                .pageRequestDTO(pageRequestDTO)
                .build();

        return pageResponseDTO;
    }
}
