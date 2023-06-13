package com.gradlereact.total.service;

import com.gradlereact.total.domain.Board;
import com.gradlereact.total.repository.BoardRepository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class BoardService {

    private final BoardRepository boardRepository; // Auto wired로 스프링 빈에 등록

    public List<Board> findBoards() {
        return boardRepository.findAll();
    }

    public Board findOne(Long boardId){
        return boardRepository.findById(boardId).orElseThrow(NullPointerException::new);
    } // id에 해당하는 board가 repository에 존재하지 않을 경우 NullPointerException 에러 핸들링
// (** 서버 죽지 않게 하기 위함 **)

    @Transactional
    public void create(Board board){
        boardRepository.save(board);

    }

    @Transactional
    public void update(Long id, String title, String content){
        Board findBoard = boardRepository.findById(id).orElseThrow(NullPointerException::new);
        findBoard.setTitle(title);    //Dirty checking -  감지하고 있다가 새로운 값이 들어오면 업데이트해줌.
        findBoard.setContent(content);
    }

    @Transactional
    public void delete(Board board) {
        boardRepository.delete(board);
    }
}
