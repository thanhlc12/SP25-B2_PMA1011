package com.example.sprint3.controller;

import com.example.sprint3.entity.SanPham;
import com.example.sprint3.repository.SanPhamRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:5500") // Chỉnh lại nếu frontend chạy cổng khác
@RestController
@RequestMapping("/api/sanPham")
public class SanPhamController {
    @Autowired
    private SanPhamRepo sanPhamRepo;

    @GetMapping
    public List<SanPham> getAllSanPham() {
        return sanPhamRepo.findAll();
    }

    @PostMapping
    public ResponseEntity<?> addSanPham(@RequestBody SanPham sanPham) {
        if (sanPhamRepo.findAll().stream().anyMatch(sp -> sp.getMaSanPham().equals(sanPham.getMaSanPham()))) {
            return ResponseEntity.badRequest().body("Mã sản phẩm đã tồn tại!");
        }
        return ResponseEntity.ok(sanPhamRepo.save(sanPham));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateSanPham(@PathVariable Integer id, @RequestBody SanPham sanPham) {
        Optional<SanPham> existingSanPham = sanPhamRepo.findById(id);
        if (existingSanPham.isPresent()) {
            SanPham updatedSanPham = existingSanPham.get();
            updatedSanPham.setMaSanPham(sanPham.getMaSanPham());
            updatedSanPham.setTenSanPham(sanPham.getTenSanPham());
            updatedSanPham.setSoLuong(sanPham.getSoLuong());
            updatedSanPham.setMoTa(sanPham.getMoTa());
            updatedSanPham.setTrangThai(sanPham.getTrangThai());
            return ResponseEntity.ok(sanPhamRepo.save(updatedSanPham));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteSanPham(@PathVariable Integer id) {
        if (sanPhamRepo.existsById(id)) {
            sanPhamRepo.deleteById(id);
            return ResponseEntity.ok("Xóa thành công!");
        } else {
            return ResponseEntity.status(404).body("Sản phẩm không tồn tại!");
        }
    }
}
