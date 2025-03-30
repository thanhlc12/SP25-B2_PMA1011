package com.example.sprin4.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "Nhan_Vien")
public class NhanVien {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Integer id;

    @Column(name = "MaNhanVien")
    private String maNhanVien;

    @Column(name = "TenNhanVien")
    private String tenNhanVien;

    @Column(name = "GioiTinh")
    private String gioiTinh;

    @Column(name = "SoDienThoai")
    private String soDienThoai;

    @Column(name = "DiaChi")
    private String diaChi;

    @Column(name = "Email")
    private String email;
}
