package com.example.backendkotlin.generated.grpc;

import static io.grpc.MethodDescriptor.generateFullMethodName;

/**
 * <pre>
 * VillageService 定義
 * </pre>
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.69.0)",
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
  }

  private static final int METHODID_CREATE_VILLAGE = 0;

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
              .build();
        }
      }
    }
    return result;
  }
}
