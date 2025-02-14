package com.example;

public class Room extends Hotel {
    private final int id_room;
    private double price;
    private String bookStatus;
    private String roomType; 
    private final int user_id;


   
    public Room(int roomNum, double price, String roomType, int user_id){
        this.id_room = roomNum;
        this.price = price;
        this.roomType = roomType;
        this.user_id = user_id;
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







    public int getUser_id() {
        return user_id;
    }






    @Override

    public String toString(){
        return "Room number :" + id_room + "\nRoom price :" + price + "\nRoom status :" + bookStatus + "\nRoom type :" + roomType + "\n ";
    }



    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + id_room;
        long temp;
        temp = Double.doubleToLongBits(price);
        result = prime * result + (int) (temp ^ (temp >>> 32));
        result = prime * result + ((bookStatus == null) ? 0 : bookStatus.hashCode());
        result = prime * result + ((roomType == null) ? 0 : roomType.hashCode());
        result = prime * result + user_id;
        return result;
    }


    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Room other = (Room) obj;
        if (id_room != other.id_room)
            return false;
        if (Double.doubleToLongBits(price) != Double.doubleToLongBits(other.price))
            return false;
        if (bookStatus == null) {
            if (other.bookStatus != null)
                return false;
        } else if (!bookStatus.equals(other.bookStatus))
            return false;
        if (roomType == null) {
            if (other.roomType != null)
                return false;
        } else if (!roomType.equals(other.roomType))
            return false;
        if (user_id != other.user_id)
            return false;
        return true;
    }

    

}
            