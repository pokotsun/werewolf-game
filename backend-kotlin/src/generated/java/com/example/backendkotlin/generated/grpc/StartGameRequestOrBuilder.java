// Generated by the protocol buffer compiler.  DO NOT EDIT!
// NO CHECKED-IN PROTOBUF GENCODE
// source: proto/village.proto
// Protobuf Java Version: 4.29.3

package com.example.backendkotlin.generated.grpc;

public interface StartGameRequestOrBuilder extends
    // @@protoc_insertion_point(interface_extends:village.StartGameRequest)
    com.google.protobuf.MessageOrBuilder {

  /**
   * <code>string village_id = 1;</code>
   * @return The villageId.
   */
  java.lang.String getVillageId();
  /**
   * <code>string village_id = 1;</code>
   * @return The bytes for villageId.
   */
  com.google.protobuf.ByteString
      getVillageIdBytes();

  /**
   * <code>string village_password = 2;</code>
   * @return The villagePassword.
   */
  java.lang.String getVillagePassword();
  /**
   * <code>string village_password = 2;</code>
   * @return The bytes for villagePassword.
   */
  com.google.protobuf.ByteString
      getVillagePasswordBytes();

  /**
   * <code>string game_master_id = 3;</code>
   * @return The gameMasterId.
   */
  java.lang.String getGameMasterId();
  /**
   * <code>string game_master_id = 3;</code>
   * @return The bytes for gameMasterId.
   */
  com.google.protobuf.ByteString
      getGameMasterIdBytes();

  /**
   * <code>string game_master_password = 4;</code>
   * @return The gameMasterPassword.
   */
  java.lang.String getGameMasterPassword();
  /**
   * <code>string game_master_password = 4;</code>
   * @return The bytes for gameMasterPassword.
   */
  com.google.protobuf.ByteString
      getGameMasterPasswordBytes();
}
