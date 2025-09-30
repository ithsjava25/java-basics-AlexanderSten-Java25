package com.example;

import com.example.api.ElpriserAPI;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        ElpriserAPI elpriserAPI = new ElpriserAPI();
        for (int i = 0; i < args.length;i++) {
            switch (args[i]) {
                case "--zone" -> System.out.println("zone selected");
                case "SE2" -> System.out.println("SE2 selected");
                case "--date" -> System.out.println("date selected");
                case "--sorted" -> System.out.println("sorted selected");
            }
        }

        LocalDate testDate = LocalDate.now();

    }
}
