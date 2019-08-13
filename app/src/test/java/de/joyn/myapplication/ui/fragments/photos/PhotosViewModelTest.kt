package de.joyn.myapplication.ui.fragments.photos

import de.joyn.myapplication.domain.dataSource.PhotoDataSourceFactory
import de.joyn.myapplication.domain.dataSource.PhotoPositionalDataSource
import de.joyn.myapplication.domain.entity.PhotoModel
import de.joyn.myapplication.domain.interactor.GetPhotoUseCase
import de.joyn.myapplication.network.dto.Models
import io.reactivex.Single
import io.reactivex.observers.TestObserver
import org.junit.After
import org.junit.Before
import org.junit.Test

import org.junit.Assert.*
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

/**
 * Unit test for [PhotosViewModel].
 */
class PhotosViewModelTest {

    @Mock
    private lateinit var mockGetPhotoUseCase: GetPhotoUseCase

    private lateinit var mockDataSourceFactory: PhotoDataSourceFactory
    private lateinit var dataSource: PhotoPositionalDataSource
    private lateinit var photosViewModel: PhotosViewModel;

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        dataSource = PhotoPositionalDataSource(mockGetPhotoUseCase)
        mockDataSourceFactory = PhotoDataSourceFactory(dataSource)
        photosViewModel = PhotosViewModel(mockDataSourceFactory)
    }

    @After
    fun tearDown() {
    }

    @Test
    fun createLiveData() {

        Mockito.`when`(mockGetPhotoUseCase.execute(PhotoModel())).thenReturn(Single.just(Models.BasePhoto()))
        val testObserver = TestObserver<Models.BasePhoto>()

        photosViewModel.createLiveData()

        testObserver.assertNoErrors()
       // testObserver.assertValue { }
    }
}