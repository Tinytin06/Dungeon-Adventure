package Model;

import java.util.ArrayList;
import java.util.HashSet;
import static Model.RoomType.*;

public class Room {
    private HashSet<Character> myRoomInventory = new HashSet<>();
    private String myDisplayIcon = "";
    Room() {
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

    void setMyDisplayIcon(final String theIcon) {
        if (theIcon.length() > 2 || theIcon.length() <= 0) {

            throw new IllegalArgumentException("The Icon string "+theIcon.length()+ " cannot be greater than 1 or less than 0");//need to change this to not throw exception
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

    void setEmptyRoom() {
        myRoomInventory.clear();
    }

    void exploreTheRoom(){
        if (myRoomInventory.size() == 1) {
            for (RoomType roomTypes : RoomType.values()) {
                if (myRoomInventory.contains(roomTypes.type)) {
                    setMyDisplayIcon(roomTypes.type + " ");
                }
            }
        }
    }

    void setEntrance(){
        setEmptyRoom();
        setMyRoomInventory(ENTRANCE);
    }

    void setExit(){
        setEmptyRoom();
        setMyRoomInventory(EXIT);
    }

    public static void main(String[] args) {
    Room myRoom = new Room();
        myRoom.exploreTheRoom();
        System.out.println(myRoom);
    }

}
