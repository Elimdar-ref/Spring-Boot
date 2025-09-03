package org.skypro.skyshop.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.skypro.skyshop.model.product.Product;
import org.skypro.skyshop.model.search.SearchResult;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class SearchServiceTest {

    @Mock
    private StorageService storageService;

    @InjectMocks
    private SearchService searchService;

    @Test
    void searchByProductName() {
        when(storageService.getAllSearchable()).thenReturn(Collections.emptyList());
        List<SearchResult> result = searchService.search("Ябл");
        assertTrue(result.isEmpty());
    }

    @Test
    void searchByProductNameN() {
        UUID testUUID = UUID.randomUUID();
        Product product = mock(Product.class);

        when(product.getSearchTerm()).thenReturn("Арбуз");
        when(product.getId()).thenReturn(testUUID);
        when(storageService.getAllSearchable()).thenReturn(List.of(product));

        List<SearchResult> result = searchService.search("Арб");

        assertEquals(1, result.size());
        verify(storageService, times(1)).getAllSearchable();
    }
}