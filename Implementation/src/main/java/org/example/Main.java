package org.example;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Scanner;
import java.sql.Date;
public class Main {
    public static String url = "jdbc:postgresql://localhost:5432/COMP 3005 Project - Health and Fitness Club Management System";
    public static String user = "postgres";
    public static String password = "jorg1357";

    public static void main(String[] args) {
//        Class.forName("org.postgresql.Driver");
        //userRegistration();
        //profileManagement(1);
        //dashboardDisplay(1);
        //memberScheduleManagement(1);
        //trainerScheduleManagement(1);
        //displayMembersByName("Jimothy");
        //roomBookingManagement();
        //equipmentMaintenanceMonitoring();

        //Class Schedule Updating
        //displayAllFitnessClasses();
        //updateReservation(1, convertTimeSlotToInt("MON 9"), 3, convertTimeSlotToInt("MON 9"));
        //displayAllFitnessClasses();

        //BONUS
        //equipmentMaintenanceUpdate(3,1, new Date(2024-1900, 3, 15));
        //viewOutstandingFees(4);

        //Billing(1, "fitness class", 20);


    }

    //Start User Registration Process
    public static void userRegistration(){
        Scanner reader = new Scanner(System.in);
        //ask for registration credentials
        System.out.println("Enter your name and skill level 1-99:");
        String userName = reader.nextLine();
        int skillLevel = reader.nextInt();
        reader.nextLine();
        //send the credentials to the DB
        try{
            Connection connection = DriverManager.getConnection(url,user,password);
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO Member (Name, SkillLevel) VALUES (?, ?);");
            preparedStatement.setString(1, userName);
            preparedStatement.setInt(2, skillLevel);
            int rowsAffected = preparedStatement.executeUpdate();
            // Check if the insertion was successful
            if (rowsAffected > 0) {
                System.out.println("New Member Added.");
            } else {
                System.out.println("Failed to add Member");
            }
            preparedStatement.close();
            connection.close();
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    //Let user manage their profile
    public static void profileManagement(int userID){
        Scanner reader = new Scanner(System.in);
        System.out.println("What do you want to update, enter the number: personal information(1), fitness goals(2), health metrics(3), exit(4)");
        int input = reader.nextInt();
        reader.nextLine();
        if(input == 1){ //let user update personal information
            System.out.println("Enter your updated name and skill level:");
            String userName = reader.nextLine();
            int skillLevel = reader.nextInt();
            reader.nextLine();
            try{
                Connection connection = DriverManager.getConnection(url,user,password);
                PreparedStatement preparedStatement = connection.prepareStatement("UPDATE Member SET Name = ?, SkillLevel = ? WHERE MemberID = ?;");

                preparedStatement.setString(1, userName);
                preparedStatement.setInt(2, skillLevel);
                preparedStatement.setInt(3, userID);
                int rowsAffected = preparedStatement.executeUpdate();
                // Check if the insertion was successful
                if (rowsAffected > 0) {
                    System.out.println("Profile updated successfully");
                } else {
                    System.out.println("Profile update failed");
                }
                preparedStatement.close();
                connection.close();
            }catch (SQLException e){
                e.printStackTrace();
            }
        }else if(input == 2){ //let user update/input fitness goals
            System.out.println("Do you want to update(1) or add(2) a new fitness goal?");
            int choice = reader.nextInt();
            reader.nextLine();
            getAllFitnessGoals(userID);
            if(choice == 1){ //update
                System.out.println("Using FitnessGoalID, which fitness goal do you want to set to completed?");
                int fitnessGoalID = reader.nextInt();
                reader.nextLine();
                try{
                    Connection connection = DriverManager.getConnection(url,user,password);
                    PreparedStatement preparedStatement = connection.prepareStatement("UPDATE Fitness_Goals SET Completed = ? WHERE FitnessGoalID = ?;");
                    preparedStatement.setBoolean(1, true);
                    preparedStatement.setInt(2, fitnessGoalID);
                    int rowsAffected = preparedStatement.executeUpdate();
                    // Check if the insertion was successful
                    if (rowsAffected > 0) {
                        System.out.println("Fitness Goal set to completed");
                    } else {
                        System.out.println("Failed to update Fitness Goal");
                    }
                    preparedStatement.close();
                    connection.close();
                }catch (SQLException e){
                    e.printStackTrace();
                }

            }else if(choice == 2){ //new fitness goal
                System.out.println("Enter the name of the fitness goal:");
                String fitness_goal = reader.nextLine();
                System.out.println("fitness goals " + fitness_goal);
                //send the credentials to the DB
                try{
                    Connection connection = DriverManager.getConnection(url,user,password);
                    PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO Fitness_Goals (MemberID, Name, Completed) VALUES (?, ?, ?);");
                    preparedStatement.setInt(1, userID);
                    preparedStatement.setString(2, fitness_goal);
                    preparedStatement.setBoolean(3, false);
                    int rowsAffected = preparedStatement.executeUpdate();
                    // Check if the insertion was successful
                    if (rowsAffected > 0) {
                        System.out.println("New Fitness Goal Added.");
                    } else {
                        System.out.println("Failed to add Fitness Goal");
                    }
                    preparedStatement.close();
                    connection.close();
                }catch (SQLException e){
                    e.printStackTrace();
                }
            }

        } else if (input == 3){ //let user input health metrics
            System.out.println("Enter your weight, steps, body fat percentage, and hours slept:");
            int weight = reader.nextInt();
            reader.nextLine();
            int steps = reader.nextInt();
            reader.nextLine();
            int bodyFat = reader.nextInt();
            reader.nextLine();
            int sleep = reader.nextInt();
            reader.nextLine();
            Date dateUploaded = Date.valueOf("2024-04-11");
            try{
                Connection connection = DriverManager.getConnection(url,user,password);
                PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO Health_Metrics (MemberID, Weight, Steps, BodyFatPercent, Sleep, DayUploaded)  VALUES (?, ?, ?, ?, ?, ?);");
                preparedStatement.setInt(1, userID);
                preparedStatement.setInt(2, weight);
                preparedStatement.setInt(3, steps);
                preparedStatement.setInt(4, bodyFat);
                preparedStatement.setInt(5, sleep);
                preparedStatement.setDate(6, dateUploaded);
                int rowsAffected = preparedStatement.executeUpdate();
                // Check if the insertion was successful
                if (rowsAffected > 0) {
                    System.out.println("Health Metric Added.");
                } else {
                    System.out.println("Failed to add Health Metric");
                }
                preparedStatement.close();
                connection.close();
            }catch (SQLException e){
                e.printStackTrace();
            }
        } else { //exit
            return;
        }
    }

    //will display to the user all the outstanding fees that a member has
    public static void viewOutstandingFees(int memberID){
        try{
            Connection connection = DriverManager.getConnection(url,user,password);
            PreparedStatement preparedStatement  = connection.prepareStatement("SELECT * FROM Bills WHERE MemberID = ? AND Paid = false");
            preparedStatement.setInt(1, memberID);
            ResultSet resultSet = preparedStatement.executeQuery();
            System.out.println("Displaying Outstanding Fees:");
            while (resultSet.next()){
                System.out.println("Fitness Goal ID: "+resultSet.getInt("BillID") + ", "
                        + "Bill Name: "+ resultSet.getString("BillName") + ", "
                        + "Amount: "+ resultSet.getInt("Amount"));
            }
            resultSet.close();
            preparedStatement.close();
            connection.close();
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    public static void getAllFitnessGoals(int memberID){
        try{
            Connection connection = DriverManager.getConnection(url,user,password);
            PreparedStatement preparedStatement  = connection.prepareStatement("SELECT * FROM Fitness_Goals WHERE MemberID = ?");
            preparedStatement.setInt(1, memberID);
            ResultSet resultSet = preparedStatement.executeQuery();
            System.out.println("Displaying Fitness Goals:");
            while (resultSet.next()){
                System.out.println("Fitness Goal ID: "+resultSet.getInt("FitnessGoalID") + ", "
                        + "Member ID: "+ resultSet.getInt("MemberID") + " "
                        + "Name of Fitness Goal: "+ resultSet.getString("Name") + ", "
                        + "Completed: "+ resultSet.getBoolean("Completed"));
            }
            resultSet.close();
            preparedStatement.close();
            connection.close();
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    //display member's dashboard
    public static void dashboardDisplay(int memberID){

        try{
            Connection connection = DriverManager.getConnection(url,user,password);
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT Fitness_Class.TimeSlotID, Fitness_Class.ClassName FROM Registered JOIN Fitness_Class ON Registered.ClassID = Fitness_Class.ClassID WHERE Registered.MemberID = ?; ");
            preparedStatement.setInt(1, memberID);
            ResultSet resultSet = preparedStatement.executeQuery();
            if(!resultSet.next()){
                System.out.println("There are no exercise routines");
            }else {
                System.out.println("Exercise Routines:");
                do{
                    System.out.println(resultSet.getInt("TimeSlotID") + ", "
                            + resultSet.getString("ClassName") );
                }while (resultSet.next());
            }

            resultSet.close();
            preparedStatement.close();

            PreparedStatement preparedStatement2 = connection.prepareStatement("SELECT * FROM Fitness_Goals WHERE MemberID = ? AND Completed = true;");
            preparedStatement2.setInt(1, memberID);
            ResultSet resultSet2 = preparedStatement2.executeQuery();
            if(!resultSet2.next()){
                System.out.println("\nThere are no fitness achievements");
            }else {
                System.out.println("\nFitness Achievements");
                do{

                    System.out.println("Name of goal: "+resultSet2.getString("Name") + ", "
                            + resultSet2.getBoolean("Completed"));
                }while (resultSet2.next());
            }

            resultSet2.close();
            preparedStatement2.close();
            PreparedStatement preparedStatement3 = connection.prepareStatement("SELECT * FROM Health_Metrics WHERE MemberID = ?;");
            preparedStatement3.setInt(1, memberID);
            ResultSet resultSet3 = preparedStatement3.executeQuery();
            ArrayList<Integer> weight = new ArrayList<>();
            ArrayList<Integer> steps = new ArrayList<>();
            ArrayList<Integer> bodyfat = new ArrayList<>();
            ArrayList<Integer> sleep = new ArrayList<>();
            if(!resultSet3.next()){
                System.out.println("\nThere are no health statistics");
            }else {
                System.out.println("\nHealth Statistics: ");
                do{

                    weight.add(resultSet3.getInt("Weight"));
                    steps.add(resultSet3.getInt("Steps"));
                    bodyfat.add(resultSet3.getInt("BodyFatPercent"));
                    sleep.add(resultSet3.getInt("Sleep"));

                }while   (resultSet3.next());
                System.out.println("Average weight: "+ findAverage(weight) + ", "
                        + "Average steps: "+findAverage(steps)
                        + " Average Body Fat Percent: "+findAverage(bodyfat)
                        + " Average Sleep: "+findAverage(sleep) );
                System.out.println("Minimun weight: "+ Collections.min(weight) + ", "
                        + "Minimun steps: "+ Collections.min(steps)
                        + " Minimun Body Fat Percent: "+Collections.min(bodyfat)
                        + " Minimun Sleep: "+Collections.min(sleep));
                System.out.println("Maximum weight: "+ Collections.max(weight) + ", "
                        + "Maximum steps: "+Collections.max(steps)
                        + " Maximum Body Fat Percent: "+Collections.max(bodyfat)
                        + " Maximum Sleep: "+Collections.max(sleep) );
            }

            resultSet3.close();
            preparedStatement3.close();
            connection.close();
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    //finds average from an array of numbers
    public static double findAverage(ArrayList<Integer> arr){
        int sum = 0;

        for (int num : arr) {
            sum += num;
        }

        return (double) sum / arr.size();
    }


    //member's schedule management
    public static void memberScheduleManagement(int memberID){
        Scanner reader = new Scanner(System.in);
        System.out.println("Schedule personal training session(1) or group fitness class (2)");
        int input = reader.nextInt();
        reader.nextLine();
        if(input == 1){ //schedule training session
            System.out.println("Schedule(1), reschedule(2), cancel(3) training session");
            input = reader.nextInt();
            reader.nextLine();
            if(input == 1){//schedule
                System.out.println("Here is the available schedule of the trainers:");
                displayAvailableTrainerSchedule();


                while(true){
                    System.out.println("Enter the Trainer and Time Slot you want to schedule:");
                    int trainerID = reader.nextInt();
                    reader.nextLine();
                    String timeSlotString = reader.nextLine();
                    int timeSlotID = convertTimeSlotToInt(timeSlotString);
                    if (verifyTrainerBookingAvailability(trainerID, timeSlotID)){ //is available
                        scheduleTrainingSession(trainerID, memberID, timeSlotID);
                        break;
                    }
                    System.out.println("Error: either trainer or timeslot was not available");
                }


            }else if(input == 2){//reschedule
                if(displayMemberTrainingSessions(memberID) > 0) {
                    System.out.println("Enter the Trainer and Time Slot you want to reschedule");
                    int prevTrainerID = reader.nextInt();
                    reader.nextLine();
                    String prevTimeSlotString = reader.nextLine();
                    int prevTimeSlotID = convertTimeSlotToInt(prevTimeSlotString);
                    while (true) {
                        System.out.println("Here is the available schedule of the trainers:");
                        displayAvailableTrainerSchedule();
                        System.out.println("Enter the Trainer and Time Slot you want to schedule");
                        int trainerID = reader.nextInt();
                        reader.nextLine();
                        String timeSlotString = reader.nextLine();
                        int timeSlotID = convertTimeSlotToInt(timeSlotString);
                        if (verifyTrainerBookingAvailability(trainerID, timeSlotID)) { //is available
                            cancelTrainingSession(prevTrainerID, prevTimeSlotID, true);
                            scheduleTrainingSession(trainerID, memberID, timeSlotID);
                            break;
                        }
                        System.out.println("Error: either trainer or timeslot was not available");
                    }
                }
            }else if(input == 3) {//cancel
                if(displayMemberTrainingSessions(memberID) > 0){
                    System.out.println("Enter the Trainer and Time Slot you want to cancel");
                    int prevTrainerID = reader.nextInt();
                    reader.nextLine();
                    String prevTimeSlotString = reader.nextLine();
                    int prevTimeSlotID = convertTimeSlotToInt(prevTimeSlotString);

                    cancelTrainingSession(prevTrainerID, prevTimeSlotID, true);
                }

            }


        }else if (input == 2){ //schedule group fitness class
            displayAllFitnessClasses();

            while(true){
                System.out.println("Enter the time slot and the class ID you want to enroll for:");
                String timeSlotString = reader.nextLine();
                int classID = reader.nextInt();
                reader.nextLine();
                int timeSlotID = convertTimeSlotToInt(timeSlotString);

                if(checkMemberFitnessClassAvailability(memberID, timeSlotID)){ //allow user to register
                    registerInFitnessClass(memberID, classID);
                    break;
                }
                System.out.println("Error: you are already enrolled in a class during this time slot");
            }

        }
    }

    //trainer's schedule management
    public static void trainerScheduleManagement(int trainerID){
        displaySingleTrainerSchedule(trainerID);
        Scanner reader = new Scanner(System.in);
        int timeSlotID;

        while (true){
            System.out.println("Enter the time slot you want to toggle availability:");
            String timeSlotString = reader.nextLine();
            timeSlotID = convertTimeSlotToInt(timeSlotString);
            if(timeSlotID > -1){
                break;
            }
            System.out.println("Error: time slot not valid");
        }
//        toggleTrainerAvailability(trainerID,timeSlotID);
        cancelTrainingSession(trainerID, timeSlotID ,false);

    }

    //view the profiles of members by their name
    public static void displayMembersByName(String name){
        try {
            Connection connection = DriverManager.getConnection(url, user, password);
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM Member WHERE Name = ?");
            preparedStatement.setString(1, name);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                System.out.println("Member ID: "+resultSet.getInt("MemberID") + ", "
                        + "Name: "+resultSet.getString("Name") + ", "
                        + "Skill Level: "+resultSet.getInt("SkillLevel"));
            }
            resultSet.close();
            preparedStatement.close();
            connection.close();
        } catch (SQLException e){
            e.printStackTrace();
        }
    }

    //staff's functionality to manage the booking of the rooms
    public static void roomBookingManagement(){
        Scanner reader = new Scanner(System.in);
        System.out.println("Create(1), Modify(2), Delete(3) Reservation");
        int input = reader.nextInt();
        if(input == 1){//create new reservation
            displayAvailableRooms();
            System.out.println("Enter the room id and time slot you would like to create a new reservation in:");
            int roomID = reader.nextInt();
            reader.nextLine();
            String timeSlotString = reader.nextLine();
            int timeSlotID = convertTimeSlotToInt(timeSlotString);
            createReservation(roomID, timeSlotID);
        }else if(input == 2){//modify existing reservation
            displayAllFitnessClasses();
            System.out.println("Enter the room id and time slot of the reservation you want to change:");
            int oldRoomID = reader.nextInt();
            reader.nextLine();
            String oldTimeSlotString = reader.nextLine();
            int oldTimeSlotID = convertTimeSlotToInt(oldTimeSlotString);
            displayAvailableRooms();
            System.out.println("Enter the new room id and time slot:");
            int newRoomID = reader.nextInt();
            reader.nextLine();
            String newTimeSlotString = reader.nextLine();
            int newTimeSlotID = convertTimeSlotToInt(newTimeSlotString);
            updateReservation(oldRoomID,oldTimeSlotID,newRoomID,newTimeSlotID);
        }else if(input == 3){//delete reservation
            displayAllFitnessClasses();
            System.out.println("Enter the room id and time slot of the reservation you want to delete:");
            int roomID = reader.nextInt();
            reader.nextLine();
            String timeSlotString = reader.nextLine();
            int timeSlotID = convertTimeSlotToInt(timeSlotString);
            deleteReservation(roomID, timeSlotID);
        }
    }

    //Staff's Equipment maintenance monitoring
    public static void equipmentMaintenanceMonitoring(){
        try {
            Connection connection = DriverManager.getConnection(url, user, password);
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM Equipment");
            //preparedStatement.setInt(1, memberID);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                System.out.println("Equipment ID: " + resultSet.getInt("EquipmentID") + ", "
                        + "Recommended Maintenance Schedule: " + resultSet.getInt("RecMaintenanceSch") + ", "
                        + "Previous Maintenance Date: " + resultSet.getDate("PrevMaintenance") + ", "
                        + "Previous Employee: " + resultSet.getInt("PrevEmployee"));
            }
            resultSet.close();
            preparedStatement.close();
            connection.close();
        } catch (SQLException e){
            e.printStackTrace();
        }
    }

    public static void equipmentMaintenanceUpdate(int staffID, int equipmentID, Date date){
        try{
            Connection connection = DriverManager.getConnection(url,user,password);
            PreparedStatement preparedStatement = connection.prepareStatement("UPDATE Equipment SET PrevMaintenance = ?, PrevEmployee = ? WHERE EquipmentID = ?");
            preparedStatement.setDate(1, date);
            preparedStatement.setInt(2, staffID);
            preparedStatement.setInt(3, equipmentID);
            int rowsAffected = preparedStatement.executeUpdate();
            // Check if the insertion was successful
            if (rowsAffected > 0) {
                System.out.println("Equipment Maintenance successfully updated");
            } else {
                System.out.println("Equipment Maintenance failed");
            }
            preparedStatement.close();


            connection.close();
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    //Staff's ability to create a bill for a user
    public static void Billing(int memberID,  String billName, int amount){
        int billID = createBill(memberID,billName,amount);
        Scanner reader = new Scanner(System.in);
        System.out.println("Member will pay the bill now(1) or later(2)");
        int input = reader.nextInt();
        if(input == 1){ //pay now
            payBill(billID);
        }else if(input == 2){
            System.out.println("Member has chosen to pay later");
        }
    }

    //create bill
    public static int createBill(int memberID, String billName, int amount){
        int billID = -1;
        try{
            Connection connection = DriverManager.getConnection(url,user,password);
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO Bills (BillName, Paid, MemberID, Amount) VALUES (?, ?, ?, ?);", Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, billName);
            preparedStatement.setBoolean(2, false);
            preparedStatement.setInt(3, memberID);
            preparedStatement.setInt(4, amount);
            int rowsAffected = preparedStatement.executeUpdate();
            // Check if the insertion was successful
            if (rowsAffected > 0) {
                System.out.println("Created Bill successfully.");
            } else {
                System.out.println("Failed to create bill");
            }

            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            while (resultSet.next()){
                billID = resultSet.getInt(1);
            }
            resultSet.close();

            preparedStatement.close();
            connection.close();
        }catch (SQLException e){
            e.printStackTrace();
        }
        return billID;
    }

    //pay bill
    public static void payBill(int billID){
        try{
            Connection connection = DriverManager.getConnection(url,user,password);
            PreparedStatement preparedStatement = connection.prepareStatement("UPDATE Bills SET Paid = true WHERE BillID = ?");
            preparedStatement.setInt(1, billID);
            int rowsAffected = preparedStatement.executeUpdate();
            // Check if the insertion was successful
            if (rowsAffected > 0) {
                System.out.println("Bill paid successfully");
            } else {
                System.out.println("Bill was not paid");
            }
            preparedStatement.close();

            connection.close();
        }catch (SQLException e){
            e.printStackTrace();
        }
    }


    /////////////////////MAIN FUNCTIONS END /////////////////

    /////////////////////TRAINING SESSION FUNCTIONS START /////////////////
    //verifies if the selected trainer for the specific time slot is available
    public static Boolean verifyTrainerBookingAvailability(int trainerID, int timeSlotID){
        boolean available = false;
        try {
            Connection connection = DriverManager.getConnection(url, user, password);
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM Trainer WHERE TrainerID = ? AND TimeSlotID = ?;");
            preparedStatement.setInt(1, trainerID);
            preparedStatement.setInt(2, timeSlotID);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                available = resultSet.getBoolean("Available");
            }
            resultSet.close();
            preparedStatement.close();
            connection.close();
        } catch (SQLException e){
            e.printStackTrace();
        }

        return available;
    }

    //cancel training session for member
    public static void cancelTrainingSession(int trainerID, int timeSlotID, boolean cancel){
        try {

                Connection connection = DriverManager.getConnection(url, user, password);
                PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM Member_Trainer_Session WHERE TrainerID = ? AND TimeSlotID = ?");
                preparedStatement.setInt(1, trainerID);
                preparedStatement.setInt(2, timeSlotID);
                int rowsAffected = preparedStatement.executeUpdate();
                // Check if the insertion was successful
                if (rowsAffected > 0) {
                    System.out.println("Training session canceled successfully.");
                } else {
                    if(cancel){
                        System.out.println("Failed to cancel training session");
                    }
                }
                preparedStatement.close();
                connection.close();


            //add trainer availability for training session
            toggleTrainerAvailability(trainerID,timeSlotID);


        } catch (SQLException e){
            e.printStackTrace();
        }
    }

    //toggle trainer availability
    public static void toggleTrainerAvailability(int trainerID, int timeSlotID){
        try {
            Connection connection = DriverManager.getConnection(url, user, password);
            PreparedStatement preparedStatement = connection.prepareStatement("UPDATE Trainer SET Available = NOT Available WHERE TrainerID = ? AND TimeSlotID = ?;");
            preparedStatement.setInt(1, trainerID);
            preparedStatement.setInt(2, timeSlotID);
            int rowsAffected2 = preparedStatement.executeUpdate();
            // Check if the insertion was successful
            if (rowsAffected2 > 0) {
                System.out.println("Trainer availability toggled");
            } else {
                System.out.println("Failed to toggle trainer availability");
            }
            preparedStatement.close();
            connection.close();
        } catch (SQLException e){
            e.printStackTrace();
        }
    }


    public static void scheduleTrainingSession(int trainerID, int memberID, int timeSlotID){
        try{
            Connection connection = DriverManager.getConnection(url,user,password);
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO Member_Trainer_Session (TrainerID, MemberID, TimeSlotID) VALUES (?, ?, ?);");
            preparedStatement.setInt(1, trainerID);
            preparedStatement.setInt(2, memberID);
            preparedStatement.setInt(3, timeSlotID);
            int rowsAffected = preparedStatement.executeUpdate();
            // Check if the insertion was successful
            if (rowsAffected > 0) {
                System.out.println("Registered in class successfully.");
            } else {
                System.out.println("Failed to register in class");
            }
            preparedStatement.close();

            //trainer availability to false for timeslot id
            PreparedStatement preparedStatement2 = connection.prepareStatement("UPDATE Trainer SET Available = false WHERE TrainerID = ? AND TimeSlotID = ?");
            preparedStatement2.setInt(1, trainerID);
            preparedStatement2.setInt(2, timeSlotID);
            int rowsAffected2 = preparedStatement2.executeUpdate();
            // Check if the insertion was successful
            if (rowsAffected2 > 0) {
                System.out.println("Trainer availability removed");
            } else {
                System.out.println("Failed to remove trainer availability");
            }
            preparedStatement2.close();

            connection.close();
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    //displays all the Member Trainer Sessions for a specific member
    public static int displayMemberTrainingSessions(int memberID){
        int trainingSessions = 0;
        try {
            Connection connection = DriverManager.getConnection(url, user, password);
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM Member_Trainer_Session WHERE MemberID = ?");
            preparedStatement.setInt(1, memberID);
            ResultSet resultSet = preparedStatement.executeQuery();
            if(!resultSet.next()){
                System.out.println("You are currently not enrolled in an training sessions");
            }else {
                do{
                    trainingSessions++;
                    System.out.println("Session ID: " + resultSet.getInt("MTSessionID") + ", "
                            + "Trainer ID: " + resultSet.getInt("TrainerID") + ", "
                            + convertTimeSlotToString(resultSet.getInt("TimeSlotID")));
                } while (resultSet.next());
            }
            resultSet.close();
            preparedStatement.close();
            connection.close();
        } catch (SQLException e){
            e.printStackTrace();
        }
        return trainingSessions;
    }

    //displays the available schedule for trainers
    public static void displayAvailableTrainerSchedule(){
        try {
            Connection connection = DriverManager.getConnection(url, user, password);
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM Trainer WHERE Available = true");
            //preparedStatement.setInt(1, memberID);
            ResultSet resultSet = preparedStatement.executeQuery();
            if(!resultSet.next()){
                System.out.println("There are currently no available trainers");
            }else {
                do{
                    System.out.println(resultSet.getInt("TrainerID") + ", "
                            + convertTimeSlotToString(resultSet.getInt("TimeSlotID")));
                }while (resultSet.next());
            }

            resultSet.close();
            preparedStatement.close();
            connection.close();
        } catch (SQLException e){
            e.printStackTrace();
        }
    }

    //displays the entire schedule for specific trainer
    public static void displaySingleTrainerSchedule(int trainerID){
        try {
            Connection connection = DriverManager.getConnection(url, user, password);
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM Trainer WHERE TrainerID = ?");
            preparedStatement.setInt(1, trainerID);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                System.out.println(resultSet.getInt("TrainerID") + ", "
                        + convertTimeSlotToString(resultSet.getInt("TimeSlotID")) + ", "
                        + resultSet.getBoolean("Available"));
            }
            resultSet.close();
            preparedStatement.close();
            connection.close();
        } catch (SQLException e){
            e.printStackTrace();
        }
    }

    /////////////////////TRAINING SESSION / TRAINER FUNCTIONS END /////////////////

    /////////////////////FITNESS CLASS FUNCTIONS START /////////////////

    //verify if a member has already booked a fitness class in a specific timeslot
    //also verify that the fitness class is not being overbooked
    public static boolean checkMemberFitnessClassAvailability(int memberID, int timeSlotID){
        boolean available = true;
        try {
            Connection connection = DriverManager.getConnection(url, user, password);
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM Fitness_Class JOIN Registered ON Fitness_Class.ClassID = Registered.ClassID WHERE Registered.MemberID = ? AND Fitness_Class.TimeSlotID = ?");
            preparedStatement.setInt(1, memberID);
            preparedStatement.setInt(2, timeSlotID);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){ //if enters means rows where returned, thus member shoudlnt be allowed to take a class.
                available = false;
            }
            resultSet.close();
            preparedStatement.close();
            connection.close();
        } catch (SQLException e){
            e.printStackTrace();
        }
        return available;
    }

    //register a member into a specific fitness class
    public static void registerInFitnessClass(int memberID, int classID){
        try{
            Connection connection = DriverManager.getConnection(url,user,password);
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO Registered (ClassID, MemberID) VALUES (?, ?);");
            preparedStatement.setInt(1, classID);
            preparedStatement.setInt(2, memberID);
            int rowsAffected = preparedStatement.executeUpdate();
            // Check if the insertion was successful
            if (rowsAffected > 0) {
                System.out.println("Registered in class successfully.");
            } else {
                System.out.println("Failed to register in class");
            }
            preparedStatement.close();
            connection.close();
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    //show all available fitness classes
    public static void displayAllFitnessClasses(){
        System.out.println("Displaying All Fitness Classes: ");
        try {
            Connection connection = DriverManager.getConnection(url, user, password);
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM Fitness_Class;");
            //preparedStatement.setInt(1, memberID);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                System.out.println("Class ID: "+resultSet.getInt("ClassID") + ", "
                        + "Room ID: "+resultSet.getInt("RoomID") + ", "
                        + "Class Name: "+resultSet.getString("ClassName") + ", "
                        + "Time Slot: "+convertTimeSlotToString(resultSet.getInt("TimeSlotID")));
            }
            resultSet.close();
            preparedStatement.close();
            connection.close();
        } catch (SQLException e){
            e.printStackTrace();
        }
    }
    /////////////////////FITNESS CLASS FUNCTIONS END /////////////////


    /////////////////////ROOM BOOKING FUNCTIONS START /////////////////
    //create reservation
    public static void createReservation(int roomID, int timeSlotID){
        Scanner reader = new Scanner(System.in);
        System.out.println("Please enter the name of the class you want to create:");
        String className = reader.nextLine();
        try{
            Connection connection = DriverManager.getConnection(url,user,password);
            PreparedStatement preparedStatement = connection.prepareStatement("UPDATE Room SET IsBooked = true WHERE RoomID = ? AND TimeSlotID = ?");
            preparedStatement.setInt(1, roomID);
            preparedStatement.setInt(2, timeSlotID);
            int rowsAffected = preparedStatement.executeUpdate();
            // Check if the insertion was successful
            if (rowsAffected > 0) {
                System.out.println("Room reservation created successfully.");
                PreparedStatement preparedStatement2 = connection.prepareStatement("INSERT INTO Fitness_Class (RoomID, TimeSlotID, ClassName) VALUES (?,?,?)");
                preparedStatement2.setInt(1, roomID);
                preparedStatement2.setInt(2, timeSlotID);
                preparedStatement2.setString(3, className);
                int rowsAffected2 = preparedStatement.executeUpdate();
                if(rowsAffected2 > 0){
                    System.out.println("Fitness class reservation successfully created");
                }else{
                    System.out.println("Fitness class reservation failed to create");
                    System.out.println("Removing room reservation");
                    PreparedStatement preparedStatement3 = connection.prepareStatement("UPDATE Room SET IsBooked = false WHERE RoomID = ? AND TimeSlotID = ?");
                    preparedStatement3.setInt(1, roomID);
                    preparedStatement3.setInt(2, timeSlotID);
                    preparedStatement3.close();
                }
                preparedStatement2.close();

            } else {
                System.out.println("Reservation creation failed.");
            }
            preparedStatement.close();


            connection.close();
        }catch (SQLException e){
            e.printStackTrace();
        }
    }



    //update reservation (change rooms)
    public static void updateReservation(int oldRoomID, int oldRoomTimeSlot, int newRoomID, int newRoomTimeSlot){
        try{
            Connection connection = DriverManager.getConnection(url,user,password);
            PreparedStatement preparedStatement = connection.prepareStatement("UPDATE Fitness_Class SET RoomID = ?, TimeSlotID = ? WHERE RoomID = ? AND TimeSlotID = ?");
            preparedStatement.setInt(1, newRoomID);
            preparedStatement.setInt(2, newRoomTimeSlot);
            preparedStatement.setInt(3, oldRoomID);
            preparedStatement.setInt(4, oldRoomTimeSlot);
            int rowsAffected = preparedStatement.executeUpdate();
            // Check if the insertion was successful
            if (rowsAffected > 0) {
                System.out.println("Reservation updated successfully.");
            } else {
                System.out.println("Reservation update failed.");
            }

            preparedStatement.close();

            //toggle old room availability
            PreparedStatement preparedStatement2 = connection.prepareStatement("UPDATE Room SET IsBooked = false WHERE RoomID = ? AND TimeSlotID = ?");
            preparedStatement2.setInt(1, oldRoomID);
            preparedStatement2.setInt(2, oldRoomTimeSlot);
            preparedStatement2.executeUpdate();
            preparedStatement2.close();

            //toggle new room availability
            PreparedStatement preparedStatement3 = connection.prepareStatement("UPDATE Room SET IsBooked = true WHERE RoomID = ? AND TimeSlotID = ?");
            preparedStatement3.setInt(1, newRoomID);
            preparedStatement3.setInt(2, newRoomTimeSlot);
            preparedStatement3.executeUpdate();
            preparedStatement3.close();

            connection.close();
        }catch (SQLException e){
            e.printStackTrace();
        }
    }


    //remove reservation
    public static void deleteReservation(int roomID, int timeSlotID){
        try{
            Connection connection = DriverManager.getConnection(url,user,password);
            PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM Fitness_Class WHERE RoomID = ? AND TimeSlotID = ?");
            preparedStatement.setInt(1, roomID);
            preparedStatement.setInt(2, timeSlotID);
            int rowsAffected = preparedStatement.executeUpdate();
            // Check if the insertion was successful
            if (rowsAffected > 0) {
                System.out.println("Reservation deleted successfully.");
            } else {
                System.out.println("Reservation deletion failed.");
            }

            preparedStatement.close();

            PreparedStatement preparedStatement2 = connection.prepareStatement("UPDATE Room SET IsBooked = false WHERE RoomID = ? AND TimeSlotID = ?");
            preparedStatement2.setInt(1, roomID);
            preparedStatement2.setInt(2, timeSlotID);
            int rowsAffected2 = preparedStatement2.executeUpdate();
            // Check if the insertion was successful
            if (rowsAffected2 > 0) {
                System.out.println("Room is now updated to not booked");
            } else {
                System.out.println("Room booking toggle failed");
            }
            preparedStatement2.close();

            connection.close();
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    //display all the available rooms
    public static void displayAvailableRooms(){
        System.out.println("Displaying available room timeslots: ");
        try {
            Connection connection = DriverManager.getConnection(url, user, password);
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM Room WHERE IsBooked = false;");
            //preparedStatement.setInt(1, memberID);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                System.out.println("Room ID: "+resultSet.getInt("RoomID") + ", "
                        + "Time Slot: "+ convertTimeSlotToString(resultSet.getInt("TimeSlotID")));
            }
            resultSet.close();
            preparedStatement.close();
            connection.close();
        } catch (SQLException e){
            e.printStackTrace();
        }
    }



    //display all rooms
    public static void displayAllRooms(){
        try {
            Connection connection = DriverManager.getConnection(url, user, password);
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM Room;");
            //preparedStatement.setInt(1, memberID);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                System.out.println("Room ID: "+resultSet.getInt("RoomID") + ", "
                        + "Time Slot: "+ convertTimeSlotToString(resultSet.getInt("TimeSlotID")));
            }
            resultSet.close();
            preparedStatement.close();
            connection.close();
        } catch (SQLException e){
            e.printStackTrace();
        }
    }

    /////////////////////ROOM BOOKING FUNCTIONS END /////////////////


    //takes an int timeslotid and produces the day and hour that it corresponds to
    public static String convertTimeSlotToString(int timeSlotID){
        String timeSlotString = null;

        try {
            Connection connection = DriverManager.getConnection(url, user, password);
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM Time_Slot WHERE TimeSlotID = ?");
            preparedStatement.setInt(1, timeSlotID);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                timeSlotString = resultSet.getString("TimeSlotDay") + " " + resultSet.getInt("StartHr");
            }
            resultSet.close();
            preparedStatement.close();
            connection.close();
        } catch (SQLException e){
            e.printStackTrace();
        }

        return timeSlotString;
    }

    //comvert time slot string to time slot id number
    public static int convertTimeSlotToInt(String timeSlotString){
        int timeSlotID = -1;
        String[] timeSlotStringArr = timeSlotString.split("\\s+");
        try {
            Connection connection = DriverManager.getConnection(url, user, password);
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM Time_Slot WHERE TimeSlotDay = ? AND StartHr = ?");
            preparedStatement.setString(1, timeSlotStringArr[0]);
            preparedStatement.setInt(2, Integer.parseInt(timeSlotStringArr[1]));
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                timeSlotID = resultSet.getInt("TimeSlotID");
            }
            resultSet.close();
            preparedStatement.close();
            connection.close();
        } catch (SQLException e){
            e.printStackTrace();
        }

        return timeSlotID;
    }

}