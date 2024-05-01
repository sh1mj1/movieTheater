package woowacourse.movie.ui

import android.widget.TextView
import androidx.databinding.BindingAdapter
import woowacourse.movie.domain.model.DateRange
import woowacourse.movie.domain.model.Image
import woowacourse.movie.domain.model.Movie
import woowacourse.movie.domain.model.Screen
import woowacourse.movie.domain.model.ScreenAd
import woowacourse.movie.domain.model.Seat
import woowacourse.movie.domain.model.Seats
import java.util.Locale

fun Screen.toPreviewUI(image: Image<Any>) =
    ScreenAd.ScreenPreviewUi(
        id = id,
        moviePreviewUI = movie.toPreviewUI(image),
        dateRange = dateRange,
    )

fun Movie.toPreviewUI(image: Image<Any>) =
    MoviePreviewUI(
        title = title,
        image = image,
        runningTime = runningTime,
    )

fun Screen.toDetailUI(image: Image<Any>) =
    ScreenDetailUI(
        id = id,
        movieDetailUI =
            MovieDetailUI(
                title = movie.title,
                runningTime = movie.runningTime,
                description = movie.description,
                image = image,
            ),
        dateRange = dateRange,
    )

fun DateRange.toUi(): String = "$start ~ $endInclusive"

@BindingAdapter("app:textFormat")
fun textUiFormat(
    textView: TextView,
    seats: List<Seat>,
) {
    textView.text =
        seats.joinToString(
            separator = ",",
            postfix = " |",
        ) { "${'A' + it.position.row}${it.position.col + 1}" }
}

@BindingAdapter("app:currency")
fun currency(
    textView: TextView,
    seats: Seats,
) {
    textView.text = String.format("%s(현장 결제)", Currency.of(Locale.getDefault().country).format(seats.totalPrice()))
}
