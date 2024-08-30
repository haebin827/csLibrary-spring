package edu.library.libraryspring.mapper;

import edu.library.libraryspring.domain.BookVO;
import edu.library.libraryspring.dto.PageRequestDTO;

import java.util.List;

public interface BookMapper {

    void insert(BookVO bookVO);

    List<BookVO> selectAll();

    BookVO selectOne(Long b_no);

    void delete(Long b_no);

    void update(BookVO bookVO);

    List<BookVO> selectList(PageRequestDTO pageRequestDTO);

    int getCount(PageRequestDTO pageRequestDTO);
}
