package woowacourse.movie.ui.screen

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import woowacourse.movie.R
import woowacourse.movie.databinding.ActivityMainBinding
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
    private val binding: ActivityMainBinding by lazy { DataBindingUtil.setContentView(this, R.layout.activity_main) }

    private val screenPresenter: ScreenContract.Presenter by lazy {
        ScreenPresenter(
            this,
            DummyMovies(),
            DummyScreens(),
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initAdapter()
        screenPresenter.loadScreen()
    }

    private fun initAdapter() {
        adapter =
            ScreenAdapter(
                { screenId -> screenPresenter.loadTheaters(screenId) },
                { adId -> Toast.makeText(this, "광고 클릭 id: $adId", Toast.LENGTH_SHORT).show() },
            )
        binding.adapter = adapter
    }

    override fun showScreens(screens: List<ScreenAd>) {
        adapter.submitList(screens)
    }

    override fun showTheaters(
        screen: Screen,
        theaters: Theaters,
    ) {
        val theaterAdapter =
            TheaterAdapter(screen) { screenId, theaterId ->
                ScreenDetailActivity.startActivity(this, screenId, theaterId)
            }
        val theaterBottomSheet = TheaterBottomSheet(theaterAdapter)

        theaterAdapter.submitList(theaters.theaters)
        theaterBottomSheet.show(supportFragmentManager, "theaterBottomSheet")
    }
}
