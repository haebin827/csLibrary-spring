package edu.library.libraryspring.repository;

import edu.library.libraryspring.domain.Rental;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.stream.IntStream;

@SpringBootTest
@Log4j2
public class RentalRepositoryTests {

    @Autowired
    private RentalRepository renRepo;


    @Test
    public void testInsert() {
        IntStream.rangeClosed(1,100).forEach(i -> {
            Rental rental = Rental.builder()
                    .m_id("testMId " + i)
                    .b_no(12 + i)
                    .r_rentalDate(LocalDate.of(2024, 7, 12))
                    .r_returnDate(LocalDate.of(2024, 7, 13))
                    .r_whenToReturn(LocalDate.of(2024, 8, 13))
                    .build();

            Rental result = renRepo.save(rental);
            log.info("result: " + result);
        });
    }

    /*
    @Test
    public void testSelect() {
        Long r_id = 2L;
        Optional<Rental> result = renRepo.findById(r_id);
        Rental rental = result.orElseThrow();
        log.info(rental);
    }

    @Test
    public void testUpdate() {
        Long r_id = 2L;
        Optional<Rental> result = renRepo.findById(r_id);
        Rental rental = result.orElseThrow();
        rental.change("update..m_id 100", "update b_no 100");
        renRepo.save(rental);
    }

    @Test
    public void testDelete() {
        Long r_id = 2L;
        renRepo.deleteById(r_id);
    }
*/
}
