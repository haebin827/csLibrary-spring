package edu.library.libraryspring.service;

import edu.library.libraryspring.config.ModelMapperConfig;
import edu.library.libraryspring.domain.BookVO;
import edu.library.libraryspring.domain.FaqVO;
import edu.library.libraryspring.dto.BookDTO;
import edu.library.libraryspring.dto.FaqDTO;
import edu.library.libraryspring.dto.PageRequestDTO;
import edu.library.libraryspring.dto.PageResponseDTO;
import edu.library.libraryspring.mapper.FaqMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.apache.ibatis.annotations.Mapper;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Log4j2
@RequiredArgsConstructor
public class FaqServiceImpl implements FaqService {

    private final FaqMapper fm;
    private final ModelMapper mm;
    private final ModelMapperConfig modelMapperConfig;

    @Override
    public void register(FaqDTO faqDTO) {

        FaqVO faqVO = mm.map(faqDTO, FaqVO.class);

        log.info("faqVO: " + faqVO);
        fm.insert(faqVO);
    }

    @Override
    public List<FaqDTO> getAll() {

        List<FaqDTO> dtoList = fm.selectAll().stream()
                .map(vo -> mm.map(vo, FaqDTO.class))
                .collect(Collectors.toList());

        return dtoList;
    }

    @Override
    public FaqDTO getOne(Long f_id) {

        FaqVO faqVO = fm.selectOne(f_id);
        FaqDTO faqDTO = mm.map(faqVO, FaqDTO.class);

        return faqDTO;
    }

    @Override
    public void remove(Long f_id) {

        fm.delete(f_id);
    }

    @Override
    public void modify(FaqDTO faqDTO) {

        FaqVO faqVO = mm.map(faqDTO, FaqVO.class);
        fm.update(faqVO);
    }

    @Override
    public PageResponseDTO<FaqDTO> getList(PageRequestDTO pageRequestDTO) {

        List<FaqVO> voList = fm.selectList(pageRequestDTO);
        List<FaqDTO> dtoList = voList.stream()
                .map(vo -> mm.map(vo, FaqDTO.class))
                .collect(Collectors.toList());

        int total = fm.getCount(pageRequestDTO);

        PageResponseDTO<FaqDTO> pageResponseDTO = PageResponseDTO.<FaqDTO>withAll()
                .dtoList(dtoList)
                .total(total)
                .pageRequestDTO(pageRequestDTO)
                .build();

        return pageResponseDTO;
    }
}
