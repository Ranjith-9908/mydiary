package com.MyDiary.servicelmpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.MyDiary.entity.DiaryEntry;
import com.MyDiary.entity.User;
import com.MyDiary.repository.DiaryEntryRepository;
import com.MyDiary.service.DiaryEntryService;

@Service
public class DiaryEntryServiceImpl implements DiaryEntryService {

	@Autowired
	private DiaryEntryRepository diaryEntryRepository;

	// Add a new entry
	@Override
	public DiaryEntry addEntry(User user, String title, String content) {
		DiaryEntry entry = new DiaryEntry();
		entry.setUser(user);
		entry.setTitle(title);
		entry.setContent(content);
		return diaryEntryRepository.save(entry);
	}

	// Update entry
	@Override
	public DiaryEntry updateEntry(Long entryId,User user, String newTitle, String newContent) {
		Optional<DiaryEntry> existing = diaryEntryRepository.findById(entryId);
		if (existing.isPresent()) {
			DiaryEntry entry = existing.get();
			entry.setTitle(newTitle);
			entry.setContent(newContent);
			return diaryEntryRepository.save(entry);
		}
		return null;
	}

	// Delete entry
	@Override
	public boolean deleteEntry(Long entryId) {
		if (diaryEntryRepository.existsById(entryId)) {
			diaryEntryRepository.deleteById(entryId);
			return true;
		}
		return false;
	}

	// Get entries for user
	@Override
	public List<DiaryEntry> getEntriesByUser(User user) {
		return diaryEntryRepository.findByUser(user);
	}

	// Get entry by ID
	@Override
	public DiaryEntry getEntryById(Long entryId) {
		return diaryEntryRepository.findById(entryId).orElse(null);
	}
}
