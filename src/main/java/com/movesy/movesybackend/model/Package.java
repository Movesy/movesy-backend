package com.movesy.movesybackend.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Document(collection="packages")
public class Package {
    @Id
    private String id;
    private Location from;
    private Location to;
    private Date deadline;
    private int price;
    private int weight;
    private Size size;
}


