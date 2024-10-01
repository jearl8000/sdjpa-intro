package guru.springframework.sdjpaintro;

import guru.springframework.sdjpaintro.domain.*;
import guru.springframework.sdjpaintro.repositories.*;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.boot.test.autoconfigure.jdbc.*;
import org.springframework.boot.test.autoconfigure.orm.jpa.*;
import org.springframework.context.annotation.*;
import org.springframework.test.annotation.*;

import static org.assertj.core.api.AssertionsForClassTypes.*;

/**
 * Created by jt on 7/3/21.
 */
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@DataJpaTest
@ComponentScan(basePackages = {"guru.springframework.sdjpaintro.bootstrap"})
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class SpringBootJpaTestSlice {

    @Autowired
    BookRepository bookRepository;

    @Commit
    @Order(1)
    @Test
    void testJpaTestSplice() {
        long countBefore = bookRepository.count();
        assertThat(countBefore).isEqualTo(2);

        bookRepository.save(new Book("My Book", "1235555", "Self", null));

        long countAfter = bookRepository.count();

        assertThat(countBefore).isLessThan(countAfter);
    }

    @Order(2)
    @Test
    void testJpaTestSpliceTransaction() {
        long countBefore = bookRepository.count();
        assertThat(countBefore).isEqualTo(3);

    }
}
