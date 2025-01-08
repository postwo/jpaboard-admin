package com.example.JpaBoard.repository.querydsl;

import com.example.JpaBoard.domain.Article;
import com.example.JpaBoard.domain.QArticle;
import com.example.JpaBoard.domain.QHashtag;
import com.querydsl.jpa.JPQLQuery;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

import java.util.Collection;
import java.util.List;

public class ArticleRepositoryCustomImpl extends QuerydslRepositorySupport implements ArticleRepositoryCustom {

    public ArticleRepositoryCustomImpl() {
        super(Article.class);  // 1.부모 클래스인 QuerydslRepositorySupport에 Article 클래스를 전달하는 것 //2. 이 구현체가 Article 엔티티와 관련된 일을 할 거야"라고 부모 클래스에게 알려주는 역할
    }

    @Override
    public List<String> findAllDistinctHashtags() {

        QArticle article = QArticle.article;

        return from(article)
                .distinct()
                .select(article.hashtags.any().hashtagName)
                .fetch();
    }

    @Override
    public Page<Article> findByHashtagNames(Collection<String> hashtagNames, Pageable pageable) {
        QHashtag hashtag = QHashtag.hashtag;
        QArticle article = QArticle.article;
        JPQLQuery<Article> query = from(article)
                .innerJoin(article.hashtags, hashtag)
                .where(hashtag.hashtagName.in(hashtagNames));
        List<Article> articles = getQuerydsl().applyPagination(pageable, query).fetch();
        return new PageImpl<>(articles, pageable, query.fetchCount());
    }
}