package com.cherrypick.order.repository;

import com.cherrypick.order.entity.Member;
import com.cherrypick.order.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
    List<Order> findAllByMemberId(Long memberId);

    Optional<Order> findFirstByMemberOrderByCreatedAtDesc(Member member);
}