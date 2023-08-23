package com.example.codegymfoods.controller.blog;

import com.example.codegymfoods.dto.blog.BlogDTO;
import com.example.codegymfoods.model.blog.Blog;
import com.example.codegymfoods.service.blog.IBlogService;
import com.example.codegymfoods.service.employee.IEmployeeService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
@RequestMapping("/blog")
public class BlogController {
    @Autowired
    private IBlogService iBlogService;
    @Autowired
    private IEmployeeService iEmployeeService;

    @GetMapping("/create-form")
    public String createFormBlog (Model model) {
        model.addAttribute("blogs", new BlogDTO());
        model.addAttribute("employee", this.iEmployeeService.getEmployee());


        return "/blog/createBlog";
    }
    @PostMapping("/createBlog")
    public String createBlog (@Valid @ModelAttribute(name = "blogs") BlogDTO blogDTO, BindingResult bindingResult,
                              RedirectAttributes redirectAttributes, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("blogDTO", blogDTO);
            return "/blog/createBlog";
        }
        Blog blog = new Blog();
        BeanUtils.copyProperties(blogDTO, blog);
        iBlogService.save(blog);
        redirectAttributes.addFlashAttribute("message", "Thêm bài viết thành công");
        return "redirect:/home/success";
    }
    @GetMapping("/{id}/update")
    public String update(@PathVariable Integer id, Model model) {
        model.addAttribute("blogs", iBlogService.findById(id));
        model.addAttribute("employee", this.iEmployeeService.getEmployee());
        return "/blog/updateBlog";
    }

    @PostMapping("/updateBlog")
    public String update(@Valid @ModelAttribute(name = "blogs") BlogDTO blogDTO, BindingResult bindingResult,
                         RedirectAttributes redirectAttributes, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("blogDTO", blogDTO);
            return "/blog/updateBlog";
        }
        Blog blog = new Blog();
        BeanUtils.copyProperties(blogDTO, blog);
        iBlogService.update(blog);
        redirectAttributes.addFlashAttribute("message", "Cập nhật bài viết thành công");
        return "redirect:/home/success";
    }
    @GetMapping("/{id}/detail")
    public String detail(@PathVariable Integer id, Model model) {
        model.addAttribute("blog", iBlogService.findById(id));
        model.addAttribute("employee", this.iEmployeeService.getEmployee());
        return "/blog/detailBlog";
    }

    @GetMapping("/delete")
    public String delete(@RequestParam Integer idDeleteBlog, RedirectAttributes redirectAttributes) {
        iBlogService.delete(idDeleteBlog);
        redirectAttributes.addFlashAttribute("message", "Xoá bài viết thành công");
        return "redirect:/home/success";
    }
}
