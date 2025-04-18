// Generated by the protocol buffer compiler.  DO NOT EDIT!
// NO CHECKED-IN PROTOBUF GENCODE
// source: proto/village.proto
// Protobuf Java Version: 4.29.3

package com.example.backendkotlin.generated.grpc;

/**
 * <pre>
 * ListVillagesRequest メッセージ
 * </pre>
 *
 * Protobuf type {@code village.ListVillagesRequest}
 */
public final class ListVillagesRequest extends
    com.google.protobuf.GeneratedMessage implements
    // @@protoc_insertion_point(message_implements:village.ListVillagesRequest)
    ListVillagesRequestOrBuilder {
private static final long serialVersionUID = 0L;
  static {
    com.google.protobuf.RuntimeVersion.validateProtobufGencodeVersion(
      com.google.protobuf.RuntimeVersion.RuntimeDomain.PUBLIC,
      /* major= */ 4,
      /* minor= */ 29,
      /* patch= */ 3,
      /* suffix= */ "",
      ListVillagesRequest.class.getName());
  }
  // Use ListVillagesRequest.newBuilder() to construct.
  private ListVillagesRequest(com.google.protobuf.GeneratedMessage.Builder<?> builder) {
    super(builder);
  }
  private ListVillagesRequest() {
  }

  public static final com.google.protobuf.Descriptors.Descriptor
      getDescriptor() {
    return com.example.backendkotlin.generated.grpc.VillageServiceProto.internal_static_village_ListVillagesRequest_descriptor;
  }

  @java.lang.Override
  protected com.google.protobuf.GeneratedMessage.FieldAccessorTable
      internalGetFieldAccessorTable() {
    return com.example.backendkotlin.generated.grpc.VillageServiceProto.internal_static_village_ListVillagesRequest_fieldAccessorTable
        .ensureFieldAccessorsInitialized(
            com.example.backendkotlin.generated.grpc.ListVillagesRequest.class, com.example.backendkotlin.generated.grpc.ListVillagesRequest.Builder.class);
  }

  public static final int IS_RECRUITED_ONLY_FIELD_NUMBER = 1;
  private boolean isRecruitedOnly_ = false;
  /**
   * <code>bool is_recruited_only = 1;</code>
   * @return The isRecruitedOnly.
   */
  @java.lang.Override
  public boolean getIsRecruitedOnly() {
    return isRecruitedOnly_;
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
    if (isRecruitedOnly_ != false) {
      output.writeBool(1, isRecruitedOnly_);
    }
    getUnknownFields().writeTo(output);
  }

  @java.lang.Override
  public int getSerializedSize() {
    int size = memoizedSize;
    if (size != -1) return size;

    size = 0;
    if (isRecruitedOnly_ != false) {
      size += com.google.protobuf.CodedOutputStream
        .computeBoolSize(1, isRecruitedOnly_);
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
    if (!(obj instanceof com.example.backendkotlin.generated.grpc.ListVillagesRequest)) {
      return super.equals(obj);
    }
    com.example.backendkotlin.generated.grpc.ListVillagesRequest other = (com.example.backendkotlin.generated.grpc.ListVillagesRequest) obj;

    if (getIsRecruitedOnly()
        != other.getIsRecruitedOnly()) return false;
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
    hash = (37 * hash) + IS_RECRUITED_ONLY_FIELD_NUMBER;
    hash = (53 * hash) + com.google.protobuf.Internal.hashBoolean(
        getIsRecruitedOnly());
    hash = (29 * hash) + getUnknownFields().hashCode();
    memoizedHashCode = hash;
    return hash;
  }

  public static com.example.backendkotlin.generated.grpc.ListVillagesRequest parseFrom(
      java.nio.ByteBuffer data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static com.example.backendkotlin.generated.grpc.ListVillagesRequest parseFrom(
      java.nio.ByteBuffer data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static com.example.backendkotlin.generated.grpc.ListVillagesRequest parseFrom(
      com.google.protobuf.ByteString data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static com.example.backendkotlin.generated.grpc.ListVillagesRequest parseFrom(
      com.google.protobuf.ByteString data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static com.example.backendkotlin.generated.grpc.ListVillagesRequest parseFrom(byte[] data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static com.example.backendkotlin.generated.grpc.ListVillagesRequest parseFrom(
      byte[] data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static com.example.backendkotlin.generated.grpc.ListVillagesRequest parseFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessage
        .parseWithIOException(PARSER, input);
  }
  public static com.example.backendkotlin.generated.grpc.ListVillagesRequest parseFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessage
        .parseWithIOException(PARSER, input, extensionRegistry);
  }

  public static com.example.backendkotlin.generated.grpc.ListVillagesRequest parseDelimitedFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessage
        .parseDelimitedWithIOException(PARSER, input);
  }

  public static com.example.backendkotlin.generated.grpc.ListVillagesRequest parseDelimitedFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessage
        .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
  }
  public static com.example.backendkotlin.generated.grpc.ListVillagesRequest parseFrom(
      com.google.protobuf.CodedInputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessage
        .parseWithIOException(PARSER, input);
  }
  public static com.example.backendkotlin.generated.grpc.ListVillagesRequest parseFrom(
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
  public static Builder newBuilder(com.example.backendkotlin.generated.grpc.ListVillagesRequest prototype) {
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
   * ListVillagesRequest メッセージ
   * </pre>
   *
   * Protobuf type {@code village.ListVillagesRequest}
   */
  public static final class Builder extends
      com.google.protobuf.GeneratedMessage.Builder<Builder> implements
      // @@protoc_insertion_point(builder_implements:village.ListVillagesRequest)
      com.example.backendkotlin.generated.grpc.ListVillagesRequestOrBuilder {
    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return com.example.backendkotlin.generated.grpc.VillageServiceProto.internal_static_village_ListVillagesRequest_descriptor;
    }

    @java.lang.Override
    protected com.google.protobuf.GeneratedMessage.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return com.example.backendkotlin.generated.grpc.VillageServiceProto.internal_static_village_ListVillagesRequest_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              com.example.backendkotlin.generated.grpc.ListVillagesRequest.class, com.example.backendkotlin.generated.grpc.ListVillagesRequest.Builder.class);
    }

    // Construct using com.example.backendkotlin.generated.grpc.ListVillagesRequest.newBuilder()
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
      isRecruitedOnly_ = false;
      return this;
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.Descriptor
        getDescriptorForType() {
      return com.example.backendkotlin.generated.grpc.VillageServiceProto.internal_static_village_ListVillagesRequest_descriptor;
    }

    @java.lang.Override
    public com.example.backendkotlin.generated.grpc.ListVillagesRequest getDefaultInstanceForType() {
      return com.example.backendkotlin.generated.grpc.ListVillagesRequest.getDefaultInstance();
    }

    @java.lang.Override
    public com.example.backendkotlin.generated.grpc.ListVillagesRequest build() {
      com.example.backendkotlin.generated.grpc.ListVillagesRequest result = buildPartial();
      if (!result.isInitialized()) {
        throw newUninitializedMessageException(result);
      }
      return result;
    }

    @java.lang.Override
    public com.example.backendkotlin.generated.grpc.ListVillagesRequest buildPartial() {
      com.example.backendkotlin.generated.grpc.ListVillagesRequest result = new com.example.backendkotlin.generated.grpc.ListVillagesRequest(this);
      if (bitField0_ != 0) { buildPartial0(result); }
      onBuilt();
      return result;
    }

    private void buildPartial0(com.example.backendkotlin.generated.grpc.ListVillagesRequest result) {
      int from_bitField0_ = bitField0_;
      if (((from_bitField0_ & 0x00000001) != 0)) {
        result.isRecruitedOnly_ = isRecruitedOnly_;
      }
    }

    @java.lang.Override
    public Builder mergeFrom(com.google.protobuf.Message other) {
      if (other instanceof com.example.backendkotlin.generated.grpc.ListVillagesRequest) {
        return mergeFrom((com.example.backendkotlin.generated.grpc.ListVillagesRequest)other);
      } else {
        super.mergeFrom(other);
        return this;
      }
    }

    public Builder mergeFrom(com.example.backendkotlin.generated.grpc.ListVillagesRequest other) {
      if (other == com.example.backendkotlin.generated.grpc.ListVillagesRequest.getDefaultInstance()) return this;
      if (other.getIsRecruitedOnly() != false) {
        setIsRecruitedOnly(other.getIsRecruitedOnly());
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
            case 8: {
              isRecruitedOnly_ = input.readBool();
              bitField0_ |= 0x00000001;
              break;
            } // case 8
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

    private boolean isRecruitedOnly_ ;
    /**
     * <code>bool is_recruited_only = 1;</code>
     * @return The isRecruitedOnly.
     */
    @java.lang.Override
    public boolean getIsRecruitedOnly() {
      return isRecruitedOnly_;
    }
    /**
     * <code>bool is_recruited_only = 1;</code>
     * @param value The isRecruitedOnly to set.
     * @return This builder for chaining.
     */
    public Builder setIsRecruitedOnly(boolean value) {

      isRecruitedOnly_ = value;
      bitField0_ |= 0x00000001;
      onChanged();
      return this;
    }
    /**
     * <code>bool is_recruited_only = 1;</code>
     * @return This builder for chaining.
     */
    public Builder clearIsRecruitedOnly() {
      bitField0_ = (bitField0_ & ~0x00000001);
      isRecruitedOnly_ = false;
      onChanged();
      return this;
    }

    // @@protoc_insertion_point(builder_scope:village.ListVillagesRequest)
  }

  // @@protoc_insertion_point(class_scope:village.ListVillagesRequest)
  private static final com.example.backendkotlin.generated.grpc.ListVillagesRequest DEFAULT_INSTANCE;
  static {
    DEFAULT_INSTANCE = new com.example.backendkotlin.generated.grpc.ListVillagesRequest();
  }

  public static com.example.backendkotlin.generated.grpc.ListVillagesRequest getDefaultInstance() {
    return DEFAULT_INSTANCE;
  }

  private static final com.google.protobuf.Parser<ListVillagesRequest>
      PARSER = new com.google.protobuf.AbstractParser<ListVillagesRequest>() {
    @java.lang.Override
    public ListVillagesRequest parsePartialFrom(
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

  public static com.google.protobuf.Parser<ListVillagesRequest> parser() {
    return PARSER;
  }

  @java.lang.Override
  public com.google.protobuf.Parser<ListVillagesRequest> getParserForType() {
    return PARSER;
  }

  @java.lang.Override
  public com.example.backendkotlin.generated.grpc.ListVillagesRequest getDefaultInstanceForType() {
    return DEFAULT_INSTANCE;
  }

}

