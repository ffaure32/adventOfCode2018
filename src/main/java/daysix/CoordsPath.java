package daysix;

public class CoordsPath {
    public final int coordId;
    public final int shortestPath;

    public CoordsPath(int coordId, int shortestPath) {
        this.coordId = coordId;
        this.shortestPath = shortestPath;
    }

    public int getCoordId() {
        return coordId;
    }

    public int getShortestPath() {
        return shortestPath;
    }
}
