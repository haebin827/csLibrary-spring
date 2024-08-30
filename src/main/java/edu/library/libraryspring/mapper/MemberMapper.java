package edu.library.libraryspring.mapper;

import edu.library.libraryspring.domain.MemberVO;
import edu.library.libraryspring.dto.PageRequestDTO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface MemberMapper {

    List<MemberVO> selectAll();

    MemberVO selectOne(String m_id);

    void delete(String m_id);

    List<MemberVO> selectList(PageRequestDTO pageRequestDTO);

    int getCount(PageRequestDTO pageRequestDTO);

    MemberVO authenticate(@Param("m_id") String m_id, @Param("m_pw") String m_pw);

    void updateUuid(@Param("m_id") String m_id, @Param("m_uuid") String m_uuid);

    MemberVO selectUUID(String m_uuid);

    void insert(MemberVO memVO);

    int checkPhone(String m_phone);

    int checkId(String m_id);
}
