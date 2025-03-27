package com.example.sprint3.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "san_pham")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"}) // Tránh lỗi JSON serialization
public class SanPham {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id; // Đổi từ int sang Integer để tránh lỗi khi id chưa được gán

    @Column(name = "MaSanPham", nullable = false, unique = true)
    private String maSanPham;

    @Column(name = "TenSanPham", nullable = false)
    private String tenSanPham;

    @Column(name = "SoLuong")
    private Integer soLuong;

    @Column(name = "MoTa")
    private String moTa;

    @Column(name = "TrangThai", nullable = false)
    private Boolean trangThai; // Đổi từ Byte sang Boolean
}
