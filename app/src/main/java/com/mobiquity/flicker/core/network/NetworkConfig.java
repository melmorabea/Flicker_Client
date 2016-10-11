package com.mobiquity.flicker.core.network;

import javax.inject.Inject;

/**
 * Created by mahmoudelmorabea on 11/10/16.
 */

public class NetworkConfig {

    private static final int DEFAULT_TIMEOUT = 10; // Seconds!

    private int connectionTimeout, readTimeout, writeTimeout; // In seconds!

    @Inject
    public NetworkConfig() {
        this(DEFAULT_TIMEOUT, DEFAULT_TIMEOUT, DEFAULT_TIMEOUT);
    }

    /**
     ** @param connectionTimeout In seconds
     * @param readTimeout In seconds
     * @param writeTimeout In seconds
     */
    public NetworkConfig(int connectionTimeout, int readTimeout, int writeTimeout) {
        this.connectionTimeout = connectionTimeout;
        this.readTimeout = readTimeout;
        this.writeTimeout = writeTimeout;
    }

    public int getConnectionTimeout() {
        return connectionTimeout;
    }

    public int getReadTimeout() {
        return readTimeout;
    }

    public int getWriteTimeout() {
        return writeTimeout;
    }

}
