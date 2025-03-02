// Generated by the protocol buffer compiler.  DO NOT EDIT!
// NO CHECKED-IN PROTOBUF GENCODE
// source: proto/village.proto
// Protobuf Java Version: 4.29.3

package com.example.backendkotlin.generated.grpc;

public final class VillageServiceProto {
  private VillageServiceProto() {}
  static {
    com.google.protobuf.RuntimeVersion.validateProtobufGencodeVersion(
      com.google.protobuf.RuntimeVersion.RuntimeDomain.PUBLIC,
      /* major= */ 4,
      /* minor= */ 29,
      /* patch= */ 3,
      /* suffix= */ "",
      VillageServiceProto.class.getName());
  }
  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistryLite registry) {
  }

  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistry registry) {
    registerAllExtensions(
        (com.google.protobuf.ExtensionRegistryLite) registry);
  }
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_village_CreateVillageRequest_descriptor;
  static final 
    com.google.protobuf.GeneratedMessage.FieldAccessorTable
      internal_static_village_CreateVillageRequest_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_village_CreateVillageResponse_descriptor;
  static final 
    com.google.protobuf.GeneratedMessage.FieldAccessorTable
      internal_static_village_CreateVillageResponse_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_village_ListVillagesRequest_descriptor;
  static final 
    com.google.protobuf.GeneratedMessage.FieldAccessorTable
      internal_static_village_ListVillagesRequest_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_village_ListVillagesResponse_descriptor;
  static final 
    com.google.protobuf.GeneratedMessage.FieldAccessorTable
      internal_static_village_ListVillagesResponse_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_village_VillageResponse_descriptor;
  static final 
    com.google.protobuf.GeneratedMessage.FieldAccessorTable
      internal_static_village_VillageResponse_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_village_EnterVillageRequest_descriptor;
  static final 
    com.google.protobuf.GeneratedMessage.FieldAccessorTable
      internal_static_village_EnterVillageRequest_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_village_EnterVillageResponse_descriptor;
  static final 
    com.google.protobuf.GeneratedMessage.FieldAccessorTable
      internal_static_village_EnterVillageResponse_fieldAccessorTable;

  public static com.google.protobuf.Descriptors.FileDescriptor
      getDescriptor() {
    return descriptor;
  }
  private static  com.google.protobuf.Descriptors.FileDescriptor
      descriptor;
  static {
    java.lang.String[] descriptorData = {
      "\n\023proto/village.proto\022\007village\"\240\002\n\024Creat" +
      "eVillageRequest\022\014\n\004name\030\001 \001(\t\022\025\n\rcitizen" +
      "_count\030\002 \001(\005\022\026\n\016werewolf_count\030\003 \001(\005\022\034\n\024" +
      "fortune_teller_count\030\004 \001(\005\022\024\n\014knight_cou" +
      "nt\030\005 \001(\005\022\025\n\rpsychic_count\030\006 \001(\005\022\024\n\014madma" +
      "n_count\030\007 \001(\005\022 \n\030is_initial_action_activ" +
      "e\030\010 \001(\010\022\020\n\010password\030\t \001(\t\022\030\n\020game_master" +
      "_name\030\n \001(\t\022\034\n\024game_master_password\030\013 \001(" +
      "\t\"\262\002\n\025CreateVillageResponse\022\n\n\002id\030\001 \001(\t\022" +
      "\014\n\004name\030\002 \001(\t\022\023\n\013user_number\030\003 \001(\005\022\025\n\rci" +
      "tizen_count\030\004 \001(\005\022\026\n\016werewolf_count\030\005 \001(" +
      "\005\022\034\n\024fortune_teller_count\030\006 \001(\005\022\024\n\014knigh" +
      "t_count\030\007 \001(\005\022\025\n\rpsychic_count\030\010 \001(\005\022\024\n\014" +
      "madman_count\030\t \001(\005\022 \n\030is_initial_action_" +
      "active\030\n \001(\010\022\033\n\023game_master_user_id\030\013 \001(" +
      "\t\022\033\n\023current_user_number\030\014 \001(\005\"\025\n\023ListVi" +
      "llagesRequest\"B\n\024ListVillagesResponse\022*\n" +
      "\010villages\030\001 \003(\0132\030.village.VillageRespons" +
      "e\"\217\002\n\017VillageResponse\022\n\n\002id\030\001 \001(\t\022\014\n\004nam" +
      "e\030\002 \001(\t\022\023\n\013user_number\030\003 \001(\005\022\025\n\rcitizen_" +
      "count\030\004 \001(\005\022\026\n\016werewolf_count\030\005 \001(\005\022\034\n\024f" +
      "ortune_teller_count\030\006 \001(\005\022\024\n\014knight_coun" +
      "t\030\007 \001(\005\022\025\n\rpsychic_count\030\010 \001(\005\022\024\n\014madman" +
      "_count\030\t \001(\005\022 \n\030is_initial_action_active" +
      "\030\n \001(\010\022\033\n\023current_user_number\030\013 \001(\005\"m\n\023E" +
      "nterVillageRequest\022\022\n\nvillage_id\030\001 \001(\t\022\030" +
      "\n\020village_password\030\002 \001(\t\022\021\n\tuser_name\030\003 " +
      "\001(\t\022\025\n\ruser_password\030\004 \001(\t\";\n\024EnterVilla" +
      "geResponse\022\022\n\nvillage_id\030\001 \001(\t\022\017\n\007user_i" +
      "d\030\002 \001(\t2\372\001\n\016VillageService\022N\n\rCreateVill" +
      "age\022\035.village.CreateVillageRequest\032\036.vil" +
      "lage.CreateVillageResponse\022K\n\014ListVillag" +
      "es\022\034.village.ListVillagesRequest\032\035.villa" +
      "ge.ListVillagesResponse\022K\n\014EnterVillage\022" +
      "\034.village.EnterVillageRequest\032\035.village." +
      "EnterVillageResponseBl\n(com.example.back" +
      "endkotlin.generated.grpcB\023VillageService" +
      "ProtoP\001Z)github.com/pokotsun/werewolf/gr" +
      "pc/villageb\006proto3"
    };
    descriptor = com.google.protobuf.Descriptors.FileDescriptor
      .internalBuildGeneratedFileFrom(descriptorData,
        new com.google.protobuf.Descriptors.FileDescriptor[] {
        });
    internal_static_village_CreateVillageRequest_descriptor =
      getDescriptor().getMessageTypes().get(0);
    internal_static_village_CreateVillageRequest_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessage.FieldAccessorTable(
        internal_static_village_CreateVillageRequest_descriptor,
        new java.lang.String[] { "Name", "CitizenCount", "WerewolfCount", "FortuneTellerCount", "KnightCount", "PsychicCount", "MadmanCount", "IsInitialActionActive", "Password", "GameMasterName", "GameMasterPassword", });
    internal_static_village_CreateVillageResponse_descriptor =
      getDescriptor().getMessageTypes().get(1);
    internal_static_village_CreateVillageResponse_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessage.FieldAccessorTable(
        internal_static_village_CreateVillageResponse_descriptor,
        new java.lang.String[] { "Id", "Name", "UserNumber", "CitizenCount", "WerewolfCount", "FortuneTellerCount", "KnightCount", "PsychicCount", "MadmanCount", "IsInitialActionActive", "GameMasterUserId", "CurrentUserNumber", });
    internal_static_village_ListVillagesRequest_descriptor =
      getDescriptor().getMessageTypes().get(2);
    internal_static_village_ListVillagesRequest_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessage.FieldAccessorTable(
        internal_static_village_ListVillagesRequest_descriptor,
        new java.lang.String[] { });
    internal_static_village_ListVillagesResponse_descriptor =
      getDescriptor().getMessageTypes().get(3);
    internal_static_village_ListVillagesResponse_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessage.FieldAccessorTable(
        internal_static_village_ListVillagesResponse_descriptor,
        new java.lang.String[] { "Villages", });
    internal_static_village_VillageResponse_descriptor =
      getDescriptor().getMessageTypes().get(4);
    internal_static_village_VillageResponse_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessage.FieldAccessorTable(
        internal_static_village_VillageResponse_descriptor,
        new java.lang.String[] { "Id", "Name", "UserNumber", "CitizenCount", "WerewolfCount", "FortuneTellerCount", "KnightCount", "PsychicCount", "MadmanCount", "IsInitialActionActive", "CurrentUserNumber", });
    internal_static_village_EnterVillageRequest_descriptor =
      getDescriptor().getMessageTypes().get(5);
    internal_static_village_EnterVillageRequest_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessage.FieldAccessorTable(
        internal_static_village_EnterVillageRequest_descriptor,
        new java.lang.String[] { "VillageId", "VillagePassword", "UserName", "UserPassword", });
    internal_static_village_EnterVillageResponse_descriptor =
      getDescriptor().getMessageTypes().get(6);
    internal_static_village_EnterVillageResponse_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessage.FieldAccessorTable(
        internal_static_village_EnterVillageResponse_descriptor,
        new java.lang.String[] { "VillageId", "UserId", });
    descriptor.resolveAllFeaturesImmutable();
  }

  // @@protoc_insertion_point(outer_class_scope)
}
