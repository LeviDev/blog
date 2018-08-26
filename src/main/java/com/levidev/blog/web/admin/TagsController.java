package com.levidev.blog.web.admin;

import com.levidev.blog.po.Tag;
import com.levidev.blog.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;


@Controller
@RequestMapping("/admin")
public class TagsController {

    @Autowired
    private TagService tagService;

    @GetMapping("/tags")
    public String tags(@PageableDefault(size = 8, sort = {"id"}, direction = Sort.Direction.DESC) Pageable pageable,
                        Model model)  {
        model.addAttribute("page", tagService.listTag(pageable));
        return "admin/tags";
    }
    @GetMapping("/tags/{id}/input")
    public String editInput(@PathVariable Long id, Model model) {
//        System.out.println(String.format("Levi debug: %d", id));
//        Tag tag =  tagService.getType(id);
//        System.out.println(tag);
        model.addAttribute("tag", tagService.getTag(id));
        return "admin/tag-input";
    }

    @GetMapping("/tags/{id}/delete")
    public String deleteType(@PathVariable Long id) {
        tagService.deleteTag(id);
        return "redirect:/admin/tags";
    }

    @GetMapping("/tags/input")
    public String input(Model model) {
        model.addAttribute("tag", new Tag());
        return "admin/tag-input";
    }

    @PostMapping("tags/input")
    public String postType(@Valid Tag tag, BindingResult result, RedirectAttributes attributes){

        Tag tag2 = tagService.getTagByName(tag.getName());
        if (tag2 != null) {
            result.rejectValue("name", "nameError", "不能添加重复分类");
        }

        if (result.hasErrors()) {
            return "admin/tag-input";
        }

        Tag tag1 = tagService.saveTag(tag);
        if (tag1 == null) {
            attributes.addFlashAttribute("message", "新增失败");
        } else {
            attributes.addFlashAttribute("message", "新增成功");
        }
        return "redirect:/admin/tags";
    }

    @PostMapping("/tags/{id}/input")
    public String editPost(@Valid Tag tag, BindingResult result, @PathVariable Long id, RedirectAttributes attributes) {
        Tag tag1 = tagService.getTagByName(tag.getName());
        if (tag1 != null) {
            result.rejectValue("name", "nameError", "不能添加重复分类");
        }
        if (result.hasErrors()) {
            return "/admin/tags-input";
        }
        Tag tag2 = tagService.updateTag(id, tag);
        if (tag2 == null) {
            attributes.addFlashAttribute("message", "更新失败");
        } else {
            attributes.addFlashAttribute("message", "更新成功");
        }
        return "redirect:/admin/tags";
    }

}
