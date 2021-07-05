package com.group19.javafxgame.utils;

import com.group19.javafxgame.Rooms.RoomComponent;

import java.util.HashMap;
import java.util.Random;

public class RandomRoomUtils {

    private static RandomRoomUtils instance = new RandomRoomUtils();
    private static String[] leftRooms = new String[]{"Middle1.tmx", "Middle2.tmx","Tunnel1.tmx"};
    private static String[] rightRooms = new String[]{"Middle1.tmx", "Middle2.tmx", "Tunnel1.tmx"};
    private static String[] topRooms = new String[]{"Middle1.tmx", "Middle2.tmx"};
    private static String[] botRooms = new String[]{"Middle1.tmx", "Middle2.tmx"};
    private static String[] finalLeft = new String[]{"FinalLeft.tmx"};
    private static String[] finalRight = new String[]{"FinalRight.tmx"};
    private static String[] finalTop = new String[]{"FinalTop.tmx"};
    private static String[] finalBot = new String[]{"FinalBot.tmx"};

    private static HashMap<String, String[]> roomMap = new HashMap<>();

    static {
        roomMap.put("left", leftRooms);
        roomMap.put("right", rightRooms);
        roomMap.put("top", topRooms);
        roomMap.put("bottom", botRooms);
        roomMap.put("finalleft", finalLeft);
        roomMap.put("finalright", finalRight);
        roomMap.put("finaltop", finalTop);
        roomMap.put("finalbottom", finalBot);

    }

    private RandomRoomUtils() {

    }

    private Random rand = new Random();

    public String getRandomRoom(String currRoomSide) {

        String[] roomList;

        if (currRoomSide.equals("left")) {
            roomList = roomMap.get("right");
        } else if (currRoomSide.equals("right")) {
            roomList = roomMap.get("left");
        } else if (currRoomSide.equals("top")) {
            roomList = roomMap.get("bottom");
        } else if (currRoomSide.equals("bottom")) {
            roomList = roomMap.get("top");
        } else if (currRoomSide.equals("finalleft")){
            roomList = roomMap.get("finalright");
        } else if (currRoomSide.equals("finalright")){
            roomList = roomMap.get("finalleft");
        } else if (currRoomSide.equals("finaltop")){
            roomList = roomMap.get("finalbottom");
        }else if (currRoomSide.equals("finalbottom")){
            roomList = roomMap.get("finaltop");
        }else {
            throw new IllegalArgumentException(currRoomSide + " is not" +
                    "valid as a side");
        }
        System.out.println(currRoomSide);
        System.out.println(roomList);
        return roomList[rand.nextInt(roomList.length)];
    }

    public static RandomRoomUtils getInstance() {
        return instance;
    }
}
