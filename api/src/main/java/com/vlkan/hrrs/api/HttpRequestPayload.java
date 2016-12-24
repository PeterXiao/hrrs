package com.vlkan.hrrs.api;

import org.immutables.value.Value;

@Value.Immutable
public interface HttpRequestPayload {

    String getType();

    byte[] getData();

    long getMissingByteCount();

}