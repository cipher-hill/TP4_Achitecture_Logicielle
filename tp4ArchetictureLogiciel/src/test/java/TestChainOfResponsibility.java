import jdk.jfr.MemoryAddress;
import org.junit.Assert;
import org.junit.Test;

public class TestChainOfResponsibility {

    @Test
    public void testHandlers(){
        ServiceHandler serviceHandler = new ServiceComptableHandler();
        ServiceHandler serviceHandler1 = new ServicePatrimoineHandler();
        serviceHandler.setNext(serviceHandler1);
        Assert.assertNotNull(serviceHandler.getNext());
        assert serviceHandler.getNext() instanceof ServicePatrimoineHandler;
    }
    @Test
    public void testDepartement(){
        Departement departement = new Departement();
        departement.setBudget(1000);
        Assert.assertEquals(departement.getMontantBloque(), 0);

        departement.setMontantBloque(10);
        Assert.assertEquals(departement.getBudget(), 1000);
        Assert.assertEquals(departement.getMontantBloque(), 10);
    }
    @Test
    public void testDemandeAchat(){
        Departement departement = new Departement();
        departement.setBudget(1000);
        DemandeAchat demandeAchat = new DemandeAchat(departement);
        demandeAchat.addMaterial(new Material(1000));
        demandeAchat.addMaterial(new Material(9000));
        Assert.assertEquals(demandeAchat.getDepartement(), departement);
        Assert.assertEquals(demandeAchat.getMontantGlobal(),10000);
    }
    @Test
    public void testServiceComptableHandler(){
        Departement departement = new Departement();
        departement.setBudget(1000);

        //
        DemandeAchat demandeAchat = new DemandeAchat(departement);
        demandeAchat.addMaterial(new Material(100));
        demandeAchat.addMaterial(new Material(200));
        ServiceHandler serviceHandler = new ServiceComptableHandler();
        serviceHandler.handle(demandeAchat);
        Assert.assertEquals(departement.getBudget(), 1000);
        Assert.assertEquals(departement.getMontantBloque(), 300);

        Departement departement2 = new Departement();
        departement2.setBudget(1000);
        DemandeAchat demandeAchat2 = new DemandeAchat(departement2);
        demandeAchat2.addMaterial(new Material(2000));
        serviceHandler.handle(demandeAchat2);
        Assert.assertEquals(departement2.getBudget(), 1000);
        Assert.assertEquals(departement2.getMontantBloque(), 0);
    }

    @Test
    public void testServicePatrimoineHandler(){
        Departement departement = new Departement();
        departement.setBudget(1000);
        departement.setMontantBloque(30);
        DemandeAchat demandeAchat = new DemandeAchat(departement);
        demandeAchat.addMaterial(new Material(100));

        ServiceHandler serviceHandler = new ServicePatrimoineHandler();
        serviceHandler.handle(demandeAchat);

        Assert.assertEquals(demandeAchat.getDepartement().getMontantBloque(), 0);
        Assert.assertEquals(demandeAchat.getDepartement().getBudget(), 970);


    }

    @Test
    public void testChainingOfTheTowHandlers(){
        Departement departement = new Departement();
        departement.setBudget(1000);
        DemandeAchat demandeAchat = new DemandeAchat(departement);
        demandeAchat.addMaterial(new Material(100));
        ServiceHandler serviceHandler = new ServiceComptableHandler();
        serviceHandler.setNext(new ServicePatrimoineHandler());
        serviceHandler.handle(demandeAchat);
        Assert.assertEquals(demandeAchat.getDepartement().getMontantBloque(), 0);
        Assert.assertEquals(demandeAchat.getDepartement().getBudget(), 900);


    }
}
