package racingcar.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.List;
import org.assertj.core.util.Lists;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import racingcar.utils.TestNumberGenerator;

public class CarsTest {

    private Cars cars;

    @BeforeEach
    void setUp() {
        cars = new Cars(List.of("car1", "car2", "car3"));
    }

    @Test
    @DisplayName("생성자는 중복된 이름이 존재하는 목록을 입력받으면 예외를 던진다.")
    void should_throwException_when_inputDuplicatedNames() {
        List<String> duplicatedNames = List.of("car1", "car1");

        assertThatThrownBy(() -> new Cars(duplicatedNames))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("중복된 차 이름이 없어야 합니다.");
    }

    @Test
    @DisplayName("move 메서드는 자동차 경주를 1회 진행한다.")
    void should_playRacingGame_when_move() {
        NumberGenerator numberGenerator = new TestNumberGenerator(Lists.newArrayList(4, 3, 5));

        cars.move(numberGenerator);

        assertThat(cars.getCars())
                .extracting(Car::getPosition)
                .containsExactly(1, 0, 1);
    }

    @Test
    @DisplayName("findWinners 메서드는 우승자 이름 목록을 반환한다.")
    void should_returnWinnersName_when_findWinners() {
        NumberGenerator numberGenerator = new TestNumberGenerator(Lists.newArrayList(4, 3, 5));
        cars.move(numberGenerator);

        List<String> result = cars.findWinners();

        assertThat(result).containsExactly("car1", "car3");
    }

    @Test
    @DisplayName("findWinners 메서드는 우승자가 존재하지 않는 경우 예외를 던진다.")
    void should_throwException_when_emptyCars() {
        Cars emptyCars = new Cars(List.of());

        assertThatThrownBy(emptyCars::findWinners)
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("우승자가 존재하지 않습니다.");
    }
}
