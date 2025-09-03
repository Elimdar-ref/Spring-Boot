package org.skypro.skyshop.service;

import org.skypro.skyshop.error.NoSuchProductException;
import org.skypro.skyshop.model.article.Article;
import org.skypro.skyshop.model.product.DiscountedProduct;
import org.skypro.skyshop.model.product.FixPriceProduct;
import org.skypro.skyshop.model.product.Product;
import org.skypro.skyshop.model.product.SimpleProduct;
import org.skypro.skyshop.model.search.Searchable;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class StorageService {
    private final Map<UUID, Product> storageProduct;
    private final Map<UUID, Article> storageArticle;

    public StorageService(Map<UUID, Product> storageProduct, Map<UUID, Article> storageArticle) {
        this.storageProduct = storageProduct;
        this.storageArticle = storageArticle;
        createTest();
    }

    public Collection <Product> getAllStorageProduct() {
        return storageProduct.values();
    }

    public Collection <Article> getAllStorageArticle() {
        return storageArticle.values();
    }

    public Collection<Searchable> getAllSearchable() {
        List<Searchable> allSearchable = new ArrayList<>();
        allSearchable.addAll(storageProduct.values());
        allSearchable.addAll(storageArticle.values());
        return allSearchable;
    }

    private void createTest() {
        SimpleProduct apple = new SimpleProduct("Яблоко", 2, UUID.randomUUID());
        SimpleProduct banana = new SimpleProduct("Банан", 770, UUID.randomUUID());
        SimpleProduct orange = new SimpleProduct("Апельсин",6555, UUID.randomUUID());
        SimpleProduct grape = new SimpleProduct("Виноград", 30, UUID.randomUUID());
        SimpleProduct watermelon = new SimpleProduct("Арбуз", 300, UUID.randomUUID());
        DiscountedProduct fish = new DiscountedProduct("Рыба", 80, 10, UUID.randomUUID());
        DiscountedProduct meat = new DiscountedProduct("Мясо", 100, 20, UUID.randomUUID());
        FixPriceProduct egg = new FixPriceProduct("Яйцо", UUID.randomUUID());
        FixPriceProduct tea = new FixPriceProduct("Чай", UUID.randomUUID());
        Article article1 = new Article("Хлеб", "Состав хлеб", UUID.randomUUID());
        Article article2 = new Article("Сыр", "История создание продукта", UUID.randomUUID());
        Article article3 = new Article("Яицо", "Полезный свойства", UUID.randomUUID());

        storageProduct.put(apple.getId(), apple);
        storageProduct.put(orange.getId(), orange);
        storageProduct.put(fish.getId(), fish);
        storageProduct.put(egg.getId(), egg);
        storageArticle.put(article1.getId(), article1);
        storageArticle.put(article2.getId(), article2);
    }

    public Optional<Product> getProductById(UUID id) {
        if (id == null) {
            throw new NoSuchProductException("Нет такого продукта");
        }
        return Optional.ofNullable(storageProduct.get(id));
    }
}