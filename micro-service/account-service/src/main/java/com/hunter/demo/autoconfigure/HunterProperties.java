package com.hunter.demo.autoconfigure;


import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(
        prefix = "hunter"
)
public class HunterProperties {

    public static final String HUNTER_PREFIX = "hunter";
    private String clientAddress;

    private Boolean adminClient;

    public HunterProperties() {
    }

    public String getClientAddress() {
        return clientAddress;
    }

    public void setClientAddress(String clientAddress) {
        this.clientAddress = clientAddress;
    }

    public Boolean getAdminClient() {
        return adminClient;
    }

    public void setAdminClient(Boolean adminClient) {
        this.adminClient = adminClient;
    }
}
