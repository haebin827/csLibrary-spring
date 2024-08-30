package edu.library.libraryspring.domain;
/*
    classname : BookVO (client read only)
    creation date : 2024-07-16
    updated date :
    remarks :
 */
import lombok.*;

@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BookVO {

    private Integer b_no;           // int AI PK
    private String b_isbn;      // varchar(10) NN
    private String b_title;     // varchar(255) NN
    private String b_author;    // varchar(255) default NULL
    private Integer b_year;   // int default NULL
    private String b_publisher; // varchar(255) default NULL
    private Boolean isRental;   // tinyint(1) NN default '0'
    private Boolean isActive;   // tinyint(1) NN default '1'
    private String b_category;  // varchar(255) default NULL
}
