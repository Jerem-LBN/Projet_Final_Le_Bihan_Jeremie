package com.example.LeBihanJeremie.projet_final.repositories;

import com.example.LeBihanJeremie.projet_final.models.Article;
import org.springframework.stereotype.Repository;

@Repository
public interface ArticleRepo {
    Article save(Article article);
}
