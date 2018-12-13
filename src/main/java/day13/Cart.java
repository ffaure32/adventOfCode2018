package day13;

import day10.Position;

public class Cart implements Comparable<Cart> {

    @Override
    public int compareTo(Cart o) {
        int thisRow = this.position.y;
        int otherRow = o.position.y;
        int result = thisRow - otherRow;
        if(result == 0) {
            return this.hashCode() - o.hashCode();
        } else {
            return result;
        }
    }

    public CartDirection direction;
    public Position position;
    private Turn nextTurn;

    public Cart(CartDirection direction, Position position) {
        this.direction = direction;
        this.position = position;
        this.nextTurn = Turn.LEFT;
    }

    public void move() {
        switch (direction) {
            case UP:
                position = new Position(position.x, position.y - 1);
                break;
            case DOWN:
                position = new Position(position.x, position.y + 1);
                break;
            case LEFT:
                position = new Position(position.x - 1, position.y);
                break;
            case RIGHT:
                position = new Position(position.x + 1, position.y);
                break;
            default:
                throw new RuntimeException();
        }
    }

    public void newDirection(PathType trackPath) {
        switch (trackPath) {
            case SLASH:
                direction = CartDirection.nextDirectionForSlash(direction);
                break;
            case BACKSLASH:
                direction = CartDirection.nextDirectionForBacklash(direction);
                break;
            case INTERSECTION:
                direction = CartDirection.nextDirectionForIntersection(direction, nextTurn);
                nextTurn = Turn.nextTurn(nextTurn);
                break;
            default:
                return;
        }
    }

    public void print() {
        System.out.print(direction.dirChar);
    }


}
