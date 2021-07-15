package com.cherrypick.order.service;

import com.cherrypick.order.dto.LatestOrderResponseDto;
import com.cherrypick.order.dto.OrderResponseDto;
import com.cherrypick.order.entity.Member;
import com.cherrypick.order.entity.Order;
import com.cherrypick.order.repository.OrderRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Service
public class OrderService {
    private final OrderRepository orderRepository;

    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Transactional(readOnly = true)
    public List<OrderResponseDto> getOrdersByMemberId(Long memberId) {
        return orderRepository.findAllByMemberId(memberId).stream()
                .map(OrderResponseDto::of).collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public LatestOrderResponseDto getLatestOrderByMember(Member member) {
        Optional<Order> order = orderRepository.findFirstByMemberOrderByCreatedAtDesc(member);
        if (order.isEmpty()) return null;
        return LatestOrderResponseDto.of(order.get());
    }
}
