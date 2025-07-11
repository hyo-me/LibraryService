package com.test.library.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RestController;

import com.test.library.dto.FindBooksReqDto;
import com.test.library.dto.FindBooksResDto;
import com.test.library.service.LibraryService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class LibraryController {
	
	private final LibraryService libraryService;
	
	@GetMapping("/books")
	public FindBooksResDto findBooks(@ModelAttribute FindBooksReqDto request) {
		return libraryService.findBooks(request);
	}

}
