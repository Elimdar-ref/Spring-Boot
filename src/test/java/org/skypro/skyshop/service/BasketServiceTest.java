package org.skypro.skyshop.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.skypro.skyshop.error.NoSuchProductException;
import org.skypro.skyshop.model.basket.ProductBasket;
import org.skypro.skyshop.model.product.Product;

import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertThrows;
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
}
