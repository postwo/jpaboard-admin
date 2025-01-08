package com.example.JpaBoard.controller;

import com.example.JpaBoard.domain.constant.FormStatus;
import com.example.JpaBoard.domain.constant.SearchType;
import com.example.JpaBoard.dto.request.ArticleRequest;
import com.example.JpaBoard.dto.response.ArticleResponse;
import com.example.JpaBoard.dto.response.ArticleWithCommentsResponse;
import com.example.JpaBoard.dto.security.BoardPrincipal;
import com.example.JpaBoard.service.ArticleService;
import com.example.JpaBoard.service.PaginationService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RequestMapping("/articles")
@Controller
public class ArticleController {

    private final ArticleService articleService;
    private final PaginationService paginationService;

    //게시글 리스트 페이지
    @GetMapping
    public String articles(
            @RequestParam(name = "searchType",required = false) SearchType searchType,
            @RequestParam(name = "searchValue",required = false) String searchValue,
            @PageableDefault(size = 10, sort = "createdAt", direction = Sort.Direction.DESC) Pageable pageable,
            ModelMap map) { // ModelMap = 컨트롤러에서 데이터를 전달하기 위한 객체로, 뷰(HTML 페이지 등)로 데이터를 넘길 때 사용

        Page<ArticleResponse> articles = articleService.searchArticles(searchType, searchValue, pageable).map(ArticleResponse::from);
        List<Integer> barNumbers = paginationService.getPaginationBarNumbers(pageable.getPageNumber(), articles.getTotalPages());

        map.addAttribute("articles", articles);
        map.addAttribute("paginationBarNumbers", barNumbers);
        map.addAttribute("searchTypes", SearchType.values());
        map.addAttribute("searchTypeHashtag", SearchType.HASHTAG);

        return "articles/index";
    }

    //상세 페이지 조회
    //@PathVariable에 파라미터 이름을 명시적으로 추가
    @GetMapping("/{articleId}")
    public String article(@PathVariable("articleId") Long articleId, ModelMap map) {
        ArticleWithCommentsResponse article = ArticleWithCommentsResponse.from(articleService.getArticleWithComments(articleId));

        map.addAttribute("article", article);
        map.addAttribute("articleComments", article.articleCommentsResponse());
        map.addAttribute("totalCount", articleService.getArticleCount());
        map.addAttribute("searchTypeHashtag", SearchType.HASHTAG);

        return "articles/detail";
    }

    @GetMapping("/search-hashtag")
    public String searchArticleHashtag(
            @RequestParam(name = "searchValue", required = false) String searchValue,
            @PageableDefault(size = 10, sort = "createdAt", direction = Sort.Direction.DESC) Pageable pageable,
            ModelMap map
    ) {
        Page<ArticleResponse> articles = articleService.searchArticlesViaHashtag(searchValue, pageable).map(ArticleResponse::from);
        List<Integer> barNumbers = paginationService.getPaginationBarNumbers(pageable.getPageNumber(), articles.getTotalPages());
        List<String> hashtags = articleService.getHashtags();

        System.out.println("==========>"+hashtags);

        map.addAttribute("articles", articles);
        map.addAttribute("hashtags", hashtags);
        map.addAttribute("paginationBarNumbers", barNumbers);
        map.addAttribute("searchType", SearchType.HASHTAG);

        return "articles/search-hashtag";
    }

    @GetMapping("/form")
    public String articleForm(ModelMap map) {
        map.addAttribute("formStatus", FormStatus.CREATE);
        return "articles/form";
    }

    @PostMapping ("/form")
    public String postNewArticle(
            @AuthenticationPrincipal BoardPrincipal boardPrincipal,
            ArticleRequest articleRequest
    ) {
        articleService.saveArticle(articleRequest.toDto(boardPrincipal.toDto()));
        return "redirect:/articles";
    }

    @GetMapping("/{articleId}/form")
    public String updateArticleForm(@PathVariable("articleId") Long articleId, ModelMap map) {
        ArticleResponse article = ArticleResponse.from(articleService.getArticle(articleId));
        map.addAttribute("article", article);
        map.addAttribute("formStatus", FormStatus.UPDATE);
        return "articles/form";
    }

    @PostMapping ("/{articleId}/form")
    public String updateArticle(
            @PathVariable("articleId") Long articleId,
            @AuthenticationPrincipal BoardPrincipal boardPrincipal,
            ArticleRequest articleRequest
    ) {
        articleService.updateArticle(articleId, articleRequest.toDto(boardPrincipal.toDto()));
        return "redirect:/articles/" + articleId;
    }

    @PostMapping("/{articleId}/delete")
    public String deleteArticle(
            @PathVariable("articleId")  Long articleId,
            @AuthenticationPrincipal BoardPrincipal boardPrincipal
    ) {
        articleService.deleteArticle(articleId, boardPrincipal.getUsername());
        return "redirect:/articles";
    }
}
