package org.skypro.skyshop.model.article;

import org.skypro.skyshop.model.search.Searchable;

import java.util.Objects;
import java.util.UUID;

public final class Article implements Searchable {

    public String articleTitle;
    public String textTitle;
    private final UUID id;

    public Article(String articleTitle, String textTitle, UUID id) {
        this.articleTitle = articleTitle;
        this.textTitle = textTitle;
        this.id = id;
    }

    public String getArticleTitle() {
        return articleTitle;
    }

    public String getTextTitle() {
        return textTitle;
    }

    @Override
    public String toString() {
        return "Название статьи: " + articleTitle + " / " + "Текст статьи " + textTitle;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Article article = (Article) o;
        return Objects.equals(articleTitle, article.articleTitle);
    }

    @Override
    public int hashCode() {
        return Objects.hash(articleTitle);
    }

    @Override
    public UUID getId() {
        return id;
    }

    @Override
    public String getSearchTerm() {
        return toString();
    }

    @Override
    public String getContentType() {
        return "Article";
    }

    @Override
    public String getName() {
        return toString();
    }
}