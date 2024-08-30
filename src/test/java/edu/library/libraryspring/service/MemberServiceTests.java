package edu.library.libraryspring.service;

import edu.library.libraryspring.domain.MemberVO;
import edu.library.libraryspring.dto.MemberDTO;
import edu.library.libraryspring.dto.PageRequestDTO;
import edu.library.libraryspring.dto.PageResponseDTO;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@Log4j2
@ExtendWith(SpringExtension.class)
@ContextConfiguration(locations = "file:src/main/webapp/WEB-INF/root-context.xml")
public class MemberServiceTests {

    @Autowired
    private MemberService ms;

    @Test
    public void testPaging() {
        PageRequestDTO pageRequestDTO = PageRequestDTO.builder().page(1).size(10).build();
        PageResponseDTO<MemberDTO> responseDTO = ms.getList(pageRequestDTO);

        log.info(responseDTO);
        responseDTO.getDtoList().stream().forEach(memDTO -> log.info(memDTO));
    }

    @Test
    public void testUpdateUuid() {
        ms.setUuid("haebin", "TEST2");
    }

    @Test
    public void testSelectUuid() {
        MemberDTO memDTO = ms.getByUUID("TEST2");
        log.info("SelectUuid: " + memDTO);
    }
}
