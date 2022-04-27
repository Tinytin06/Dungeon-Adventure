package Model;

import java.util.ArrayList;
import java.util.HashSet;

import static Model.RoomType.*;

public class Room {
    private HashSet<Character> myRoomInventory = new HashSet<>();
    private String myDisplayIcon = "";
    protected Room() {
        //is there a way to make this a switch statement?
        if (Math.random() < .1) {
            setMyRoomInventory(HEALING);
        }
        if (Math.random() < .05) {
            setMyRoomInventory(VISION);
        }
        if (Math.random() < .1) {
            setMyRoomInventory(PIT);
        }
        if (Math.random() < .1) {
            setMyRoomInventory(FIGHT);
        }
        if(myRoomInventory.isEmpty()){
            setMyRoomInventory(NORMAL);
        }

    }

    protected void setMyDisplayIcon(final String theIcon) {
        if (theIcon.length() > 2 || theIcon.length() <= 0) {
            throw new IllegalArgumentException("The Icon string length cannot be greater than 1 or less than 0");//need to change this to not throw exception
        } else {
            myDisplayIcon = theIcon;
        }
    }

    void setMyRoomInventory(RoomType theType) {
        myRoomInventory.add(theType.type);
    }

    HashSet<Character> getMyRoomInventory(){
        return myRoomInventory;
    }
    
    void removeMyTypes(RoomType theType){
        myRoomInventory.remove(theType.type);
    }

    public String toString() {
        if (myRoomInventory.contains(PLAYER.type)){
            return "* ";
        }
        return myDisplayIcon;
    }

    protected void setEmptyRoom() {
        myRoomInventory.clear();
    }

    protected void exploreTheRoom(){
        if (myRoomInventory.size() == 1) {
            for (RoomType roomTypes : RoomType.values()) {
                if (myRoomInventory.contains(roomTypes.type)) {
                    setMyDisplayIcon(roomTypes + " ");
                }
            }
        }
    }

    public static void main(String[] args) {
    Room myRoom = new Room();
        System.out.println(myRoom.getMyRoomInventory());
        myRoom.setMyRoomInventory(NORMAL);
        System.out.println(myRoom.getMyRoomInventory());
        myRoom.setMyRoomInventory(FIGHT);
        System.out.println(myRoom.getMyRoomInventory());
        myRoom.setMyRoomInventory(PILLAR);
        myRoom.setMyRoomInventory(PLAYER);
        System.out.println(myRoom.getMyRoomInventory());
        myRoom.removeMyTypes(NORMAL);
        System.out.println(myRoom);
        myRoom.exploreTheRoom();
    }

}
