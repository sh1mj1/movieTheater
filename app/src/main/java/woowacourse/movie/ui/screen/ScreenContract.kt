package woowacourse.movie.ui.screen

import woowacourse.movie.domain.model.Screen
import woowacourse.movie.domain.model.ScreenAd
import woowacourse.movie.domain.model.Theaters

interface ScreenContract {
    interface View {
        fun showScreens(screens: List<ScreenAd>)

        fun showTheaters(
            screen: Screen,
            theaters: Theaters,
        )
    }

    interface Presenter {
        fun loadScreen()

        fun loadTheaters(screenId: Int)
    }
}
