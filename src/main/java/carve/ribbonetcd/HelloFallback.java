package carve.ribbonetcd;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.UnpooledByteBufAllocator;

import java.nio.charset.StandardCharsets;
import java.util.Map;

import rx.Observable;

import com.netflix.hystrix.HystrixInvokableInfo;
import com.netflix.ribbon.hystrix.FallbackHandler;

public class HelloFallback implements FallbackHandler<ByteBuf> {

    @Override
    public Observable<ByteBuf> getFallback(HystrixInvokableInfo<?> hystrixInfo,
            Map<String, Object> requestProperties) {
        byte[] bytes = "Hello Fallback".getBytes(StandardCharsets.ISO_8859_1);
        ByteBuf byteBuf = UnpooledByteBufAllocator.DEFAULT.buffer(bytes.length);
        byteBuf.writeBytes(bytes);
        return Observable.just(byteBuf);
    }

}
