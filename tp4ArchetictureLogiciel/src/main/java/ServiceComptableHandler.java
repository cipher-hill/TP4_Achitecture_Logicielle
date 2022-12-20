
public class ServiceComptableHandler implements ServiceHandler{
    public ServiceHandler next;

    @Override
    public void setNext(ServiceHandler serviceHandler) {
        next = serviceHandler;
    }

    @Override
    public void handle(DemandeAchat demandeAchat) {
        if(demandeAchat.getMontantGlobal() <=
                (demandeAchat.getDepartement().getBudget() -
                        demandeAchat.getDepartement().getMontantBloque())
        ){
            // Approvée
            demandeAchat.getDepartement().setMontantBloque(demandeAchat.getMontantGlobal());
            // If approved then pass to the next handler in the chain
            if(next != null){
                next.handle(demandeAchat);
            }
        }
    }

    public ServiceHandler getNext() {
        return next;
    }
}
