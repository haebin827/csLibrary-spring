package edu.library.libraryspring.mapper;

import edu.library.libraryspring.domain.NotificationVO;
import edu.library.libraryspring.dto.PageRequestDTO;

import java.util.List;

public interface NotificationMapper {

    void insert(NotificationVO notifVO);

    List<NotificationVO> selectAll();

    NotificationVO selectOne(Long n_no);

    void delete(Long n_no);

    void update(NotificationVO notificationVO);

    List<NotificationVO> selectList(PageRequestDTO pageRequestDTO);

    int getCount(PageRequestDTO pageRequestDTO);
}
