package edu.library.libraryspring.domain;
/*
    classname : MemberVO (Admin read only)
    creation date : 2024-07-20
    updated date : 2024-07-22 (Added m_no field)
                   2024-07-22 (Add m_uuid field for login cookie)
    remarks :
 */

import lombok.*;

import java.time.LocalDate;

@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MemberVO {

    private Integer m_no;               // int AI NN PK
    private String m_id;                // varchar(15) NN
    private String m_pw;                // varchar(255) NN
    private String m_name;              // varchar(50) NN
    private String m_phone;             // varchar(20) NN
    private String m_level;             // varchar(15) NN default 'BRONZE'
    private Integer m_point;            // int NN default '0'
    private Integer m_noOfLateReturn;   // int NN default '0'
    private Boolean m_isBlacklist;      // tinyint(1) NN default '0' => 0: Not in a blacklist | 1: In a blacklist
    private LocalDate m_regDate;        // timestamp NN default CURRENT_TIMESTAMP
    private String m_uuid;                // varchar(50)
}
