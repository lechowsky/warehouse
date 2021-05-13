package com.org.warehouse.business;

import static org.junit.jupiter.api.Assertions.*;

import com.org.warehouse.repository.DataTypes;
import io.quarkus.test.Mock;
import java.util.stream.Stream;
import javax.enterprise.inject.Instance;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

public class LoadCommandFactoryImplTest {

    private Instance<LoadCommand> mockLoadCommand;

    @BeforeEach
    public  void init(){
        mockLoadCommand = Mockito.mock(Instance.class);
    }
    @Test
    void testGet() {
        LoadCommandFactoryImpl sut = new LoadCommandFactoryImpl();
        sut.loadCommands = mockLoadCommand;
        Mockito.when(mockLoadCommand.stream()).thenReturn(Stream.<LoadCommand>builder().add(new LoadCommandProducts(null, null)).build());

        assertNotNull(sut.get(DataTypes.PRODUCTS));

    }
}