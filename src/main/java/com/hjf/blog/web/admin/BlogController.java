package com.hjf.blog.web.admin;

import com.hjf.blog.dao.TagRepository;
import com.hjf.blog.dto.BlogQuery;
import com.hjf.blog.entity.Blog;
import com.hjf.blog.entity.Tag;
import com.hjf.blog.entity.User;
import com.hjf.blog.service.BlogService;
import com.hjf.blog.service.TagService;
import com.hjf.blog.service.TypeService;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/admin")
public class BlogController {

    private static final String INPUT = "admin/blogs-input";
    private static final String LIST = "admin/blogs";
    private static final String REDIRECT_LIST = "redirect:/" + LIST;

    @Autowired
    private BlogService blogService;

    @Autowired
    private TypeService typeService;

    @Autowired
    private TagService tagService;

    @Autowired
    private TagRepository tagRepository;

    @GetMapping("/blogs")
    public String blogs(Model model, BlogQuery blog,
             @PageableDefault(size = 2, sort = {"updateTime"}, direction = Sort.Direction.DESC) Pageable pageable) {
        model.addAttribute("types", typeService.listType());
        model.addAttribute("page", blogService.listBlog(pageable, blog));
        return LIST;
    }

    @PostMapping("/blogs/search")
    public String search(Model model, BlogQuery blog,
             @PageableDefault(size = 2, sort = {"updateTime"}, direction = Sort.Direction.DESC) Pageable pageable) {
        model.addAttribute("page", blogService.listBlog(pageable, blog));
        return "admin/blogs::blogList";
    }

    @GetMapping("/blogs/input")
    public String input(Model model) {
        setTypeAndTag(model);
        model.addAttribute("blog", new Blog());
        return INPUT;
    }

    @PostMapping("/blogs")
    public String post(Blog blog, RedirectAttributes attributes, HttpSession session) {
//        blog.setTagIds(tagsDeal(blog.getTagIds()));
        blog.setUser((User) session.getAttribute("user"));
        blog.setType(typeService.getType(blog.getType().getId()));
//        blog.setTags(tagService.listTag(blog.getTagIds()));
        blog.setTags(tagService.listTag(tagsDeal(blog.getTagIds())));
//        System.out.println(blog);
        Blog b;
        if (blog.getId() == null) {
            b = blogService.saveBlog(blog);
        } else {
            b = blogService.updateBlog(blog.getId(), blog);
        }
        if (b == null) {
            attributes.addFlashAttribute("message", "操作失败");
        } else {
            attributes.addFlashAttribute("message", "操作成功");
        }
        return REDIRECT_LIST;
    }

    @GetMapping("/blogs/{id}/input")
    public String editInput(@PathVariable("id") Long id, Model model) {
        setTypeAndTag(model);
        Blog blog = blogService.getBlog(id);
        blog.init();
        model.addAttribute("blog", blog);
        return INPUT;
    }

    @GetMapping("/blogs/{id}/delete")
    public String delete(@PathVariable("id") Long id, RedirectAttributes attributes) {
        blogService.deleteBlog(id);
        attributes.addFlashAttribute("message", "删除成功");
        return REDIRECT_LIST;
    }

    private void setTypeAndTag(Model model) {
        model.addAttribute("types", typeService.listType());
        model.addAttribute("tags", tagService.listTag());
    }

    private String tagsDeal(String tagIds) {
        StringBuilder sb = new StringBuilder();
        String[] tagId = tagIds.split(",");
        for (int i = 0; i < tagId.length; i++) {
            if ( !isNumeric(tagId[i]) ) {
                Tag tag = new Tag();
                tag.setName(tagId[i]);
                tagRepository.save(tag);
                tagId[i] = tag.getId().toString();
            }
            if (i != tagId.length-1) {
                sb.append(tagId[i]+",");
            } else {
                sb.append(tagId[i]);
            }
        }
        return sb.toString();
    }

    private boolean isNumeric(String str){
        Pattern pattern = Pattern.compile("[0-9]*");
        Matcher isNum = pattern.matcher(str);
        if( !isNum.matches() ){
            return false;
        }
        return true;
    }
}
