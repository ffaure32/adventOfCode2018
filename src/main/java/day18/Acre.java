package day18;

import com.google.common.base.Objects;

import java.util.ArrayList;
import java.util.List;

public class Acre {
    public final AcreType acreType;
    public final List<AcreType> adjacents;

    public Acre(AcreType acreType) {
        this.acreType = acreType;
        this.adjacents = new ArrayList<>();
    }

    public void fillAdjacents(List<AcreType> adjacents) {
        this.adjacents.addAll(adjacents);
    }

    public Acre(AcreType acreType, List<AcreType> adjacents) {
        this.acreType = acreType;
        this.adjacents = adjacents;
    }

    public AcreType change() {
        return acreType.change(adjacents);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Acre acre = (Acre) o;
        return acreType == acre.acreType;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(acreType);
    }
}
