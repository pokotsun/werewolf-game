package com.example.backendkotlin.generated.grpc;

import static io.grpc.MethodDescriptor.generateFullMethodName;

/**
 * <pre>
 * VillageService 定義
 * </pre>
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.69.1)",
    comments = "Source: proto/village.proto")
@io.grpc.stub.annotations.GrpcGenerated
public final class VillageServiceGrpc {

  private VillageServiceGrpc() {}

  public static final java.lang.String SERVICE_NAME = "village.VillageService";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<com.example.backendkotlin.generated.grpc.CreateVillageRequest,
      com.example.backendkotlin.generated.grpc.CreateVillageResponse> getCreateVillageMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "CreateVillage",
      requestType = com.example.backendkotlin.generated.grpc.CreateVillageRequest.class,
      responseType = com.example.backendkotlin.generated.grpc.CreateVillageResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.example.backendkotlin.generated.grpc.CreateVillageRequest,
      com.example.backendkotlin.generated.grpc.CreateVillageResponse> getCreateVillageMethod() {
    io.grpc.MethodDescriptor<com.example.backendkotlin.generated.grpc.CreateVillageRequest, com.example.backendkotlin.generated.grpc.CreateVillageResponse> getCreateVillageMethod;
    if ((getCreateVillageMethod = VillageServiceGrpc.getCreateVillageMethod) == null) {
      synchronized (VillageServiceGrpc.class) {
        if ((getCreateVillageMethod = VillageServiceGrpc.getCreateVillageMethod) == null) {
          VillageServiceGrpc.getCreateVillageMethod = getCreateVillageMethod =
              io.grpc.MethodDescriptor.<com.example.backendkotlin.generated.grpc.CreateVillageRequest, com.example.backendkotlin.generated.grpc.CreateVillageResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "CreateVillage"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.example.backendkotlin.generated.grpc.CreateVillageRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.example.backendkotlin.generated.grpc.CreateVillageResponse.getDefaultInstance()))
              .setSchemaDescriptor(new VillageServiceMethodDescriptorSupplier("CreateVillage"))
              .build();
        }
      }
    }
    return getCreateVillageMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.example.backendkotlin.generated.grpc.ListVillagesRequest,
      com.example.backendkotlin.generated.grpc.ListVillagesResponse> getListVillagesMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "ListVillages",
      requestType = com.example.backendkotlin.generated.grpc.ListVillagesRequest.class,
      responseType = com.example.backendkotlin.generated.grpc.ListVillagesResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.example.backendkotlin.generated.grpc.ListVillagesRequest,
      com.example.backendkotlin.generated.grpc.ListVillagesResponse> getListVillagesMethod() {
    io.grpc.MethodDescriptor<com.example.backendkotlin.generated.grpc.ListVillagesRequest, com.example.backendkotlin.generated.grpc.ListVillagesResponse> getListVillagesMethod;
    if ((getListVillagesMethod = VillageServiceGrpc.getListVillagesMethod) == null) {
      synchronized (VillageServiceGrpc.class) {
        if ((getListVillagesMethod = VillageServiceGrpc.getListVillagesMethod) == null) {
          VillageServiceGrpc.getListVillagesMethod = getListVillagesMethod =
              io.grpc.MethodDescriptor.<com.example.backendkotlin.generated.grpc.ListVillagesRequest, com.example.backendkotlin.generated.grpc.ListVillagesResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "ListVillages"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.example.backendkotlin.generated.grpc.ListVillagesRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.example.backendkotlin.generated.grpc.ListVillagesResponse.getDefaultInstance()))
              .setSchemaDescriptor(new VillageServiceMethodDescriptorSupplier("ListVillages"))
              .build();
        }
      }
    }
    return getListVillagesMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.example.backendkotlin.generated.grpc.GetVillageRequest,
      com.example.backendkotlin.generated.grpc.GetVillageResponse> getGetVillageMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "GetVillage",
      requestType = com.example.backendkotlin.generated.grpc.GetVillageRequest.class,
      responseType = com.example.backendkotlin.generated.grpc.GetVillageResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.example.backendkotlin.generated.grpc.GetVillageRequest,
      com.example.backendkotlin.generated.grpc.GetVillageResponse> getGetVillageMethod() {
    io.grpc.MethodDescriptor<com.example.backendkotlin.generated.grpc.GetVillageRequest, com.example.backendkotlin.generated.grpc.GetVillageResponse> getGetVillageMethod;
    if ((getGetVillageMethod = VillageServiceGrpc.getGetVillageMethod) == null) {
      synchronized (VillageServiceGrpc.class) {
        if ((getGetVillageMethod = VillageServiceGrpc.getGetVillageMethod) == null) {
          VillageServiceGrpc.getGetVillageMethod = getGetVillageMethod =
              io.grpc.MethodDescriptor.<com.example.backendkotlin.generated.grpc.GetVillageRequest, com.example.backendkotlin.generated.grpc.GetVillageResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "GetVillage"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.example.backendkotlin.generated.grpc.GetVillageRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.example.backendkotlin.generated.grpc.GetVillageResponse.getDefaultInstance()))
              .setSchemaDescriptor(new VillageServiceMethodDescriptorSupplier("GetVillage"))
              .build();
        }
      }
    }
    return getGetVillageMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.example.backendkotlin.generated.grpc.EnterVillageRequest,
      com.example.backendkotlin.generated.grpc.EnterVillageResponse> getEnterVillageMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "EnterVillage",
      requestType = com.example.backendkotlin.generated.grpc.EnterVillageRequest.class,
      responseType = com.example.backendkotlin.generated.grpc.EnterVillageResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.example.backendkotlin.generated.grpc.EnterVillageRequest,
      com.example.backendkotlin.generated.grpc.EnterVillageResponse> getEnterVillageMethod() {
    io.grpc.MethodDescriptor<com.example.backendkotlin.generated.grpc.EnterVillageRequest, com.example.backendkotlin.generated.grpc.EnterVillageResponse> getEnterVillageMethod;
    if ((getEnterVillageMethod = VillageServiceGrpc.getEnterVillageMethod) == null) {
      synchronized (VillageServiceGrpc.class) {
        if ((getEnterVillageMethod = VillageServiceGrpc.getEnterVillageMethod) == null) {
          VillageServiceGrpc.getEnterVillageMethod = getEnterVillageMethod =
              io.grpc.MethodDescriptor.<com.example.backendkotlin.generated.grpc.EnterVillageRequest, com.example.backendkotlin.generated.grpc.EnterVillageResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "EnterVillage"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.example.backendkotlin.generated.grpc.EnterVillageRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.example.backendkotlin.generated.grpc.EnterVillageResponse.getDefaultInstance()))
              .setSchemaDescriptor(new VillageServiceMethodDescriptorSupplier("EnterVillage"))
              .build();
        }
      }
    }
    return getEnterVillageMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.example.backendkotlin.generated.grpc.GetCurrentVillageUsersRequest,
      com.example.backendkotlin.generated.grpc.GetCurrentVillageUsersResponse> getGetCurrentVillageUsersMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "GetCurrentVillageUsers",
      requestType = com.example.backendkotlin.generated.grpc.GetCurrentVillageUsersRequest.class,
      responseType = com.example.backendkotlin.generated.grpc.GetCurrentVillageUsersResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.SERVER_STREAMING)
  public static io.grpc.MethodDescriptor<com.example.backendkotlin.generated.grpc.GetCurrentVillageUsersRequest,
      com.example.backendkotlin.generated.grpc.GetCurrentVillageUsersResponse> getGetCurrentVillageUsersMethod() {
    io.grpc.MethodDescriptor<com.example.backendkotlin.generated.grpc.GetCurrentVillageUsersRequest, com.example.backendkotlin.generated.grpc.GetCurrentVillageUsersResponse> getGetCurrentVillageUsersMethod;
    if ((getGetCurrentVillageUsersMethod = VillageServiceGrpc.getGetCurrentVillageUsersMethod) == null) {
      synchronized (VillageServiceGrpc.class) {
        if ((getGetCurrentVillageUsersMethod = VillageServiceGrpc.getGetCurrentVillageUsersMethod) == null) {
          VillageServiceGrpc.getGetCurrentVillageUsersMethod = getGetCurrentVillageUsersMethod =
              io.grpc.MethodDescriptor.<com.example.backendkotlin.generated.grpc.GetCurrentVillageUsersRequest, com.example.backendkotlin.generated.grpc.GetCurrentVillageUsersResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.SERVER_STREAMING)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "GetCurrentVillageUsers"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.example.backendkotlin.generated.grpc.GetCurrentVillageUsersRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.example.backendkotlin.generated.grpc.GetCurrentVillageUsersResponse.getDefaultInstance()))
              .setSchemaDescriptor(new VillageServiceMethodDescriptorSupplier("GetCurrentVillageUsers"))
              .build();
        }
      }
    }
    return getGetCurrentVillageUsersMethod;
  }

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static VillageServiceStub newStub(io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<VillageServiceStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<VillageServiceStub>() {
        @java.lang.Override
        public VillageServiceStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new VillageServiceStub(channel, callOptions);
        }
      };
    return VillageServiceStub.newStub(factory, channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static VillageServiceBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<VillageServiceBlockingStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<VillageServiceBlockingStub>() {
        @java.lang.Override
        public VillageServiceBlockingStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new VillageServiceBlockingStub(channel, callOptions);
        }
      };
    return VillageServiceBlockingStub.newStub(factory, channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static VillageServiceFutureStub newFutureStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<VillageServiceFutureStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<VillageServiceFutureStub>() {
        @java.lang.Override
        public VillageServiceFutureStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new VillageServiceFutureStub(channel, callOptions);
        }
      };
    return VillageServiceFutureStub.newStub(factory, channel);
  }

  /**
   * <pre>
   * VillageService 定義
   * </pre>
   */
  public interface AsyncService {

    /**
     */
    default void createVillage(com.example.backendkotlin.generated.grpc.CreateVillageRequest request,
        io.grpc.stub.StreamObserver<com.example.backendkotlin.generated.grpc.CreateVillageResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getCreateVillageMethod(), responseObserver);
    }

    /**
     */
    default void listVillages(com.example.backendkotlin.generated.grpc.ListVillagesRequest request,
        io.grpc.stub.StreamObserver<com.example.backendkotlin.generated.grpc.ListVillagesResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getListVillagesMethod(), responseObserver);
    }

    /**
     */
    default void getVillage(com.example.backendkotlin.generated.grpc.GetVillageRequest request,
        io.grpc.stub.StreamObserver<com.example.backendkotlin.generated.grpc.GetVillageResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getGetVillageMethod(), responseObserver);
    }

    /**
     */
    default void enterVillage(com.example.backendkotlin.generated.grpc.EnterVillageRequest request,
        io.grpc.stub.StreamObserver<com.example.backendkotlin.generated.grpc.EnterVillageResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getEnterVillageMethod(), responseObserver);
    }

    /**
     */
    default void getCurrentVillageUsers(com.example.backendkotlin.generated.grpc.GetCurrentVillageUsersRequest request,
        io.grpc.stub.StreamObserver<com.example.backendkotlin.generated.grpc.GetCurrentVillageUsersResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getGetCurrentVillageUsersMethod(), responseObserver);
    }
  }

  /**
   * Base class for the server implementation of the service VillageService.
   * <pre>
   * VillageService 定義
   * </pre>
   */
  public static abstract class VillageServiceImplBase
      implements io.grpc.BindableService, AsyncService {

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return VillageServiceGrpc.bindService(this);
    }
  }

  /**
   * A stub to allow clients to do asynchronous rpc calls to service VillageService.
   * <pre>
   * VillageService 定義
   * </pre>
   */
  public static final class VillageServiceStub
      extends io.grpc.stub.AbstractAsyncStub<VillageServiceStub> {
    private VillageServiceStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected VillageServiceStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new VillageServiceStub(channel, callOptions);
    }

    /**
     */
    public void createVillage(com.example.backendkotlin.generated.grpc.CreateVillageRequest request,
        io.grpc.stub.StreamObserver<com.example.backendkotlin.generated.grpc.CreateVillageResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getCreateVillageMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void listVillages(com.example.backendkotlin.generated.grpc.ListVillagesRequest request,
        io.grpc.stub.StreamObserver<com.example.backendkotlin.generated.grpc.ListVillagesResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getListVillagesMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void getVillage(com.example.backendkotlin.generated.grpc.GetVillageRequest request,
        io.grpc.stub.StreamObserver<com.example.backendkotlin.generated.grpc.GetVillageResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getGetVillageMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void enterVillage(com.example.backendkotlin.generated.grpc.EnterVillageRequest request,
        io.grpc.stub.StreamObserver<com.example.backendkotlin.generated.grpc.EnterVillageResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getEnterVillageMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void getCurrentVillageUsers(com.example.backendkotlin.generated.grpc.GetCurrentVillageUsersRequest request,
        io.grpc.stub.StreamObserver<com.example.backendkotlin.generated.grpc.GetCurrentVillageUsersResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncServerStreamingCall(
          getChannel().newCall(getGetCurrentVillageUsersMethod(), getCallOptions()), request, responseObserver);
    }
  }

  /**
   * A stub to allow clients to do synchronous rpc calls to service VillageService.
   * <pre>
   * VillageService 定義
   * </pre>
   */
  public static final class VillageServiceBlockingStub
      extends io.grpc.stub.AbstractBlockingStub<VillageServiceBlockingStub> {
    private VillageServiceBlockingStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected VillageServiceBlockingStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new VillageServiceBlockingStub(channel, callOptions);
    }

    /**
     */
    public com.example.backendkotlin.generated.grpc.CreateVillageResponse createVillage(com.example.backendkotlin.generated.grpc.CreateVillageRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getCreateVillageMethod(), getCallOptions(), request);
    }

    /**
     */
    public com.example.backendkotlin.generated.grpc.ListVillagesResponse listVillages(com.example.backendkotlin.generated.grpc.ListVillagesRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getListVillagesMethod(), getCallOptions(), request);
    }

    /**
     */
    public com.example.backendkotlin.generated.grpc.GetVillageResponse getVillage(com.example.backendkotlin.generated.grpc.GetVillageRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getGetVillageMethod(), getCallOptions(), request);
    }

    /**
     */
    public com.example.backendkotlin.generated.grpc.EnterVillageResponse enterVillage(com.example.backendkotlin.generated.grpc.EnterVillageRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getEnterVillageMethod(), getCallOptions(), request);
    }

    /**
     */
    public java.util.Iterator<com.example.backendkotlin.generated.grpc.GetCurrentVillageUsersResponse> getCurrentVillageUsers(
        com.example.backendkotlin.generated.grpc.GetCurrentVillageUsersRequest request) {
      return io.grpc.stub.ClientCalls.blockingServerStreamingCall(
          getChannel(), getGetCurrentVillageUsersMethod(), getCallOptions(), request);
    }
  }

  /**
   * A stub to allow clients to do ListenableFuture-style rpc calls to service VillageService.
   * <pre>
   * VillageService 定義
   * </pre>
   */
  public static final class VillageServiceFutureStub
      extends io.grpc.stub.AbstractFutureStub<VillageServiceFutureStub> {
    private VillageServiceFutureStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected VillageServiceFutureStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new VillageServiceFutureStub(channel, callOptions);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<com.example.backendkotlin.generated.grpc.CreateVillageResponse> createVillage(
        com.example.backendkotlin.generated.grpc.CreateVillageRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getCreateVillageMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<com.example.backendkotlin.generated.grpc.ListVillagesResponse> listVillages(
        com.example.backendkotlin.generated.grpc.ListVillagesRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getListVillagesMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<com.example.backendkotlin.generated.grpc.GetVillageResponse> getVillage(
        com.example.backendkotlin.generated.grpc.GetVillageRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getGetVillageMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<com.example.backendkotlin.generated.grpc.EnterVillageResponse> enterVillage(
        com.example.backendkotlin.generated.grpc.EnterVillageRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getEnterVillageMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_CREATE_VILLAGE = 0;
  private static final int METHODID_LIST_VILLAGES = 1;
  private static final int METHODID_GET_VILLAGE = 2;
  private static final int METHODID_ENTER_VILLAGE = 3;
  private static final int METHODID_GET_CURRENT_VILLAGE_USERS = 4;

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
        case METHODID_CREATE_VILLAGE:
          serviceImpl.createVillage((com.example.backendkotlin.generated.grpc.CreateVillageRequest) request,
              (io.grpc.stub.StreamObserver<com.example.backendkotlin.generated.grpc.CreateVillageResponse>) responseObserver);
          break;
        case METHODID_LIST_VILLAGES:
          serviceImpl.listVillages((com.example.backendkotlin.generated.grpc.ListVillagesRequest) request,
              (io.grpc.stub.StreamObserver<com.example.backendkotlin.generated.grpc.ListVillagesResponse>) responseObserver);
          break;
        case METHODID_GET_VILLAGE:
          serviceImpl.getVillage((com.example.backendkotlin.generated.grpc.GetVillageRequest) request,
              (io.grpc.stub.StreamObserver<com.example.backendkotlin.generated.grpc.GetVillageResponse>) responseObserver);
          break;
        case METHODID_ENTER_VILLAGE:
          serviceImpl.enterVillage((com.example.backendkotlin.generated.grpc.EnterVillageRequest) request,
              (io.grpc.stub.StreamObserver<com.example.backendkotlin.generated.grpc.EnterVillageResponse>) responseObserver);
          break;
        case METHODID_GET_CURRENT_VILLAGE_USERS:
          serviceImpl.getCurrentVillageUsers((com.example.backendkotlin.generated.grpc.GetCurrentVillageUsersRequest) request,
              (io.grpc.stub.StreamObserver<com.example.backendkotlin.generated.grpc.GetCurrentVillageUsersResponse>) responseObserver);
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
          getCreateVillageMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              com.example.backendkotlin.generated.grpc.CreateVillageRequest,
              com.example.backendkotlin.generated.grpc.CreateVillageResponse>(
                service, METHODID_CREATE_VILLAGE)))
        .addMethod(
          getListVillagesMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              com.example.backendkotlin.generated.grpc.ListVillagesRequest,
              com.example.backendkotlin.generated.grpc.ListVillagesResponse>(
                service, METHODID_LIST_VILLAGES)))
        .addMethod(
          getGetVillageMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              com.example.backendkotlin.generated.grpc.GetVillageRequest,
              com.example.backendkotlin.generated.grpc.GetVillageResponse>(
                service, METHODID_GET_VILLAGE)))
        .addMethod(
          getEnterVillageMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              com.example.backendkotlin.generated.grpc.EnterVillageRequest,
              com.example.backendkotlin.generated.grpc.EnterVillageResponse>(
                service, METHODID_ENTER_VILLAGE)))
        .addMethod(
          getGetCurrentVillageUsersMethod(),
          io.grpc.stub.ServerCalls.asyncServerStreamingCall(
            new MethodHandlers<
              com.example.backendkotlin.generated.grpc.GetCurrentVillageUsersRequest,
              com.example.backendkotlin.generated.grpc.GetCurrentVillageUsersResponse>(
                service, METHODID_GET_CURRENT_VILLAGE_USERS)))
        .build();
  }

  private static abstract class VillageServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    VillageServiceBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return com.example.backendkotlin.generated.grpc.VillageServiceProto.getDescriptor();
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
      return getFileDescriptor().findServiceByName("VillageService");
    }
  }

  private static final class VillageServiceFileDescriptorSupplier
      extends VillageServiceBaseDescriptorSupplier {
    VillageServiceFileDescriptorSupplier() {}
  }

  private static final class VillageServiceMethodDescriptorSupplier
      extends VillageServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
    private final java.lang.String methodName;

    VillageServiceMethodDescriptorSupplier(java.lang.String methodName) {
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
      synchronized (VillageServiceGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new VillageServiceFileDescriptorSupplier())
              .addMethod(getCreateVillageMethod())
              .addMethod(getListVillagesMethod())
              .addMethod(getGetVillageMethod())
              .addMethod(getEnterVillageMethod())
              .addMethod(getGetCurrentVillageUsersMethod())
              .build();
        }
      }
    }
    return result;
  }
}
