package ru.isaev.demoserver;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(classes = DemoserverApplication.class)
class BookRepositoryImplTest {
//	ApplicationContext context = new ClassPathXmlApplicationContext (
//			new String[]{"springContext.xml"}
//	);

	@Test
	void findBook() {
//		BookRepository bookRepository = (BookRepository) context.getBean("bookRepository");
//		List<Book> books = bookRepository.showAllInDB();
//		System.out.println("Count = " + books.size());
//		System.out.println("Class = " + books.get(0).getClass());
//		System.out.println("Contain : ");
//		books.forEach(book -> {
//			System.out.println("ID = " +  book.getId());
//			System.out.println("Title = " + book.getBookName());
//			System.out.println("Author = " + book.getAuthor());
//		});
	}

}
