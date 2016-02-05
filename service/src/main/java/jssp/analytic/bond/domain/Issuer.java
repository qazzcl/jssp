package jssp.analytic.bond.domain;

public class Issuer {
    public final String institutionCode;
    public final String issuerName;
    public final String enterpriseType;

    public Issuer(String institutionCode, String issuerName, String enterpriseType) {
        this.institutionCode = institutionCode;
        this.issuerName = issuerName;
        this.enterpriseType = enterpriseType;
    }
}
