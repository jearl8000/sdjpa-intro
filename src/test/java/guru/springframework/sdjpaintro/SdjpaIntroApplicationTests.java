package guru.springframework.sdjpaintro;

import guru.springframework.sdjpaintro.repositories.*;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.boot.test.context.*;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
class SdjpaIntroApplicationTests {

    @Autowired
    BookRepository bookRepository;

    @Test
    void testBookRepository(){
        long count = bookRepository.count();

        assertThat(count).isGreaterThan(0);
    }

    @Test
    void contextLoads() {
    }

}
