package edu.library.libraryspring.service;

import edu.library.libraryspring.dto.BookDTO;
import edu.library.libraryspring.dto.FaqDTO;
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
public class FaqServiceTests {

    @Autowired
    private FaqService fs;

    @Test
    public void testRegister() {
        FaqDTO faqDTO = FaqDTO.builder()
                .f_question("ServiceTest1")
                .f_answer("ServiceTest1")
                .build();

        fs.register(faqDTO);
    }

    @Test
    public void testPaging() {
        PageRequestDTO pageRequestDTO = PageRequestDTO.builder().page(1).size(10).build();
        PageResponseDTO<FaqDTO> responseDTO = fs.getList(pageRequestDTO);

        log.info(responseDTO);
        responseDTO.getDtoList().stream().forEach(faqDTO -> log.info(faqDTO));
    }
}
