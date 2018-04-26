package com.ajender.ecommerce.repositories;

import com.ajender.ecommerce.models.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
//    @Query("SELECT o FROM Order o JOIN FETCH o.items WHERE o.id = (:id)")
//    public Order findOneWithItems(@Param("id") Long id);
}
