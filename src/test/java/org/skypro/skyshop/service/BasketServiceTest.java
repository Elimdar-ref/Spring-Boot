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
import java.util.stream.Collector;
import java.util.stream.Collectors;

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
        UserBasket basket1 = basketService.getUserBasket();
        assertTrue(basket1.getItems().isEmpty());
    }

    @Test
    void getUserBasket_WhenBasketIsNotEmpty() {
        UUID id = UUID.randomUUID();
        Product product = new SimpleProduct("Яблоко", 2, id);
        when(basket.getBasket()).thenReturn(Map.of(id, 3));
        when(storageService.getProductById(id)).thenReturn(Optional.of(product));

        UserBasket basket1 = basketService.getUserBasket();

        assertEquals(1, basket1.getItems().size());

        BasketItem item = basket1.getItems().get(0);

        assertEquals("Яблоко", item.getProduct().getProductName());
        assertEquals(2, item.getProduct().getPrice());
        assertEquals(id, item.getProduct().getId());
        assertEquals(3, item.getQuantity());
    }
}