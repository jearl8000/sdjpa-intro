package guru.springframework.sdjpaintro;

import guru.springframework.sdjpaintro.domain.*;
import guru.springframework.sdjpaintro.domain.composite.*;
import guru.springframework.sdjpaintro.repositories.*;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.boot.test.autoconfigure.jdbc.*;
import org.springframework.boot.test.autoconfigure.orm.jpa.*;
import org.springframework.context.annotation.*;
import org.springframework.test.context.*;

import static org.assertj.core.api.AssertionsForClassTypes.*;

/**
 * Created by jt on 7/4/21.
 */
@ActiveProfiles("local")
@DataJpaTest
@ComponentScan(basePackages = {"guru.springframework.sdjpaintro.bootstrap"})
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class MySQLIntegrationTest {

    @Autowired
    BookRepository bookRepository;

    @Autowired
    BookUuidRepository bookUuidRepository;

    @Autowired
    AuthorUuidRepository authorUuidRepository;

    @Autowired
    BookNaturalRepository bookNaturalRepository;

    @Autowired
    AuthorCompositeRepository authorCompositeRepository;

    @Autowired
    AuthorEmbeddedRepository authorEmbeddedRepository;

    @Test
    void testMySQL() {
        long countBefore = bookRepository.count();
        assertThat(countBefore).isEqualTo(2);
    }

    @Test
    void testAuthorEmbedded() {
        NameId nameId = new NameId("John", "Doe");
        AuthorEmbedded authorEmbedded = new AuthorEmbedded(nameId);
        authorEmbedded.setCountry("USA");
        AuthorEmbedded saved = authorEmbeddedRepository.save(authorEmbedded);
        assertThat(saved).isNotNull();

        AuthorEmbedded fetched = authorEmbeddedRepository.findById(nameId).get();
        assertThat(fetched).isNotNull();
    }

    @Test
    void testAuthorComposite() {
        NameId nameId = new NameId("John", "Doe");
        AuthorComposite authorComposite = new AuthorComposite();
        authorComposite.setFirstName(nameId.getFirstName());
        authorComposite.setLastName(nameId.getLastName());
        AuthorComposite saved = authorCompositeRepository.save(authorComposite);
        assertThat(saved).isNotNull();

        AuthorComposite fetched = authorCompositeRepository.findById(nameId).get();
        assertThat(fetched).isNotNull();
    }

    @Test
    void testSaveBookUuid() {
        BookUuid bookUuid = new BookUuid();
        bookUuidRepository.save(bookUuid);
    }

    @Test
    void testSaveAuthorUuid() {
        AuthorUuid authorUuid = new AuthorUuid();
        authorUuidRepository.save(authorUuid);
    }

    @Test
    void testGetBookUuid() {
        BookUuid bookUuid = new BookUuid();
        bookUuidRepository.save(bookUuid);
        BookUuid fetchedBookUuid = bookUuidRepository.findById(bookUuid.getId()).get();
        assertThat(fetchedBookUuid).isNotNull();
}

    @Test
    void testGetAuthorUuid() {
        AuthorUuid authorUuid = new AuthorUuid();
        authorUuidRepository.save(authorUuid);
        AuthorUuid FetchedAuthorUuid1 = authorUuidRepository.findById(authorUuid.getId()).get();
        assertThat(FetchedAuthorUuid1).isNotNull();
    }

    @Test
    void testBookNatural() {
        BookNatural bookNatural = new BookNatural();
        bookNatural.setTitle("My Book");
        bookNaturalRepository.save(bookNatural);
        BookNatural fetchedBookNatural = bookNaturalRepository.findById(bookNatural.getTitle()).get();
        assertThat(fetchedBookNatural).isNotNull();
    }

}


