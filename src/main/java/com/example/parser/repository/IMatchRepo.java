package com.example.parser.repository;

import com.example.parser.model.Match;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IMatchRepo extends JpaRepository<Match, Long> {

    //@Query("select m from Match m where m.matchId =:matchId")
}
