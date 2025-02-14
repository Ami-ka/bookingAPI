package com.example;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.json.JSONArray;
import org.json.JSONObject;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;

public class HotelBookingSystem {
    private static final String URL = "jdbc:postgresql://localhost:5432/hotel_db"; // Change as per your database
    private static final String USER = "postgres"; // Change to your PostgreSQL username
    private static final String PASSWORD = "1111"; // Change to your PostgreSQL password

    public static void main(String[] args) throws IOException{
        HttpServer server = HttpServer.create(new InetSocketAddress(8080), 0);
        server.createContext("/room", new RoomHandler());
        server.createContext("/user", new UserHandler());
        
        server.setExecutor(null);
        server.start();
        System.out.println("Server started on port 8080...");
    }

    static class RoomHandler implements HttpHandler {
        @Override
        public void handle(HttpExchange exchange) throws IOException{
            String method = exchange.getRequestMethod();

            switch (method) {
                case "GET":
                    handleGetRoom(exchange);
                    break;
                case "POST":
                    handleCreateRoom(exchange);
                    break;
                default:
                    sendResponse(exchange, 405, "Method Not Allowed");
            }
        }

        private void handleGetRoom(HttpExchange exchange) throws IOException{
            JSONArray rooms = new JSONArray();
            try (Connection con = DriverManager.getConnection(URL, USER, PASSWORD)){
                Statement stm = con.createStatement();
                ResultSet rs = stm.executeQuery("SELECT * FROM rooms");
                while (rs.next()) {
                    JSONObject room = new JSONObject();
                    room.put("id_room", rs.getInt("id_room"));
                    room.put("price", rs.getDouble("price"));
                    room.put("bookStatus", rs.getString("bookStatus"));
                    room.put("roomType", rs.getString("roomType"));
                    room.put("user_id", rs.getInt("user_id"));
                    rooms.put(room);
                }
            } catch (SQLException e) {
                e.printStackTrace();
                sendResponse(exchange, 500, "Database Error");
                return;
            }
            sendResponse(exchange, 200, rooms.toString());
        }
        private void handleCreateRoom(HttpExchange exchange) throws IOException {
            String requestBody = readRequestBody(exchange);
            JSONObject json = new JSONObject(requestBody);  

            Double price = json.getDouble("price");
            String password = json.getString("bookStatus");
            String roomType = json.getString("roomType");
            int user_id = json.getInt("user_id");

            try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
                    PreparedStatement pstmt = conn.prepareStatement(
                            "INSERT INTO rooms (price, bookStatus, roomType, user_id) VALUES (?, ?, ?, ?) RETURNING id_room")) {
                pstmt.setDouble(1, price);
                pstmt.setString(2, password);
                pstmt.setString(3, roomType);
                pstmt.setInt(4,user_id);
                
                
                ResultSet rs = pstmt.executeQuery();
                if (rs.next()) {
                    int id_room = rs.getInt("id_room");
                    sendResponse(exchange, 201, "{\"id_room\":" + id_room + "}");
                }
            } catch (SQLException e) {
                e.printStackTrace();
                sendResponse(exchange, 500, "Database Error");
            }
        }
    }

    static class UserHandler implements HttpHandler{
        @Override
        public void handle(HttpExchange exchange) throws IOException {
            String method = exchange.getRequestMethod();

            switch (method) {
                case "GET":
                    handleGetUser(exchange);
                    break;
                case "POST":
                    handleCreateUser(exchange);
                    break;
                default:
                    sendResponse(exchange, 405, "Method Not Allowed");
            }
        }
        public void handleGetUser(HttpExchange exchange) throws IOException{
            JSONArray users = new JSONArray();
            try (Connection con = DriverManager.getConnection(URL, USER, PASSWORD)){
                Statement stm = con.createStatement();
                ResultSet rs = stm.executeQuery("SELECT * FROM users");
                while (rs.next()) {
                    JSONObject user = new JSONObject();
                    user.put("user_id", rs.getInt("user_id"));
                    user.put("user_login", rs.getString("user_login"));
                    user.put("user_password", rs.getString("user_password"));
                    users.put(user);
                }
            }     
            catch (SQLException e) {
                e.printStackTrace();
                sendResponse(exchange, 500, "Database Error");
                return;
            }
            sendResponse(exchange, 200, users.toString());
        }


        public void handleCreateUser(HttpExchange exchange) throws IOException{
            String requestBody = readRequestBody(exchange);
            JSONObject json = new JSONObject(requestBody);

            String user_login = json.getString("user_login");
            String user_password = json.getString("user_password");

            try (Connection con = DriverManager.getConnection(URL, USER, PASSWORD);
                 PreparedStatement pstmt= con.prepareStatement("INSERT INTO users (user_login, user_password) VALUES(?,?) RETURNING user_id")) {
                    pstmt.setString(1, user_login);
                    pstmt.setString(2, user_password);
                    ResultSet rs = pstmt.executeQuery();
                    if (rs.next()) {
                        int user_id = rs.getInt("user_id");
                        sendResponse(exchange, 201, "{\"user_id\":" + user_id + "}");
                    }
            } catch (Exception e) {
                e.printStackTrace();
                sendResponse(exchange, 500, "Database Error");
            }



        }
    }






    private static String readRequestBody(HttpExchange exchange) throws IOException {
        InputStream inputStream = exchange.getRequestBody();
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
        StringBuilder sb = new StringBuilder();
        String line;
        while ((line = reader.readLine()) != null) {
            sb.append(line);
        }
        return sb.toString();
    }


    private static void sendResponse(HttpExchange exchange, int statusCode, String response) throws IOException {
        exchange.getResponseHeaders().set("Content-Type", "application/json");
        exchange.sendResponseHeaders(statusCode, response.getBytes().length);
        OutputStream os = exchange.getResponseBody();
        os.write(response.getBytes());
        os.close();
    }
}


