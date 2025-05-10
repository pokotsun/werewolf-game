package com.example.backendkotlin.generated.grpc;

import static io.grpc.MethodDescriptor.generateFullMethodName;

/**
 * <pre>
 * GameService 定義
 * </pre>
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.69.1)",
    comments = "Source: proto/game.proto")
@io.grpc.stub.annotations.GrpcGenerated
public final class GameServiceGrpc {

  private GameServiceGrpc() {}

  public static final java.lang.String SERVICE_NAME = "game.GameService";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<com.example.backendkotlin.generated.grpc.GetActorRequest,
      com.example.backendkotlin.generated.grpc.GetActorResponse> getGetActorMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "GetActor",
      requestType = com.example.backendkotlin.generated.grpc.GetActorRequest.class,
      responseType = com.example.backendkotlin.generated.grpc.GetActorResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.example.backendkotlin.generated.grpc.GetActorRequest,
      com.example.backendkotlin.generated.grpc.GetActorResponse> getGetActorMethod() {
    io.grpc.MethodDescriptor<com.example.backendkotlin.generated.grpc.GetActorRequest, com.example.backendkotlin.generated.grpc.GetActorResponse> getGetActorMethod;
    if ((getGetActorMethod = GameServiceGrpc.getGetActorMethod) == null) {
      synchronized (GameServiceGrpc.class) {
        if ((getGetActorMethod = GameServiceGrpc.getGetActorMethod) == null) {
          GameServiceGrpc.getGetActorMethod = getGetActorMethod =
              io.grpc.MethodDescriptor.<com.example.backendkotlin.generated.grpc.GetActorRequest, com.example.backendkotlin.generated.grpc.GetActorResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "GetActor"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.example.backendkotlin.generated.grpc.GetActorRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.example.backendkotlin.generated.grpc.GetActorResponse.getDefaultInstance()))
              .setSchemaDescriptor(new GameServiceMethodDescriptorSupplier("GetActor"))
              .build();
        }
      }
    }
    return getGetActorMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.example.backendkotlin.generated.grpc.GetGameStatusRequest,
      com.example.backendkotlin.generated.grpc.GetGameStatusResponse> getGetGameStatusMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "GetGameStatus",
      requestType = com.example.backendkotlin.generated.grpc.GetGameStatusRequest.class,
      responseType = com.example.backendkotlin.generated.grpc.GetGameStatusResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.example.backendkotlin.generated.grpc.GetGameStatusRequest,
      com.example.backendkotlin.generated.grpc.GetGameStatusResponse> getGetGameStatusMethod() {
    io.grpc.MethodDescriptor<com.example.backendkotlin.generated.grpc.GetGameStatusRequest, com.example.backendkotlin.generated.grpc.GetGameStatusResponse> getGetGameStatusMethod;
    if ((getGetGameStatusMethod = GameServiceGrpc.getGetGameStatusMethod) == null) {
      synchronized (GameServiceGrpc.class) {
        if ((getGetGameStatusMethod = GameServiceGrpc.getGetGameStatusMethod) == null) {
          GameServiceGrpc.getGetGameStatusMethod = getGetGameStatusMethod =
              io.grpc.MethodDescriptor.<com.example.backendkotlin.generated.grpc.GetGameStatusRequest, com.example.backendkotlin.generated.grpc.GetGameStatusResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "GetGameStatus"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.example.backendkotlin.generated.grpc.GetGameStatusRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.example.backendkotlin.generated.grpc.GetGameStatusResponse.getDefaultInstance()))
              .setSchemaDescriptor(new GameServiceMethodDescriptorSupplier("GetGameStatus"))
              .build();
        }
      }
    }
    return getGetGameStatusMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.example.backendkotlin.generated.grpc.GetDeadPlayersLogRequest,
      com.example.backendkotlin.generated.grpc.GetDeadPlayersLogResponse> getGetDeadPlayersLogMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "GetDeadPlayersLog",
      requestType = com.example.backendkotlin.generated.grpc.GetDeadPlayersLogRequest.class,
      responseType = com.example.backendkotlin.generated.grpc.GetDeadPlayersLogResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.example.backendkotlin.generated.grpc.GetDeadPlayersLogRequest,
      com.example.backendkotlin.generated.grpc.GetDeadPlayersLogResponse> getGetDeadPlayersLogMethod() {
    io.grpc.MethodDescriptor<com.example.backendkotlin.generated.grpc.GetDeadPlayersLogRequest, com.example.backendkotlin.generated.grpc.GetDeadPlayersLogResponse> getGetDeadPlayersLogMethod;
    if ((getGetDeadPlayersLogMethod = GameServiceGrpc.getGetDeadPlayersLogMethod) == null) {
      synchronized (GameServiceGrpc.class) {
        if ((getGetDeadPlayersLogMethod = GameServiceGrpc.getGetDeadPlayersLogMethod) == null) {
          GameServiceGrpc.getGetDeadPlayersLogMethod = getGetDeadPlayersLogMethod =
              io.grpc.MethodDescriptor.<com.example.backendkotlin.generated.grpc.GetDeadPlayersLogRequest, com.example.backendkotlin.generated.grpc.GetDeadPlayersLogResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "GetDeadPlayersLog"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.example.backendkotlin.generated.grpc.GetDeadPlayersLogRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.example.backendkotlin.generated.grpc.GetDeadPlayersLogResponse.getDefaultInstance()))
              .setSchemaDescriptor(new GameServiceMethodDescriptorSupplier("GetDeadPlayersLog"))
              .build();
        }
      }
    }
    return getGetDeadPlayersLogMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.example.backendkotlin.generated.grpc.GetSelfActionLogRequest,
      com.example.backendkotlin.generated.grpc.GetSelfActionLogResponse> getGetSelfActionLogMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "GetSelfActionLog",
      requestType = com.example.backendkotlin.generated.grpc.GetSelfActionLogRequest.class,
      responseType = com.example.backendkotlin.generated.grpc.GetSelfActionLogResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.example.backendkotlin.generated.grpc.GetSelfActionLogRequest,
      com.example.backendkotlin.generated.grpc.GetSelfActionLogResponse> getGetSelfActionLogMethod() {
    io.grpc.MethodDescriptor<com.example.backendkotlin.generated.grpc.GetSelfActionLogRequest, com.example.backendkotlin.generated.grpc.GetSelfActionLogResponse> getGetSelfActionLogMethod;
    if ((getGetSelfActionLogMethod = GameServiceGrpc.getGetSelfActionLogMethod) == null) {
      synchronized (GameServiceGrpc.class) {
        if ((getGetSelfActionLogMethod = GameServiceGrpc.getGetSelfActionLogMethod) == null) {
          GameServiceGrpc.getGetSelfActionLogMethod = getGetSelfActionLogMethod =
              io.grpc.MethodDescriptor.<com.example.backendkotlin.generated.grpc.GetSelfActionLogRequest, com.example.backendkotlin.generated.grpc.GetSelfActionLogResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "GetSelfActionLog"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.example.backendkotlin.generated.grpc.GetSelfActionLogRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.example.backendkotlin.generated.grpc.GetSelfActionLogResponse.getDefaultInstance()))
              .setSchemaDescriptor(new GameServiceMethodDescriptorSupplier("GetSelfActionLog"))
              .build();
        }
      }
    }
    return getGetSelfActionLogMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.example.backendkotlin.generated.grpc.ExecSelfActionRequest,
      com.example.backendkotlin.generated.grpc.ExecSelfActionResponse> getExecSelfActionMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "ExecSelfAction",
      requestType = com.example.backendkotlin.generated.grpc.ExecSelfActionRequest.class,
      responseType = com.example.backendkotlin.generated.grpc.ExecSelfActionResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.example.backendkotlin.generated.grpc.ExecSelfActionRequest,
      com.example.backendkotlin.generated.grpc.ExecSelfActionResponse> getExecSelfActionMethod() {
    io.grpc.MethodDescriptor<com.example.backendkotlin.generated.grpc.ExecSelfActionRequest, com.example.backendkotlin.generated.grpc.ExecSelfActionResponse> getExecSelfActionMethod;
    if ((getExecSelfActionMethod = GameServiceGrpc.getExecSelfActionMethod) == null) {
      synchronized (GameServiceGrpc.class) {
        if ((getExecSelfActionMethod = GameServiceGrpc.getExecSelfActionMethod) == null) {
          GameServiceGrpc.getExecSelfActionMethod = getExecSelfActionMethod =
              io.grpc.MethodDescriptor.<com.example.backendkotlin.generated.grpc.ExecSelfActionRequest, com.example.backendkotlin.generated.grpc.ExecSelfActionResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "ExecSelfAction"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.example.backendkotlin.generated.grpc.ExecSelfActionRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.example.backendkotlin.generated.grpc.ExecSelfActionResponse.getDefaultInstance()))
              .setSchemaDescriptor(new GameServiceMethodDescriptorSupplier("ExecSelfAction"))
              .build();
        }
      }
    }
    return getExecSelfActionMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.example.backendkotlin.generated.grpc.GetGameResultRequest,
      com.example.backendkotlin.generated.grpc.GetGameResultResponse> getGetGameResultMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "GetGameResult",
      requestType = com.example.backendkotlin.generated.grpc.GetGameResultRequest.class,
      responseType = com.example.backendkotlin.generated.grpc.GetGameResultResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.example.backendkotlin.generated.grpc.GetGameResultRequest,
      com.example.backendkotlin.generated.grpc.GetGameResultResponse> getGetGameResultMethod() {
    io.grpc.MethodDescriptor<com.example.backendkotlin.generated.grpc.GetGameResultRequest, com.example.backendkotlin.generated.grpc.GetGameResultResponse> getGetGameResultMethod;
    if ((getGetGameResultMethod = GameServiceGrpc.getGetGameResultMethod) == null) {
      synchronized (GameServiceGrpc.class) {
        if ((getGetGameResultMethod = GameServiceGrpc.getGetGameResultMethod) == null) {
          GameServiceGrpc.getGetGameResultMethod = getGetGameResultMethod =
              io.grpc.MethodDescriptor.<com.example.backendkotlin.generated.grpc.GetGameResultRequest, com.example.backendkotlin.generated.grpc.GetGameResultResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "GetGameResult"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.example.backendkotlin.generated.grpc.GetGameResultRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.example.backendkotlin.generated.grpc.GetGameResultResponse.getDefaultInstance()))
              .setSchemaDescriptor(new GameServiceMethodDescriptorSupplier("GetGameResult"))
              .build();
        }
      }
    }
    return getGetGameResultMethod;
  }

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static GameServiceStub newStub(io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<GameServiceStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<GameServiceStub>() {
        @java.lang.Override
        public GameServiceStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new GameServiceStub(channel, callOptions);
        }
      };
    return GameServiceStub.newStub(factory, channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static GameServiceBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<GameServiceBlockingStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<GameServiceBlockingStub>() {
        @java.lang.Override
        public GameServiceBlockingStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new GameServiceBlockingStub(channel, callOptions);
        }
      };
    return GameServiceBlockingStub.newStub(factory, channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static GameServiceFutureStub newFutureStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<GameServiceFutureStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<GameServiceFutureStub>() {
        @java.lang.Override
        public GameServiceFutureStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new GameServiceFutureStub(channel, callOptions);
        }
      };
    return GameServiceFutureStub.newStub(factory, channel);
  }

  /**
   * <pre>
   * GameService 定義
   * </pre>
   */
  public interface AsyncService {

    /**
     */
    default void getActor(com.example.backendkotlin.generated.grpc.GetActorRequest request,
        io.grpc.stub.StreamObserver<com.example.backendkotlin.generated.grpc.GetActorResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getGetActorMethod(), responseObserver);
    }

    /**
     */
    default void getGameStatus(com.example.backendkotlin.generated.grpc.GetGameStatusRequest request,
        io.grpc.stub.StreamObserver<com.example.backendkotlin.generated.grpc.GetGameStatusResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getGetGameStatusMethod(), responseObserver);
    }

    /**
     */
    default void getDeadPlayersLog(com.example.backendkotlin.generated.grpc.GetDeadPlayersLogRequest request,
        io.grpc.stub.StreamObserver<com.example.backendkotlin.generated.grpc.GetDeadPlayersLogResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getGetDeadPlayersLogMethod(), responseObserver);
    }

    /**
     */
    default void getSelfActionLog(com.example.backendkotlin.generated.grpc.GetSelfActionLogRequest request,
        io.grpc.stub.StreamObserver<com.example.backendkotlin.generated.grpc.GetSelfActionLogResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getGetSelfActionLogMethod(), responseObserver);
    }

    /**
     */
    default void execSelfAction(com.example.backendkotlin.generated.grpc.ExecSelfActionRequest request,
        io.grpc.stub.StreamObserver<com.example.backendkotlin.generated.grpc.ExecSelfActionResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getExecSelfActionMethod(), responseObserver);
    }

    /**
     */
    default void getGameResult(com.example.backendkotlin.generated.grpc.GetGameResultRequest request,
        io.grpc.stub.StreamObserver<com.example.backendkotlin.generated.grpc.GetGameResultResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getGetGameResultMethod(), responseObserver);
    }
  }

  /**
   * Base class for the server implementation of the service GameService.
   * <pre>
   * GameService 定義
   * </pre>
   */
  public static abstract class GameServiceImplBase
      implements io.grpc.BindableService, AsyncService {

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return GameServiceGrpc.bindService(this);
    }
  }

  /**
   * A stub to allow clients to do asynchronous rpc calls to service GameService.
   * <pre>
   * GameService 定義
   * </pre>
   */
  public static final class GameServiceStub
      extends io.grpc.stub.AbstractAsyncStub<GameServiceStub> {
    private GameServiceStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected GameServiceStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new GameServiceStub(channel, callOptions);
    }

    /**
     */
    public void getActor(com.example.backendkotlin.generated.grpc.GetActorRequest request,
        io.grpc.stub.StreamObserver<com.example.backendkotlin.generated.grpc.GetActorResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getGetActorMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void getGameStatus(com.example.backendkotlin.generated.grpc.GetGameStatusRequest request,
        io.grpc.stub.StreamObserver<com.example.backendkotlin.generated.grpc.GetGameStatusResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getGetGameStatusMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void getDeadPlayersLog(com.example.backendkotlin.generated.grpc.GetDeadPlayersLogRequest request,
        io.grpc.stub.StreamObserver<com.example.backendkotlin.generated.grpc.GetDeadPlayersLogResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getGetDeadPlayersLogMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void getSelfActionLog(com.example.backendkotlin.generated.grpc.GetSelfActionLogRequest request,
        io.grpc.stub.StreamObserver<com.example.backendkotlin.generated.grpc.GetSelfActionLogResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getGetSelfActionLogMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void execSelfAction(com.example.backendkotlin.generated.grpc.ExecSelfActionRequest request,
        io.grpc.stub.StreamObserver<com.example.backendkotlin.generated.grpc.ExecSelfActionResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getExecSelfActionMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void getGameResult(com.example.backendkotlin.generated.grpc.GetGameResultRequest request,
        io.grpc.stub.StreamObserver<com.example.backendkotlin.generated.grpc.GetGameResultResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getGetGameResultMethod(), getCallOptions()), request, responseObserver);
    }
  }

  /**
   * A stub to allow clients to do synchronous rpc calls to service GameService.
   * <pre>
   * GameService 定義
   * </pre>
   */
  public static final class GameServiceBlockingStub
      extends io.grpc.stub.AbstractBlockingStub<GameServiceBlockingStub> {
    private GameServiceBlockingStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected GameServiceBlockingStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new GameServiceBlockingStub(channel, callOptions);
    }

    /**
     */
    public com.example.backendkotlin.generated.grpc.GetActorResponse getActor(com.example.backendkotlin.generated.grpc.GetActorRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getGetActorMethod(), getCallOptions(), request);
    }

    /**
     */
    public com.example.backendkotlin.generated.grpc.GetGameStatusResponse getGameStatus(com.example.backendkotlin.generated.grpc.GetGameStatusRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getGetGameStatusMethod(), getCallOptions(), request);
    }

    /**
     */
    public com.example.backendkotlin.generated.grpc.GetDeadPlayersLogResponse getDeadPlayersLog(com.example.backendkotlin.generated.grpc.GetDeadPlayersLogRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getGetDeadPlayersLogMethod(), getCallOptions(), request);
    }

    /**
     */
    public com.example.backendkotlin.generated.grpc.GetSelfActionLogResponse getSelfActionLog(com.example.backendkotlin.generated.grpc.GetSelfActionLogRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getGetSelfActionLogMethod(), getCallOptions(), request);
    }

    /**
     */
    public com.example.backendkotlin.generated.grpc.ExecSelfActionResponse execSelfAction(com.example.backendkotlin.generated.grpc.ExecSelfActionRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getExecSelfActionMethod(), getCallOptions(), request);
    }

    /**
     */
    public com.example.backendkotlin.generated.grpc.GetGameResultResponse getGameResult(com.example.backendkotlin.generated.grpc.GetGameResultRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getGetGameResultMethod(), getCallOptions(), request);
    }
  }

  /**
   * A stub to allow clients to do ListenableFuture-style rpc calls to service GameService.
   * <pre>
   * GameService 定義
   * </pre>
   */
  public static final class GameServiceFutureStub
      extends io.grpc.stub.AbstractFutureStub<GameServiceFutureStub> {
    private GameServiceFutureStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected GameServiceFutureStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new GameServiceFutureStub(channel, callOptions);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<com.example.backendkotlin.generated.grpc.GetActorResponse> getActor(
        com.example.backendkotlin.generated.grpc.GetActorRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getGetActorMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<com.example.backendkotlin.generated.grpc.GetGameStatusResponse> getGameStatus(
        com.example.backendkotlin.generated.grpc.GetGameStatusRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getGetGameStatusMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<com.example.backendkotlin.generated.grpc.GetDeadPlayersLogResponse> getDeadPlayersLog(
        com.example.backendkotlin.generated.grpc.GetDeadPlayersLogRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getGetDeadPlayersLogMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<com.example.backendkotlin.generated.grpc.GetSelfActionLogResponse> getSelfActionLog(
        com.example.backendkotlin.generated.grpc.GetSelfActionLogRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getGetSelfActionLogMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<com.example.backendkotlin.generated.grpc.ExecSelfActionResponse> execSelfAction(
        com.example.backendkotlin.generated.grpc.ExecSelfActionRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getExecSelfActionMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<com.example.backendkotlin.generated.grpc.GetGameResultResponse> getGameResult(
        com.example.backendkotlin.generated.grpc.GetGameResultRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getGetGameResultMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_GET_ACTOR = 0;
  private static final int METHODID_GET_GAME_STATUS = 1;
  private static final int METHODID_GET_DEAD_PLAYERS_LOG = 2;
  private static final int METHODID_GET_SELF_ACTION_LOG = 3;
  private static final int METHODID_EXEC_SELF_ACTION = 4;
  private static final int METHODID_GET_GAME_RESULT = 5;

  private static final class MethodHandlers<Req, Resp> implements
      io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {
    private final AsyncService serviceImpl;
    private final int methodId;

    MethodHandlers(AsyncService serviceImpl, int methodId) {
      this.serviceImpl = serviceImpl;
      this.methodId = methodId;
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_GET_ACTOR:
          serviceImpl.getActor((com.example.backendkotlin.generated.grpc.GetActorRequest) request,
              (io.grpc.stub.StreamObserver<com.example.backendkotlin.generated.grpc.GetActorResponse>) responseObserver);
          break;
        case METHODID_GET_GAME_STATUS:
          serviceImpl.getGameStatus((com.example.backendkotlin.generated.grpc.GetGameStatusRequest) request,
              (io.grpc.stub.StreamObserver<com.example.backendkotlin.generated.grpc.GetGameStatusResponse>) responseObserver);
          break;
        case METHODID_GET_DEAD_PLAYERS_LOG:
          serviceImpl.getDeadPlayersLog((com.example.backendkotlin.generated.grpc.GetDeadPlayersLogRequest) request,
              (io.grpc.stub.StreamObserver<com.example.backendkotlin.generated.grpc.GetDeadPlayersLogResponse>) responseObserver);
          break;
        case METHODID_GET_SELF_ACTION_LOG:
          serviceImpl.getSelfActionLog((com.example.backendkotlin.generated.grpc.GetSelfActionLogRequest) request,
              (io.grpc.stub.StreamObserver<com.example.backendkotlin.generated.grpc.GetSelfActionLogResponse>) responseObserver);
          break;
        case METHODID_EXEC_SELF_ACTION:
          serviceImpl.execSelfAction((com.example.backendkotlin.generated.grpc.ExecSelfActionRequest) request,
              (io.grpc.stub.StreamObserver<com.example.backendkotlin.generated.grpc.ExecSelfActionResponse>) responseObserver);
          break;
        case METHODID_GET_GAME_RESULT:
          serviceImpl.getGameResult((com.example.backendkotlin.generated.grpc.GetGameResultRequest) request,
              (io.grpc.stub.StreamObserver<com.example.backendkotlin.generated.grpc.GetGameResultResponse>) responseObserver);
          break;
        default:
          throw new AssertionError();
      }
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public io.grpc.stub.StreamObserver<Req> invoke(
        io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        default:
          throw new AssertionError();
      }
    }
  }

  public static final io.grpc.ServerServiceDefinition bindService(AsyncService service) {
    return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
        .addMethod(
          getGetActorMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              com.example.backendkotlin.generated.grpc.GetActorRequest,
              com.example.backendkotlin.generated.grpc.GetActorResponse>(
                service, METHODID_GET_ACTOR)))
        .addMethod(
          getGetGameStatusMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              com.example.backendkotlin.generated.grpc.GetGameStatusRequest,
              com.example.backendkotlin.generated.grpc.GetGameStatusResponse>(
                service, METHODID_GET_GAME_STATUS)))
        .addMethod(
          getGetDeadPlayersLogMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              com.example.backendkotlin.generated.grpc.GetDeadPlayersLogRequest,
              com.example.backendkotlin.generated.grpc.GetDeadPlayersLogResponse>(
                service, METHODID_GET_DEAD_PLAYERS_LOG)))
        .addMethod(
          getGetSelfActionLogMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              com.example.backendkotlin.generated.grpc.GetSelfActionLogRequest,
              com.example.backendkotlin.generated.grpc.GetSelfActionLogResponse>(
                service, METHODID_GET_SELF_ACTION_LOG)))
        .addMethod(
          getExecSelfActionMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              com.example.backendkotlin.generated.grpc.ExecSelfActionRequest,
              com.example.backendkotlin.generated.grpc.ExecSelfActionResponse>(
                service, METHODID_EXEC_SELF_ACTION)))
        .addMethod(
          getGetGameResultMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              com.example.backendkotlin.generated.grpc.GetGameResultRequest,
              com.example.backendkotlin.generated.grpc.GetGameResultResponse>(
                service, METHODID_GET_GAME_RESULT)))
        .build();
  }

  private static abstract class GameServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    GameServiceBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return com.example.backendkotlin.generated.grpc.GameServiceProto.getDescriptor();
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
      return getFileDescriptor().findServiceByName("GameService");
    }
  }

  private static final class GameServiceFileDescriptorSupplier
      extends GameServiceBaseDescriptorSupplier {
    GameServiceFileDescriptorSupplier() {}
  }

  private static final class GameServiceMethodDescriptorSupplier
      extends GameServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
    private final java.lang.String methodName;

    GameServiceMethodDescriptorSupplier(java.lang.String methodName) {
      this.methodName = methodName;
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.MethodDescriptor getMethodDescriptor() {
      return getServiceDescriptor().findMethodByName(methodName);
    }
  }

  private static volatile io.grpc.ServiceDescriptor serviceDescriptor;

  public static io.grpc.ServiceDescriptor getServiceDescriptor() {
    io.grpc.ServiceDescriptor result = serviceDescriptor;
    if (result == null) {
      synchronized (GameServiceGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new GameServiceFileDescriptorSupplier())
              .addMethod(getGetActorMethod())
              .addMethod(getGetGameStatusMethod())
              .addMethod(getGetDeadPlayersLogMethod())
              .addMethod(getGetSelfActionLogMethod())
              .addMethod(getExecSelfActionMethod())
              .addMethod(getGetGameResultMethod())
              .build();
        }
      }
    }
    return result;
  }
}
