package jssp.server.message;

/**
 * NOTICE:
 * private filed will be unmarshaled from qpid message to java class;
 * but only public fields will be marshaled to qpid message
 */
@SuppressWarnings("unused")
public class CalcMessage {

    public static final int ERROR_UNKNOWN = 190;

    private Float executionTime = 0f;

    private String userId;                                  // 用户id

    public Integer errorCode = 0;

    public String errorMsg;

    public Float getExecutionTime() {
        return executionTime;
    }

    public void setExecutionTime(Float executionTime) {
        this.executionTime = executionTime;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

}
