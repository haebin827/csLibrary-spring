package edu.library.libraryspring.service;

import edu.library.libraryspring.domain.BookVO;
import edu.library.libraryspring.dto.BookDTO;
import edu.library.libraryspring.dto.PageRequestDTO;
import edu.library.libraryspring.dto.PageResponseDTO;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

@Log4j2
@ExtendWith(SpringExtension.class)
@ContextConfiguration(locations = "file:src/main/webapp/WEB-INF/root-context.xml")
public class BookServiceTests {

    @Autowired
    private BookService bs;

    @Test
    public void testRegister() {
        BookDTO bookDTO = BookDTO.builder()
                .b_title("TEST TITLE2")
                .b_author("TEST AUTHOR2")
                .b_isbn("0123456789")
                .b_year(2024)
                .b_publisher("TEST PUBLISHER")
                .b_category("9999")
                .build();

        bs.register(bookDTO);
    }

    @Test
    public void testPaging() {
        PageRequestDTO pageRequestDTO = PageRequestDTO.builder().page(1).size(10).build();
        PageResponseDTO<BookDTO> responseDTO = bs.getList(pageRequestDTO);

        log.info(responseDTO);
        responseDTO.getDtoList().stream().forEach(bookDTO -> log.info(bookDTO));
    }
}
