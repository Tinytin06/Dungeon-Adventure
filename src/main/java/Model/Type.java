package Model;

public enum Type {
     FIGHT('F'), PIT('P'), NORMAL('N'), PILLAR('P');

    public final char label;
    Type(char theLabel) {
    this.label = theLabel;
    }
}
