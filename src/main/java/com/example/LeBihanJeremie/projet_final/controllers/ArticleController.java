package com.example.LeBihanJeremie.projet_final.controllers;

import com.example.LeBihanJeremie.projet_final.models.Article;
import com.example.LeBihanJeremie.projet_final.services.ArticleService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("api")
public class ArticleController {

    @Autowired
    ArticleService articleService;

    @GetMapping("/products")
    public ResponseEntity<?> getAllArticles(){
        List<Article> articles = articleService.getAllArticles();
        if(articles.isEmpty())
            return new ResponseEntity<>(
                    "No articles found",
                    HttpStatus.INTERNAL_SERVER_ERROR
            );
        return new ResponseEntity<>(
                articles,
                HttpStatus.OK
        );
    }

    @PostMapping("/products/{id}")
    public ResponseEntity<?> getArticleById(@PathVariable Long id){
        Optional<Article> article = articleService.getArticleById(id);
        if(article.isEmpty())
            return new ResponseEntity<>(
                    "No article found",
                    HttpStatus.NOT_FOUND
            );
        return new ResponseEntity<>(
                article,
                HttpStatus.OK
        );
    }

    @PostMapping("/products")
    public ResponseEntity<?> addArticle(@Valid @RequestBody Article article, BindingResult result){
        if(article == null)
            return new ResponseEntity<>(
                    "Invalid article",
                    HttpStatus.BAD_REQUEST
            );
        if(result.hasErrors())
            return new ResponseEntity<>(
                    "Invalid article",
                    HttpStatus.BAD_REQUEST
            );
        return new ResponseEntity<>(
                articleService.createArticle(article),
                HttpStatus.CREATED
        );
    }

    @PutMapping("/products/{id}")
    public ResponseEntity<?> updateArticle(@Valid @PathVariable Long id,  @RequestBody Article article, BindingResult result){
        if(article == null)
            return new ResponseEntity<>(
                    "Invalid article",
                    HttpStatus.BAD_REQUEST
            );
        if(result.hasErrors())
            return new ResponseEntity<>(
                    "Invalid article",
                    HttpStatus.BAD_REQUEST
            );
        Optional<Article> article1 = articleService.getArticleById(id);
        if(article1.isEmpty())
            return new ResponseEntity<>(
                    "No article found",
                    HttpStatus.NOT_FOUND
            );
        article1.get().setDescription(article.getDescription());
        article1.get().setPrice(article.getPrice());
        article1.get().setName(article.getName());
        article1.get().setQuantity(article.getQuantity());
        return new ResponseEntity<>(
                articleService.createArticle(article1.get()),
                HttpStatus.OK
        );
    }

    /* Pas eu le temps de test cette méthode
    @PutMapping("/products/{userId}/{id}")
    public ResponseEntity<?> updateArticle(@Valid @PathVariable Long userId, @PathVariable Long id,  @RequestBody Article article, BindingResult result){
        User user = userService.getUserById(userId).get();
        List<Article> articles = user.getStock();
        Optional<Article> articleInBDD = articleService.getArticleById(id);
        if(articleInBDD.isEmpty())
            return new ResponseEntity<>(
                    "No article found",
                    HttpStatus.NOT_FOUND
            );
        if(articles.isEmpty())
            return new ResponseEntity<>(
                    "User don't have any articles",
                    HttpStatus.BAD_REQUEST
            );
        if(result.hasErrors())
            return new ResponseEntity<>(
                    "Invalid article",
                    HttpStatus.BAD_REQUEST
            );
        articles.forEach(article1 -> {
            if(article1.getId() == articleInBDD.get().getId()){
                article1.setDescription(article.getDescription());
                article1.setPrice(article.getPrice());
                article1.setName(article.getName());
                article1.setQuantity(article.getQuantity());
                articleService.createArticle(article1);
            }
        });
        return new ResponseEntity<>(
                articleService.createArticle(articleInBDD.get()),
                HttpStatus.OK
        );
    }*/

    @DeleteMapping("/products/{id}")
    public ResponseEntity<?> deleteArticle(@PathVariable Long id){
        Optional<Article> article = articleService.getArticleById(id);
        if(article.isEmpty())
            return new ResponseEntity<>(
                    "No article found",
                    HttpStatus.NOT_FOUND
            );
        articleService.deleteArticle(article.get());
        return new ResponseEntity<>(
                "Article deleted",
                HttpStatus.OK
        );
    }

    @PostMapping("stock/entry")
    public ResponseEntity<?> addStock(@RequestBody Map<String,Integer> map){
        int productId = map.get("productId");
        int quantity = map.get("quantity");
        Optional<Article> article = articleService.getArticleById((long) productId);
        if(article.isEmpty())
            return new ResponseEntity<>(
                "No article found",
                HttpStatus.BAD_REQUEST
        );
        article.get().setQuantity(article.get().getQuantity() + quantity);
        return new ResponseEntity<>(
                articleService.createArticle(article.get()),
                HttpStatus.CREATED
        );

    }

    @PostMapping("stock/exit")
    public ResponseEntity<?> removeStock(@RequestBody Map<String,Integer> map){
        int productId = map.get("productId");
        int quantity = map.get("quantity");
        Optional<Article> article = articleService.getArticleById((long) productId);
        if(article.isEmpty())
            return new ResponseEntity<>(
                    "No article found",
                    HttpStatus.BAD_REQUEST
            );
        article.get().setQuantity(article.get().getQuantity() - quantity);
        return new ResponseEntity<>(
                articleService.createArticle(article.get()),
                HttpStatus.CREATED
        );

    }

}
