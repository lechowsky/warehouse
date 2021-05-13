package com.org.warehouse.repository;

import static org.mockito.Mockito.*;
import static org.wildfly.common.Assert.assertNotNull;
import static org.wildfly.common.Assert.assertTrue;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.org.warehouse.business.LoadDataResult;
import com.org.warehouse.controller.dto.ProductDTO;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class LoadDataTest {

    public static final String PATH = "src/test/resources";

    private LoadDataFromSystem sut;
    private LoadDataProperties properties;

    private static final ObjectMapper mapper = new ObjectMapper();
    @BeforeEach
    public void setUp() {
        properties = mock(LoadDataProperties.class);
        sut = new LoadDataFromSystem(properties);
    }


    @Test
    public void loadDataOkTest(){
        when(properties.getLocation()).thenReturn(PATH);
        LoadDataResult result = sut.load(DataTypes.PRODUCTS);
        assertNotNull(result);
        ProductDTO mappedResult = mapResult(result.getResult(), ProductDTO.class);
        assertTrue(mappedResult.getProducts().size() == 2);
    }

    @Test
    public void loadDataKoTest(){
        when(properties.getLocation()).thenReturn(null);
        Assertions.assertThrows(RuntimeException.class,
            ()->sut.load(DataTypes.PRODUCTS));
    }

    private <T> T mapResult(String result, Class<T> clazz){
        try {
            return mapper.readValue(result, clazz);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
}