package woowacourse.movie.ui.screen

import woowacourse.movie.domain.repository.DummyAdvertisement
import woowacourse.movie.domain.repository.DummyTheaters
import woowacourse.movie.domain.repository.MovieRepository
import woowacourse.movie.domain.repository.ScreenRepository
import woowacourse.movie.domain.repository.TheaterRepository
import woowacourse.movie.ui.toPreviewUI

class ScreenPresenter(
    private val view: ScreenContract.View,
    private val movieRepository: MovieRepository,
    private val screenRepository: ScreenRepository,
    private val adRepository: DummyAdvertisement = DummyAdvertisement(),
    private val theaterRepository: TheaterRepository = DummyTheaters(),
) : ScreenContract.Presenter {
    override fun loadScreen() {
        val screens =
            screenRepository.load()
                .map { screen -> screen.toPreviewUI(image = movieRepository.imageSrc(screen.movie.id)) }

        val ad = adRepository.load()

        view.showScreens(screens + ad)
    }

    override fun loadTheaters(screenId: Int) {
        val screen = screenRepository.findById(screenId).getOrThrow()
        val theaters = theaterRepository.loadAll()

        view.showTheaters(screen, theaters.screeningTheater(screen))
    }
}
