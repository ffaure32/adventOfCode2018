package day9;

public class Marble {
    private final int marbleIndex;
    private Marble previous;
    private Marble next;

    public Marble(int marbleIndex) {
        this.marbleIndex = marbleIndex;
    }

    public void setPrevious(Marble previous) {
        this.previous = previous;
        if(this != previous) {
            previous.next = this;
        }
    }

    public void setNext(Marble next) {
        this.next = next;
        if(this != next) {
            next.previous = this;
        }
    }

    public Marble getPrevious() {
        return previous;
    }

    public Marble getNext() {
        return next;
    }

    public int getMarbleIndex() {
        return marbleIndex;
    }

    public Marble moveLeft(int moves) {
        Marble leftMarble = this;
        for (int i = 0; i < moves; i++) {
            leftMarble = leftMarble.previous;
        }
        return leftMarble;
    }

    public Marble moveRight(int moves) {
        Marble rightMarble = this;
        for (int i = 0; i < moves; i++) {
            rightMarble = rightMarble.next;
        }
        return rightMarble;
    }

    void insertBefore(Marble newMarble) {
        Marble left = getPrevious();
        newMarble.setNext(this);
        newMarble.setPrevious(left);
    }
}
