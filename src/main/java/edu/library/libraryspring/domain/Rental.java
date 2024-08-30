package edu.library.libraryspring.domain;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Rental extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long r_id;

    @Column(length = 15, nullable = false)
    private String m_id;

    @Column(nullable = false)
    private Integer b_no;

    @Column(nullable = false)
    private LocalDate r_rentalDate;
    private LocalDate r_returnDate;
    private LocalDate r_whenToReturn;

    @Column(nullable = false)
    private Boolean r_isExtended;

    @Column(nullable = false)
    private Boolean r_done;

    @Column(nullable = false)
    private Boolean r_isReturnReq;

    public void change(String m_id, int b_no, LocalDate r_rentalDate, LocalDate r_returnDate, LocalDate r_whenToReturn ) {
        this.m_id = m_id;
        this.b_no = b_no;
        this.r_rentalDate = r_rentalDate;
        this.r_returnDate = r_returnDate;
        this.r_whenToReturn = r_whenToReturn;
    }
}
