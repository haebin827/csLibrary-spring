package edu.library.libraryspring.service;

import edu.library.libraryspring.domain.NotificationVO;
import edu.library.libraryspring.dto.BookDTO;
import edu.library.libraryspring.dto.NotificationDTO;
import edu.library.libraryspring.dto.PageRequestDTO;
import edu.library.libraryspring.dto.PageResponseDTO;

import java.util.List;

public interface NotificationService {

    void register(NotificationDTO notifDTO);

    List<NotificationDTO> getAll();

    NotificationDTO getOne(Long n_no);

    void remove(Long n_no);

    PageResponseDTO<NotificationDTO> getList(PageRequestDTO pageRequestDTO);

    void modify(NotificationDTO notifDTO);
}
