package SwiatWitrualny;

public class BrakOrganizmuNaPozycjiException extends Exception {
    public BrakOrganizmuNaPozycjiException()
    {
        super();
    }
    public BrakOrganizmuNaPozycjiException(String messeage)
    {
        super(messeage);
    }

    public BrakOrganizmuNaPozycjiException(Throwable cause)
    {
        super(cause);
    }

    public BrakOrganizmuNaPozycjiException( String messeage, Throwable cause)
    {
        super(messeage,cause);
    }
}
