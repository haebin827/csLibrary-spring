package edu.library.libraryspring.mapper;

import edu.library.libraryspring.domain.BookVO;
import edu.library.libraryspring.dto.PageRequestDTO;
import edu.library.libraryspring.mapper.BookMapper;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDate;
import java.util.List;

@Log4j2
@ExtendWith(SpringExtension.class)
@ContextConfiguration(locations="file:src/main/webapp/WEB-INF/root-context.xml")
public class BookMapperTests {

    @Autowired(required = false)
    private BookMapper bm;

    @Test
    public void testInsert() {

        BookVO bookVO = BookVO.builder()
                .b_title("TEST TITLE")
                .b_author("TEST AUTHOR")
                .b_isbn("0123456789")
                .b_year(2024)
                .b_publisher("TEST PUBLISHER")
                .b_category("9999")
                .build();

        bm.insert(bookVO);
    }

    @Test
    public void testSelectAll() {
        List<BookVO> voList = bm.selectAll();
        voList.forEach(vo -> log.info(vo));
    }

    @Test
    public void testSelectOne() {
        BookVO bookVO = bm.selectOne(3L);
        log.info(bookVO);
    }

    @Test
    public void testSelectList() {
        PageRequestDTO pageRequestDTO = PageRequestDTO.builder()
                .page(1)
                .size(10)
                .build();

        List<BookVO> voList = bm.selectList(pageRequestDTO);
        voList.forEach(vo -> log.info(vo));
    }

    @Test
    public void testSelectSearch() {
        PageRequestDTO pageReqDTO = PageRequestDTO.builder()
                .page(1)
                .size(10)
                .types(new String[]{""})
                .keyword("")
                .keyword2("3346079090")
                .from(LocalDate.of(2001, 01, 01))
                .to(LocalDate.of(2020, 07,01))
                .build();

        List<BookVO> voList = bm.selectList(pageReqDTO);
        voList.forEach(vo -> log.info(vo));
    }
}
