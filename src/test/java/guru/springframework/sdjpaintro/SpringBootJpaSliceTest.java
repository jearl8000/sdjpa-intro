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

/* demonstrates using the same bootstrapping data as the app to run tests agains.
    @Commit is used to override the default rollback behavior of @DataJpaTest
 */
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@DataJpaTest
@ComponentScan(basePackages = {"guru.springframework.sdjpaintro.bootstrap"})
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class SpringBootJpaSliceTest {

    @Autowired
    BookRepository bookRepository;

    @Commit
    @Order(1)
    @Test
    void testJpaSliceTest() {
        long countBefore = bookRepository.count();

        bookRepository.save(new Book("Test Book", "1234567890", "Self"));
        long countAfter = bookRepository.count();

        assertThat(countAfter).isGreaterThan(countBefore);
    }

    @Order(2)
    @Test
    void testJpaSliceTestTransaction() {
        long countBefore = bookRepository.count();

        assertThat(countBefore).isEqualTo(3);
    }


}
