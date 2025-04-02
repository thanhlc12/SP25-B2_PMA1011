package com.example.sprin4.controller;

import com.example.sprin4.entity.KhachHang;
import com.example.sprin4.repository.KhachHangRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:5500") // Chỉnh lại nếu frontend chạy cổng khác
@RestController
@RequestMapping("/api/khachHang")
public class KhachHangController {
    @Autowired
    private KhachHangRepo khachHangRepo;

    @GetMapping
    public List<KhachHang> getAllKhachHang() {
        return khachHangRepo.findAll();
    }

    @PostMapping
    public ResponseEntity<?> addKhachHang(@RequestBody KhachHang khachHang) {
        if (khachHangRepo.findAll().stream().anyMatch(kh -> kh.getMaKhachHang().equals(khachHang.getMaKhachHang()))) {
            return ResponseEntity.badRequest().body("Mã khách hàng đã tồn tại!");
        }
        return ResponseEntity.ok(khachHangRepo.save(khachHang));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateKhachHang(@PathVariable Integer id, @RequestBody KhachHang khachHang) {
        Optional<KhachHang> existingKhachHang = khachHangRepo.findById(id);
        if (existingKhachHang.isPresent()) {
            KhachHang updatedKhachHang = existingKhachHang.get();
            updatedKhachHang.setMaKhachHang(khachHang.getMaKhachHang());
            updatedKhachHang.setTenKhachHang(khachHang.getTenKhachHang());
            updatedKhachHang.setGioiTinh(khachHang.getGioiTinh());
            updatedKhachHang.setDiaChi(khachHang.getDiaChi());
            updatedKhachHang.setSoDienThoai(khachHang.getSoDienThoai());
            updatedKhachHang.setEmail(khachHang.getEmail());
            updatedKhachHang.setTrangThai(khachHang.getTrangThai());
            return ResponseEntity.ok(khachHangRepo.save(updatedKhachHang));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteKhachHang(@PathVariable Integer id) {
        if (khachHangRepo.existsById(id)) {
            khachHangRepo.deleteById(id);
            return ResponseEntity.ok("Xóa thành công!");
        } else {
            return ResponseEntity.status(404).body("Khách hàng không tồn tại!");
        }
    }
}

