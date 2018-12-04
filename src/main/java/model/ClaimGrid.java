package model;

public class ClaimGrid {
    public final int width;
    public final int height;
    public final int grid[][];


    public ClaimGrid(int width, int height) {
        this.width = width;
        this.height = height;
        grid = new int[width][height];
    }

    public void fillGrid(Claim cl) {
        for (int i = cl.leftEdge; i < cl.leftEdge+cl.width; i++) {
            for (int j = cl.topEdge; j < cl.topEdge+cl.height; j++) {
                grid[i][j] += 1;
            }
        }
    }

    public boolean isUnique(Claim cl) {
        for (int i = cl.leftEdge; i < cl.leftEdge+cl.width; i++) {
            for (int j = cl.topEdge; j < cl.topEdge+cl.height; j++) {
                if(grid[i][j] != 1) {
                    return false;
                }
            }
        }
        return true;
    }

}
