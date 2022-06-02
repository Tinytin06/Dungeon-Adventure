package Model;

// YO

import Model.Characters.Monster;

import java.io.Serializable;
import java.util.HashSet;
import static Model.RoomType.*;
/**
 * This is the Room class which populates the rooms and insures that each room is labeled correctly.
 * @author Varun Parbhakar
 * @editor Austin Luu
 */
public class Room implements Serializable {
    private static final long serialversionUID = 2291551312L;



    private HashSet<Character> myRoomInventory = new HashSet<>();
    private String myDisplayIcon = "? ";
    private int myPillarCount;


    public Room(final RoomType theType) {
        addTo_MyRoomInventory(theType);
    }


    /**
     * This is the room constructor which populates the room with healing and vision potions
     * and other items.
     */
    public Room() {
        //is there a way to make this a switch statement?
        if (Math.random() < .1) {
            addTo_MyRoomInventory(HEALING);
        }
        if (Math.random() < .05) {
            addTo_MyRoomInventory(VISION);
        }
        if (Math.random() < .1) {
            addTo_MyRoomInventory(PIT);
        }
        if (Math.random() < .1) {
            addTo_MyRoomInventory(FIGHT);
        }
        if(myRoomInventory.isEmpty()){
            addTo_MyRoomInventory(NORMAL);
        }

    }

    /**
     * This method sets the icon of the room with the passed String
     * @param theIcon (String text that represents the room)
     */
    public void setMyDisplayIcon(final String theIcon) {
        if (theIcon.length() > 2 || theIcon.length() <= 0) {
            throw new IllegalArgumentException("The Icon string "+theIcon.length()+ " cannot be greater than 1 or less than 0");//need to change this to not throw exception
        } else {
            myDisplayIcon = theIcon;
        }
    }

    public boolean hasRoomType (final RoomType theItem) {
        return myRoomInventory.contains(theItem.type);
    }


    /**
     * This method adds the passed item to the room inventory Set.
     * @param theType
     */
    public void addTo_MyRoomInventory(RoomType theType) {
        if(theType == INHERITANCE ||
                theType == ABSTRACTION ||
                theType == POLYMORPHISM||
                theType == ENCAPSULATION) {
            myPillarCount++;
            setMonster();
        }
        myRoomInventory.add(theType.type);
    }

    public boolean getHasPillar() {
        return myPillarCount > 0;
    }

    /**
     * This method return the room's inventory.
     * @return
     */
    public HashSet<Character> getMyRoomInventory(){
        return myRoomInventory;
    }

    /**
     * removes from myRoomInventory theType of room from set
     * @param theType
     */
    public void removeMyTypes(RoomType theType){
        if(theType == INHERITANCE ||
                theType == ABSTRACTION ||
                theType == POLYMORPHISM||
                theType == ENCAPSULATION) {
            myPillarCount--;
        }
        myRoomInventory.remove(theType.type);
    }

    /**
     * This toString method returns the room icon.
     * @return
     */
    public String toString() {
        //exploreTheRoom();
        if (myRoomInventory.contains(PLAYER.type)){
            return "* ";
        }
        return myDisplayIcon;
    }

    /**
     * clears room inventory so that the room has nothing in it not even normal room type
     */
    void setEmptyRoom() {
        myRoomInventory.clear();
    }

    /**
     * This is the explore method which searches the room and sets the icon of that room accordingly.
     */
    public void exploreTheRoom() {

        if (myRoomInventory.contains(ENTRANCE.type) ||
                myRoomInventory.contains(EXIT.type)) {
            if (myRoomInventory.contains(ENTRANCE.type)) {
                setMyDisplayIcon(ENTRANCE.type + " ");
                return;
            }
            if (myRoomInventory.contains(EXIT.type)) {
                setMyDisplayIcon(EXIT.type + " ");
                return;
            }

        } else if(myRoomInventory.contains(INHERITANCE.type) ||
                myRoomInventory.contains(POLYMORPHISM.type) ||
                myRoomInventory.contains(ENCAPSULATION.type) ||
                myRoomInventory.contains(ABSTRACTION.type)){
            int pillarCounter = 0;
            for (RoomType roomTypes : RoomType.getMYPillars()) {
                if (myRoomInventory.contains(roomTypes.type)) {
                    pillarCounter++;
                }
            }
            if (pillarCounter > 1) {
                setMyDisplayIcon("M ");
            } else {
                if (myRoomInventory.contains(INHERITANCE.type)) {
                    setMyDisplayIcon(INHERITANCE.type + " ");
                    return;
                }
                if (myRoomInventory.contains(ABSTRACTION.type)) {
                    setMyDisplayIcon(ABSTRACTION.type + " ");
                    return;
                }
                if (myRoomInventory.contains(ENCAPSULATION.type)) {
                    setMyDisplayIcon(ENCAPSULATION.type + " ");
                    return;
                }
                if (myRoomInventory.contains(POLYMORPHISM.type)) {
                    setMyDisplayIcon(POLYMORPHISM.type + " ");
                    return;
                }
            }
        }else if (myRoomInventory.size() == 1) {
            for (RoomType roomTypes : RoomType.values()) {
                if (myRoomInventory.contains(roomTypes.type)) {
                    setMyDisplayIcon(roomTypes.type + " ");
                }
            }
        } else if (myRoomInventory.size() > 1) {
            setMyDisplayIcon("M ");
        }

    }

    /**
     * This methods sets the room to be the entrance.
     * @param
     */
    void setEntrance(){
        setEmptyRoom();
        addTo_MyRoomInventory(ENTRANCE);
        setMyDisplayIcon(ENTRANCE.type + " ");
    }
    /**
     * This methods sets the room to be the exit.
     * @param
     */
    void setExit(){
        setEmptyRoom();
        addTo_MyRoomInventory(EXIT);

        //If the exit needs to be shielded/hidden from the player the

    }

    /**
     * This methods sets the room to be a pillar fight.
     * @param
     */
    void setMonster(){
        myRoomInventory.add(FIGHT.type);
    }

    public HashSet showMyRoomInventory(){
      HashSet<Character> nonSpecialRooms = (HashSet<Character>) myRoomInventory.clone();
      nonSpecialRooms.remove(PLAYER.type);
      nonSpecialRooms.remove(NORMAL.type);
      return nonSpecialRooms;
    }

}
