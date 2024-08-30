package edu.library.libraryspring.service;

import edu.library.libraryspring.config.ModelMapperConfig;
import edu.library.libraryspring.domain.BookVO;
import edu.library.libraryspring.domain.MemberVO;
import edu.library.libraryspring.dto.BookDTO;
import edu.library.libraryspring.dto.MemberDTO;
import edu.library.libraryspring.dto.PageRequestDTO;
import edu.library.libraryspring.dto.PageResponseDTO;
import edu.library.libraryspring.mapper.BookMapper;
import edu.library.libraryspring.mapper.MemberMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Log4j2
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService{

    private final MemberMapper memM;
    private final ModelMapper mm;
    private final ModelMapperConfig modelMapperConfig;

    @Override
    public List<MemberDTO> getAll() {

        List<MemberDTO> dtoList = memM.selectAll().stream()
                .map(vo -> mm.map(vo, MemberDTO.class))
                .collect(Collectors.toList());

        return dtoList;
    }

    @Override
    public MemberDTO getOne(String m_id) {

        MemberVO memVO = memM.selectOne(m_id);
        MemberDTO memDTO = mm.map(memVO, MemberDTO.class);

        return memDTO;
    }

    @Override
    public void remove(String m_id) {

        memM.delete(m_id);
    }

    @Override
    public PageResponseDTO<MemberDTO> getList(PageRequestDTO pageRequestDTO) {

        List<MemberVO> voList = memM.selectList(pageRequestDTO);
        List<MemberDTO> dtoList = voList.stream()
                .map(vo -> mm.map(vo, MemberDTO.class))
                .collect(Collectors.toList());

        int total = memM.getCount(pageRequestDTO);

        PageResponseDTO<MemberDTO> pageResponseDTO = PageResponseDTO.<MemberDTO>withAll()
                .dtoList(dtoList)
                .total(total)
                .pageRequestDTO(pageRequestDTO)
                .build();

        return pageResponseDTO;
    }

    /*
    @Override
    public int userAuth(String m_id, String m_pw) {
        int count = memM.userAuthenticate(m_id, m_pw);
        return count;
    }

    @Override
    public int adminAuth(String m_id, String m_pw) {
        int count = memM.adminAuthenticate(m_id, m_pw);
        return count;
    }
    */

    @Override
    public MemberDTO verify(String m_id, String m_pw) {
        MemberVO memVO = memM.authenticate(m_id, m_pw);
        MemberDTO memDTO = mm.map(memVO, MemberDTO.class);
        return memDTO;
    }

    @Override
    public void setUuid(String m_id, String m_uuid) {

        memM.updateUuid(m_id, m_uuid);
    }

    @Override
    public MemberDTO getByUUID(String m_uuid) {

        MemberVO memVO = memM.selectUUID(m_uuid);
        MemberDTO memDTO = mm.map(memVO, MemberDTO.class);

        return memDTO;
    }

    @Override
    public void register(MemberDTO memDTO) {

        MemberVO memVO = mm.map(memDTO, MemberVO.class);
        memM.insert(memVO);
    }

    @Override
    public int verifyPhone(String m_phone) {
        return memM.checkPhone(m_phone);
    }

    @Override
    public int verifyId(String m_id) {
        return memM.checkId(m_id);
    }
}
