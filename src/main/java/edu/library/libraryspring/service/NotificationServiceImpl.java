package edu.library.libraryspring.service;

import edu.library.libraryspring.config.ModelMapperConfig;
import edu.library.libraryspring.domain.BookVO;
import edu.library.libraryspring.domain.NotificationVO;
import edu.library.libraryspring.dto.BookDTO;
import edu.library.libraryspring.dto.NotificationDTO;
import edu.library.libraryspring.dto.PageRequestDTO;
import edu.library.libraryspring.dto.PageResponseDTO;
import edu.library.libraryspring.mapper.NotificationMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Log4j2
@RequiredArgsConstructor
public class NotificationServiceImpl implements NotificationService {

    private final NotificationMapper nm;
    private final ModelMapper mm;
    private final ModelMapperConfig modelMapperConfig;

    @Override
    public void register(NotificationDTO notifDTO) {

        NotificationVO notifVO = mm.map(notifDTO, NotificationVO.class);

        log.info("notifyVO: " + notifVO);
        nm.insert(notifVO);
    }

    @Override
    public List<NotificationDTO> getAll() {

        List<NotificationDTO> dtoList = nm.selectAll().stream()
                .map(vo -> mm.map(vo, NotificationDTO.class))
                .collect(Collectors.toList());

        return dtoList;
    }

    @Override
    public NotificationDTO getOne(Long n_no) {

        NotificationVO notifVO = nm.selectOne(n_no);
        NotificationDTO notifDTO = mm.map(notifVO, NotificationDTO.class);

        return notifDTO;
    }

    @Override
    public void remove(Long n_no) {

        nm.delete(n_no);
    }

    @Override
    public PageResponseDTO<NotificationDTO> getList(PageRequestDTO pageRequestDTO) {

        List<NotificationVO> voList = nm.selectList(pageRequestDTO);
        List<NotificationDTO> dtoList = voList.stream()
                .map(vo -> mm.map(vo, NotificationDTO.class))
                .collect(Collectors.toList());

        int total = nm.getCount(pageRequestDTO);

        PageResponseDTO<NotificationDTO> pageResponseDTO = PageResponseDTO.<NotificationDTO> withAll()
                .dtoList(dtoList)
                .total(total)
                .pageRequestDTO(pageRequestDTO)
                .build();

        return pageResponseDTO;
    }

    @Override
    public void modify(NotificationDTO notifDTO) {

        NotificationVO notifVO = mm.map(notifDTO, NotificationVO.class);
        nm.update(notifVO);
    }
}
