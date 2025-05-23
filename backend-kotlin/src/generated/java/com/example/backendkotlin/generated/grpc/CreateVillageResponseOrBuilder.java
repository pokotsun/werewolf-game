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
   * <code>int32 citizen_count = 4;</code>
   * @return The citizenCount.
   */
  int getCitizenCount();

  /**
   * <code>int32 werewolf_count = 5;</code>
   * @return The werewolfCount.
   */
  int getWerewolfCount();

  /**
   * <code>int32 fortune_teller_count = 6;</code>
   * @return The fortuneTellerCount.
   */
  int getFortuneTellerCount();

  /**
   * <code>int32 knight_count = 7;</code>
   * @return The knightCount.
   */
  int getKnightCount();

  /**
   * <code>int32 psychic_count = 8;</code>
   * @return The psychicCount.
   */
  int getPsychicCount();

  /**
   * <code>int32 madman_count = 9;</code>
   * @return The madmanCount.
   */
  int getMadmanCount();

  /**
   * <code>bool is_initial_action_active = 10;</code>
   * @return The isInitialActionActive.
   */
  boolean getIsInitialActionActive();

  /**
   * <code>string game_master_user_id = 11;</code>
   * @return The gameMasterUserId.
   */
  java.lang.String getGameMasterUserId();
  /**
   * <code>string game_master_user_id = 11;</code>
   * @return The bytes for gameMasterUserId.
   */
  com.google.protobuf.ByteString
      getGameMasterUserIdBytes();

  /**
   * <code>int32 current_user_number = 12;</code>
   * @return The currentUserNumber.
   */
  int getCurrentUserNumber();
}
