package com.test.library.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.test.library.dto.FindBooksReqDto;
import com.test.library.dto.FindBooksResDto;
import com.test.library.entity.Book;
import com.test.library.repository.LibraryRepository;
import com.test.library.service.LibraryService;

import io.micrometer.common.util.StringUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class LibraryServiceImpl implements LibraryService {

	private final LibraryRepository libraryRepo;
	private final ObjectMapper mapper;

	/*
	 * public LibraryServiceImpl(LibraryMapper libraryMapper, ObjectMapper
	 * objectMapper) { this.libraryMapper = libraryMapper; }
	 */

	@Override
	public FindBooksResDto findBooks(FindBooksReqDto request) {
		try {

			log.info("## findBooks? - {}", request.toString());

			String keyword = request.getKeyword();
			
			// 검색어가 없는 경우
			if (StringUtils.isBlank(keyword)) {
				return FindBooksResDto.of(200, "성공", libraryRepo.findAll());
			}
			
			List<Book> bookList = libraryRepo.findBookByTitleContaining(keyword);

			if (ObjectUtils.isEmpty(bookList)) {
				return FindBooksResDto.from(204, "조회 결과가 없습니다.");
			}

			return FindBooksResDto.of(200, "성공", bookList);

		} catch (Exception e) {
			log.error("## ERROR findBooks - {}", e.getMessage(), e);
			return FindBooksResDto.from(604, "조회에 실패하였습니다.");
		}
	}

}
