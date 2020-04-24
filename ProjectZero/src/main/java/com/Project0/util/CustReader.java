package com.Project0.util;

import com.Project0.model.Golfer;
import com.Project0.model.League;
import com.Project0.model.MatchScore;
import com.Project0.model.User;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;

public class CustReader {

    /* =======================    USER OPERATIONS   =============================*/

    public User readUserLoginRequest(String filePath, String username, String password) {
        FileReader reader = null;
        BufferedReader bReader = null;

        try{
            reader = new FileReader(filePath);
            bReader = new BufferedReader(reader);

            String line = "";
            while((line = bReader.readLine()) != null) {
                String attributes[] = line.split(",");
                if(attributes[0].equals(username) && attributes[1].equals(password)) {
                    User thisUser = new User(attributes[0], attributes[1], attributes[2]);
                    return thisUser;
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("Error loading user file");
            return null;
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
        return null;
    }


    /* =======================    GOLFER OPERATIONS   =============================*/

    public ArrayList<Golfer> getGolfers(String filePath) {
        FileReader reader = null;
        BufferedReader bReader = null;
        ArrayList<Golfer> golfers = new ArrayList<>();

        try{
            reader = new FileReader(filePath);
            bReader = new BufferedReader(reader);

            String line = "";
            while((line = bReader.readLine()) != null) {
                String attr[] = line.split(",");
                Golfer thisGolfer = new Golfer(Long.parseLong(attr[0]), attr[1], attr[2], attr[3], attr[4], attr[5], attr[6], attr[7]);
                golfers.add(thisGolfer);
            }
            return golfers;
        } catch (FileNotFoundException e) {
            System.out.println("Error loading user file");
            return golfers;
        } catch (IOException ex) {
            ex.printStackTrace();
            return golfers;
        }
    }

    public ArrayList<Golfer> viewGolferInfo(String golferName) {
        FileReader reader = null;
        BufferedReader bReader = null;
        ArrayList<Golfer> golfers = new ArrayList<>();

        try{
            reader = new FileReader("src/main/resources/Golfers");
            bReader = new BufferedReader(reader);

            String line = "";
            while((line = bReader.readLine()) != null) {
                String attributes[] = line.split(",");
                String lcaseattr = attributes[1].toLowerCase();
                String lcasegolfer = golferName.toLowerCase();
                if(lcaseattr.contains(lcasegolfer)) {
                    Golfer thisGolfer = new Golfer(Long.parseLong(attributes[0]), attributes[1], attributes[2], attributes[3], attributes[4],
                            attributes[5], attributes[6], attributes[7]);
                    golfers.add(thisGolfer);
                }
            }
            return golfers;
        } catch (FileNotFoundException e) {
            System.out.println("Error loading user file");
            return golfers;
        } catch (IOException ex) {
            ex.printStackTrace();
            return golfers;
        }
    }

    //read golfer scores for single golfer
    public ArrayList<MatchScore> readGolferScores(Golfer golfer) throws Exception{
        FileReader reader = null;
        BufferedReader bReader = null;
        ArrayList<MatchScore> scores = new ArrayList<>();

        try{
            reader = new FileReader("src/main/resources/LeagueScores");
            bReader = new BufferedReader(reader);

            String line = "";
            while((line = bReader.readLine()) != null) {
                String attr[] = line.split(",");
                String lcaseattr = attr[0].toLowerCase();
                String lcasegolfer = golfer.getName().toLowerCase();
                if(lcaseattr.contains(lcasegolfer)) {
                    //System.out.println(line);
                    String dateparsed[] = attr[2].split("-");
                    LocalDate thisDate = LocalDate.of(Integer.parseInt(dateparsed[0]), Integer.parseInt(dateparsed[1]), Integer.parseInt(dateparsed[2]));
                    MatchScore thisMatchScore = new MatchScore(golfer, Integer.parseInt(attr[1]), thisDate);
                    scores.add(thisMatchScore);
                   // System.out.printf("ADDING THIS SCORE: %s", thisMatchScore.toString());
                }
            }
            //debug
//            for(MatchScore e : scores) {
//                System.out.printf(e.toString() + "\n");
//            }
            return scores;
        } catch (FileNotFoundException e) {
            System.out.println("Error loading user file");
            return scores;
        } catch (IOException ex) {
            ex.printStackTrace();
            return scores;
        }
    }



    /* =======================    LEAGUE OPERATIONS   =============================*/

    public ArrayList<League> getAllLeagues(){
        FileReader reader = null;
        BufferedReader bReader = null;
        ArrayList<League> leagues = new ArrayList<>();

        try{
            reader = new FileReader("src/main/resources/Leagues");
            bReader = new BufferedReader(reader);

            String line = "";
            while((line = bReader.readLine()) != null) {
                String attr[] = line.split(",");
                //System.out.println(line);
                String dateparsed[] = attr[1].split("-");
                LocalDate thisDate = LocalDate.of(Integer.parseInt(dateparsed[0]), Integer.parseInt(dateparsed[1]), Integer.parseInt(dateparsed[2]));
                ArrayList<Golfer> golfers = new ArrayList<>();
                League thisLeague = new League(attr[0], thisDate, Integer.parseInt(attr[2]), golfers);
                leagues.add(thisLeague);
                // System.out.printf("ADDING THIS SCORE: %s", thisMatchScore.toString());
            }
            //debug
            for(League e : leagues) {
                System.out.printf(e.toString() + "\n");
            }
            return leagues;
        } catch (FileNotFoundException e) {
            System.out.println("Error loading user file");
            return leagues;
        } catch (IOException ex) {
            ex.printStackTrace();
            return leagues;
        }
    }
}
