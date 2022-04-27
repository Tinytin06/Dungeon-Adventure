package Model;

public class Room {
    public char myType;
    protected Room() {

    }

    void setMyType(RoomType theType) {
        myType = theType.type;
    }

    public static void main(String[] args) {
    Room myRoom = new Room();
    myRoom.setMyType(RoomType.NORMAL);
        System.out.println(myRoom.myType);
        myRoom.setMyType(RoomType.FIGHT);
        System.out.println(myRoom.myType);
    }

}
