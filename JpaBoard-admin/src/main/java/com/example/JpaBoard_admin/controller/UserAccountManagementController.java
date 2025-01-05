package com.example.JpaBoard_admin.controller;

import com.example.JpaBoard_admin.service.UserAccountManagementService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RequestMapping("/management/user-accounts")
@Controller
public class UserAccountManagementController {

    private final UserAccountManagementService userAccountManagementService;

    @GetMapping
    public String userAccounts(
            Model model, HttpServletRequest request
    ) {
        model.addAttribute(
                "userAccounts",
                userAccountManagementService.getUserAccounts().stream().map(UserAccountResponse::from).toList()
        );

        // requestURI를 모델에 추가
        model.addAttribute("currentURI", request.getRequestURI());

        return "management/user-accounts";
    }

    @ResponseBody
    @GetMapping("/{userId}")
    public UserAccountResponse userAccount(@PathVariable String userId) {
        return UserAccountResponse.from(userAccountManagementService.getUserAccount(userId));
    }

    @PostMapping("/{userId}")
    public String deleteUserAccount(@PathVariable String userId) {
        userAccountManagementService.deleteUserAccount(userId);
        return "redirect:/management/user-accounts";
    }
}