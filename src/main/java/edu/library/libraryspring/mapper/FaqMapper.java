package edu.library.libraryspring.mapper;

import edu.library.libraryspring.domain.FaqVO;
import edu.library.libraryspring.dto.PageRequestDTO;

import java.util.List;

public interface FaqMapper {

    void insert(FaqVO faqVO);

    List<FaqVO> selectAll();

    FaqVO selectOne(Long f_id);

    void delete(Long f_id);

    void update(FaqVO faqVO);

    List<FaqVO> selectList(PageRequestDTO pageRequestDTO);

    int getCount(PageRequestDTO pageRequestDTO);
}
