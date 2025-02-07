package com.example;    
import java.util.ArrayList;

abstract class Hotel {
    private static String name;
    private static String address;
    private static ArrayList<Room> rooms = new ArrayList<>();
    private static ArrayList<User> users = new ArrayList<>();



    public static void roomAdd(Room room){
        rooms.add(room);
    };

    public static void registration(User user){
        users.add(user);
    }

    public static String login(User user){
        
        for(User i : users){
            if ((i.get_login().equals(user.get_login())) && (i.get_password_hash() == user.get_password_hash() )){
                return "login sucessful";
                
            }
            
        }
        return "Wrong login or password";
    }


    static void showHotelINFO(){
        System.out.println("Hotel: " + name);
        System.out.println("Hotel address: " + address);
        System.out.println("Amount of rooms: " + rooms.size() + "\n");
        System.out.println("Rooms:\n");

        for(Room i : rooms){
            System.out.println(i.toString());
        }

        
    }

    

    public static void set_HotelINFO(String name, String address){
        Hotel.name = name;
        Hotel.address = address;
    }
}   



