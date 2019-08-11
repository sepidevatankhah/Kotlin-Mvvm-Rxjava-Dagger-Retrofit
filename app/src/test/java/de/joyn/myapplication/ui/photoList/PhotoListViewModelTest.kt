package de.joyn.myapplication.ui.photoList

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.nhaarman.mockito_kotlin.any
import com.nhaarman.mockito_kotlin.times
import com.nhaarman.mockito_kotlin.verify
import com.nhaarman.mockito_kotlin.whenever
import de.joyn.myapplication.RxImmediateSchedulerRule
import de.joyn.myapplication.domain.executer.PostExecutionThread
import de.joyn.myapplication.domain.executer.UseCaseExecutor
import de.joyn.myapplication.domain.interactor.GetPhotoUseCase
import de.joyn.myapplication.domain.repository.Repository
import de.joyn.myapplication.network.dto.Models
import io.reactivex.Single
import org.junit.*
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import org.mockito.junit.MockitoJUnit
import org.junit.Rule



//class PhotoListViewModelTest@Inject constructor(private val getFlowerUseCase: GetPhotoUseCase) :
//    BaseViewModel<PhotoListViewState>() {
class PhotoListViewModelTest {

    @Rule
    var mockitoRule = MockitoJUnit.rule()

    @Rule
    @JvmField
    var testSchedulerRule = RxImmediateSchedulerRule()

    @Rule
    @JvmField
    val ruleForLivaData = InstantTaskExecutorRule()

    //@Mock
    //lateinit var mockLiveDataObserver: Observer<PhotoListViewState>

    @Mock
    lateinit var mockDataRepository: Repository
    lateinit var postExecutionThread: PostExecutionThread
    lateinit var useCaseExecutor: UseCaseExecutor
    @Mock
    lateinit var mockPhotoUseCase: GetPhotoUseCase


    //lateinit var myViewModel: PhotoListViewModel
    lateinit var flowerResult: List<Models.PhotoResponse>


    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        //myViewModel = PhotoListViewModel(mockPhotoUseCase)
        mockPhotoUseCase = GetPhotoUseCase(useCaseExecutor, postExecutionThread, mockDataRepository)
    }

    @Test
    fun `Given DataRepository returns data, when getPhotos() called, then update live data`() {
        //Setting how up the mock behaves
        //whenever(mockPhotoUseCase.execute("flowers")).thenReturn(Single.just(flowerResult))

        //Fire the test method
       // myViewModel.getPhotos("flowers")

        //Check that our live data is updated
       // Assert.assertEquals("Data", myViewModel.stateLiveData.value)
    }

    @After
    fun tearDown() {
    }

    @Test
    fun `Given DataRepository returns error, when getPhotos() called, then do not change live data`() {
        //Setting how up the mock behaves
       // whenever(mockPhotoUseCase.execute("flowers")).thenReturn(Single.error(Throwable()))

//        myViewModel.stateLiveData.observeForever(mockLiveDataObserver)
//
//        //Fire the test method
//        myViewModel.getPhotos("flowers")
//
//        verify(mockLiveDataObserver, times(0)).onChanged(any())
    }
}