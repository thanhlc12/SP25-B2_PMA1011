package com.example.sprin4.controller;

import com.example.sprin4.entity.NhanVien;
import com.example.sprin4.repository.NhanVienRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:5500") // Chỉnh lại nếu frontend chạy cổng khác
@RestController
@RequestMapping("/api/nhanVien")
public class NhanVienController {
    @Autowired
    private NhanVienRepo nhanVienRepo;

    @GetMapping
    public List<NhanVien> getAllNhanVien() {
        return nhanVienRepo.findAll();
    }

    @PostMapping
    public ResponseEntity<?> addNhanVien(@RequestBody NhanVien nhanVien) {
        if (nhanVienRepo.findAll().stream().anyMatch(nv -> nv.getMaNhanVien().equals(nhanVien.getMaNhanVien()))) {
            return ResponseEntity.badRequest().body("Mã nhân viên đã tồn tại!");
        }
        return ResponseEntity.ok(nhanVienRepo.save(nhanVien));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateNhanVien(@PathVariable Integer id, @RequestBody NhanVien nhanVien) {
        Optional<NhanVien> existingNhanVien = nhanVienRepo.findById(id);
        if (existingNhanVien.isPresent()) {
            NhanVien updatedNhanVien = existingNhanVien.get();
            updatedNhanVien.setMaNhanVien(nhanVien.getMaNhanVien());
            updatedNhanVien.setTenNhanVien(nhanVien.getTenNhanVien());
            updatedNhanVien.setGioiTinh(nhanVien.getGioiTinh());
            updatedNhanVien.setSoDienThoai(nhanVien.getSoDienThoai());
            updatedNhanVien.setDiaChi(nhanVien.getDiaChi());
            updatedNhanVien.setEmail(nhanVien.getEmail());
            return ResponseEntity.ok(nhanVienRepo.save(updatedNhanVien));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteNhanVien(@PathVariable Integer id) {
        if (nhanVienRepo.existsById(id)) {
            nhanVienRepo.deleteById(id);
            return ResponseEntity.ok("Xóa thành công!");
        } else {
            return ResponseEntity.status(404).body("Nhân viên không tồn tại!");
        }
    }
}
