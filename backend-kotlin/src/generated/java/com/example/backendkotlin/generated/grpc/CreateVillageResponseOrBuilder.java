// Generated by the protocol buffer compiler.  DO NOT EDIT!
// NO CHECKED-IN PROTOBUF GENCODE
// source: proto/village.proto
// Protobuf Java Version: 4.29.3

package com.example.backendkotlin.generated.grpc;

public interface CreateVillageResponseOrBuilder extends
    // @@protoc_insertion_point(interface_extends:village.CreateVillageResponse)
    com.google.protobuf.MessageOrBuilder {

  /**
   * <code>string id = 1;</code>
   * @return The id.
   */
  java.lang.String getId();
  /**
   * <code>string id = 1;</code>
   * @return The bytes for id.
   */
  com.google.protobuf.ByteString
      getIdBytes();

  /**
   * <code>string name = 2;</code>
   * @return The name.
   */
  java.lang.String getName();
  /**
   * <code>string name = 2;</code>
   * @return The bytes for name.
   */
  com.google.protobuf.ByteString
      getNameBytes();

  /**
   * <code>int32 user_number = 3;</code>
   * @return The userNumber.
   */
  int getUserNumber();

  /**
   * <code>string created_at = 4;</code>
   * @return The createdAt.
   */
  java.lang.String getCreatedAt();
  /**
   * <code>string created_at = 4;</code>
   * @return The bytes for createdAt.
   */
  com.google.protobuf.ByteString
      getCreatedAtBytes();

  /**
   * <code>string updated_at = 5;</code>
   * @return The updatedAt.
   */
  java.lang.String getUpdatedAt();
  /**
   * <code>string updated_at = 5;</code>
   * @return The bytes for updatedAt.
   */
  com.google.protobuf.ByteString
      getUpdatedAtBytes();
}
