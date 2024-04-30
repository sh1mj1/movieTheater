package woowacourse.movie.ui.screen

import io.mockk.every
import io.mockk.just
import io.mockk.mockk
import io.mockk.runs
import io.mockk.verify
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import woowacourse.movie.domain.repository.FakeMovieRepository
import woowacourse.movie.domain.repository.FakeScreenRepository

class ScreenPresenterTest {
    private lateinit var view: ScreenContract.View
    private lateinit var presenter: ScreenContract.Presenter

    @BeforeEach
    fun setUp() {
        view = mockk<ScreenContract.View>()
        presenter =
            ScreenPresenter(
                view = view,
                movieRepository = FakeMovieRepository(),
                screenRepository = FakeScreenRepository(),
            )
    }

    @Test
    fun `영화들을 보여준다`() {
        every { view.showScreens(any()) } just runs

        presenter.loadScreen()

        verify { view.showScreens(any()) }
    }

    @Test
    fun `영화를 상영하는 상영관들을 보여준다`() {
        every { view.showTheaters(any(), any()) } just runs

        presenter.loadTheaters(1)

        verify { view.showTheaters(any(), any()) }
    }
}
