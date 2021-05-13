package com.org.warehouse.business;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.org.warehouse.repository.DataTypes;
import com.org.warehouse.repository.LoadData;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class LoaderImplTest {

    private LoaderImpl sut;

    private LoadData loadDataMock;
    private LoadCommandFactory factoryMock;
    private LoadCommand command;

    @BeforeEach
    void setUp() {
        loadDataMock = mock(LoadData.class);
        factoryMock =  mock(LoadCommandFactory.class);
        command = mock(LoadCommand.class);
        sut =  new LoaderImpl(loadDataMock, factoryMock);
    }

    @Test
    void load() {
        LoadDataResult loadedData =  LoadDataResult.builder().result("").build();

        when(loadDataMock.load(DataTypes.ARTICLES)).thenReturn(loadedData);
        when(factoryMock.get(DataTypes.ARTICLES)).thenReturn(command);
        doNothing().when(command).execute(DataTypes.ARTICLES, "");
        sut.load(DataTypes.ARTICLES);

        verify(loadDataMock).load(DataTypes.ARTICLES);
        verify(factoryMock).get(DataTypes.ARTICLES);
        verify(command).execute(DataTypes.ARTICLES, "");

    }
}