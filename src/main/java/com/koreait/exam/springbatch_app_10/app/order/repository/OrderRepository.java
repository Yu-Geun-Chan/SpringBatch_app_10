package com.koreait.exam.springbatch_app_10.app.order.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.koreait.exam.springbatch_app_10.app.order.entity.Order;

public interface OrderRepository extends JpaRepository<Order, Long> {
}
