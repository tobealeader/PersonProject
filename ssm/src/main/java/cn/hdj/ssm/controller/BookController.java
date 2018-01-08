package cn.hdj.ssm.controller;

import cn.hdj.ssm.entity.Book;
import cn.hdj.ssm.service.BookService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;


@Controller
@RequestMapping("/book")
public class BookController {

	@Resource
	private BookService bookService;

	@RequestMapping(value = "/list")
	@ResponseBody
	public List<Book> list() {
		List<Book> list = bookService.getList();
		for (Book book : list) {
			System.out.println(book.toString());
		}
		return list;
	}

	@RequestMapping(value = "/index")
	public String index() {
		return "index";
	}

	@RequestMapping(value = "/{bookId}/detail")
	@ResponseBody
	public Book detail(@PathVariable("bookId") Long bookId) {
		Book book = bookService.getById(bookId);
		return book;
	}

	@RequestMapping(value = "/testLogger")
	public void testLogger() {
		bookService.textLogger();
	}

}
