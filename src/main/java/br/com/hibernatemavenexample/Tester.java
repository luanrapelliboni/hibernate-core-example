package br.com.hibernatemavenexample;

import java.util.List;

import br.com.hibernatemavenexample.domain.Book;
import br.com.hibernatemavenexample.repository.BookRepository;

public class Tester {
	public static void main(String[] args) {
		showBook(1L);
		listBooks();		
	}
	
	private static void showBook(Long id) {
		BookRepository bookRepository = new BookRepository();
		Book book = bookRepository.find(id);
		System.out.println("Book #id = " + id);
		System.out.println("ISBN: " + book.getIsbn());
		System.out.println("Title: " + book.getTitle());
		System.out.println("Pages: " + book.getNumberOfPages());
		if (book.getAuthor() != null)
			System.out.println("Author: " + book.getAuthor().getName() + ", Age:" + book.getAuthor().getAge());
	}
	
	private static void listBooks() {
		BookRepository bookRepository = new BookRepository();
		List<Book> books = bookRepository.findAll();
		System.out.println("All Books:");
		for (Book book : books) {
			System.out.println("ISBN: " + book.getIsbn());
			System.out.println("Title: " + book.getTitle());
			System.out.println("Pages: " + book.getNumberOfPages());
			if (book.getAuthor() != null)
				System.out.println("Author: " + book.getAuthor().getName() + ", Age:" + book.getAuthor().getAge());
			System.out.println("");
		}
	}
}
