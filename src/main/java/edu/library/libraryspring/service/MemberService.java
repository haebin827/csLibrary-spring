package edu.library.libraryspring.service;

import edu.library.libraryspring.domain.MemberVO;
import edu.library.libraryspring.dto.BookDTO;
import edu.library.libraryspring.dto.MemberDTO;
import edu.library.libraryspring.dto.PageRequestDTO;
import edu.library.libraryspring.dto.PageResponseDTO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface MemberService {

    List<MemberDTO> getAll();

    PageResponseDTO<MemberDTO> getList(PageRequestDTO pageRequestDTO);

    MemberDTO getOne(String m_id);

    void remove(String m_id);

    MemberDTO verify(String m_id, String m_pw);

    void setUuid(String m_id, String m_uuid);

    MemberDTO getByUUID(String m_uuid);

    void register(MemberDTO memDTO);

    int verifyPhone(String m_phone);

    int verifyId(String m_id);
}
