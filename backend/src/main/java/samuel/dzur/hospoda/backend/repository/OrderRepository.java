package samuel.dzur.hospoda.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import samuel.dzur.hospoda.backend.repository.entity.OrderEntity;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<OrderEntity, Long> {

    @Query("""
            select o
            from OrderEntity o
            join fetch o.user
            join fetch o.priceRecord priceRecord
            join fetch priceRecord.drink
            where priceRecord.drink.id = :drinkId
            """)
    List<OrderEntity> findAllByDrinkIdWithUser(@Param("drinkId") Long drinkId);

    @Query("""
            select o
            from OrderEntity o
            join fetch o.priceRecord priceRecord
            join fetch priceRecord.drink
            where o.user.id = :userId
            """)
    List<OrderEntity> findAllByUserIdWithDrink(@Param("userId") Long userId);

    @Query("""
            select o
            from OrderEntity o
            join fetch o.priceRecord priceRecord
            join fetch priceRecord.drink
            """)
    List<OrderEntity> findAllWithDrink();
}
