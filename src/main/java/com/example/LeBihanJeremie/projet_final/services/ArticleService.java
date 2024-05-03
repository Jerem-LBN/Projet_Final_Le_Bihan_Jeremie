package com.example.LeBihanJeremie.projet_final.services;

import com.example.LeBihanJeremie.projet_final.models.Article;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


public interface ArticleService {
    List<Article> getAllArticles();

    Optional<Article> getArticleById(Long id);

    Article createArticle(Article article);

    void deleteArticle(Article article);
}
