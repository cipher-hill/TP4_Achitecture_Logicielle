import java.util.Objects;

public class Departement {
    private int budget = 0;
    private int montantBloque = 0;

    public Departement(){

    }

    public int getBudget() {
        return budget;
    }

    public void setBudget(int budget) {
        this.budget = budget;
    }

    public int getMontantBloque() {
        return montantBloque;
    }

    public void setMontantBloque(int montantBloque) {
        this.montantBloque = montantBloque;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Departement that = (Departement) o;
        return budget == that.budget && montantBloque == that.montantBloque;
    }

    @Override
    public int hashCode() {
        return Objects.hash(budget, montantBloque);
    }
}
