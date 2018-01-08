package cn.hdj.ssm.service.impl;

import cn.hdj.ssm.dao.BookDao;
import cn.hdj.ssm.entity.Book;
import cn.hdj.ssm.service.BookService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookServiceImpl implements BookService {
	private Logger logger = LoggerFactory.getLogger(this.getClass());

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

	@Override
	public void textLogger() {
		try {
			int m = 0;
			int b = 2/m;
			System.out.println("can't arrive");
		}catch (Exception e) {
//			System.out.println(e);
			logger.error(e.getMessage(), e);
		}
	}


}
