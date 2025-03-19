package persoonlijkeUitwerkingen.WireMockAPI;

public class Token {
    String value;
    String expirationTime;

    public Token(String value, String expirationTime) {
        this.value = value;
        this.expirationTime = expirationTime;
    }
}
