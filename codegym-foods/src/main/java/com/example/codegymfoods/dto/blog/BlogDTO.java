package com.example.codegymfoods.dto.blog;

import com.example.codegymfoods.model.employee.Employee;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

public class BlogDTO {
    private Integer id;
    @Size(max = 40,min = 1,message = "Ít hơn 40 kí tự")
    @NotEmpty(message = "Không thể để trống")
    private String title;
    @NotBlank(message = "Không thể để trống")
    private String dateNow;
    @NotBlank(message = "Không thể để trống")
    @Size(max = 255,min = 1,message = "Ít hơn 255 kí tự")
    private String imgUrl;
    @NotBlank(message = "Không thể để trống")
    private String content;
    private Employee employee;

    public BlogDTO() {
    }

    public BlogDTO(Integer id, String title, String dateNow, String imgUrl, String content, Employee employee) {
        this.id = id;
        this.title = title;
        this.dateNow = dateNow;
        this.imgUrl = imgUrl;
        this.content = content;
        this.employee = employee;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDateNow() {
        return dateNow;
    }

    public void setDateNow(String dateNow) {
        this.dateNow = dateNow;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }
}
