public class ServicePatrimoineHandler implements ServiceHandler{
    private ServiceHandler next;

    @Override
    public void setNext(ServiceHandler serviceHandler) {
        next = serviceHandler;
    }

    @Override
    public void handle(DemandeAchat demandeAchat) {
        demandeAchat.getDepartement()
                .setBudget(demandeAchat.getDepartement().getBudget()
                        - demandeAchat.getDepartement().getMontantBloque());
        demandeAchat.getDepartement().setMontantBloque(0);
        if(next != null){
            next.handle(demandeAchat);
        }
    }

    public ServiceHandler getNext() {
        return next;
    }
}
