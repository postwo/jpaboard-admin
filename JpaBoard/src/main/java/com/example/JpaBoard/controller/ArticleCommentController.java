package com.example.JpaBoard.controller;

import com.example.JpaBoard.dto.request.ArticleCommentRequest;
import com.example.JpaBoard.dto.security.BoardPrincipal;
import com.example.JpaBoard.service.ArticleCommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RequiredArgsConstructor
@RequestMapping("/comments")
@Controller
public class ArticleCommentController {

    private final ArticleCommentService articleCommentService;

    @PostMapping("/new")
    public String postNewArticleComment(
            @AuthenticationPrincipal BoardPrincipal boardPrincipal,
            ArticleCommentRequest articleCommentRequest
    ) {
        articleCommentService.saveArticleComment(articleCommentRequest.toDto(boardPrincipal.toDto()));
        return "redirect:/articles/" + articleCommentRequest.articleId();
    }

    @PostMapping ("/{commentId}/delete")
    public String deleteArticleComment(@PathVariable("commentId") Long commentId,@AuthenticationPrincipal BoardPrincipal boardPrincipal
            ,@RequestParam("articleId") Long articleId) {
        articleCommentService.deleteArticleComment(commentId, boardPrincipal.getUsername());
        return "redirect:/articles/" + articleId;
    }
}
