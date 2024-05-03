package com.example.LeBihanJeremie.projet_final.repositories;

import com.example.LeBihanJeremie.projet_final.models.Article;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ArticleRepo extends JpaRepository<Article, Long> {
    Article save(Article article);

    List<Article> findAll();
}
