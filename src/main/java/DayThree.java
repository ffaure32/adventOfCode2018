import model.Claim;
import model.ClaimGrid;

import java.util.*;
import java.util.stream.Collectors;

public class DayThree {
    private final List<Claim> claims;
    private final ClaimGrid claimGrid;

    public DayThree(List<String> lines) {
        claims = lines.stream().map(cl -> Claim.toClaim(cl)).collect(Collectors.toList());
        claimGrid = calculateGrid();
        claims.stream().forEach(cl -> claimGrid.fillGrid(cl));

    }

    private ClaimGrid calculateGrid() {
        Optional<Claim> maxWidth = claims.stream().max(Comparator.comparing(Claim::totalWidth));
        Optional<Claim> maxHeight = claims.stream().max(Comparator.comparing(Claim::totalHeight));
        if(maxWidth.isPresent() && maxHeight.isPresent()) {
            return new ClaimGrid(maxWidth.get().totalWidth(), maxHeight.get().totalHeight());
        } else {
            throw new IllegalStateException("grille malformée");
        }
    }

    public List<Claim> getClaims() {
        return Collections.unmodifiableList(claims);
    }

    public int countOverlappingCells() {
        int cells = Arrays.stream(claimGrid.grid).mapToInt(line -> (int) Arrays.stream(line).filter(i -> i > 1).count()).sum();
        return cells;
    }

    public Claim getUniqueClaim() {
        Optional<Claim> uniqueClaim = claims.stream().filter(cl -> claimGrid.isUnique(cl)).findFirst();
        return uniqueClaim.orElseThrow(IllegalStateException::new);
    }
}
