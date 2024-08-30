package edu.library.libraryspring.dto;

import lombok.*;

import javax.validation.constraints.NotEmpty;
import java.time.LocalDate;

@ToString
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MemberDTO {

    private Integer m_no;               // int AI NN PK

    @NotEmpty
    private String m_id;                // varchar(15) NN

    @NotEmpty
    private String m_pw;                // varchar(255) NN

    @NotEmpty
    private String m_name;              // varchar(50) NN

    @NotEmpty
    private String m_phone;             // varchar(20) NN

    private String m_level;             // varchar(15) NN default 'BRONZE'
    private Integer m_point;            // int NN default '0'
    private Integer m_noOfLateReturn;   // int NN default '0'
    private Boolean m_isBlacklist;      // tinyint(1) NN default '0'
    private LocalDate m_regDate;        // timestamp NN default CURRENT_TIMESTAMP
    private String m_uuid;                // varchar(50)
}
