package com.example.LeBihanJeremie.projet_final.implementations;

import com.example.LeBihanJeremie.projet_final.models.Article;
import com.example.LeBihanJeremie.projet_final.repositories.ArticleRepo;
import com.example.LeBihanJeremie.projet_final.services.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ArticleImplem implements ArticleService {
    @Autowired
    ArticleRepo articleRepo;
    @Override
    public List<Article> getAllArticles() {
        return articleRepo.findAll();
    }

    @Override
    public Optional<Article> getArticleById(Long id) {
        return articleRepo.findById(id);
    }

    @Override
    public Article createArticle(Article article) {
        return articleRepo.save(article);
    }

    @Override
    public void deleteArticle(Article article) {
        articleRepo.delete(article);
    }
}
