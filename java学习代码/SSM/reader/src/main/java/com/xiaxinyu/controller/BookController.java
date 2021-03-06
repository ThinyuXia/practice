package com.xiaxinyu.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.xiaxinyu.entity.Book;
import com.xiaxinyu.entity.Category;
import com.xiaxinyu.entity.Evaluation;
import com.xiaxinyu.service.BookService;
import com.xiaxinyu.service.CategoryService;
import com.xiaxinyu.service.EvaluationService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import java.util.List;

@Controller
public class BookController {

    @Resource
    private CategoryService categoryService;

    @Resource
    private BookService bookService;

    @Resource
    private EvaluationService evaluationService;

    /**
     * 显示首页
     * @return
     */
    @GetMapping("/index")
    public ModelAndView showIndex(){
        ModelAndView mav = new ModelAndView("/index");
        List<Category> categoryList = categoryService.selectAll();
        mav.addObject("categoryList",categoryList);
        return mav;
    }

    /**
     * 分页查询图书列表
     * @param p 页号
     * @return 页面对象
     */
    @GetMapping("/books")
    @ResponseBody
    public IPage<Book> selectBook(Long categoryId,String order,Integer p){
        if(p == null) p = 1;
        return bookService.paging(categoryId,order,p,10);
    }


    @GetMapping("/book/{id}")
    public ModelAndView showDetail(@PathVariable("id") Long id){
        Book book = bookService.selectById(id);
        List<Evaluation> evaluationList = evaluationService.selectByBookId(id);
        ModelAndView mav = new ModelAndView("/detail");
        mav.addObject("book",book);
        mav.addObject("evaluationList",evaluationList);
        return mav;
    }
}
