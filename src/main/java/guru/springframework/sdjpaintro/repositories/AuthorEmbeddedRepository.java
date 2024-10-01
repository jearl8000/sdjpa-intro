package guru.springframework.sdjpaintro.repositories;

import guru.springframework.sdjpaintro.domain.composite.*;
import org.springframework.data.jpa.repository.*;

public interface AuthorEmbeddedRepository extends JpaRepository<AuthorEmbedded, NameId> {
}
