package com.yucheng.blog.service;

import com.yucheng.blog.dao.BlogRepository;
import com.yucheng.blog.pojo.Blog;
import com.yucheng.blog.pojo.Type;
import com.yucheng.blog.util.MarkdownUtils;
import com.yucheng.blog.vo.BlogQuery;
import javassist.NotFoundException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author YuCheng
 * @date 2021/9/24 - 下午 02:41
 */

@Service
public class BlogServiceImpl implements BlogService {

    @Autowired
    private BlogRepository blogRepository;

    @Override
    public Blog getBlog(Long id) {
        return blogRepository.findById(id).orElse(null);
    }

    @Override
    public Page<Blog> listBlog(Pageable pageable, BlogQuery blogQuery) {

        //DAO繼承JpaSpecificationExecutor，特定條件搜尋參數Specification
        return blogRepository.findAll(new Specification<Blog>() {

            //root可以獲取屬性值字段代表查詢物件，criteriaQuery查詢語句，criteriaBuilder建構動態條件
            @Override
            public Predicate toPredicate(Root<Blog> root,
                                         CriteriaQuery<?> criteriaQuery,
                                         CriteriaBuilder criteriaBuilder) {
                //條件表單
                List<Predicate> predicateList = new ArrayList<>();

                //判斷第一個條件輸入的標題不為空
                if (!"".equals(blogQuery.getTitle()) && blogQuery.getTitle() != null) {
                    //添加語句語法和欄位名稱，第二個參數為屬性值，like前後需加百分號
                    predicateList.add(criteriaBuilder.like(root.<String>get("title"), "%" + blogQuery.getTitle() + "%"));
                }

                //判斷第二個條件分類選單，內容不為空
                if (blogQuery.getTypeId() != null) {
                    predicateList.add(criteriaBuilder.equal(root.<Type>get("type").get("id"), blogQuery.getTypeId()));
                }

                //判斷第三個條件是否推薦
                if (blogQuery.isRecommend()) {
                    predicateList.add(criteriaBuilder.equal(root.<Boolean>get("recommend"), blogQuery.isRecommend()));
                }

                //動態條件傳入語句，清單需轉為陣列
                criteriaQuery.where(predicateList.toArray(new Predicate[predicateList.size()]));
                return null;
            }

        }, pageable);

    }

    public Page<Blog> listBlog(Pageable pageable) {
        return blogRepository.findAll(pageable);
    }

    @Override
    public List<Blog> listBlog(Integer size) {
        Pageable pageable = PageRequest.of(0,size, Sort.by(Sort.Direction.DESC,"updateTime"));
        return blogRepository.findTop(pageable);
    }

    @Override
    @Transactional
    public Blog saveBlog(Blog blog) {
        //判斷是新增文章才去初始化
        if (blog.getId() == null) {
            blog.setCreateTime(new Date());
            blog.setUpdateTime(new Date());
            blog.setViews(0);
        } else {
            Blog b = blogRepository.findById(blog.getId()).orElse(null);
            blog.setCreateTime(b.getCreateTime());
            blog.setViews(b.getViews());
            blog.setUpdateTime(new Date());
        }

        return blogRepository.save(blog);
    }

    @Override
    @Transactional
    public Blog updateBlog(Long id, Blog blog) {
        Blog b = blogRepository.findById(id).orElse(null);

        if (b == null) {
            return null;
        }
        blog.setUpdateTime(new Date());
        return blogRepository.save(blog);
    }

    @Override
    @Transactional
    public void deleteBlog(Long id) {
        blogRepository.deleteById(id);
    }

    @Transactional
    @Override
    public Blog getAndConvert(Long id) throws NotFoundException {
        Blog blog = blogRepository.findById(id).orElse(null);
        if (blog == null) {
            throw new NotFoundException("文章不存在");
        }
        Blog b = new Blog();
        BeanUtils.copyProperties(blog,b);
        String content = b.getContent();
        b.setContent(MarkdownUtils.markdownToHtmlExtensions(content));

        blogRepository.updateViews(id);
        return b;
    }
}
