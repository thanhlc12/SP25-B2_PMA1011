package com.example.sprint3.Controller;

import com.example.sprint3.Entity.SanPham;
import com.example.sprint3.Repository.SanPhamRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/api/sanPham")
public class SanPhamController {
@Autowired
private SanPhamRepo sanPhamRepo;

@GetMapping
    public List<SanPham> getAllSanPham(){
    return sanPhamRepo.findAll();
}

@PostMapping
    public SanPham addSanPham(@RequestBody SanPham sanPham){
    return sanPhamRepo.save(sanPham);
}

@PutMapping("/{id}")
    public SanPham updateSanPham(@PathVariable Integer id, @RequestBody SanPham sanPham){
    sanPham.setID(id);
    return sanPhamRepo.save(sanPham);
}

@DeleteMapping("/{id}")
    public void deleteSanPham(@PathVariable Integer id){
    sanPhamRepo.deleteById(id);
}



}
