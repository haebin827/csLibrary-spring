package edu.library.libraryspring.mapper;

import edu.library.libraryspring.domain.BookVO;
import edu.library.libraryspring.domain.NotificationVO;
import edu.library.libraryspring.dto.PageRequestDTO;
import edu.library.libraryspring.mapper.NotificationMapper;
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
public class NotificationMapperTests {

    @Autowired(required = false)
    private NotificationMapper nm;

    @Test
    public void testInsert() {
        NotificationVO notifVO = NotificationVO.builder()
                .n_title("TEST TITLE")
                .n_content("TEST CONTENT")
                .n_isImp(true)
                .build();

        nm.insert(notifVO);
    }

    @Test
    public void testSelectAll() {
        List<NotificationVO> voList = nm.selectAll();
        voList.forEach(vo -> log.info(vo));
    }

    @Test
    public void testSelectOne() {
        NotificationVO notifVO = nm.selectOne(5L);
        log.info(notifVO);
    }

    @Test
    public void testSelectList() {
        PageRequestDTO pageRequestDTO = PageRequestDTO.builder()
                .page(1)
                .size(10)
                .build();

        List<NotificationVO> voList = nm.selectList(pageRequestDTO);
        voList.forEach(vo -> log.info(vo));
    }
}
