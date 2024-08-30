package edu.library.libraryspring.mapper;

import edu.library.libraryspring.domain.BookVO;
import edu.library.libraryspring.domain.MemberVO;
import edu.library.libraryspring.dto.PageRequestDTO;
import lombok.extern.log4j.Log4j2;
import org.apache.ibatis.annotations.Param;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.lang.reflect.Member;
import java.util.List;

@Log4j2
@ExtendWith(SpringExtension.class)
@ContextConfiguration(locations="file:src/main/webapp/WEB-INF/root-context.xml")
public class MemberMapperTests {

    @Autowired(required = false)
    private MemberMapper mm;

    @Test
    public void testSelectAll() {
        List<MemberVO> voList = mm.selectAll();
        voList.forEach(vo -> log.info(vo));
    }

    @Test
    public void testSelectOne() {
        MemberVO memVO = mm.selectOne("haebin0702");
        log.info(memVO);
    }

    @Test
    public void testSelectList() {
        PageRequestDTO pageRequestDTO = PageRequestDTO.builder()
                .page(1)
                .size(10)
                .build();

        List<MemberVO> voList = mm.selectList(pageRequestDTO);
        voList.forEach(vo -> log.info(vo));
    }

    /*
    @Test public void testLoginCheck() {
        int check = mm.adminAuthenticate("admin", "0000");
        log.info("Login check: " + check);
    }
     */

    @Test
    public void testUpdateUuid() {
        mm.updateUuid("haebin", "TEST");
    }

    @Test
    public void testSelectUuid() {
        MemberVO memVO = mm.selectUUID("TEST");
        log.info("SelectUuid: " + memVO);
    }
}
