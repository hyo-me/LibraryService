package com.test.library.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.test.library.entity.Book;

@Repository
public interface LibraryRepository extends JpaRepository<Book, Long> {
	
	List<Book> findAll();
	
	List<Book> findBookByTitleContaining(String title);
}
