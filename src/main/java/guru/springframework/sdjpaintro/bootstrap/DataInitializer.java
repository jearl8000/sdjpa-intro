package guru.springframework.sdjpaintro.bootstrap;

import guru.springframework.sdjpaintro.domain.*;
import guru.springframework.sdjpaintro.repositories.*;
import org.springframework.boot.*;
import org.springframework.stereotype.*;

@Component
public class DataInitializer implements CommandLineRunner {

    private final BookRepository bookRepository;

    public DataInitializer(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        Book bookDDD = new Book("Domain Driven Design", "123123", "Addison Wesley");
        Book savedDDD = bookRepository.save(bookDDD);

        Book bookSIA = new Book("Spring in Action", "321321", "Manning");
        Book savedSIA = bookRepository.save(bookSIA);

        bookRepository.findAll().forEach(book -> {
            System.out.println("Book Id: " + book.getId());
            System.out.println("Book Title: " + book.getTitle());
        });
    }
}
