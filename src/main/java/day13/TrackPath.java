package day13;

import day10.Position;

public class TrackPath {
    public final Position position;
    public final PathType pathType;
    private Cart cart;

    public TrackPath(int x, int y, char input) {
        this.position = new Position(x, y);
        this.pathType = from(input);
    }

    public int getX() {
        return position.x;
    }

    public int getY() {
        return position.y;
    }

    public void setCart(Cart cart) {
        this.cart = cart;
    }

    public PathType from(char input) {
        CartDirection ct = CartDirection.fromType(input);
        PathType pt;
        if(ct !=null) {
            pt = ct.getPathType();
            this.cart = new Cart(ct, position);
        } else {
            pt = PathType.fromType(input);
        }
        return pt;
    }

    public void print() {
        if(cart != null) {
            cart.print();
        } else {
            pathType.print();
        }
    }

    public Cart getCart() {
        return cart;
    }
}
