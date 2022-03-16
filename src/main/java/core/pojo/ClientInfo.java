package core.pojo;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by gorchakov on 13.02.2020.
 */
public class ClientInfo {

    @JsonProperty("type")
    private String type;
    @JsonProperty("description")
    private String description;

    private ClientInfo() {}

    public static ClientInfo.Builder newBuilder() {
        return new ClientInfo().new Builder();
    }

    public class Builder {

        private Builder() {
        }

        public ClientInfo.Builder setType(String value) {
            ClientInfo.this.type = value;
            return this;
        }

        public ClientInfo.Builder setDescription(String value) {
            ClientInfo.this.description = value;
            return this;
        }

        public ClientInfo build() {
            return ClientInfo.this;
        }

    }

    @Override
    public String toString() {
        return "Type: " + this.type + "; Description: " + this.description;
    }

}
