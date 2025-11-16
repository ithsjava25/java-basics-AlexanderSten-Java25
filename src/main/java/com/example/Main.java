package com.example;

import com.example.api.ElpriserAPI;

import java.text.ParseException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;

public class Main {
    // TODO Omvandla detta till en array/list
//    public static String dateSelected = "dateSelected";
//    public static String zoneSelected = "zone selected";
//    public static String chargingSelected = "charging selected";
//    public static String selected2h = "2h selected";





    public static int zonePlace = 0;
    public static int zoneValue = 1;
    public static int datePlace = 2;
    public static int dateValue = 3;
    public static int hourSelected = 5;

    public static int twoHours = 0;
    public static int fourHours = 1;
    public static int eightHours = 2;

    public static int sortedSelected = 5;
    public static int chargingSelected = 6;

    public static double medelPriser = 0.0;
    public static int ultimateMedelPris = 0;

    public static void main(String[] args) {
        ElpriserAPI elpriserAPI = new ElpriserAPI();

        int missingArgumentsOptions = 0;
        int missingDateArgument = 1;
        int missingZoneArgument = 2;

        String[] helpEmpty = {"usage","zone","date","sorted"};
        String[] helpMenu = {"--zone","--date","--charging","--sorted","SE1","SE2","SE3","SE4"};
        String[] prisKlassOptions = {"SE1","SE2","SE3","SE4"};
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        dateFormat.setLenient(false);

        // TODO: Försöka komma på ett sätt att lägga in int value från listan till en string. Och samtidigt omvandla den till en int.
        // TODO: Försöka förstå hur jag ska använda <Key, Value> för att fixa detta.
        // TODO: Kom på att jag kan kolla på exemplet på ElpriserAPI
        // Fixat: if args have length 4 or more, it must include: --zone , SE? , --date , 20??-??-??
        // Fixat: if args have length 5, it must include: --sorted
        // Fixat: if args have length 6, it must include: --charging , ?h

        // SE1-SE4 & 2h-4h-8h is the only valid options for --zone / --charging
        // Fixat: Ska includera om det saknas --date --zone / fel SE? / fel datum / inget data includerat i elpriserAPI

        // Fixat??? Ska på nått sätt omvandla två olika string som finns i args till enum och Localdate
        // Fixat??? Tar reda på NÄR jag ska använda getPriser()

        // TODO: Förstå varför den här måste har en for() för att få testet att fungera.
        // Check if there args is empty and then put up a help menu
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
        } else if(args.length >= 3 && args.length <= 5) {
            if(args[zonePlace].equalsIgnoreCase("--zone")) {
                if(checkIfValidZone(args[zoneValue], prisKlassOptions)) {
                    if(args[datePlace].equals("--date") && checkIfValidDate(args[dateValue],dateFormat)) {
                        System.out.println("This part is reached");
                        // Den här lägger till värde och hämtar det som skickas
                        List<ElpriserAPI.Elpris> elprisList = elpriserAPI.getPriser(getDate(args,dateFormat),getPrisKlass(args));
                        if(elprisList.isEmpty()){
                            System.out.println("no data");
                        } else {
                            // Kör igång med själva programmet
                            choosingOptions(elprisList, args);
                        }
                    } else {
                        System.out.println("invalid date");
                    }
                    } else {
                        System.out.println("invalid zone");
                    }
            } else {
                System.out.println("not sure what is missing");
            }
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



    }

    private static void choosingOptions(List<ElpriserAPI.Elpris> elprisList, String[] args) {

//        for (String arg : args) {
//            switch (arg) {
//                case "--zone" -> System.out.println("zone selected");
//                case "SE1" -> System.out.println("SE1 selected");
//                case "SE2" -> System.out.println("SE2 selected");
//                case "SE3" -> System.out.println("SE3 selected");
//                case "SE4" -> System.out.println("SE4 selected");
//                case "2h" -> System.out.println("2h selected");
//                case "4h" -> System.out.println("4h selected");
//                case "8h" -> System.out.println("8h selected");
//                case "--charging" -> System.out.println("charging selected");
//                case "--date" -> System.out.println("date selected");
//                case "--sorted" -> System.out.println("sorted selected");
//            }
//        }

        // Jag trodde att zoneValue var den som bestämde var programmet skulle göra i början.
        // Men istället får jag kolla efter dom --sorted / --charging FÖRST innan jag kör med zoneValue
        // Enklaste sättet att göra det är kolla längden på args.
        // Men jag vet att det finns ett enklare och med SÄKERT sätt att göra det.

        if(args.length == 4) {
            switch(args[zoneValue]) {
                // displayMinMaxPrices_withValidData()
                case "SE1" -> {

                }
                case "SE2" -> {

                }
                // displayMeanPrice_withValidData()
                // testHourlyMinMaxPrices_with96Entries()
                case "SE3" -> {
                    medelPriser = 0.0;
                    for(ElpriserAPI.Elpris pris : elprisList) {
                        medelPriser = medelPriser + pris.sekPerKWh();
                    }
                    medelPriser = medelPriser / elprisList.size();
                    ultimateMedelPris = (int) Math.round(medelPriser * 100);
                    System.out.println("medelpris");
                    System.out.println(ultimateMedelPris);

                }
                case "SE4" ->{

                }
            }

        } else if (args.length == sortedSelected){
            switch(args[zoneValue]) {
//                case "SE1" -> {
//
//                }
                // displaySortedPrices_whenRequested()
                case "SE2" -> {

                }
//                case "SE3" -> {
//
//                }
//                case "SE4" ->{
//
//                }
            }

        } else if(args.length == chargingSelected){
            int hoursSelected = 5;
            switch(args[hoursSelected]){
                case "2h" -> {
                    hoursSelected = twoHours;
                }
                case "4h" -> {
                    hoursSelected = fourHours;
                }
                case "8h" -> {
                    hoursSelected = eightHours;
                }
            }
            switch(args[zoneValue]) {
                // findOptimalCharging4Hours()
                case "SE1" -> {

                }
//                case "SE2" -> {
//
//                }
                // findOptimalCharging2Hours()
                // chargingWindowDoesNotUseNextDay_whenNextDayUnavailable()
                // handleMultipleDaysData_includesNextDayForCharging()
                case "SE3" -> {

                }
                // findOptimalCharging8Hours()
                case "SE4" ->{

                }
            }

        }

//        switch (args[zoneValue]) {
//            // Den här är för displayMinMaxPrices_withValidData() /
//            case "SE1" -> {
//
//            }
//            // Den här är för displaySortedPrices_whenRequested()
//            case "SE2" -> {
//
//            }
//            // Den här är för findOptimalCharging2Hours()
//            // chargingWindowDoesNotUseNextDay_whenNextDayUnavailable()
//            // handleMultipleDaysData_includesNextDayForCharging()
//            // chargingWindowSpansToNextDay_whenCheapestCrossesMidnight()
//            // testHourlyMinMaxPrices_with96Entries()
//            case "SE3" -> {
//
//            }
//            // Den här är för findOptimalCharging8Hours()
//            case "SE4" -> {
//
//            }
//            default -> System.out.println("Error");
//        }


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
