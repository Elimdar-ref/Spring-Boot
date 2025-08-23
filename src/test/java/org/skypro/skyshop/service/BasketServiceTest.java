package org.skypro.skyshop.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.skypro.skyshop.error.NoSuchProductException;
import org.skypro.skyshop.model.basket.BasketItem;
import org.skypro.skyshop.model.basket.ProductBasket;
import org.skypro.skyshop.model.basket.UserBasket;
import org.skypro.skyshop.model.product.Product;
import org.skypro.skyshop.model.product.SimpleProduct;
import org.skypro.skyshop.model.search.SearchResult;
import org.skypro.skyshop.model.search.Searchable;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class BasketServiceTest {

    @Mock
    private ProductBasket basket;

    @Mock
    private StorageService storageService;

    @InjectMocks
    private BasketService basketService;

    @Test
    void addProduct_WhenBasketIsNotEmpty_thenGetUserBasket() {
        UUID testUUID = UUID.randomUUID();
        when(storageService.getProductById(testUUID))
                .thenReturn(Optional.empty());

        assertThrows(NoSuchProductException.class, () -> basketService.addProduct(testUUID));
    }

    @Test
    void addProduct_WhenBasketIsEmpty() {
        UUID testUUID = UUID.randomUUID();
        Product product = Mockito.mock(Product.class);
        when(storageService.getProductById(testUUID))
                .thenReturn(Optional.of(product));

        basketService.addProduct(testUUID);

        verify(basket, times(1)).addProduct(testUUID);
    }

    @Test
    void getUserBasket_WhenBasketIsEmpty() {
        when(basket.getBasket()).thenReturn(Collections.emptyMap());
        assertTrue(basket.getBasket().isEmpty());
    }

    @Test
    void getUserBasket_WhenBasketIsNotEmpty() {
        UUID id = UUID.randomUUID();
        Product product = mock(Product.class);
        when(product.getPrice()).thenReturn(30);
        when(basket.getBasket()).thenReturn(Map.of(id, 2));
        when(storageService.getAllSearchable()).thenReturn(Map.of(id, product));

        UserBasket basket1 = basketService.getUserBasket();
        List<BasketItem> items = basket.getBasket();
        assertEquals(1, items.size());
        assertEquals(200, basket1.getTotal());
    }
}