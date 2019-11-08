package com.hjf.blog.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.hjf.blog.entity.Tag;
import com.hjf.blog.service.BlogService;
import com.hjf.blog.service.TagService;

@Controller
public class TagShowController {

    @Autowired
    private TagService tagService;

    @Autowired
    private BlogService blogService;

    @GetMapping("/tags/{id}")
    public String tags(@PathVariable("id") Long id, Model model,
            @PageableDefault(size = 8, sort = {"updateTime"}, direction = Sort.Direction.DESC) Pageable pageable) {
        List<Tag> Tags = tagService.listTagTop(9999);
        if (id == -1) {
            id = Tags.get(0).getId();
        }
        model.addAttribute("tags", Tags);
        model.addAttribute("page", blogService.listBlog(pageable, id));
        model.addAttribute("activeTagId", id);
        return "tags";
    }
}
