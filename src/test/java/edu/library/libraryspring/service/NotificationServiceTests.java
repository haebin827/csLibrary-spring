package edu.library.libraryspring.service;

import edu.library.libraryspring.dto.BookDTO;
import edu.library.libraryspring.dto.NotificationDTO;
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
public class NotificationServiceTests {

    @Autowired
    private NotificationService ns;

    @Test
    public void testRegister() {
        NotificationDTO notifDTO = NotificationDTO.builder()
                .n_title("TEST1")
                .n_content("TEST1")
                .build();

        ns.register(notifDTO);
    }

    @Test
    public void testPaging() {
        PageRequestDTO pageRequestDTO = PageRequestDTO.builder().page(1).size(10).build();
        PageResponseDTO<NotificationDTO> responseDTO = ns.getList(pageRequestDTO);

        log.info(responseDTO);
        responseDTO.getDtoList().stream().forEach(notifDTO -> log.info(notifDTO));
    }
}
