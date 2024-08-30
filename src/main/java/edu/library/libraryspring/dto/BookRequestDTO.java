package edu.library.libraryspring.dto;

import lombok.*;

import javax.validation.constraints.NotEmpty;
import java.time.LocalDateTime;

@ToString
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BookRequestDTO {

    private Integer br_no;            // int AI PK

    @NotEmpty
    private String m_id;              // varchar(15) NN

    @NotEmpty
    private String br_title;          // varchar(255) NN
    private String br_isbn;           // varchar(10)
    private LocalDateTime br_date;    // timestamp not null default current_timestamp
    private String br_reason;         // int default NULL
}
