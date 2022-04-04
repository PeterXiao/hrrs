/*
 * Copyright 2016-2022 Volkan Yazıcı
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permits and
 * limitations under the License.
 */

package com.vlkan.hrrs.api;

import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;

import java.util.Arrays;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkNotNull;

public class ImmutableHttpRequestPayload implements HttpRequestPayload {

    private final int missingByteCount;

    private final byte[] bytes;

    private ImmutableHttpRequestPayload(int missingByteCount, byte[] bytes) {
        checkArgument(missingByteCount >= 0, "expecting: missingByteCount >= 0, found: %s", missingByteCount);
        this.missingByteCount = missingByteCount;
        this.bytes = checkNotNull(bytes, "bytes");
    }

    @Override
    public int getMissingByteCount() {
        return missingByteCount;
    }

    @Override
    public byte[] getBytes() {
        return bytes;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ImmutableHttpRequestPayload that = (ImmutableHttpRequestPayload) o;
        return missingByteCount == that.missingByteCount &&
                Arrays.equals(bytes, that.bytes);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(missingByteCount, bytes);
    }

    @Override
    public String toString() {
        return MoreObjects
                .toStringHelper(this)
                .add("missingByteCount", missingByteCount)
                .add("byteCount", bytes.length)
                .toString();
    }

    @Override
    public Builder toBuilder() {
        Builder builder = new Builder();
        builder.missingByteCount = missingByteCount;
        builder.bytes = bytes;
        return builder;
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    public static class Builder implements HttpRequestPayload.Builder {

        private int missingByteCount;

        private byte[] bytes;

        private Builder() {
            // Do nothing.
        }

        @Override
        public Builder setMissingByteCount(int missingByteCount) {
            this.missingByteCount = missingByteCount;
            return this;
        }

        @Override
        public Builder setBytes(byte[] bytes) {
            this.bytes = bytes;
            return this;
        }

        @Override
        public ImmutableHttpRequestPayload build() {
            return new ImmutableHttpRequestPayload(missingByteCount, bytes);
        }

    }

}
