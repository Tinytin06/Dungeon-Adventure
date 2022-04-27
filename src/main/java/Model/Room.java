package Model;

public class Room {
    public Type myType;
    protected Room() {

    }

    void setMyType(Type theType) {
        myType = theType;
    }

    public static void main(String[] args) {
    Room myRoom = new Room();
    myRoom.setMyType(Type.NORMAL);
        System.out.println();
    }

}
