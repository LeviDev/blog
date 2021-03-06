package com.levidev.blog.web.admin;

import com.levidev.blog.po.Type;
import com.levidev.blog.service.TypeService;
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
public class TypesController {

    @Autowired
    private TypeService typeService;

    @GetMapping("/types")
    public String types(@PageableDefault(size = 8, sort = {"id"}, direction = Sort.Direction.DESC) Pageable pageable,
                        Model model)  {
        model.addAttribute("page", typeService.listType(pageable));
        return "admin/types";
    }
    @GetMapping("/types/{id}/input")
    public String editInput(@PathVariable Long id, Model model) {
//        System.out.println(String.format("Levi debug: %d", id));
//        Type type =  typeService.getType(id);
//        System.out.println(type);
        model.addAttribute("type", typeService.getType(id));
        return "admin/type-input";
    }

    @GetMapping("/types/{id}/delete")
    public String deleteType(@PathVariable Long id) {
        typeService.deleteType(id);
        return "redirect:/admin/types";
    }

    @GetMapping("/types/input")
    public String input(Model model) {
        model.addAttribute("type", new Type());
        return "admin/type-input";
    }

    @PostMapping("types/input")
    public String postType(@Valid Type type, BindingResult result, RedirectAttributes attributes){

        Type type2 = typeService.getTypeByName(type.getName());
        if (type2 != null) {
            result.rejectValue("name", "nameError", "不能添加重复分类");
        }

        if (result.hasErrors()) {
            return "admin/type-input";
        }

        Type type1 = typeService.saveType(type);
        if (type1 == null) {
            attributes.addFlashAttribute("message", "新增失败");
        } else {
            attributes.addFlashAttribute("message", "新增成功");
        }
        return "redirect:/admin/types";
    }

    @PostMapping("/types/{id}/input")
    public String editPost(@Valid Type type, BindingResult result, @PathVariable Long id, RedirectAttributes attributes) {
        Type type1 = typeService.getTypeByName(type.getName());
        if (type1 != null) {
            result.rejectValue("name", "nameError", "不能添加重复分类");
        }
        if (result.hasErrors()) {
            return "/admin/types-input";
        }
        Type type2 = typeService.updateType(id, type);
        if (type2 == null) {
            attributes.addFlashAttribute("message", "更新失败");
        } else {
            attributes.addFlashAttribute("message", "更新成功");
        }
        return "redirect:/admin/types";
    }

}
