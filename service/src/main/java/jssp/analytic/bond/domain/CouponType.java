package jssp.analytic.bond.domain;

public enum CouponType {
    Fixed("固定债"), Floating("浮动债"), PayAtMaturity("一次还本付息"), Discount("零息债");

    public final String chineseName;

    CouponType(String chineseName) {
        this.chineseName = chineseName;
    }
}
