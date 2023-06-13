package com.gradlereact.total.repository;

import com.gradlereact.total.domain.Board;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BoardRepository extends JpaRepository<Board,Long> {
}
