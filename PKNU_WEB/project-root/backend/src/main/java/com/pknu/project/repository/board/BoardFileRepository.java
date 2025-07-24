package com.pknu.project.repository.board;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pknu.project.model.board.BoardFile;

@Repository
public interface BoardFileRepository extends JpaRepository<BoardFile, Long> {

}

