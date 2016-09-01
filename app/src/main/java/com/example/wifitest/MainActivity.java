package com.example.wifitest;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

import android.os.Bundle;
import android.os.Looper;
import android.app.Activity;
import android.view.Menu;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {
	private Socket socket;
	private PrintWriter out;
	private BufferedReader br;
	private TextView textView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		Thread thread = new Thread(new Runnable() {
			@Override
			public void run() {
				Looper.prepare();
				
				while (true) {
					try {
						
						socket = new Socket("192.168.11.254", 8080); //ip为服务器地址
						System.out.println("hello world2!\r\n");
						out = new PrintWriter(new OutputStreamWriter(
								socket.getOutputStream()), true);
						br = new BufferedReader(new InputStreamReader(
								socket.getInputStream()));
						String msg = br.readLine();
						if (null != msg) {
							System.out.println(msg); //msg即为收到的数据
						} else {
							System.out.println("error\n");
						}
						out.close();
						br.close();
					} catch (UnknownHostException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		});
		thread.start();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
