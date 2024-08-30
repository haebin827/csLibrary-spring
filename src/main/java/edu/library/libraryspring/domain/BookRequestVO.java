package edu.library.libraryspring.domain;
/*
    classname : BookRequestVO
    creation date : 2024-07-22
    updated date :
    remarks :
 */
import lombok.*;

import java.time.LocalDateTime;

@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BookRequestVO {

    private Integer br_no;            // int AI PK
    private String m_id;              // varchar(15) NN
    private String br_title;          // varchar(255) NN
    private String br_isbn;           // varchar(10)
    private LocalDateTime br_date;    // timestamp not null default current_timestamp
    private String br_reason;         // int default NULL
}
