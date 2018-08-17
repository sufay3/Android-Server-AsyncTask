package com.jl.app;

import android.os.Environment;
import android.util.Log;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import com.jl.app.utils.HttpProtocol;

/**
 * computing thread
 */
public class ComputingThread extends Thread {
	private static final String TAG = "ComputingThread";

	@Override
	/*
	 * public void run() { FileOutputStream fo;
	 * 
	 * try { fo = new FileOutputStream(new File("/data/data/com.jl.app",
	 * "result2.txt"), true); } catch (FileNotFoundException e) {
	 * e.printStackTrace(); return; }
	 * 
	 * try { fo.write((int) (Math.random() * 1000)); } catch (IOException e) {
	 * e.printStackTrace(); } }
	 */

	public void run() {
		FileOutputStream fo = null;

		try {
			fo = new FileOutputStream(new File("/data/data/com.jl.app", "baidu.html"));
			fo.write(HttpProtocol.request("http://www.baidu.com/").getBytes());
		} catch (FileNotFoundException e) {
			Log.v(TAG, e.getMessage());
		} catch (IOException e) {
			Log.v(TAG, e.getMessage());
		} finally {
			if (fo != null) {
				try {
					fo.close();
				} catch (IOException e) {
					Log.v(TAG, e.getMessage());
				}
			}
		}
	}
}
