package com.example.readexcel.repository;

import com.example.readexcel.model.MySheet;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MySheetRepository extends JpaRepository<MySheet,Long> {
}
