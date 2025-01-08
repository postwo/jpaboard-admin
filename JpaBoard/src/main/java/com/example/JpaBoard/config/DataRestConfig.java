package com.example.JpaBoard.config;

import com.example.JpaBoard.domain.Article;
import com.example.JpaBoard.domain.ArticleComment;
import com.example.JpaBoard.domain.Hashtag;
import com.example.JpaBoard.domain.UserAccount;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurer;

@Configuration
public class DataRestConfig {

    @Bean
    public RepositoryRestConfigurer repositoryRestConfigurer() {
        return RepositoryRestConfigurer.withConfig((config, cors) ->
                config
                        .exposeIdsFor(UserAccount.class)
                        .exposeIdsFor(Article.class)
                        .exposeIdsFor(ArticleComment.class)
                        .exposeIdsFor(Hashtag.class)
        );
    }

}
