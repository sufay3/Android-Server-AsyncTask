package com.s.demo2;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import java.lang.Thread;
import android.content.SharedPreferences;
import com.s.demo2.utils.NativeProxy;
import com.s.demo2.utils.JSONBuilder;
import org.json.JSONObject;
import com.s.demo2.utils.XMLParser;

public class MainActivity extends Activity {

	private TextView tv;
	private EditText et;
	private Button btChg;
	private Button btSrv;
	private Button btShift;
	private Button btHandler;

	private Handler handler;
	private SharedPreferences user;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		Init(this);
	}

	private void Init(final Context context) {
		tv = (TextView) findViewById(R.id.textview_desc);
		et = (EditText) findViewById(R.id.editor_input);
		btChg = (Button) findViewById(R.id.button_change);
		btSrv = (Button) findViewById(R.id.button_service);
		btShift = (Button) findViewById(R.id.button_shift);
		btHandler = (Button) findViewById(R.id.button_handler);

		btChg.setOnClickListener(new Button.OnClickListener() {
			public void onClick(View v) {
                //tv.setText(NativeProxy.genCryptValue("This is a genus"));
				String xml="<philiosopher name=\"yali\"></philiosopher>";
				XMLParser.build(xml);
			}
		});

		btSrv.setOnClickListener(new Button.OnClickListener() {
			public void onClick(View v) {
				Log.d("onClick", "starting service");
				startService(new Intent(context, ComputingService.class));
			}
		});

		btShift.setOnClickListener(new Button.OnClickListener() {
			public void onClick(View v) {
				/*
				 * Intent carrier = new Intent(context, ResultActivity.class);
				 * carrier.putExtra("result", "This is a ghost");
				 * startActivity(carrier);
				 */

				HttpResponseHandler httpResponserHandler = new HttpResponseHandler() {
					@Override
					public void handle(String response) {
						int code = -1;
						JSONObject json = JSONBuilder.build(response);

						if (json != null) {
							code = json.optInt("code");
						}

						et.setText(code == 0 ? "The operation is successful" : "Failed in operation");
					}
				};

				new HttpTask(httpResponserHandler).executeParallel("http://www.qqxoo.com/");
			}
		});

		handler = new Handler(new Handler.Callback() {
			@Override
			public boolean handleMessage(Message msg) {
				if (msg.what == 1) {
					tv.setText(msg.arg1 + "");
				}

				return false;
			}
		});

		btHandler.setOnClickListener(new Button.OnClickListener() {
			public void onClick(View v) {
				new Thread() {
					public void run() {
						int i = 0;
						int count = 0;

						while (i < 100000) {
							if (++i % 4 == 0) {
								Message msg = Message.obtain(handler);
								msg.what = 1;
								msg.arg1 = ++count;

								msg.sendToTarget();
							}
						}
					}
				}.start();
			}
		});

		user = getSharedPreferences("user", 0);
	}

	@Override
	protected void onResume() {
		super.onResume();

		et.setText(user.getString("name", ""));
	}

	@Override
	protected void onPause() {
		super.onPause();

		SharedPreferences.Editor ed = user.edit();

		ed.putString("name", et.getText().toString());
		ed.apply();
	}

}
