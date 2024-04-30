package woowacourse.movie.ui.screen

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import woowacourse.movie.R
import woowacourse.movie.domain.model.Screen
import woowacourse.movie.domain.model.ScreenAd
import woowacourse.movie.domain.model.Theaters
import woowacourse.movie.domain.repository.DummyMovies
import woowacourse.movie.domain.repository.DummyScreens
import woowacourse.movie.ui.detail.ScreenDetailActivity
import woowacourse.movie.ui.screen.adapter.ScreenAdapter
import woowacourse.movie.ui.screen.adapter.TheaterAdapter

class ScreenActivity : AppCompatActivity(), ScreenContract.View {
    private lateinit var adapter: ScreenAdapter

    private val screenPresenter: ScreenContract.Presenter by lazy {
        ScreenPresenter(
            this,
            DummyMovies(),
            DummyScreens(),
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initAdapter()
        screenPresenter.loadScreen()
    }

    private fun initAdapter() {
        val recyclerView = findViewById<RecyclerView>(R.id.lv_screen)
        adapter =
            ScreenAdapter { screenId ->
                screenPresenter.loadTheaters(screenId)
            }
        recyclerView.adapter = adapter
    }

    override fun showScreens(screens: List<ScreenAd>) {
        adapter.submitList(screens)
    }

    override fun showTheaters(
        screen: Screen,
        theaters: Theaters,
    ) {
        val theaterAdapter =
            TheaterAdapter(screen) {
                ScreenDetailActivity.startActivity(this, screen.id)
            }
        val theaterBottomSheet = TheaterBottomSheet(theaterAdapter)

        theaterAdapter.submitList(theaters.theaters)
        theaterBottomSheet.show(supportFragmentManager, "theaterBottomSheet")
    }
}
