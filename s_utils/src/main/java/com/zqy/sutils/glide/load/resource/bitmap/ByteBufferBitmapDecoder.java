package com.zqy.sutils.glide.load.resource.bitmap;

import android.graphics.Bitmap;

import com.zqy.sutils.glide.load.Options;
import com.zqy.sutils.glide.load.ResourceDecoder;
import com.zqy.sutils.glide.load.engine.Resource;
import com.zqy.sutils.glide.util.ByteBufferUtil;

import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;

/**
 * Decodes {@link Bitmap Bitmaps} from {@link ByteBuffer ByteBuffers}.
 */
public class ByteBufferBitmapDecoder implements ResourceDecoder<ByteBuffer, Bitmap> {
  private final com.zqy.sutils.glide.load.resource.bitmap.Downsampler downsampler;

  public ByteBufferBitmapDecoder(Downsampler downsampler) {
    this.downsampler = downsampler;
  }

  @Override
  public boolean handles( ByteBuffer source,  Options options) {
    return downsampler.handles(source);
  }

  @Override
  public Resource<Bitmap> decode( ByteBuffer source, int width, int height,
       Options options)
      throws IOException {
    InputStream is = ByteBufferUtil.toStream(source);
    return downsampler.decode(is, width, height, options);
  }
}
