package com.cherrypick.order.repository;

import com.cherrypick.order.entity.Member;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MemberRepository extends PagingAndSortingRepository<Member, Long> {
    Optional<Member> findByEmail(String email);

    boolean existsByEmail(String email);

    List<Member> findAllByNameContains(String name, Pageable pageable);

    List<Member> findAllByEmailContains(String email, Pageable pageable);
}
