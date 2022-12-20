import java.util.ArrayList;
import java.util.List;

public class DemandeAchat {
    private Departement departement;
    private List<Material> materialList;

    public DemandeAchat(Departement departement) {
        this.departement = departement;
        this.materialList = new ArrayList<>();
    }

    public Departement getDepartement() {
        return departement;
    }

    public void addMaterial(Material material){
        this.materialList.add(material);
    }
    public void setDepartement(Departement departement) {
        this.departement = departement;
    }

//    public DemandeAchat(Departement departement, List<Material> materialList) {
//        this.departement = departement;
//        this.materialList = materialList;
//    }

    public int getMontantGlobal(){
        return materialList.stream().mapToInt(Material::getMontant).sum();
    }
}
