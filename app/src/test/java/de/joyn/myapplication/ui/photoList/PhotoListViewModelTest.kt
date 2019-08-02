package de.joyn.myapplication.ui.photoList

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.nhaarman.mockito_kotlin.any
import com.nhaarman.mockito_kotlin.times
import com.nhaarman.mockito_kotlin.verify
import com.nhaarman.mockito_kotlin.whenever
import de.joyn.myapplication.RxImmediateSchedulerRule
import de.joyn.myapplication.domain.interactor.GetPhotoUseCase
import de.joyn.myapplication.domain.repository.Repository
import de.joyn.myapplication.network.dto.Models
import io.reactivex.Single
import org.junit.*
import org.mockito.Mock
import org.mockito.MockitoAnnotations

//class PhotoListViewModelTest@Inject constructor(private val getFlowerUseCase: GetPhotoUseCase) :
//    BaseViewModel<PhotoListViewState>() {
class PhotoListViewModelTest  {


    @Rule
    @JvmField
    var testSchedulerRule = RxImmediateSchedulerRule()

    @Rule
    @JvmField
    val ruleForLivaData = InstantTaskExecutorRule()

    @Mock
    lateinit var mockLiveDataObserver: Observer<PhotoListViewState>

    @Mock
    lateinit var mockDataRepository: Repository
    @Mock
    lateinit var mockPhotoUseCase: GetPhotoUseCase


    lateinit var myViewModel: PhotoListViewModel
    lateinit var flowerResult : List<Models.FlowerResponse>



    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        myViewModel = PhotoListViewModel(mockPhotoUseCase)
    }

    @Test
    fun `Given DataRepository returns data, when getPhotos() called, then update live data`() {
        //Setting how up the mock behaves
        whenever(mockPhotoUseCase.execute("flowers")).thenReturn(Single.just(flowerResult))

        //Fire the test method
        myViewModel.getPhotos("flowers")

        //Check that our live data is updated
        Assert.assertEquals("Data", myViewModel.stateLiveData.value)
    }

    @After
    fun tearDown() {
    }

    @Test
    fun `Given DataRepository returns error, when getPhotos() called, then do not change live data`() {
        //Setting how up the mock behaves
        whenever(mockPhotoUseCase.execute("flowers")).thenReturn(Single.error(Throwable()))

        myViewModel.stateLiveData.observeForever(mockLiveDataObserver)

        //Fire the test method
        myViewModel.getPhotos("flowers")

        verify(mockLiveDataObserver, times(0)).onChanged(any())
    }
}