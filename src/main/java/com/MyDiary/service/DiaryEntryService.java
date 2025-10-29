package com.MyDiary.service;

import java.util.List;

import com.MyDiary.entity.DiaryEntry;
import com.MyDiary.entity.User;

public interface DiaryEntryService {

	// Add a new diary entry for a user
	DiaryEntry addEntry(User user, String title, String content);

	// Update an existing diary entry
	DiaryEntry updateEntry(Long entryId, User user, String newTitle, String newContent);

	// Delete an entry by its ID
	boolean deleteEntry(Long entryId);

	// Fetch all entries for a user
	List<DiaryEntry> getEntriesByUser(User user);

	// Get single entry by ID
	DiaryEntry getEntryById(Long entryId);
}
