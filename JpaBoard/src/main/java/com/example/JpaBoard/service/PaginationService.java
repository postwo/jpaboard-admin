package com.example.JpaBoard.service;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.IntStream;

@Service
public class PaginationService {

    private static final int BAR_LENGTH = 5;

    public List<Integer> getPaginationBarNumbers(int currentPageNumber, int totalPages) {
        //현재 페이지에서 (BAR_LENGTH / 2)만큼 빼서 중앙에 현재 페이지가 오도록 합니다. 예를 들어, currentPageNumber가 3이면, 5개의 페이지 번호 중 1, 2, 3, 4, 5를 보여주고 싶기 때문입니다.
        //하지만, 페이지 번호가 음수가 될 수 없으므로, 최소값을 0으로 설정
        int startNumber = Math.max(currentPageNumber - (BAR_LENGTH / 2), 0);

        //시작 번호에 BAR_LENGTH를 더해서 끝 번호를 계산합니다. 하지만, 전체 페이지 수(totalPages)를 넘지 않도록 Math.min()을 사용해 조정
        int endNumber = Math.min(startNumber + BAR_LENGTH, totalPages);

        //startNumber부터 endNumber까지의 범위에 해당하는 페이지 번호들을 리스트로 변환해서 반환합니다.
        //예를 들어, 시작 번호가 1, 끝 번호가 6이면, [1, 2, 3, 4, 5] 리스트가 반환
        return IntStream.range(startNumber, endNumber).boxed().toList();
    }

    public int currentBarLength() {
        return BAR_LENGTH;
    }
}
