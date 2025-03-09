// Generated by the protocol buffer compiler.  DO NOT EDIT!
// NO CHECKED-IN PROTOBUF GENCODE
// source: proto/village.proto
// Protobuf Java Version: 4.29.3

package com.example.backendkotlin.generated.grpc;

/**
 * <pre>
 * GetVillageResponse メッセージ
 * </pre>
 *
 * Protobuf type {@code village.GetVillageResponse}
 */
public final class GetVillageResponse extends
    com.google.protobuf.GeneratedMessage implements
    // @@protoc_insertion_point(message_implements:village.GetVillageResponse)
    GetVillageResponseOrBuilder {
private static final long serialVersionUID = 0L;
  static {
    com.google.protobuf.RuntimeVersion.validateProtobufGencodeVersion(
      com.google.protobuf.RuntimeVersion.RuntimeDomain.PUBLIC,
      /* major= */ 4,
      /* minor= */ 29,
      /* patch= */ 3,
      /* suffix= */ "",
      GetVillageResponse.class.getName());
  }
  // Use GetVillageResponse.newBuilder() to construct.
  private GetVillageResponse(com.google.protobuf.GeneratedMessage.Builder<?> builder) {
    super(builder);
  }
  private GetVillageResponse() {
    id_ = "";
    name_ = "";
  }

  public static final com.google.protobuf.Descriptors.Descriptor
      getDescriptor() {
    return com.example.backendkotlin.generated.grpc.VillageServiceProto.internal_static_village_GetVillageResponse_descriptor;
  }

  @java.lang.Override
  protected com.google.protobuf.GeneratedMessage.FieldAccessorTable
      internalGetFieldAccessorTable() {
    return com.example.backendkotlin.generated.grpc.VillageServiceProto.internal_static_village_GetVillageResponse_fieldAccessorTable
        .ensureFieldAccessorsInitialized(
            com.example.backendkotlin.generated.grpc.GetVillageResponse.class, com.example.backendkotlin.generated.grpc.GetVillageResponse.Builder.class);
  }

  public static final int ID_FIELD_NUMBER = 1;
  @SuppressWarnings("serial")
  private volatile java.lang.Object id_ = "";
  /**
   * <code>string id = 1;</code>
   * @return The id.
   */
  @java.lang.Override
  public java.lang.String getId() {
    java.lang.Object ref = id_;
    if (ref instanceof java.lang.String) {
      return (java.lang.String) ref;
    } else {
      com.google.protobuf.ByteString bs = 
          (com.google.protobuf.ByteString) ref;
      java.lang.String s = bs.toStringUtf8();
      id_ = s;
      return s;
    }
  }
  /**
   * <code>string id = 1;</code>
   * @return The bytes for id.
   */
  @java.lang.Override
  public com.google.protobuf.ByteString
      getIdBytes() {
    java.lang.Object ref = id_;
    if (ref instanceof java.lang.String) {
      com.google.protobuf.ByteString b = 
          com.google.protobuf.ByteString.copyFromUtf8(
              (java.lang.String) ref);
      id_ = b;
      return b;
    } else {
      return (com.google.protobuf.ByteString) ref;
    }
  }

  public static final int NAME_FIELD_NUMBER = 2;
  @SuppressWarnings("serial")
  private volatile java.lang.Object name_ = "";
  /**
   * <code>string name = 2;</code>
   * @return The name.
   */
  @java.lang.Override
  public java.lang.String getName() {
    java.lang.Object ref = name_;
    if (ref instanceof java.lang.String) {
      return (java.lang.String) ref;
    } else {
      com.google.protobuf.ByteString bs = 
          (com.google.protobuf.ByteString) ref;
      java.lang.String s = bs.toStringUtf8();
      name_ = s;
      return s;
    }
  }
  /**
   * <code>string name = 2;</code>
   * @return The bytes for name.
   */
  @java.lang.Override
  public com.google.protobuf.ByteString
      getNameBytes() {
    java.lang.Object ref = name_;
    if (ref instanceof java.lang.String) {
      com.google.protobuf.ByteString b = 
          com.google.protobuf.ByteString.copyFromUtf8(
              (java.lang.String) ref);
      name_ = b;
      return b;
    } else {
      return (com.google.protobuf.ByteString) ref;
    }
  }

  public static final int USER_NUMBER_FIELD_NUMBER = 3;
  private int userNumber_ = 0;
  /**
   * <code>int32 user_number = 3;</code>
   * @return The userNumber.
   */
  @java.lang.Override
  public int getUserNumber() {
    return userNumber_;
  }

  public static final int CITIZEN_COUNT_FIELD_NUMBER = 4;
  private int citizenCount_ = 0;
  /**
   * <code>int32 citizen_count = 4;</code>
   * @return The citizenCount.
   */
  @java.lang.Override
  public int getCitizenCount() {
    return citizenCount_;
  }

  public static final int WEREWOLF_COUNT_FIELD_NUMBER = 5;
  private int werewolfCount_ = 0;
  /**
   * <code>int32 werewolf_count = 5;</code>
   * @return The werewolfCount.
   */
  @java.lang.Override
  public int getWerewolfCount() {
    return werewolfCount_;
  }

  public static final int FORTUNE_TELLER_COUNT_FIELD_NUMBER = 6;
  private int fortuneTellerCount_ = 0;
  /**
   * <code>int32 fortune_teller_count = 6;</code>
   * @return The fortuneTellerCount.
   */
  @java.lang.Override
  public int getFortuneTellerCount() {
    return fortuneTellerCount_;
  }

  public static final int KNIGHT_COUNT_FIELD_NUMBER = 7;
  private int knightCount_ = 0;
  /**
   * <code>int32 knight_count = 7;</code>
   * @return The knightCount.
   */
  @java.lang.Override
  public int getKnightCount() {
    return knightCount_;
  }

  public static final int PSYCHIC_COUNT_FIELD_NUMBER = 8;
  private int psychicCount_ = 0;
  /**
   * <code>int32 psychic_count = 8;</code>
   * @return The psychicCount.
   */
  @java.lang.Override
  public int getPsychicCount() {
    return psychicCount_;
  }

  public static final int MADMAN_COUNT_FIELD_NUMBER = 9;
  private int madmanCount_ = 0;
  /**
   * <code>int32 madman_count = 9;</code>
   * @return The madmanCount.
   */
  @java.lang.Override
  public int getMadmanCount() {
    return madmanCount_;
  }

  public static final int IS_INITIAL_ACTION_ACTIVE_FIELD_NUMBER = 10;
  private boolean isInitialActionActive_ = false;
  /**
   * <code>bool is_initial_action_active = 10;</code>
   * @return The isInitialActionActive.
   */
  @java.lang.Override
  public boolean getIsInitialActionActive() {
    return isInitialActionActive_;
  }

  public static final int CURRENT_USER_NUMBER_FIELD_NUMBER = 11;
  private int currentUserNumber_ = 0;
  /**
   * <code>int32 current_user_number = 11;</code>
   * @return The currentUserNumber.
   */
  @java.lang.Override
  public int getCurrentUserNumber() {
    return currentUserNumber_;
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
    if (!com.google.protobuf.GeneratedMessage.isStringEmpty(id_)) {
      com.google.protobuf.GeneratedMessage.writeString(output, 1, id_);
    }
    if (!com.google.protobuf.GeneratedMessage.isStringEmpty(name_)) {
      com.google.protobuf.GeneratedMessage.writeString(output, 2, name_);
    }
    if (userNumber_ != 0) {
      output.writeInt32(3, userNumber_);
    }
    if (citizenCount_ != 0) {
      output.writeInt32(4, citizenCount_);
    }
    if (werewolfCount_ != 0) {
      output.writeInt32(5, werewolfCount_);
    }
    if (fortuneTellerCount_ != 0) {
      output.writeInt32(6, fortuneTellerCount_);
    }
    if (knightCount_ != 0) {
      output.writeInt32(7, knightCount_);
    }
    if (psychicCount_ != 0) {
      output.writeInt32(8, psychicCount_);
    }
    if (madmanCount_ != 0) {
      output.writeInt32(9, madmanCount_);
    }
    if (isInitialActionActive_ != false) {
      output.writeBool(10, isInitialActionActive_);
    }
    if (currentUserNumber_ != 0) {
      output.writeInt32(11, currentUserNumber_);
    }
    getUnknownFields().writeTo(output);
  }

  @java.lang.Override
  public int getSerializedSize() {
    int size = memoizedSize;
    if (size != -1) return size;

    size = 0;
    if (!com.google.protobuf.GeneratedMessage.isStringEmpty(id_)) {
      size += com.google.protobuf.GeneratedMessage.computeStringSize(1, id_);
    }
    if (!com.google.protobuf.GeneratedMessage.isStringEmpty(name_)) {
      size += com.google.protobuf.GeneratedMessage.computeStringSize(2, name_);
    }
    if (userNumber_ != 0) {
      size += com.google.protobuf.CodedOutputStream
        .computeInt32Size(3, userNumber_);
    }
    if (citizenCount_ != 0) {
      size += com.google.protobuf.CodedOutputStream
        .computeInt32Size(4, citizenCount_);
    }
    if (werewolfCount_ != 0) {
      size += com.google.protobuf.CodedOutputStream
        .computeInt32Size(5, werewolfCount_);
    }
    if (fortuneTellerCount_ != 0) {
      size += com.google.protobuf.CodedOutputStream
        .computeInt32Size(6, fortuneTellerCount_);
    }
    if (knightCount_ != 0) {
      size += com.google.protobuf.CodedOutputStream
        .computeInt32Size(7, knightCount_);
    }
    if (psychicCount_ != 0) {
      size += com.google.protobuf.CodedOutputStream
        .computeInt32Size(8, psychicCount_);
    }
    if (madmanCount_ != 0) {
      size += com.google.protobuf.CodedOutputStream
        .computeInt32Size(9, madmanCount_);
    }
    if (isInitialActionActive_ != false) {
      size += com.google.protobuf.CodedOutputStream
        .computeBoolSize(10, isInitialActionActive_);
    }
    if (currentUserNumber_ != 0) {
      size += com.google.protobuf.CodedOutputStream
        .computeInt32Size(11, currentUserNumber_);
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
    if (!(obj instanceof com.example.backendkotlin.generated.grpc.GetVillageResponse)) {
      return super.equals(obj);
    }
    com.example.backendkotlin.generated.grpc.GetVillageResponse other = (com.example.backendkotlin.generated.grpc.GetVillageResponse) obj;

    if (!getId()
        .equals(other.getId())) return false;
    if (!getName()
        .equals(other.getName())) return false;
    if (getUserNumber()
        != other.getUserNumber()) return false;
    if (getCitizenCount()
        != other.getCitizenCount()) return false;
    if (getWerewolfCount()
        != other.getWerewolfCount()) return false;
    if (getFortuneTellerCount()
        != other.getFortuneTellerCount()) return false;
    if (getKnightCount()
        != other.getKnightCount()) return false;
    if (getPsychicCount()
        != other.getPsychicCount()) return false;
    if (getMadmanCount()
        != other.getMadmanCount()) return false;
    if (getIsInitialActionActive()
        != other.getIsInitialActionActive()) return false;
    if (getCurrentUserNumber()
        != other.getCurrentUserNumber()) return false;
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
    hash = (37 * hash) + ID_FIELD_NUMBER;
    hash = (53 * hash) + getId().hashCode();
    hash = (37 * hash) + NAME_FIELD_NUMBER;
    hash = (53 * hash) + getName().hashCode();
    hash = (37 * hash) + USER_NUMBER_FIELD_NUMBER;
    hash = (53 * hash) + getUserNumber();
    hash = (37 * hash) + CITIZEN_COUNT_FIELD_NUMBER;
    hash = (53 * hash) + getCitizenCount();
    hash = (37 * hash) + WEREWOLF_COUNT_FIELD_NUMBER;
    hash = (53 * hash) + getWerewolfCount();
    hash = (37 * hash) + FORTUNE_TELLER_COUNT_FIELD_NUMBER;
    hash = (53 * hash) + getFortuneTellerCount();
    hash = (37 * hash) + KNIGHT_COUNT_FIELD_NUMBER;
    hash = (53 * hash) + getKnightCount();
    hash = (37 * hash) + PSYCHIC_COUNT_FIELD_NUMBER;
    hash = (53 * hash) + getPsychicCount();
    hash = (37 * hash) + MADMAN_COUNT_FIELD_NUMBER;
    hash = (53 * hash) + getMadmanCount();
    hash = (37 * hash) + IS_INITIAL_ACTION_ACTIVE_FIELD_NUMBER;
    hash = (53 * hash) + com.google.protobuf.Internal.hashBoolean(
        getIsInitialActionActive());
    hash = (37 * hash) + CURRENT_USER_NUMBER_FIELD_NUMBER;
    hash = (53 * hash) + getCurrentUserNumber();
    hash = (29 * hash) + getUnknownFields().hashCode();
    memoizedHashCode = hash;
    return hash;
  }

  public static com.example.backendkotlin.generated.grpc.GetVillageResponse parseFrom(
      java.nio.ByteBuffer data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static com.example.backendkotlin.generated.grpc.GetVillageResponse parseFrom(
      java.nio.ByteBuffer data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static com.example.backendkotlin.generated.grpc.GetVillageResponse parseFrom(
      com.google.protobuf.ByteString data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static com.example.backendkotlin.generated.grpc.GetVillageResponse parseFrom(
      com.google.protobuf.ByteString data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static com.example.backendkotlin.generated.grpc.GetVillageResponse parseFrom(byte[] data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static com.example.backendkotlin.generated.grpc.GetVillageResponse parseFrom(
      byte[] data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static com.example.backendkotlin.generated.grpc.GetVillageResponse parseFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessage
        .parseWithIOException(PARSER, input);
  }
  public static com.example.backendkotlin.generated.grpc.GetVillageResponse parseFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessage
        .parseWithIOException(PARSER, input, extensionRegistry);
  }

  public static com.example.backendkotlin.generated.grpc.GetVillageResponse parseDelimitedFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessage
        .parseDelimitedWithIOException(PARSER, input);
  }

  public static com.example.backendkotlin.generated.grpc.GetVillageResponse parseDelimitedFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessage
        .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
  }
  public static com.example.backendkotlin.generated.grpc.GetVillageResponse parseFrom(
      com.google.protobuf.CodedInputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessage
        .parseWithIOException(PARSER, input);
  }
  public static com.example.backendkotlin.generated.grpc.GetVillageResponse parseFrom(
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
  public static Builder newBuilder(com.example.backendkotlin.generated.grpc.GetVillageResponse prototype) {
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
   * GetVillageResponse メッセージ
   * </pre>
   *
   * Protobuf type {@code village.GetVillageResponse}
   */
  public static final class Builder extends
      com.google.protobuf.GeneratedMessage.Builder<Builder> implements
      // @@protoc_insertion_point(builder_implements:village.GetVillageResponse)
      com.example.backendkotlin.generated.grpc.GetVillageResponseOrBuilder {
    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return com.example.backendkotlin.generated.grpc.VillageServiceProto.internal_static_village_GetVillageResponse_descriptor;
    }

    @java.lang.Override
    protected com.google.protobuf.GeneratedMessage.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return com.example.backendkotlin.generated.grpc.VillageServiceProto.internal_static_village_GetVillageResponse_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              com.example.backendkotlin.generated.grpc.GetVillageResponse.class, com.example.backendkotlin.generated.grpc.GetVillageResponse.Builder.class);
    }

    // Construct using com.example.backendkotlin.generated.grpc.GetVillageResponse.newBuilder()
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
      id_ = "";
      name_ = "";
      userNumber_ = 0;
      citizenCount_ = 0;
      werewolfCount_ = 0;
      fortuneTellerCount_ = 0;
      knightCount_ = 0;
      psychicCount_ = 0;
      madmanCount_ = 0;
      isInitialActionActive_ = false;
      currentUserNumber_ = 0;
      return this;
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.Descriptor
        getDescriptorForType() {
      return com.example.backendkotlin.generated.grpc.VillageServiceProto.internal_static_village_GetVillageResponse_descriptor;
    }

    @java.lang.Override
    public com.example.backendkotlin.generated.grpc.GetVillageResponse getDefaultInstanceForType() {
      return com.example.backendkotlin.generated.grpc.GetVillageResponse.getDefaultInstance();
    }

    @java.lang.Override
    public com.example.backendkotlin.generated.grpc.GetVillageResponse build() {
      com.example.backendkotlin.generated.grpc.GetVillageResponse result = buildPartial();
      if (!result.isInitialized()) {
        throw newUninitializedMessageException(result);
      }
      return result;
    }

    @java.lang.Override
    public com.example.backendkotlin.generated.grpc.GetVillageResponse buildPartial() {
      com.example.backendkotlin.generated.grpc.GetVillageResponse result = new com.example.backendkotlin.generated.grpc.GetVillageResponse(this);
      if (bitField0_ != 0) { buildPartial0(result); }
      onBuilt();
      return result;
    }

    private void buildPartial0(com.example.backendkotlin.generated.grpc.GetVillageResponse result) {
      int from_bitField0_ = bitField0_;
      if (((from_bitField0_ & 0x00000001) != 0)) {
        result.id_ = id_;
      }
      if (((from_bitField0_ & 0x00000002) != 0)) {
        result.name_ = name_;
      }
      if (((from_bitField0_ & 0x00000004) != 0)) {
        result.userNumber_ = userNumber_;
      }
      if (((from_bitField0_ & 0x00000008) != 0)) {
        result.citizenCount_ = citizenCount_;
      }
      if (((from_bitField0_ & 0x00000010) != 0)) {
        result.werewolfCount_ = werewolfCount_;
      }
      if (((from_bitField0_ & 0x00000020) != 0)) {
        result.fortuneTellerCount_ = fortuneTellerCount_;
      }
      if (((from_bitField0_ & 0x00000040) != 0)) {
        result.knightCount_ = knightCount_;
      }
      if (((from_bitField0_ & 0x00000080) != 0)) {
        result.psychicCount_ = psychicCount_;
      }
      if (((from_bitField0_ & 0x00000100) != 0)) {
        result.madmanCount_ = madmanCount_;
      }
      if (((from_bitField0_ & 0x00000200) != 0)) {
        result.isInitialActionActive_ = isInitialActionActive_;
      }
      if (((from_bitField0_ & 0x00000400) != 0)) {
        result.currentUserNumber_ = currentUserNumber_;
      }
    }

    @java.lang.Override
    public Builder mergeFrom(com.google.protobuf.Message other) {
      if (other instanceof com.example.backendkotlin.generated.grpc.GetVillageResponse) {
        return mergeFrom((com.example.backendkotlin.generated.grpc.GetVillageResponse)other);
      } else {
        super.mergeFrom(other);
        return this;
      }
    }

    public Builder mergeFrom(com.example.backendkotlin.generated.grpc.GetVillageResponse other) {
      if (other == com.example.backendkotlin.generated.grpc.GetVillageResponse.getDefaultInstance()) return this;
      if (!other.getId().isEmpty()) {
        id_ = other.id_;
        bitField0_ |= 0x00000001;
        onChanged();
      }
      if (!other.getName().isEmpty()) {
        name_ = other.name_;
        bitField0_ |= 0x00000002;
        onChanged();
      }
      if (other.getUserNumber() != 0) {
        setUserNumber(other.getUserNumber());
      }
      if (other.getCitizenCount() != 0) {
        setCitizenCount(other.getCitizenCount());
      }
      if (other.getWerewolfCount() != 0) {
        setWerewolfCount(other.getWerewolfCount());
      }
      if (other.getFortuneTellerCount() != 0) {
        setFortuneTellerCount(other.getFortuneTellerCount());
      }
      if (other.getKnightCount() != 0) {
        setKnightCount(other.getKnightCount());
      }
      if (other.getPsychicCount() != 0) {
        setPsychicCount(other.getPsychicCount());
      }
      if (other.getMadmanCount() != 0) {
        setMadmanCount(other.getMadmanCount());
      }
      if (other.getIsInitialActionActive() != false) {
        setIsInitialActionActive(other.getIsInitialActionActive());
      }
      if (other.getCurrentUserNumber() != 0) {
        setCurrentUserNumber(other.getCurrentUserNumber());
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
              id_ = input.readStringRequireUtf8();
              bitField0_ |= 0x00000001;
              break;
            } // case 10
            case 18: {
              name_ = input.readStringRequireUtf8();
              bitField0_ |= 0x00000002;
              break;
            } // case 18
            case 24: {
              userNumber_ = input.readInt32();
              bitField0_ |= 0x00000004;
              break;
            } // case 24
            case 32: {
              citizenCount_ = input.readInt32();
              bitField0_ |= 0x00000008;
              break;
            } // case 32
            case 40: {
              werewolfCount_ = input.readInt32();
              bitField0_ |= 0x00000010;
              break;
            } // case 40
            case 48: {
              fortuneTellerCount_ = input.readInt32();
              bitField0_ |= 0x00000020;
              break;
            } // case 48
            case 56: {
              knightCount_ = input.readInt32();
              bitField0_ |= 0x00000040;
              break;
            } // case 56
            case 64: {
              psychicCount_ = input.readInt32();
              bitField0_ |= 0x00000080;
              break;
            } // case 64
            case 72: {
              madmanCount_ = input.readInt32();
              bitField0_ |= 0x00000100;
              break;
            } // case 72
            case 80: {
              isInitialActionActive_ = input.readBool();
              bitField0_ |= 0x00000200;
              break;
            } // case 80
            case 88: {
              currentUserNumber_ = input.readInt32();
              bitField0_ |= 0x00000400;
              break;
            } // case 88
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

    private java.lang.Object id_ = "";
    /**
     * <code>string id = 1;</code>
     * @return The id.
     */
    public java.lang.String getId() {
      java.lang.Object ref = id_;
      if (!(ref instanceof java.lang.String)) {
        com.google.protobuf.ByteString bs =
            (com.google.protobuf.ByteString) ref;
        java.lang.String s = bs.toStringUtf8();
        id_ = s;
        return s;
      } else {
        return (java.lang.String) ref;
      }
    }
    /**
     * <code>string id = 1;</code>
     * @return The bytes for id.
     */
    public com.google.protobuf.ByteString
        getIdBytes() {
      java.lang.Object ref = id_;
      if (ref instanceof String) {
        com.google.protobuf.ByteString b = 
            com.google.protobuf.ByteString.copyFromUtf8(
                (java.lang.String) ref);
        id_ = b;
        return b;
      } else {
        return (com.google.protobuf.ByteString) ref;
      }
    }
    /**
     * <code>string id = 1;</code>
     * @param value The id to set.
     * @return This builder for chaining.
     */
    public Builder setId(
        java.lang.String value) {
      if (value == null) { throw new NullPointerException(); }
      id_ = value;
      bitField0_ |= 0x00000001;
      onChanged();
      return this;
    }
    /**
     * <code>string id = 1;</code>
     * @return This builder for chaining.
     */
    public Builder clearId() {
      id_ = getDefaultInstance().getId();
      bitField0_ = (bitField0_ & ~0x00000001);
      onChanged();
      return this;
    }
    /**
     * <code>string id = 1;</code>
     * @param value The bytes for id to set.
     * @return This builder for chaining.
     */
    public Builder setIdBytes(
        com.google.protobuf.ByteString value) {
      if (value == null) { throw new NullPointerException(); }
      checkByteStringIsUtf8(value);
      id_ = value;
      bitField0_ |= 0x00000001;
      onChanged();
      return this;
    }

    private java.lang.Object name_ = "";
    /**
     * <code>string name = 2;</code>
     * @return The name.
     */
    public java.lang.String getName() {
      java.lang.Object ref = name_;
      if (!(ref instanceof java.lang.String)) {
        com.google.protobuf.ByteString bs =
            (com.google.protobuf.ByteString) ref;
        java.lang.String s = bs.toStringUtf8();
        name_ = s;
        return s;
      } else {
        return (java.lang.String) ref;
      }
    }
    /**
     * <code>string name = 2;</code>
     * @return The bytes for name.
     */
    public com.google.protobuf.ByteString
        getNameBytes() {
      java.lang.Object ref = name_;
      if (ref instanceof String) {
        com.google.protobuf.ByteString b = 
            com.google.protobuf.ByteString.copyFromUtf8(
                (java.lang.String) ref);
        name_ = b;
        return b;
      } else {
        return (com.google.protobuf.ByteString) ref;
      }
    }
    /**
     * <code>string name = 2;</code>
     * @param value The name to set.
     * @return This builder for chaining.
     */
    public Builder setName(
        java.lang.String value) {
      if (value == null) { throw new NullPointerException(); }
      name_ = value;
      bitField0_ |= 0x00000002;
      onChanged();
      return this;
    }
    /**
     * <code>string name = 2;</code>
     * @return This builder for chaining.
     */
    public Builder clearName() {
      name_ = getDefaultInstance().getName();
      bitField0_ = (bitField0_ & ~0x00000002);
      onChanged();
      return this;
    }
    /**
     * <code>string name = 2;</code>
     * @param value The bytes for name to set.
     * @return This builder for chaining.
     */
    public Builder setNameBytes(
        com.google.protobuf.ByteString value) {
      if (value == null) { throw new NullPointerException(); }
      checkByteStringIsUtf8(value);
      name_ = value;
      bitField0_ |= 0x00000002;
      onChanged();
      return this;
    }

    private int userNumber_ ;
    /**
     * <code>int32 user_number = 3;</code>
     * @return The userNumber.
     */
    @java.lang.Override
    public int getUserNumber() {
      return userNumber_;
    }
    /**
     * <code>int32 user_number = 3;</code>
     * @param value The userNumber to set.
     * @return This builder for chaining.
     */
    public Builder setUserNumber(int value) {

      userNumber_ = value;
      bitField0_ |= 0x00000004;
      onChanged();
      return this;
    }
    /**
     * <code>int32 user_number = 3;</code>
     * @return This builder for chaining.
     */
    public Builder clearUserNumber() {
      bitField0_ = (bitField0_ & ~0x00000004);
      userNumber_ = 0;
      onChanged();
      return this;
    }

    private int citizenCount_ ;
    /**
     * <code>int32 citizen_count = 4;</code>
     * @return The citizenCount.
     */
    @java.lang.Override
    public int getCitizenCount() {
      return citizenCount_;
    }
    /**
     * <code>int32 citizen_count = 4;</code>
     * @param value The citizenCount to set.
     * @return This builder for chaining.
     */
    public Builder setCitizenCount(int value) {

      citizenCount_ = value;
      bitField0_ |= 0x00000008;
      onChanged();
      return this;
    }
    /**
     * <code>int32 citizen_count = 4;</code>
     * @return This builder for chaining.
     */
    public Builder clearCitizenCount() {
      bitField0_ = (bitField0_ & ~0x00000008);
      citizenCount_ = 0;
      onChanged();
      return this;
    }

    private int werewolfCount_ ;
    /**
     * <code>int32 werewolf_count = 5;</code>
     * @return The werewolfCount.
     */
    @java.lang.Override
    public int getWerewolfCount() {
      return werewolfCount_;
    }
    /**
     * <code>int32 werewolf_count = 5;</code>
     * @param value The werewolfCount to set.
     * @return This builder for chaining.
     */
    public Builder setWerewolfCount(int value) {

      werewolfCount_ = value;
      bitField0_ |= 0x00000010;
      onChanged();
      return this;
    }
    /**
     * <code>int32 werewolf_count = 5;</code>
     * @return This builder for chaining.
     */
    public Builder clearWerewolfCount() {
      bitField0_ = (bitField0_ & ~0x00000010);
      werewolfCount_ = 0;
      onChanged();
      return this;
    }

    private int fortuneTellerCount_ ;
    /**
     * <code>int32 fortune_teller_count = 6;</code>
     * @return The fortuneTellerCount.
     */
    @java.lang.Override
    public int getFortuneTellerCount() {
      return fortuneTellerCount_;
    }
    /**
     * <code>int32 fortune_teller_count = 6;</code>
     * @param value The fortuneTellerCount to set.
     * @return This builder for chaining.
     */
    public Builder setFortuneTellerCount(int value) {

      fortuneTellerCount_ = value;
      bitField0_ |= 0x00000020;
      onChanged();
      return this;
    }
    /**
     * <code>int32 fortune_teller_count = 6;</code>
     * @return This builder for chaining.
     */
    public Builder clearFortuneTellerCount() {
      bitField0_ = (bitField0_ & ~0x00000020);
      fortuneTellerCount_ = 0;
      onChanged();
      return this;
    }

    private int knightCount_ ;
    /**
     * <code>int32 knight_count = 7;</code>
     * @return The knightCount.
     */
    @java.lang.Override
    public int getKnightCount() {
      return knightCount_;
    }
    /**
     * <code>int32 knight_count = 7;</code>
     * @param value The knightCount to set.
     * @return This builder for chaining.
     */
    public Builder setKnightCount(int value) {

      knightCount_ = value;
      bitField0_ |= 0x00000040;
      onChanged();
      return this;
    }
    /**
     * <code>int32 knight_count = 7;</code>
     * @return This builder for chaining.
     */
    public Builder clearKnightCount() {
      bitField0_ = (bitField0_ & ~0x00000040);
      knightCount_ = 0;
      onChanged();
      return this;
    }

    private int psychicCount_ ;
    /**
     * <code>int32 psychic_count = 8;</code>
     * @return The psychicCount.
     */
    @java.lang.Override
    public int getPsychicCount() {
      return psychicCount_;
    }
    /**
     * <code>int32 psychic_count = 8;</code>
     * @param value The psychicCount to set.
     * @return This builder for chaining.
     */
    public Builder setPsychicCount(int value) {

      psychicCount_ = value;
      bitField0_ |= 0x00000080;
      onChanged();
      return this;
    }
    /**
     * <code>int32 psychic_count = 8;</code>
     * @return This builder for chaining.
     */
    public Builder clearPsychicCount() {
      bitField0_ = (bitField0_ & ~0x00000080);
      psychicCount_ = 0;
      onChanged();
      return this;
    }

    private int madmanCount_ ;
    /**
     * <code>int32 madman_count = 9;</code>
     * @return The madmanCount.
     */
    @java.lang.Override
    public int getMadmanCount() {
      return madmanCount_;
    }
    /**
     * <code>int32 madman_count = 9;</code>
     * @param value The madmanCount to set.
     * @return This builder for chaining.
     */
    public Builder setMadmanCount(int value) {

      madmanCount_ = value;
      bitField0_ |= 0x00000100;
      onChanged();
      return this;
    }
    /**
     * <code>int32 madman_count = 9;</code>
     * @return This builder for chaining.
     */
    public Builder clearMadmanCount() {
      bitField0_ = (bitField0_ & ~0x00000100);
      madmanCount_ = 0;
      onChanged();
      return this;
    }

    private boolean isInitialActionActive_ ;
    /**
     * <code>bool is_initial_action_active = 10;</code>
     * @return The isInitialActionActive.
     */
    @java.lang.Override
    public boolean getIsInitialActionActive() {
      return isInitialActionActive_;
    }
    /**
     * <code>bool is_initial_action_active = 10;</code>
     * @param value The isInitialActionActive to set.
     * @return This builder for chaining.
     */
    public Builder setIsInitialActionActive(boolean value) {

      isInitialActionActive_ = value;
      bitField0_ |= 0x00000200;
      onChanged();
      return this;
    }
    /**
     * <code>bool is_initial_action_active = 10;</code>
     * @return This builder for chaining.
     */
    public Builder clearIsInitialActionActive() {
      bitField0_ = (bitField0_ & ~0x00000200);
      isInitialActionActive_ = false;
      onChanged();
      return this;
    }

    private int currentUserNumber_ ;
    /**
     * <code>int32 current_user_number = 11;</code>
     * @return The currentUserNumber.
     */
    @java.lang.Override
    public int getCurrentUserNumber() {
      return currentUserNumber_;
    }
    /**
     * <code>int32 current_user_number = 11;</code>
     * @param value The currentUserNumber to set.
     * @return This builder for chaining.
     */
    public Builder setCurrentUserNumber(int value) {

      currentUserNumber_ = value;
      bitField0_ |= 0x00000400;
      onChanged();
      return this;
    }
    /**
     * <code>int32 current_user_number = 11;</code>
     * @return This builder for chaining.
     */
    public Builder clearCurrentUserNumber() {
      bitField0_ = (bitField0_ & ~0x00000400);
      currentUserNumber_ = 0;
      onChanged();
      return this;
    }

    // @@protoc_insertion_point(builder_scope:village.GetVillageResponse)
  }

  // @@protoc_insertion_point(class_scope:village.GetVillageResponse)
  private static final com.example.backendkotlin.generated.grpc.GetVillageResponse DEFAULT_INSTANCE;
  static {
    DEFAULT_INSTANCE = new com.example.backendkotlin.generated.grpc.GetVillageResponse();
  }

  public static com.example.backendkotlin.generated.grpc.GetVillageResponse getDefaultInstance() {
    return DEFAULT_INSTANCE;
  }

  private static final com.google.protobuf.Parser<GetVillageResponse>
      PARSER = new com.google.protobuf.AbstractParser<GetVillageResponse>() {
    @java.lang.Override
    public GetVillageResponse parsePartialFrom(
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

  public static com.google.protobuf.Parser<GetVillageResponse> parser() {
    return PARSER;
  }

  @java.lang.Override
  public com.google.protobuf.Parser<GetVillageResponse> getParserForType() {
    return PARSER;
  }

  @java.lang.Override
  public com.example.backendkotlin.generated.grpc.GetVillageResponse getDefaultInstanceForType() {
    return DEFAULT_INSTANCE;
  }

}

