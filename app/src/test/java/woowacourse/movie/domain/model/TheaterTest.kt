package woowacourse.movie.domain.model

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import woowacourse.movie.domain.repository.FakeScreenRepository

class TheaterTest {
    @Test
    fun `상영작이 영화관에서 상영하는지 판단`() {
        val theater =
            Theater(
                1,
                "선릉 극장",
                listOf(FakeScreenRepository.fakeScreen1, FakeScreenRepository.fakeScreen2, FakeScreenRepository.fakeScreen3),
            )

        val screen = FakeScreenRepository.fakeScreen1
        val actual = theater.isScreening(screen)

        assertThat(actual).isTrue()
    }
}
