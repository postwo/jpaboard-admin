    package com.example.JpaBoard.domain;

    import jakarta.persistence.*;
    import lombok.Getter;
    import lombok.Setter;
    import lombok.ToString;

    import java.util.Collection;
    import java.util.LinkedHashSet;
    import java.util.Objects;
    import java.util.Set;

    @Getter
    @ToString(callSuper = true) //callSuper = true는 AuditingFields에 있는거까지 찍겠다는 의미이다
    @Table(indexes = {
            @Index(columnList = "title"),
            @Index(columnList = "createdAt"),
            @Index(columnList = "createdBy")
    })
    @Entity
    public class Article extends AuditingFields{

        //id에는 setter를 설정 안하는이유는 내가 설정하는게 아니라 jpa에서만 설정 가능하게 해주기 위해
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        @Setter @ManyToOne(optional = false) @JoinColumn(name = "userId") private UserAccount userAccount; // 유저 정보 (ID)

        @Setter @Column(nullable = false) private String title; // 제목

        @Setter @Column(nullable = false, length = 10000) private String content; // 본문

        @ToString.Exclude
        @JoinTable(
                name = "article_hashtag",
                joinColumns = @JoinColumn(name = "articleId"),
                inverseJoinColumns = @JoinColumn(name = "hashtagId")
        )

        @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
        private Set<Hashtag> hashtags = new LinkedHashSet<>();

        /* 이걸 건 이유는 순환 참조를 막기위해서다(tostring을 끊어 버린다) == 순환참조란 Article entity에서 Tostring이 있는데
        여기 entity에서도 tostring이 있기 때문에 계속 무한 참조가 되는 현상을 말한다.*/
        @ToString.Exclude
        @OrderBy("createdAt DESC")
        @OneToMany(mappedBy = "article", cascade = CascadeType.ALL)
        private final Set<ArticleComment> articleComments = new LinkedHashSet<>();


        protected Article() {}

        private Article(UserAccount userAccount, String title, String content) {
            this.userAccount = userAccount;
            this.title = title;
            this.content = content;
        }

        public static Article of(UserAccount userAccount, String title, String content) {
            return new Article(userAccount, title, content);
        }

        public void addHashtag(Hashtag hashtag) {
            this.getHashtags().add(hashtag);
        }

        public void addHashtags(Collection<Hashtag> hashtags) {
            this.getHashtags().addAll(hashtags);
        }

        public void clearHashtags() {
            this.getHashtags().clear();
        }

        // equals and hashcode는 어노테이션으로도 처리 할 수 있지만 이렇게 직접 만든 이유는 전부다 동등성을 비교할필요없이 유니크한 id값으로만 비교해보면 되기 때문이다.
        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof Article that)) return false;
            return this.getId() != null && this.getId().equals(that.getId());
        }

        @Override
        public int hashCode() {
            return Objects.hash(this.getId());
        }

    }
