package day15;

import com.google.common.collect.Lists;
import day10.Position;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;

public class FightMap {
    private final int attackPowerForElves;
    Map<Integer, List<MapElement>> map = new TreeMap<>();
    public Set<UnitFight> fighters;
    int count = 0;
    public long initNumberOfElves;

    public FightMap(List<String> lines) {
        this(lines, 3);
    }

    public FightMap(List<String> lines, int attackPowerForElves) {
        this.attackPowerForElves = attackPowerForElves;
        AtomicInteger countLines = new AtomicInteger();
        fighters = new TreeSet<>();
        lines.stream().forEach(l -> initLine(countLines.getAndIncrement(), l));
        initNumberOfElves = countElves();
    }

    public long countElves() {
        return fighters.stream().filter(f -> f.type == UnitFight.UnitType.ELF).count();
    }


    private void initLine(int countLines, String line) {
        AtomicInteger countCol = new AtomicInteger();
        map.put(countLines,
        line.chars().mapToObj(i -> (char) i)
                .map(c -> newMapElement(countCol.getAndIncrement(), countLines, c))
                .collect(toList()));

    }

    private MapElement newMapElement(int col, int row, Character c) {
        Position pos = new Position(col, row);
        MapElement mapElement = new MapElement(pos, c);
        if(mapElement.isUnit()) {
            UnitFight uf = new UnitFight(pos, UnitFight.UnitType.valueOf(mapElement.type.name()), attackPowerForElves);
            fighters.add(uf);
        }
        return mapElement;
    }

    public Set<UnitFight> getUnits() {
        return fighters;
    }

    public void playRound() {
        List<UnitFight> sorted = new ArrayList<>(fighters);
        Collections.sort(sorted);
        for (UnitFight unit : sorted) {
            if(unit.hitPoints>0) {
                if (!fightPossible(unit)) {
                    move(unit);
                }
                attack(unit);
                if (isFightOver()) {
                    return;
                }
            }
        }
        count++;
    }

    public int score() {
        Integer score = fighters.stream().map(f -> f.hitPoints).collect(Collectors.summingInt(i -> i));
        return (count) * score;
    }

    private boolean fightPossible(UnitFight unit) {
        List<Position> allValidSquaresForFight = getAllValidSquares(unit.position);
        return fighters.stream().filter(u -> u.type != unit.type).map(u -> u.position).anyMatch(u -> allValidSquaresForFight.contains(u));
    }

    private Optional<UnitFight> selectOpponent(UnitFight unit) {
        List<Position> allValidSquaresForFight = getAllValidSquares(unit.position);
        Optional<UnitFight> firstOpponent = fighters.stream().filter(u -> u.type != unit.type)
                .filter(u -> allValidSquaresForFight.contains(u.position)).sorted(Comparator.comparing(u -> u.hitPoints)).findFirst();
        return firstOpponent;

    }

    private void move(UnitFight unit) {
        List<UnitFight> targets = fighters.stream().filter(u -> u.type != unit.type).collect(Collectors.toList());
        List<Position> openTargets = targets.stream()
                .map(u -> getOpenSquares(u.position))
                .flatMap(l -> l.stream())
                .collect(Collectors.toList());
        if(!openTargets.contains(unit.position)) {
            FightPath nearestTarget = findNearestTarget(unit, openTargets);
            if(nearestTarget != null) {
                while (nearestTarget.parentPosition.parentPosition != null) {
                    nearestTarget = nearestTarget.parentPosition;
                }
                Position previousPosition = unit.position;
                map.get(previousPosition.y).get(previousPosition.x).type = MapElement.ElementType.OPEN;
                Position newPosition = nearestTarget.position;
                unit.position = newPosition;
                map.get(newPosition.y).get(newPosition.x).type = MapElement.ElementType.fromChar(unit.type.name().charAt(0));
            }
        }
    }

    private FightPath findNearestTarget(UnitFight unit, List<Position> openTargets) {
        Set<Position> alreadyMarked = new HashSet<>();
        Map<Integer, List<FightPath>> nearSquares = new HashMap<>();
        Position found = null;
        int count = 0;
        nearSquares.put(count++, Lists.newArrayList(new FightPath(unit.position, null)));
        while(found == null && count < map.size() * map.size()) {
            List<FightPath> posForIndex = new ArrayList<>();
            for(FightPath previous : nearSquares.get(count-1)) {
                List<Position> squaresAround = getOpenSquares(previous.position);
                squaresAround.removeAll(alreadyMarked);
                posForIndex.addAll(squaresAround.stream().map(p -> new FightPath(p, previous)).collect(Collectors.toList()));
                alreadyMarked.addAll(squaresAround);
            }
            nearSquares.put(count, new ArrayList<>(posForIndex));
            for(Position pos : openTargets) {
                Optional<FightPath> first = posForIndex.stream().filter(fp -> fp.position.equals(pos)).findFirst();
                if(first.isPresent()) {
                    return first.get();
                }
            }
            count++;
        }
        return null;
    }

    private List<Position> getAllValidSquares(Position position) {
        return squares(position).stream()
                .filter(me -> me.type != MapElement.ElementType.WALL)
                .map(me -> me.position)
                .collect(Collectors.toList());
    }

    private List<Position> getOpenSquares(Position position) {
        return squares(position).stream()
                .filter(me -> me.type == MapElement.ElementType.OPEN)
                .map(me -> me.position)
                .collect(Collectors.toList());
    }

    private void attack(UnitFight unit) {
        Optional<UnitFight> opponentOption = selectOpponent(unit);
        opponentOption.ifPresent(opponent -> {
            boolean killed = opponent.attacked(unit.attackPower);
            if(killed) {
                map.get(opponent.position.y).get(opponent.position.x).type = MapElement.ElementType.OPEN;
                Set<UnitFight> tempList = new HashSet<>(fighters);
                tempList.remove(opponent);
                fighters = new TreeSet<>(tempList);
            }

        });

    }



    private MapElement fromPosition(Position pos) {
        return map.get(pos.y).get(pos.x);
    }

    private List<MapElement> squares(Position origin) {
        List<MapElement> squares = new ArrayList<>(4);
        squares.add(map.get(origin.y-1).get(origin.x));
        squares.add(map.get(origin.y).get(origin.x-1));
        squares.add(map.get(origin.y).get(origin.x+1));
        squares.add(map.get(origin.y+1).get(origin.x));
        return squares;
    }

    public void print() {
        map.values().stream().forEach(l -> {
            l.stream().forEach(me -> System.out.print(me.type.car));
            System.out.println();
        });
        System.out.println("count:"+count);
    }

    public void printPoints() {
        fighters.stream().forEach(f -> System.out.println(f.type+ " "+f.position+" "+f.hitPoints));
    }

    public boolean isFightOver() {
        Set<UnitFight.UnitType> types = fighters.stream().map(f -> f.type).collect(Collectors.toSet());
        return types.size() == 1;
    }
}