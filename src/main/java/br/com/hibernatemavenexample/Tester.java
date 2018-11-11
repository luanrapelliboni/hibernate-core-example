package br.com.hibernatemavenexample;

import java.util.List;

import javax.persistence.TypedQuery;

import org.hibernate.Session;

import br.com.hibernatemavenexample.domain.Book;
import br.com.hibernatemavenexample.util.HibernateUtil;

public class Tester {
	public static void main(String[] args) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();

//		Book programmingInCBook = new Book();
//		programmingInCBook.setIsbn("ISBN 999-99-999-9999-9");
//		programmingInCBook.setNumberOfPages(120);
//		programmingInCBook.setTitle("Programming in C");
//		session.save(programmingInCBook);

//		String sql = "select version()";
//		String result = (String) session.createNativeQuery(sql).getSingleResult();
//		System.out.println(result);

		TypedQuery<Book> bookQuery = session.createQuery("FROM Book", Book.class);
		List<Book> books = bookQuery.getResultList();

		for (Book book : books) {
			System.out.println("ISBN: " + book.getIsbn());
			System.out.println("Title: " + book.getTitle());
			System.out.println("Pages: " + book.getNumberOfPages());
		}

//		Book book = session.get(Book.class, 1L);
//		System.out.println("ISBN: " + book.getIsbn());
//		System.out.println("Title: " + book.getTitle());
//		System.out.println("Pages: " + book.getNumberOfPages());

		session.getTransaction().commit();
		session.close();

	}
}
