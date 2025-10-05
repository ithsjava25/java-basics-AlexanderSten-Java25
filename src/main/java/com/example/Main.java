package com.example;

import com.example.api.ElpriserAPI;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        ElpriserAPI elpriserAPI = new ElpriserAPI();

        String[] helpEmpty = {"usage","zone","date","sorted"};
        String[] helpMenu = {"--zone","--date","--charging","--sorted","SE1","SE2","SE3","SE4"};
        String[] prisKlassOptions = {"SE1","SE2","SE3","SE4"};

        // TODO: if args have length 1, it must include: --help
        // TODO: if args have length 4 or more, it must include: --zone , SE? , --date , 20??-??-??
        // TODO: if args have length 5, it must include: --sorted
        // TODO: if args have length 6, it must include: --charging , ?h
        // TODO: if any other length, it must be set as invalid.

        // TODO: SE1-SE4 & 2h-4h-8h is the only valid options for that catagory
        // TODO: Ska includera om det saknas --date --zone / fel SE? / fel datum / inget data includerat i elpriserAPI

        if(args.length == 0) {
            for(String allHelpEmptyOptions: helpEmpty) {
                System.out.println(Arrays.toString(helpEmpty));
            }
        }

        if(args[0].equalsIgnoreCase("--help")) {
                for(String allHelpMenuOptions: helpMenu){
                    System.out.println(Arrays.toString(helpMenu));
                }
        }

        if(args[0].equalsIgnoreCase("--zone")) {
            if(checkIfPrisKlassIsLegit(args[1],prisKlassOptions)) {

            } else {
                System.out.println("invalid zone");
            }
        }


        for (int i = 0; i < args.length;i++) {
            switch (args[i]) {
                case "SE1" -> System.out.println("SE1 selected");
                case "SE2" -> System.out.println("SE2 selected");
                case "SE3" -> System.out.println("SE3 selected");
                case "SE4" -> System.out.println("SE4 selected");
                case "2h" -> System.out.println("2h selected");
                case "4h" -> System.out.println("4h selected");
                case "8h" -> System.out.println("8h selected");
                case "--zone" -> System.out.println("zone selected");
                case "--charging" -> System.out.println("charging selected");
                case "--help" -> System.out.println("help selected");
                case "--date" -> System.out.println("date selected");
                case "--sorted" -> System.out.println("sorted selected");
            }
        }

        LocalDate testDate = LocalDate.now();

    }

    private static boolean checkIfPrisKlassIsLegit(String args, String[] prisKlassOptions) {
        return Arrays.asList(prisKlassOptions).contains(args);
    }
}
