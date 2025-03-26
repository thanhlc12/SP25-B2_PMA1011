package com.example.sprint3.Repository;

import com.example.sprint3.Entity.SanPham;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SanPhamRepo extends JpaRepository<SanPham,Integer> {
}
