package samuel.dzur.hospoda.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import samuel.dzur.hospoda.backend.repository.entity.DrinkEntity;

@Repository
public interface DrinkRepository extends JpaRepository<DrinkEntity, Long> {
}
