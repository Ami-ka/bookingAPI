package com.example;

public class Room extends Hotel {
    private final int id_room;
    private double price;
    private String bookStatus;
    private String roomType; 


   
    public Room(int roomNum, double price, String roomType){
        this.id_room = roomNum;
        this.price = price;
        this.roomType = roomType;
    }

    


    
    
    


    public int getId_room() {
        return id_room;
    }









    








    public double getPrice() {
        return price;
    }









    public void setPrice(double price) {
        this.price = price;
    }









    public String getBookStatus() {
        return bookStatus;
    }









    public void setBookStatus(String bookStatus) {
        this.bookStatus = bookStatus;
    }









    public String getRoomType() {
        return roomType;
    }









    public void setRoomType(String roomType) {
        this.roomType = roomType;
    }









    @Override

    public String toString(){
        return "Room number :" + id_room + "\nRoom price :" + price + "\nRoom status :" + bookStatus + "\nRoom type :" + roomType + "\n ";
    }

}
            