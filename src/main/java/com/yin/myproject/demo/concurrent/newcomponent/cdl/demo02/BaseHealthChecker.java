package com.yin.myproject.demo.concurrent.newcomponent.cdl.demo02;

import java.util.concurrent.CountDownLatch;

public abstract class BaseHealthChecker implements Runnable {
	private CountDownLatch _latch;
	private String _serviceName;
	private boolean _serviceUp;

	public BaseHealthChecker(CountDownLatch _latch, String _serviceName) {
		super();
		this._latch = _latch;
		this._serviceName = _serviceName;
		this._serviceUp = false;
	}

	public void run() {
		try {
			verifyService();
			_serviceUp = true;
		} catch (Throwable t) {
			t.printStackTrace(System.err);
			_serviceUp = false;
		} finally {
			if (_latch != null) {
				_latch.countDown();
			}
		}
	}

	public String getServiceName() {
		return _serviceName;
	}

	public boolean isServiceUp() {
		return _serviceUp;
	}

	public abstract void verifyService();
}
