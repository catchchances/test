package com.kkdz.test.io.aio;

import java.nio.ByteBuffer;
import java.nio.channels.CompletionHandler;

public class AcceptCompletionHandler implements CompletionHandler<Integer, ByteBuffer> {

	@Override
	public void completed(Integer result, ByteBuffer attachment) {
		
	}

	@Override
	public void failed(Throwable exc, ByteBuffer attachment) {
		// TODO Auto-generated method stub

	}

}
