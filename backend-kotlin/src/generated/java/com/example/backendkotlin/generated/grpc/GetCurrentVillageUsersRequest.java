// Generated by the protocol buffer compiler.  DO NOT EDIT!
// NO CHECKED-IN PROTOBUF GENCODE
// source: proto/village.proto
// Protobuf Java Version: 4.29.3

package com.example.backendkotlin.generated.grpc;

/**
 * <pre>
 * GetCurrentVillageUsersRequest メッセージ
 * </pre>
 *
 * Protobuf type {@code village.GetCurrentVillageUsersRequest}
 */
public final class GetCurrentVillageUsersRequest extends
    com.google.protobuf.GeneratedMessage implements
    // @@protoc_insertion_point(message_implements:village.GetCurrentVillageUsersRequest)
    GetCurrentVillageUsersRequestOrBuilder {
private static final long serialVersionUID = 0L;
  static {
    com.google.protobuf.RuntimeVersion.validateProtobufGencodeVersion(
      com.google.protobuf.RuntimeVersion.RuntimeDomain.PUBLIC,
      /* major= */ 4,
      /* minor= */ 29,
      /* patch= */ 3,
      /* suffix= */ "",
      GetCurrentVillageUsersRequest.class.getName());
  }
  // Use GetCurrentVillageUsersRequest.newBuilder() to construct.
  private GetCurrentVillageUsersRequest(com.google.protobuf.GeneratedMessage.Builder<?> builder) {
    super(builder);
  }
  private GetCurrentVillageUsersRequest() {
    villageId_ = "";
    villagePassword_ = "";
    userId_ = "";
    userPassword_ = "";
  }

  public static final com.google.protobuf.Descriptors.Descriptor
      getDescriptor() {
    return com.example.backendkotlin.generated.grpc.VillageServiceProto.internal_static_village_GetCurrentVillageUsersRequest_descriptor;
  }

  @java.lang.Override
  protected com.google.protobuf.GeneratedMessage.FieldAccessorTable
      internalGetFieldAccessorTable() {
    return com.example.backendkotlin.generated.grpc.VillageServiceProto.internal_static_village_GetCurrentVillageUsersRequest_fieldAccessorTable
        .ensureFieldAccessorsInitialized(
            com.example.backendkotlin.generated.grpc.GetCurrentVillageUsersRequest.class, com.example.backendkotlin.generated.grpc.GetCurrentVillageUsersRequest.Builder.class);
  }

  public static final int VILLAGE_ID_FIELD_NUMBER = 1;
  @SuppressWarnings("serial")
  private volatile java.lang.Object villageId_ = "";
  /**
   * <code>string village_id = 1;</code>
   * @return The villageId.
   */
  @java.lang.Override
  public java.lang.String getVillageId() {
    java.lang.Object ref = villageId_;
    if (ref instanceof java.lang.String) {
      return (java.lang.String) ref;
    } else {
      com.google.protobuf.ByteString bs = 
          (com.google.protobuf.ByteString) ref;
      java.lang.String s = bs.toStringUtf8();
      villageId_ = s;
      return s;
    }
  }
  /**
   * <code>string village_id = 1;</code>
   * @return The bytes for villageId.
   */
  @java.lang.Override
  public com.google.protobuf.ByteString
      getVillageIdBytes() {
    java.lang.Object ref = villageId_;
    if (ref instanceof java.lang.String) {
      com.google.protobuf.ByteString b = 
          com.google.protobuf.ByteString.copyFromUtf8(
              (java.lang.String) ref);
      villageId_ = b;
      return b;
    } else {
      return (com.google.protobuf.ByteString) ref;
    }
  }

  public static final int VILLAGE_PASSWORD_FIELD_NUMBER = 2;
  @SuppressWarnings("serial")
  private volatile java.lang.Object villagePassword_ = "";
  /**
   * <code>string village_password = 2;</code>
   * @return The villagePassword.
   */
  @java.lang.Override
  public java.lang.String getVillagePassword() {
    java.lang.Object ref = villagePassword_;
    if (ref instanceof java.lang.String) {
      return (java.lang.String) ref;
    } else {
      com.google.protobuf.ByteString bs = 
          (com.google.protobuf.ByteString) ref;
      java.lang.String s = bs.toStringUtf8();
      villagePassword_ = s;
      return s;
    }
  }
  /**
   * <code>string village_password = 2;</code>
   * @return The bytes for villagePassword.
   */
  @java.lang.Override
  public com.google.protobuf.ByteString
      getVillagePasswordBytes() {
    java.lang.Object ref = villagePassword_;
    if (ref instanceof java.lang.String) {
      com.google.protobuf.ByteString b = 
          com.google.protobuf.ByteString.copyFromUtf8(
              (java.lang.String) ref);
      villagePassword_ = b;
      return b;
    } else {
      return (com.google.protobuf.ByteString) ref;
    }
  }

  public static final int USER_ID_FIELD_NUMBER = 3;
  @SuppressWarnings("serial")
  private volatile java.lang.Object userId_ = "";
  /**
   * <code>string user_id = 3;</code>
   * @return The userId.
   */
  @java.lang.Override
  public java.lang.String getUserId() {
    java.lang.Object ref = userId_;
    if (ref instanceof java.lang.String) {
      return (java.lang.String) ref;
    } else {
      com.google.protobuf.ByteString bs = 
          (com.google.protobuf.ByteString) ref;
      java.lang.String s = bs.toStringUtf8();
      userId_ = s;
      return s;
    }
  }
  /**
   * <code>string user_id = 3;</code>
   * @return The bytes for userId.
   */
  @java.lang.Override
  public com.google.protobuf.ByteString
      getUserIdBytes() {
    java.lang.Object ref = userId_;
    if (ref instanceof java.lang.String) {
      com.google.protobuf.ByteString b = 
          com.google.protobuf.ByteString.copyFromUtf8(
              (java.lang.String) ref);
      userId_ = b;
      return b;
    } else {
      return (com.google.protobuf.ByteString) ref;
    }
  }

  public static final int USER_PASSWORD_FIELD_NUMBER = 4;
  @SuppressWarnings("serial")
  private volatile java.lang.Object userPassword_ = "";
  /**
   * <code>string user_password = 4;</code>
   * @return The userPassword.
   */
  @java.lang.Override
  public java.lang.String getUserPassword() {
    java.lang.Object ref = userPassword_;
    if (ref instanceof java.lang.String) {
      return (java.lang.String) ref;
    } else {
      com.google.protobuf.ByteString bs = 
          (com.google.protobuf.ByteString) ref;
      java.lang.String s = bs.toStringUtf8();
      userPassword_ = s;
      return s;
    }
  }
  /**
   * <code>string user_password = 4;</code>
   * @return The bytes for userPassword.
   */
  @java.lang.Override
  public com.google.protobuf.ByteString
      getUserPasswordBytes() {
    java.lang.Object ref = userPassword_;
    if (ref instanceof java.lang.String) {
      com.google.protobuf.ByteString b = 
          com.google.protobuf.ByteString.copyFromUtf8(
              (java.lang.String) ref);
      userPassword_ = b;
      return b;
    } else {
      return (com.google.protobuf.ByteString) ref;
    }
  }

  private byte memoizedIsInitialized = -1;
  @java.lang.Override
  public final boolean isInitialized() {
    byte isInitialized = memoizedIsInitialized;
    if (isInitialized == 1) return true;
    if (isInitialized == 0) return false;

    memoizedIsInitialized = 1;
    return true;
  }

  @java.lang.Override
  public void writeTo(com.google.protobuf.CodedOutputStream output)
                      throws java.io.IOException {
    if (!com.google.protobuf.GeneratedMessage.isStringEmpty(villageId_)) {
      com.google.protobuf.GeneratedMessage.writeString(output, 1, villageId_);
    }
    if (!com.google.protobuf.GeneratedMessage.isStringEmpty(villagePassword_)) {
      com.google.protobuf.GeneratedMessage.writeString(output, 2, villagePassword_);
    }
    if (!com.google.protobuf.GeneratedMessage.isStringEmpty(userId_)) {
      com.google.protobuf.GeneratedMessage.writeString(output, 3, userId_);
    }
    if (!com.google.protobuf.GeneratedMessage.isStringEmpty(userPassword_)) {
      com.google.protobuf.GeneratedMessage.writeString(output, 4, userPassword_);
    }
    getUnknownFields().writeTo(output);
  }

  @java.lang.Override
  public int getSerializedSize() {
    int size = memoizedSize;
    if (size != -1) return size;

    size = 0;
    if (!com.google.protobuf.GeneratedMessage.isStringEmpty(villageId_)) {
      size += com.google.protobuf.GeneratedMessage.computeStringSize(1, villageId_);
    }
    if (!com.google.protobuf.GeneratedMessage.isStringEmpty(villagePassword_)) {
      size += com.google.protobuf.GeneratedMessage.computeStringSize(2, villagePassword_);
    }
    if (!com.google.protobuf.GeneratedMessage.isStringEmpty(userId_)) {
      size += com.google.protobuf.GeneratedMessage.computeStringSize(3, userId_);
    }
    if (!com.google.protobuf.GeneratedMessage.isStringEmpty(userPassword_)) {
      size += com.google.protobuf.GeneratedMessage.computeStringSize(4, userPassword_);
    }
    size += getUnknownFields().getSerializedSize();
    memoizedSize = size;
    return size;
  }

  @java.lang.Override
  public boolean equals(final java.lang.Object obj) {
    if (obj == this) {
     return true;
    }
    if (!(obj instanceof com.example.backendkotlin.generated.grpc.GetCurrentVillageUsersRequest)) {
      return super.equals(obj);
    }
    com.example.backendkotlin.generated.grpc.GetCurrentVillageUsersRequest other = (com.example.backendkotlin.generated.grpc.GetCurrentVillageUsersRequest) obj;

    if (!getVillageId()
        .equals(other.getVillageId())) return false;
    if (!getVillagePassword()
        .equals(other.getVillagePassword())) return false;
    if (!getUserId()
        .equals(other.getUserId())) return false;
    if (!getUserPassword()
        .equals(other.getUserPassword())) return false;
    if (!getUnknownFields().equals(other.getUnknownFields())) return false;
    return true;
  }

  @java.lang.Override
  public int hashCode() {
    if (memoizedHashCode != 0) {
      return memoizedHashCode;
    }
    int hash = 41;
    hash = (19 * hash) + getDescriptor().hashCode();
    hash = (37 * hash) + VILLAGE_ID_FIELD_NUMBER;
    hash = (53 * hash) + getVillageId().hashCode();
    hash = (37 * hash) + VILLAGE_PASSWORD_FIELD_NUMBER;
    hash = (53 * hash) + getVillagePassword().hashCode();
    hash = (37 * hash) + USER_ID_FIELD_NUMBER;
    hash = (53 * hash) + getUserId().hashCode();
    hash = (37 * hash) + USER_PASSWORD_FIELD_NUMBER;
    hash = (53 * hash) + getUserPassword().hashCode();
    hash = (29 * hash) + getUnknownFields().hashCode();
    memoizedHashCode = hash;
    return hash;
  }

  public static com.example.backendkotlin.generated.grpc.GetCurrentVillageUsersRequest parseFrom(
      java.nio.ByteBuffer data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static com.example.backendkotlin.generated.grpc.GetCurrentVillageUsersRequest parseFrom(
      java.nio.ByteBuffer data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static com.example.backendkotlin.generated.grpc.GetCurrentVillageUsersRequest parseFrom(
      com.google.protobuf.ByteString data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static com.example.backendkotlin.generated.grpc.GetCurrentVillageUsersRequest parseFrom(
      com.google.protobuf.ByteString data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static com.example.backendkotlin.generated.grpc.GetCurrentVillageUsersRequest parseFrom(byte[] data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static com.example.backendkotlin.generated.grpc.GetCurrentVillageUsersRequest parseFrom(
      byte[] data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static com.example.backendkotlin.generated.grpc.GetCurrentVillageUsersRequest parseFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessage
        .parseWithIOException(PARSER, input);
  }
  public static com.example.backendkotlin.generated.grpc.GetCurrentVillageUsersRequest parseFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessage
        .parseWithIOException(PARSER, input, extensionRegistry);
  }

  public static com.example.backendkotlin.generated.grpc.GetCurrentVillageUsersRequest parseDelimitedFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessage
        .parseDelimitedWithIOException(PARSER, input);
  }

  public static com.example.backendkotlin.generated.grpc.GetCurrentVillageUsersRequest parseDelimitedFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessage
        .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
  }
  public static com.example.backendkotlin.generated.grpc.GetCurrentVillageUsersRequest parseFrom(
      com.google.protobuf.CodedInputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessage
        .parseWithIOException(PARSER, input);
  }
  public static com.example.backendkotlin.generated.grpc.GetCurrentVillageUsersRequest parseFrom(
      com.google.protobuf.CodedInputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessage
        .parseWithIOException(PARSER, input, extensionRegistry);
  }

  @java.lang.Override
  public Builder newBuilderForType() { return newBuilder(); }
  public static Builder newBuilder() {
    return DEFAULT_INSTANCE.toBuilder();
  }
  public static Builder newBuilder(com.example.backendkotlin.generated.grpc.GetCurrentVillageUsersRequest prototype) {
    return DEFAULT_INSTANCE.toBuilder().mergeFrom(prototype);
  }
  @java.lang.Override
  public Builder toBuilder() {
    return this == DEFAULT_INSTANCE
        ? new Builder() : new Builder().mergeFrom(this);
  }

  @java.lang.Override
  protected Builder newBuilderForType(
      com.google.protobuf.GeneratedMessage.BuilderParent parent) {
    Builder builder = new Builder(parent);
    return builder;
  }
  /**
   * <pre>
   * GetCurrentVillageUsersRequest メッセージ
   * </pre>
   *
   * Protobuf type {@code village.GetCurrentVillageUsersRequest}
   */
  public static final class Builder extends
      com.google.protobuf.GeneratedMessage.Builder<Builder> implements
      // @@protoc_insertion_point(builder_implements:village.GetCurrentVillageUsersRequest)
      com.example.backendkotlin.generated.grpc.GetCurrentVillageUsersRequestOrBuilder {
    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return com.example.backendkotlin.generated.grpc.VillageServiceProto.internal_static_village_GetCurrentVillageUsersRequest_descriptor;
    }

    @java.lang.Override
    protected com.google.protobuf.GeneratedMessage.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return com.example.backendkotlin.generated.grpc.VillageServiceProto.internal_static_village_GetCurrentVillageUsersRequest_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              com.example.backendkotlin.generated.grpc.GetCurrentVillageUsersRequest.class, com.example.backendkotlin.generated.grpc.GetCurrentVillageUsersRequest.Builder.class);
    }

    // Construct using com.example.backendkotlin.generated.grpc.GetCurrentVillageUsersRequest.newBuilder()
    private Builder() {

    }

    private Builder(
        com.google.protobuf.GeneratedMessage.BuilderParent parent) {
      super(parent);

    }
    @java.lang.Override
    public Builder clear() {
      super.clear();
      bitField0_ = 0;
      villageId_ = "";
      villagePassword_ = "";
      userId_ = "";
      userPassword_ = "";
      return this;
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.Descriptor
        getDescriptorForType() {
      return com.example.backendkotlin.generated.grpc.VillageServiceProto.internal_static_village_GetCurrentVillageUsersRequest_descriptor;
    }

    @java.lang.Override
    public com.example.backendkotlin.generated.grpc.GetCurrentVillageUsersRequest getDefaultInstanceForType() {
      return com.example.backendkotlin.generated.grpc.GetCurrentVillageUsersRequest.getDefaultInstance();
    }

    @java.lang.Override
    public com.example.backendkotlin.generated.grpc.GetCurrentVillageUsersRequest build() {
      com.example.backendkotlin.generated.grpc.GetCurrentVillageUsersRequest result = buildPartial();
      if (!result.isInitialized()) {
        throw newUninitializedMessageException(result);
      }
      return result;
    }

    @java.lang.Override
    public com.example.backendkotlin.generated.grpc.GetCurrentVillageUsersRequest buildPartial() {
      com.example.backendkotlin.generated.grpc.GetCurrentVillageUsersRequest result = new com.example.backendkotlin.generated.grpc.GetCurrentVillageUsersRequest(this);
      if (bitField0_ != 0) { buildPartial0(result); }
      onBuilt();
      return result;
    }

    private void buildPartial0(com.example.backendkotlin.generated.grpc.GetCurrentVillageUsersRequest result) {
      int from_bitField0_ = bitField0_;
      if (((from_bitField0_ & 0x00000001) != 0)) {
        result.villageId_ = villageId_;
      }
      if (((from_bitField0_ & 0x00000002) != 0)) {
        result.villagePassword_ = villagePassword_;
      }
      if (((from_bitField0_ & 0x00000004) != 0)) {
        result.userId_ = userId_;
      }
      if (((from_bitField0_ & 0x00000008) != 0)) {
        result.userPassword_ = userPassword_;
      }
    }

    @java.lang.Override
    public Builder mergeFrom(com.google.protobuf.Message other) {
      if (other instanceof com.example.backendkotlin.generated.grpc.GetCurrentVillageUsersRequest) {
        return mergeFrom((com.example.backendkotlin.generated.grpc.GetCurrentVillageUsersRequest)other);
      } else {
        super.mergeFrom(other);
        return this;
      }
    }

    public Builder mergeFrom(com.example.backendkotlin.generated.grpc.GetCurrentVillageUsersRequest other) {
      if (other == com.example.backendkotlin.generated.grpc.GetCurrentVillageUsersRequest.getDefaultInstance()) return this;
      if (!other.getVillageId().isEmpty()) {
        villageId_ = other.villageId_;
        bitField0_ |= 0x00000001;
        onChanged();
      }
      if (!other.getVillagePassword().isEmpty()) {
        villagePassword_ = other.villagePassword_;
        bitField0_ |= 0x00000002;
        onChanged();
      }
      if (!other.getUserId().isEmpty()) {
        userId_ = other.userId_;
        bitField0_ |= 0x00000004;
        onChanged();
      }
      if (!other.getUserPassword().isEmpty()) {
        userPassword_ = other.userPassword_;
        bitField0_ |= 0x00000008;
        onChanged();
      }
      this.mergeUnknownFields(other.getUnknownFields());
      onChanged();
      return this;
    }

    @java.lang.Override
    public final boolean isInitialized() {
      return true;
    }

    @java.lang.Override
    public Builder mergeFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      if (extensionRegistry == null) {
        throw new java.lang.NullPointerException();
      }
      try {
        boolean done = false;
        while (!done) {
          int tag = input.readTag();
          switch (tag) {
            case 0:
              done = true;
              break;
            case 10: {
              villageId_ = input.readStringRequireUtf8();
              bitField0_ |= 0x00000001;
              break;
            } // case 10
            case 18: {
              villagePassword_ = input.readStringRequireUtf8();
              bitField0_ |= 0x00000002;
              break;
            } // case 18
            case 26: {
              userId_ = input.readStringRequireUtf8();
              bitField0_ |= 0x00000004;
              break;
            } // case 26
            case 34: {
              userPassword_ = input.readStringRequireUtf8();
              bitField0_ |= 0x00000008;
              break;
            } // case 34
            default: {
              if (!super.parseUnknownField(input, extensionRegistry, tag)) {
                done = true; // was an endgroup tag
              }
              break;
            } // default:
          } // switch (tag)
        } // while (!done)
      } catch (com.google.protobuf.InvalidProtocolBufferException e) {
        throw e.unwrapIOException();
      } finally {
        onChanged();
      } // finally
      return this;
    }
    private int bitField0_;

    private java.lang.Object villageId_ = "";
    /**
     * <code>string village_id = 1;</code>
     * @return The villageId.
     */
    public java.lang.String getVillageId() {
      java.lang.Object ref = villageId_;
      if (!(ref instanceof java.lang.String)) {
        com.google.protobuf.ByteString bs =
            (com.google.protobuf.ByteString) ref;
        java.lang.String s = bs.toStringUtf8();
        villageId_ = s;
        return s;
      } else {
        return (java.lang.String) ref;
      }
    }
    /**
     * <code>string village_id = 1;</code>
     * @return The bytes for villageId.
     */
    public com.google.protobuf.ByteString
        getVillageIdBytes() {
      java.lang.Object ref = villageId_;
      if (ref instanceof String) {
        com.google.protobuf.ByteString b = 
            com.google.protobuf.ByteString.copyFromUtf8(
                (java.lang.String) ref);
        villageId_ = b;
        return b;
      } else {
        return (com.google.protobuf.ByteString) ref;
      }
    }
    /**
     * <code>string village_id = 1;</code>
     * @param value The villageId to set.
     * @return This builder for chaining.
     */
    public Builder setVillageId(
        java.lang.String value) {
      if (value == null) { throw new NullPointerException(); }
      villageId_ = value;
      bitField0_ |= 0x00000001;
      onChanged();
      return this;
    }
    /**
     * <code>string village_id = 1;</code>
     * @return This builder for chaining.
     */
    public Builder clearVillageId() {
      villageId_ = getDefaultInstance().getVillageId();
      bitField0_ = (bitField0_ & ~0x00000001);
      onChanged();
      return this;
    }
    /**
     * <code>string village_id = 1;</code>
     * @param value The bytes for villageId to set.
     * @return This builder for chaining.
     */
    public Builder setVillageIdBytes(
        com.google.protobuf.ByteString value) {
      if (value == null) { throw new NullPointerException(); }
      checkByteStringIsUtf8(value);
      villageId_ = value;
      bitField0_ |= 0x00000001;
      onChanged();
      return this;
    }

    private java.lang.Object villagePassword_ = "";
    /**
     * <code>string village_password = 2;</code>
     * @return The villagePassword.
     */
    public java.lang.String getVillagePassword() {
      java.lang.Object ref = villagePassword_;
      if (!(ref instanceof java.lang.String)) {
        com.google.protobuf.ByteString bs =
            (com.google.protobuf.ByteString) ref;
        java.lang.String s = bs.toStringUtf8();
        villagePassword_ = s;
        return s;
      } else {
        return (java.lang.String) ref;
      }
    }
    /**
     * <code>string village_password = 2;</code>
     * @return The bytes for villagePassword.
     */
    public com.google.protobuf.ByteString
        getVillagePasswordBytes() {
      java.lang.Object ref = villagePassword_;
      if (ref instanceof String) {
        com.google.protobuf.ByteString b = 
            com.google.protobuf.ByteString.copyFromUtf8(
                (java.lang.String) ref);
        villagePassword_ = b;
        return b;
      } else {
        return (com.google.protobuf.ByteString) ref;
      }
    }
    /**
     * <code>string village_password = 2;</code>
     * @param value The villagePassword to set.
     * @return This builder for chaining.
     */
    public Builder setVillagePassword(
        java.lang.String value) {
      if (value == null) { throw new NullPointerException(); }
      villagePassword_ = value;
      bitField0_ |= 0x00000002;
      onChanged();
      return this;
    }
    /**
     * <code>string village_password = 2;</code>
     * @return This builder for chaining.
     */
    public Builder clearVillagePassword() {
      villagePassword_ = getDefaultInstance().getVillagePassword();
      bitField0_ = (bitField0_ & ~0x00000002);
      onChanged();
      return this;
    }
    /**
     * <code>string village_password = 2;</code>
     * @param value The bytes for villagePassword to set.
     * @return This builder for chaining.
     */
    public Builder setVillagePasswordBytes(
        com.google.protobuf.ByteString value) {
      if (value == null) { throw new NullPointerException(); }
      checkByteStringIsUtf8(value);
      villagePassword_ = value;
      bitField0_ |= 0x00000002;
      onChanged();
      return this;
    }

    private java.lang.Object userId_ = "";
    /**
     * <code>string user_id = 3;</code>
     * @return The userId.
     */
    public java.lang.String getUserId() {
      java.lang.Object ref = userId_;
      if (!(ref instanceof java.lang.String)) {
        com.google.protobuf.ByteString bs =
            (com.google.protobuf.ByteString) ref;
        java.lang.String s = bs.toStringUtf8();
        userId_ = s;
        return s;
      } else {
        return (java.lang.String) ref;
      }
    }
    /**
     * <code>string user_id = 3;</code>
     * @return The bytes for userId.
     */
    public com.google.protobuf.ByteString
        getUserIdBytes() {
      java.lang.Object ref = userId_;
      if (ref instanceof String) {
        com.google.protobuf.ByteString b = 
            com.google.protobuf.ByteString.copyFromUtf8(
                (java.lang.String) ref);
        userId_ = b;
        return b;
      } else {
        return (com.google.protobuf.ByteString) ref;
      }
    }
    /**
     * <code>string user_id = 3;</code>
     * @param value The userId to set.
     * @return This builder for chaining.
     */
    public Builder setUserId(
        java.lang.String value) {
      if (value == null) { throw new NullPointerException(); }
      userId_ = value;
      bitField0_ |= 0x00000004;
      onChanged();
      return this;
    }
    /**
     * <code>string user_id = 3;</code>
     * @return This builder for chaining.
     */
    public Builder clearUserId() {
      userId_ = getDefaultInstance().getUserId();
      bitField0_ = (bitField0_ & ~0x00000004);
      onChanged();
      return this;
    }
    /**
     * <code>string user_id = 3;</code>
     * @param value The bytes for userId to set.
     * @return This builder for chaining.
     */
    public Builder setUserIdBytes(
        com.google.protobuf.ByteString value) {
      if (value == null) { throw new NullPointerException(); }
      checkByteStringIsUtf8(value);
      userId_ = value;
      bitField0_ |= 0x00000004;
      onChanged();
      return this;
    }

    private java.lang.Object userPassword_ = "";
    /**
     * <code>string user_password = 4;</code>
     * @return The userPassword.
     */
    public java.lang.String getUserPassword() {
      java.lang.Object ref = userPassword_;
      if (!(ref instanceof java.lang.String)) {
        com.google.protobuf.ByteString bs =
            (com.google.protobuf.ByteString) ref;
        java.lang.String s = bs.toStringUtf8();
        userPassword_ = s;
        return s;
      } else {
        return (java.lang.String) ref;
      }
    }
    /**
     * <code>string user_password = 4;</code>
     * @return The bytes for userPassword.
     */
    public com.google.protobuf.ByteString
        getUserPasswordBytes() {
      java.lang.Object ref = userPassword_;
      if (ref instanceof String) {
        com.google.protobuf.ByteString b = 
            com.google.protobuf.ByteString.copyFromUtf8(
                (java.lang.String) ref);
        userPassword_ = b;
        return b;
      } else {
        return (com.google.protobuf.ByteString) ref;
      }
    }
    /**
     * <code>string user_password = 4;</code>
     * @param value The userPassword to set.
     * @return This builder for chaining.
     */
    public Builder setUserPassword(
        java.lang.String value) {
      if (value == null) { throw new NullPointerException(); }
      userPassword_ = value;
      bitField0_ |= 0x00000008;
      onChanged();
      return this;
    }
    /**
     * <code>string user_password = 4;</code>
     * @return This builder for chaining.
     */
    public Builder clearUserPassword() {
      userPassword_ = getDefaultInstance().getUserPassword();
      bitField0_ = (bitField0_ & ~0x00000008);
      onChanged();
      return this;
    }
    /**
     * <code>string user_password = 4;</code>
     * @param value The bytes for userPassword to set.
     * @return This builder for chaining.
     */
    public Builder setUserPasswordBytes(
        com.google.protobuf.ByteString value) {
      if (value == null) { throw new NullPointerException(); }
      checkByteStringIsUtf8(value);
      userPassword_ = value;
      bitField0_ |= 0x00000008;
      onChanged();
      return this;
    }

    // @@protoc_insertion_point(builder_scope:village.GetCurrentVillageUsersRequest)
  }

  // @@protoc_insertion_point(class_scope:village.GetCurrentVillageUsersRequest)
  private static final com.example.backendkotlin.generated.grpc.GetCurrentVillageUsersRequest DEFAULT_INSTANCE;
  static {
    DEFAULT_INSTANCE = new com.example.backendkotlin.generated.grpc.GetCurrentVillageUsersRequest();
  }

  public static com.example.backendkotlin.generated.grpc.GetCurrentVillageUsersRequest getDefaultInstance() {
    return DEFAULT_INSTANCE;
  }

  private static final com.google.protobuf.Parser<GetCurrentVillageUsersRequest>
      PARSER = new com.google.protobuf.AbstractParser<GetCurrentVillageUsersRequest>() {
    @java.lang.Override
    public GetCurrentVillageUsersRequest parsePartialFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      Builder builder = newBuilder();
      try {
        builder.mergeFrom(input, extensionRegistry);
      } catch (com.google.protobuf.InvalidProtocolBufferException e) {
        throw e.setUnfinishedMessage(builder.buildPartial());
      } catch (com.google.protobuf.UninitializedMessageException e) {
        throw e.asInvalidProtocolBufferException().setUnfinishedMessage(builder.buildPartial());
      } catch (java.io.IOException e) {
        throw new com.google.protobuf.InvalidProtocolBufferException(e)
            .setUnfinishedMessage(builder.buildPartial());
      }
      return builder.buildPartial();
    }
  };

  public static com.google.protobuf.Parser<GetCurrentVillageUsersRequest> parser() {
    return PARSER;
  }

  @java.lang.Override
  public com.google.protobuf.Parser<GetCurrentVillageUsersRequest> getParserForType() {
    return PARSER;
  }

  @java.lang.Override
  public com.example.backendkotlin.generated.grpc.GetCurrentVillageUsersRequest getDefaultInstanceForType() {
    return DEFAULT_INSTANCE;
  }

}

