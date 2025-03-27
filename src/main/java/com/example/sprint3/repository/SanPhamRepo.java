package com.example.sprint3.repository;

import com.example.sprint3.entity.SanPham;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SanPhamRepo extends JpaRepository<SanPham,Integer> {
}
