package edu.library.libraryspring.mapper;

import edu.library.libraryspring.domain.BookVO;
import edu.library.libraryspring.domain.FaqVO;
import edu.library.libraryspring.dto.PageRequestDTO;
import edu.library.libraryspring.mapper.FaqMapper;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

@Log4j2
@ExtendWith(SpringExtension.class)
@ContextConfiguration(locations="file:src/main/webapp/WEB-INF/root-context.xml")
public class FaqMapperTests {

    @Autowired(required = false)
    private FaqMapper fm;

    @Test
    public void testInsert() {
        FaqVO faqVO = FaqVO.builder()
                .f_question("TEST QUESTION3")
                .f_answer("TEST ANSWER4")
                .build();

        fm.insert(faqVO);
    }

    @Test
    public void testSelectAll() {
        List<FaqVO> voList = fm.selectAll();
        voList.forEach(vo -> log.info(vo));
    }

    @Test
    public void testSelectOne() {
        FaqVO faqVO = fm.selectOne(3L);
        log.info(faqVO);
    }

    @Test
    public void testSelectList() {
        PageRequestDTO pageRequestDTO = PageRequestDTO.builder()
                .page(1)
                .size(10)
                .build();

        List<FaqVO> voList = fm.selectList(pageRequestDTO);
        voList.forEach(vo -> log.info(vo));
    }
}
