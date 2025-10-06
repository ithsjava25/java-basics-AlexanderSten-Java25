package com.example;

import com.example.api.ElpriserAPI;

import java.text.ParseException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;

public class Main {

    // TODO Omvandla detta till en array/list
    public static String dateSelected = "dateSelected";
    public static String zoneSelected = "zone selected";
    public static String chargingSelected = "charging selected";
    public static String selected2h = "2h selected";

    public static int zonePlace = 0;
    public static int zoneValue = 1;
    public static int datePlace = 2;
    public static int dateValue = 3;

    public enum copyPrisklass {
        SE1,SE2,SE3,SE4
    }

    public static void main(String[] args) {
        ElpriserAPI elpriserAPI = new ElpriserAPI();

        ElpriserAPI.Prisklass zone;
        String date;

        String[] argsCheckWhatsMissing = {"--zone","--date"};
        int missingArgumentsOptions = 0;
        int missingDateArgument = 1;
        int missingZoneArgument = 2;

        String[] helpEmpty = {"usage","zone","date","sorted"};
        String[] helpMenu = {"--zone","--date","--charging","--sorted","SE1","SE2","SE3","SE4"};
        String[] prisKlassOptions = {"SE1","SE2","SE3","SE4"};
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        dateFormat.setLenient(false);

        // TODO: if args have length 1, it must include: --help
        // TODO: if args have length 4 or more, it must include: --zone , SE? , --date , 20??-??-??
        // TODO: if args have length 5, it must include: --sorted
        // TODO: if args have length 6, it must include: --charging , ?h
        // TODO: if any other length, it must be set as invalid.

        // TODO: SE1-SE4 & 2h-4h-8h is the only valid options for that catagory
        // TODO: Ska includera om det saknas --date --zone / fel SE? / fel datum / inget data includerat i elpriserAPI

        // TODO: Ska på nått sätt omvandla två olika string som finns i args till enum och Localdate
        // TODO: Tar reda på NÄR jag ska använda getPriser()

        // TODO: Förstå varför den här måste har en for() för att få testet att fungera.
        if(args.length == 0) {
            for(String allHelpEmptyOptions: helpEmpty) {
                System.out.println(Arrays.toString(helpEmpty));
                break;
            }
        }

        // Check if there is just one thing in args(And probably --help)
        if(args.length == 1) {
            if(args[0].equalsIgnoreCase("--help")) {
                for(String allHelpMenuOptions: helpMenu){
                    System.out.println(Arrays.toString(helpMenu));
                }
            }
        } else if(args.length == 4) {
            if(args[zonePlace].equalsIgnoreCase("--zone")) {
                if(checkIfValidZone(args[zoneValue], prisKlassOptions)) {
                    if(args[datePlace].equals("--date") && checkIfValidDate(args[dateValue],dateFormat)) {
                        System.out.println("This part is reached");
                        // Den här lägger till värde och hämtar det som skickas
                        List<ElpriserAPI.Elpris> elprisList = elpriserAPI.getPriser(getDate(args,dateFormat),getPrisKlass(args));
                        System.out.println(elprisList);
                        choosingOptions(elprisList);
                    } else {
                        System.out.println("invalid date");
                    }
                    } else {
                        System.out.println("invalid zone");
                    }
            } else {
                System.out.println("not sure what is missing");
            }
        } else if(args.length == 6) {

        } else {
            // Kollar om vad det är som saknas från args
            missingArgumentsOptions = checkMissingArgsArugments(args);
            // TODO: Den här borde inte fungera men den gör det.
            if(missingArgumentsOptions == missingDateArgument) {
                System.out.println("date required");
            } else {
                if(missingArgumentsOptions == missingZoneArgument) {
                    System.out.println("zone required");
                }
            }
        }

        // shallUseThisMaybeLater(args, prisKlassOptions);


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

    }

    private static void choosingOptions(List<ElpriserAPI.Elpris> elprisList) {




    }

    private static void shallUseThisMaybeLater(String[] args, String[] prisKlassOptions) {
        if(args[0].equalsIgnoreCase("--zone")) {
            if(checkIfValidZone(args[1], prisKlassOptions)) {
//                if(checkIfValidDate(args[3],dateFormat)) {
//
//                } else {
//                    System.out.println("invalid date");
//                }
            } else {
                System.out.println("invalid zone");
            }
        } else {
            System.out.println("");
        }
    }

    private static int checkMissingArgsArugments(String[] args) {
        if(Arrays.asList(args).contains("--zone")) {
            return 1;
        } else {
            return 2;
        }
    }

    private static boolean checkIfValidDate(String arg,SimpleDateFormat dateFormat) {
        try{
            dateFormat.parse(arg);
                    return true;
        } catch (ParseException e) {
            return false;
        }
    }

    private static boolean checkIfValidZone(String args, String[] prisKlassOptions) {
        return Arrays.asList(prisKlassOptions).contains(args);
    }


    private static ElpriserAPI.Prisklass getPrisKlass(String[] args) {
        ElpriserAPI.Prisklass input =
                switch(args[zoneValue]) {
                    case "SE1" -> ElpriserAPI.Prisklass.SE1;
                    case "SE2" -> ElpriserAPI.Prisklass.SE2;
                    case "SE3" -> ElpriserAPI.Prisklass.SE3;
                    case "SE4" -> ElpriserAPI.Prisklass.SE4;
                    default -> throw new IllegalStateException("Unexpected value: " + args[zoneValue]);
                };
        return input;
    }

    private static LocalDate getDate(String[] args,SimpleDateFormat date) {
        // Tvungen att göra om den här till en DateTimeFormatter
        // Men kan inte använda min SimpleDateformatter här av nån anledning
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        return LocalDate.parse(args[dateValue],formatter);
    }
}
