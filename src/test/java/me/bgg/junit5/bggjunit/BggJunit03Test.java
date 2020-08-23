package me.bgg.junit5.bggjunit;

import org.assertj.core.util.Strings;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.condition.EnabledOnOs;
import org.junit.jupiter.api.condition.OS;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.aggregator.ArgumentsAccessor;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.EmptySource;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.*;

@DisplayNameGeneration(DisplayNameGenerator.Standard.class)
class BggJunit03Test {

    final static String test_env = "LOCAL";

    @Test
    @DisplayName("테스트 네이밍 1")
    @DevTest2
    void create_test(){
        BggJunit03 bggJunit03 = new BggJunit03(-1);
        assertAll(
                () -> assertNotNull(bggJunit03),
                () -> assertEquals(BggJunit03Status.DRAFT, bggJunit03.getStatus(),
                        () -> "현재상태가 DRAFT 가 아니다."),
                () -> assertTrue(bggJunit03.getLimit() > 0,
                        () -> "이 값은 0보다 커야한다."),
                () -> assertTimeout(Duration.ofMillis(100), () -> {
                            new BggJunit03(10);
                            Thread.sleep(300);
                        },
                        () -> "100밀리세컨 안에 끝나야한다.")
        );
    }

    @Test
    @EnabledOnOs({OS.LINUX, OS.WINDOWS})
    @DevTest
    void create1_test2(){
        System.out.println(test_env);
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
                () -> new BggJunit03(-1,2));
        assertEquals("a가 0보다 작으면 안된다.",exception.getMessage());
    }

    @RepeatedTest(value = 10, name =  "{displayName}, {currentRepetition}/{totalRepetitions}")
    @DisplayName("반복테스트1")
    void repeatTest(RepetitionInfo repetitionInfo){
        System.out.println("test " + repetitionInfo.getCurrentRepetition()+ "/"
                + repetitionInfo.getTotalRepetitions());
    }

    @DisplayName("반복테스트2")
    @ParameterizedTest(name = "{index}, {displayName} message={0}")
    @ValueSource(strings = {"2020년","08월","23일"})
    @NullAndEmptySource
    void parameterizedTest(String message){
        System.out.println(message);
    }

    @DisplayName("반복테스트3")
    @ParameterizedTest(name = "{index}, {displayName} message={0}")
    @CsvSource({"10, 문자열1", "20, 문자열2"})
    void csvSourceTest(ArgumentsAccessor argumentsAccessor){
        System.out.println(argumentsAccessor.getInteger(0));
        System.out.println(argumentsAccessor.getString(1));
    }

    @BeforeAll
    static void beforeAll(){
        System.out.println("before all");
    }

    @AfterAll
    static void afterAll(){
        System.out.println("after all");
    }

    @BeforeEach
    void beforeEach(){
        System.out.println("before each");
    }

    @AfterEach
    void afterEach(){
        System.out.println("after each");
    }

}