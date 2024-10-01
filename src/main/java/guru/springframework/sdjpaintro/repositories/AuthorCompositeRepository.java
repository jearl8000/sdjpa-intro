package guru.springframework.sdjpaintro.repositories;

import guru.springframework.sdjpaintro.domain.composite.*;
import org.springframework.data.jpa.repository.*;

public interface AuthorCompositeRepository extends JpaRepository<AuthorComposite, NameId> {
}
