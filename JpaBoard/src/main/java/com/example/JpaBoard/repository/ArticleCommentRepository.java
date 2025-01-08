package com.example.JpaBoard.repository;

import com.example.JpaBoard.domain.ArticleComment;
import com.example.JpaBoard.domain.QArticleComment;
import com.example.JpaBoard.domain.projection.ArticleCommentProjection;
import com.querydsl.core.types.dsl.DateTimeExpression;
import com.querydsl.core.types.dsl.StringExpression;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.querydsl.binding.QuerydslBinderCustomizer;
import org.springframework.data.querydsl.binding.QuerydslBindings;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

//댓글
@RepositoryRestResource(excerptProjection = ArticleCommentProjection.class)
public interface ArticleCommentRepository extends
        JpaRepository<ArticleComment,Long>,
        QuerydslPredicateExecutor<ArticleComment>,
        QuerydslBinderCustomizer<QArticleComment> {

    List<ArticleComment> findByArticle_Id(Long articleId);

    void deleteByIdAndUserAccount_UserId(Long articleCommentId, String userId);

    // customize를 통해 검색에 대한 세부적인 규칙을 다시 정할수 있다
    // 인터페이스라 원래 구현을 못하는데 java8 이후부터 구현 할 수 있게 되었다
    // like,likeignoreCase 와 containsIgnoreCase 차이점은 쿼리문이다르다  likeignoreCase(like '${v}'), containsIgnoreCase(like '%S{v}%')
    // 결론적으로 like,likeignoreCase는 부분검색을 할려면 내가 따로 %를 추가해야한다 하지만 containsIgnoreCase 알아서 추가가 되어있기 떄문에 필요없다
    // first는 createdAt 검색 파라미터를 한개만 받는다는 뜻이다
    @Override
    default void customize(QuerydslBindings bindings, QArticleComment root) {
        bindings.excludeUnlistedProperties(true); //기본값은 false이다  //QuerydslPredicateExecutor을 통해서 모든 필드들이 열려있어서 다 검색이 가능하기 때문에 이걸통해서 선택적으로만 검색할 수 있게 할 수 있다
        bindings.including(root.content, root.createdAt, root.createdBy); //원하는 필드들을 추가한다
        bindings.bind(root.content).first(StringExpression::containsIgnoreCase); //containsIgnoreCase = 이거는 대소문자를 구분을 안한다는 뜻이다
        bindings.bind(root.createdAt).first(DateTimeExpression::eq);// DateTimeExpression은 eq하고 동일한 역할을 한다는 뜻이다
        bindings.bind(root.createdBy).first(StringExpression::containsIgnoreCase);
    }
}
