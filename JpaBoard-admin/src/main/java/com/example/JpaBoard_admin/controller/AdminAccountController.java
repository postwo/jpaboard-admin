package com.example.JpaBoard_admin.controller;

import com.example.JpaBoard_admin.dto.response.AdminAccountResponse;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/admin/members")
@Controller
public class AdminAccountController {

    @GetMapping
    public String members(Model model, HttpServletRequest request) {
        // requestURI를 모델에 추가 이걸 추가 안해주면 thymeleaf 문법하고 맞지 않아서 에러가 뜬다.
        model.addAttribute("currentURI", request.getRequestURI());
        return "admin/members";
    }

    @ResponseBody
    @GetMapping("/api/admin/members")
    public List<AdminAccountResponse> getMembers() {
        return List.of();
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @ResponseBody
    @DeleteMapping("/api/admin/members/{userId}")
    public void delete(@PathVariable String userId) {
    }
}
