package com.MyDiary.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.MyDiary.entity.DiaryEntry;
import com.MyDiary.entity.User;

@Repository
public interface DiaryEntryRepository extends JpaRepository<DiaryEntry, Long> {

    // Fetch all diary entries for a specific user
    List<DiaryEntry> findByUser(User user);
}
