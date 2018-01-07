package cn.hdj.ssm.service.impl;

import cn.hdj.ssm.dao.BookDao;
import cn.hdj.ssm.entity.Book;
import cn.hdj.ssm.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class BookServiceImpl implements BookService {

	// 注入Service依赖
	@Autowired
	private BookDao bookDao;

	@Override
	public Book getById(long bookId) {
		return bookDao.queryById(bookId);
	}

	@Override
	public List<Book> getList() {
		return bookDao.queryAll(0, 1000);
	}

}
