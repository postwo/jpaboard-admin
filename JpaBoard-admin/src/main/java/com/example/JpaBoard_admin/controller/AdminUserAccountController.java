package com.example.JpaBoard_admin.controller;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/admin/members")
@Controller
public class AdminUserAccountController {
    @GetMapping
    public String members(
            @PageableDefault(size = 10, sort = "createdAt", direction = Sort.Direction.DESC) Pageable pageable,
            Model model, HttpServletRequest request
    ) {
        // requestURI를 모델에 추가
        model.addAttribute("currentURI", request.getRequestURI());
        return "admin/members";
    }
}
