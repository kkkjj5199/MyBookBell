package com.project.bookbell.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.params.provider.Arguments.arguments;

@DisplayName("비즈니스 로직 - 페이지네이션")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE,classes = PaginationService.class)
//webEnvironment.MOCK = mocking실제 웹 값을 넣을 수 있다. -> NONE은 아무것도 안넣음. // Void.class 빈스캔 대상을 컨피규어 에이션으로 불러드림.--아무것도 아니게 만드는것
class PaginationServiceTest {

    private final PaginationService paginationService;

    public PaginationServiceTest(@Autowired  PaginationService paginationService) {
        this.paginationService = paginationService;
    }


    @DisplayName("현재 페이지 번호와 총 페이지 수를 주면 페이징 바 리스트를 만들어준다.")
    @MethodSource
    @ParameterizedTest(name = "[{index}] 현재 페이지 : {0}, 총 페이지:{1} => {2}")
    void givenCurrentPageNumberAndTotalPages_whenCalculating_thenReturnPagenationBars(
            int current, int total, List<Integer> expected
    ) throws Exception {
        //given
        List<Integer> actual = paginationService.getPaginationBarNumbers(current,total);
        //when

        //then
        assertThat(actual).isEqualTo(expected);


    }

    static Stream<Arguments> givenCurrentPageNumberAndTotalPages_whenCalculating_thenReturnPagenationBars() {
        return Stream.of(
                arguments(0, 11, List.of(0, 1, 2, 3, 4)),
                arguments(1, 11, List.of(0, 1, 2, 3, 4)),
                arguments(2, 11, List.of(0, 1, 2, 3, 4)),
                arguments(3, 11, List.of(1, 2, 3, 4, 5)),
                arguments(4, 11, List.of(2, 3, 4, 5, 6)),
                arguments(5, 11, List.of(3, 4, 5, 6, 7)),
                arguments(6, 11, List.of(4, 5, 6, 7, 8)),
                arguments(7, 11, List.of(5, 6, 7, 8, 9)),
                arguments(8, 11, List.of(6, 7, 8, 9,10)),
                arguments(9, 11, List.of(7,8, 9,10))


        );

      }
        @DisplayName("현재 설정되어 있는 페이지네이션 바의 길이를 알려준다.")
        @Test
        public void giveBarLength() throws Exception {
            //given

            //when
            int barLength = paginationService.currentBarLength();
            //then
            assertThat(barLength).isEqualTo(5);

        }


    }


