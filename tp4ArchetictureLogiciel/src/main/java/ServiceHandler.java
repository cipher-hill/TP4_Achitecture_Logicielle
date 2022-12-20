public interface ServiceHandler {
    public void setNext(ServiceHandler serviceHandler);
    public void handle(DemandeAchat demandeAchat);
    public ServiceHandler getNext();
}
