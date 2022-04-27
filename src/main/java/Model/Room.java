package Model;

import java.util.ArrayList;

public class Room {
    private ArrayList<Character> myRoomInventory = new ArrayList<>();

    protected Room() {
        //is there a way to make this a switch statement?
        if (Math.random() < .1) {
            setMyRoomInventory(RoomType.HEALING);
        }
        if (Math.random() < .05) {
            setMyRoomInventory(RoomType.VISION);
        }
        if (Math.random() < .1) {
            setMyRoomInventory(RoomType.PIT);
        }
        if (Math.random() < .1) {
            setMyRoomInventory(RoomType.FIGHT);
        }

    }

    void setMyRoomInventory(RoomType theType) {
        myRoomInventory.add(theType.type);
    }

    ArrayList<Character> getMyRoomInventory(){
        return myRoomInventory;
    }
    
    void removeMyTypes(RoomType theType){
        myRoomInventory.remove((Character) theType.type);
    }
    public static void main(String[] args) {
    Room myRoom = new Room();
        System.out.println(myRoom.getMyRoomInventory());
    myRoom.setMyRoomInventory(RoomType.NORMAL);
        System.out.println(myRoom.getMyRoomInventory());
        myRoom.setMyRoomInventory(RoomType.FIGHT);
        System.out.println(myRoom.getMyRoomInventory());
        myRoom.setMyRoomInventory(RoomType.PILLAR);
        System.out.println(myRoom.getMyRoomInventory());
        myRoom.removeMyTypes(RoomType.NORMAL);
        System.out.println(myRoom.getMyRoomInventory());
    }

}
