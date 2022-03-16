package core.pojo;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by gorchakov on 30.01.2020.
 */
public class SessionInfo {

    @JsonProperty("client_info")
    private ClientInfo clientInfo;
    @JsonProperty("username")
    private String userName;
    @JsonProperty("domain_id")
    private String domainId;
    @JsonProperty("password")
    private String password;

    private SessionInfo() {}

    public static Builder newBuilder() {
        return new SessionInfo().new Builder();
    }

    public class Builder {

        private Builder() {
        }

        public Builder setClientInfo(ClientInfo value) {
            SessionInfo.this.clientInfo = value;
            return this;
        }

        public Builder setUserName(String value) {
            SessionInfo.this.userName = value;
            return this;
        }

        public Builder setDomainId(String value) {
            SessionInfo.this.domainId = value;
            return this;
        }

        public Builder setPassword(String value) {
            SessionInfo.this.password = value;
            return this;
        }

        public SessionInfo build() {
            return SessionInfo.this;
        }

    }

    @Override
    public String toString() {
        return "Login: " + this.userName + "; Password: " + this.password + "; Domain: " + this.domainId + "; ClientInfo: " + this.clientInfo;
    }
}
