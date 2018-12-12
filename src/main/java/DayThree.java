import model.Claim;
import model.ClaimGrid;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class DayThree {
    private final List<Claim> claims;
    private final ClaimGrid claimGrid;

    public DayThree(List<String> lines) {
        claims = lines.stream().map(Claim::toClaim).collect(Collectors.toList());
        claimGrid = initGridWithSize();
        claims.stream().forEach(claimGrid::fillGrid);

    }

    private ClaimGrid initGridWithSize() {
        Optional<Claim> maxWidth = claims.stream().max(Comparator.comparing(Claim::totalWidth));
        Optional<Claim> maxHeight = claims.stream().max(Comparator.comparing(Claim::totalHeight));
        int width = maxWidth.orElseThrow(IllegalStateException::new).totalWidth();
        int height = maxHeight.orElseThrow(IllegalStateException::new).totalHeight();
        return new ClaimGrid(width, height);
    }

    public int countOverlappingCells() {
        return Arrays.stream(claimGrid.grid).mapToInt(line -> (int) Arrays.stream(line).filter(i -> i > 1).count()).sum();
    }

    public Claim getUniqueClaim() {
        Optional<Claim> uniqueClaim = claims.stream().filter(claimGrid::isUnique).findFirst();
        return uniqueClaim.orElseThrow(IllegalStateException::new);
    }
}
