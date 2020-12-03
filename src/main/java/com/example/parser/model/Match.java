package com.example.parser.model;

import com.opencsv.bean.CsvBindByName;
import com.opencsv.bean.CsvBindByPosition;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Data
public class Match {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @CsvBindByPosition(position = 0)
    private String matchId;

    @CsvBindByPosition(position = 1)
    private String marketId;

    @CsvBindByPosition(position = 2)
    private String outcomeId;

    @CsvBindByPosition(position = 3)
    private String specifiers;

    private LocalDateTime dateInsert;
}
