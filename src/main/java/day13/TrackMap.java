package day13;

import day10.Position;

import java.util.*;

public class TrackMap {
    public final Map<Integer, List<TrackPath>> map;
    public final List<Cart> carts;
    public Position firstCollision;

    public TrackMap(List<String> input) {
        carts = new ArrayList<>();
        map = new HashMap<>();
        for (int i = 0; i < input.size(); i++) {
            List<TrackPath> line = new ArrayList<>();
            map.put(i, line);
            String mapLine = input.get(i);
            char[] pathTracks = mapLine.toCharArray();
            for (int j = 0; j < pathTracks.length; j++) {
                TrackPath tp = new TrackPath(j, i, pathTracks[j]);
                line.add(tp);
                Cart cart = tp.getCart();
                if (cart != null) {
                    carts.add(cart);
                }
            }

        }
    }

    public void move() {
        carts.stream().sorted().forEach(c -> {
            TrackPath previousTrackPath = getTrackPath(c.position);
            previousTrackPath.setCart(null);
            c.move();
            TrackPath newTrackPath = getTrackPath(c.position);
            c.newDirection(newTrackPath.pathType);
            if (isCollision(c)) {
                if (firstCollision == null) {
                    firstCollision = c.position;
                }
                carts.remove(newTrackPath.getCart());
                carts.remove(c);
                newTrackPath.setCart(null);
            } else {
                newTrackPath.setCart(c);
            }

        });
    }


    private boolean isCollision(Cart c) {
        return carts.stream().anyMatch(ca -> ca != c && c.position.equals(ca.position));
    }

    private TrackPath getTrackPath(Position position) {
        return map.get(position.y).get(position.x);
    }

    public void print() {
        map.entrySet().stream().sorted(Comparator.comparing(es -> es.getKey())).forEach(es -> {
            es.getValue().stream().forEach(tp -> tp.print());
            System.out.println();
        });
    }
}
