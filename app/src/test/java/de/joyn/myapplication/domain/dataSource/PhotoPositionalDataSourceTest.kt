package de.joyn.myapplication.domain.dataSource

import de.joyn.myapplication.domain.entity.PhotoModel
import de.joyn.myapplication.domain.interactor.GetPhotoUseCase
import de.joyn.myapplication.network.dto.Models
import io.reactivex.Single
import org.junit.After
import org.junit.Before
import org.junit.Test

import org.junit.Assert.*
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

/**
* Test for [PhotoPositionalDataSource]
*/

class PhotoPositionalDataSourceTest {

    @Mock
    private lateinit var mockGetPhotoUseCase: GetPhotoUseCase


private lateinit var dataSource: PhotoPositionalDataSource

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)


        dataSource = PhotoPositionalDataSource(mockGetPhotoUseCase)
    }

    @After
    fun tearDown() {
    }

    @Test
    fun loadInitial() {

        Mockito.`when`(mockGetPhotoUseCase.execute(PhotoModel())).thenReturn(Single.just(Models.BasePhoto()))
    }
}