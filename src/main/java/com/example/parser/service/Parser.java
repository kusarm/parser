package com.example.parser.service;

import com.example.parser.model.Match;
import com.example.parser.repository.IMatchRepo;
import com.opencsv.bean.ColumnPositionMappingStrategy;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import com.opencsv.bean.MappingStrategy;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.io.Reader;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class Parser implements InitializingBean {

    private final IMatchRepo matchRepo;

    @Getter
    private static Parser instance;

    @Override
    public void afterPropertiesSet() throws Exception {
        instance = this;
    }

    public void parse(Reader reader) {
        MappingStrategy<Match> ms = new ColumnPositionMappingStrategy<>();
        ms.setType(Match.class);

        CsvToBean cb = new CsvToBeanBuilder(reader)
                .withQuoteChar('\'')
                .withSeparator('|')
                .withType(Match.class)
                .withMappingStrategy(ms)
                .withSkipLines(1)
                .build();


        LocalDateTime start = LocalDateTime.now();

        List<Match> matches = cb.parse();

        matches = matches.stream()
                .sorted(Comparator.comparing(Match::getMatchId))
                .collect(Collectors.toList());

        matches.forEach(match -> {
            match.setDateInsert(LocalDateTime.now());
            matchRepo.save(match);
        });



        LocalDateTime end = LocalDateTime.now();

        System.out.printf("Parsed %d rows in %d seconds\n", matches.size(), Duration.between(start, end).toSeconds());
    }

    public void geMin() {
        matchRepo.findAll(Sort.by(Sort.Direction.ASC, "dateInsert")).forEach(System.out::println);
    }

    public void geMax() {
        matchRepo.findAll(Sort.by(Sort.Direction.DESC, "dateInsert")).forEach(System.out::println);
    }
}
