// Generated by the protocol buffer compiler.  DO NOT EDIT!
// NO CHECKED-IN PROTOBUF GENCODE
// source: proto/village.proto
// Protobuf Java Version: 4.29.3

package com.example.backendkotlin.generated.grpc;

public interface ListVillagesResponseOrBuilder extends
    // @@protoc_insertion_point(interface_extends:village.ListVillagesResponse)
    com.google.protobuf.MessageOrBuilder {

  /**
   * <code>repeated .village.VillageResponse villages = 1;</code>
   */
  java.util.List<com.example.backendkotlin.generated.grpc.VillageResponse> 
      getVillagesList();
  /**
   * <code>repeated .village.VillageResponse villages = 1;</code>
   */
  com.example.backendkotlin.generated.grpc.VillageResponse getVillages(int index);
  /**
   * <code>repeated .village.VillageResponse villages = 1;</code>
   */
  int getVillagesCount();
  /**
   * <code>repeated .village.VillageResponse villages = 1;</code>
   */
  java.util.List<? extends com.example.backendkotlin.generated.grpc.VillageResponseOrBuilder> 
      getVillagesOrBuilderList();
  /**
   * <code>repeated .village.VillageResponse villages = 1;</code>
   */
  com.example.backendkotlin.generated.grpc.VillageResponseOrBuilder getVillagesOrBuilder(
      int index);
}
