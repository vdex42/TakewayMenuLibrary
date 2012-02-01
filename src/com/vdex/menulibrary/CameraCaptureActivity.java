package com.vdex.menulibrary;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import android.app.Activity;
import android.content.Intent;
import android.hardware.Camera;
import android.hardware.Camera.PictureCallback;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;

public class CameraCaptureActivity extends Activity {
	private Camera mCamera;
	private CameraPreview mPreview;
	private String TAG = "CameraCaptureActivity";

	public static Camera getCameraInstance() {
		Camera c = null;
		try {
			c = Camera.open(); // attempt to get a Camera instance
		} catch (Exception e) {
			// Camera is not available (in use or does not exist)
		}
		return c; // returns null if camera is unavailable
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.camera_preview);

		// Create an instance of Camera
		mCamera = getCameraInstance();

		Button captureButton = (Button) findViewById(R.id.button_capture);
		captureButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				// get an image from the camera
				mCamera.takePicture(null, null, new PictureCallback() {

					@Override
					public void onPictureTaken(byte[] data, Camera camera) {

						// File pictureFile =
						
						// getOutputMediaFile(MEDIA_TYPE_IMAGE);
						File photo = new File(Environment
								.getExternalStorageDirectory(), "Pic.jpg");
						if (photo == null) {
							Log.d(TAG,
									"Error creating media file, check storage permissions: ");
							return;
						}

						try {
							FileOutputStream fos = new FileOutputStream(photo);
							fos.write(data);
							fos.close();
						} catch (FileNotFoundException e) {
							Log.d(TAG, "File not found: " + e.getMessage());
						} catch (IOException e) {
							Log.d(TAG,
									"Error accessing file: " + e.getMessage());
						}
						
						Intent resultIntent = new Intent();
						// TODO Add extras or a data URI to this intent as appropriate.
						setResult(Activity.RESULT_OK, resultIntent);
						finish();
					}
				});
			}
		});

		// Create our Preview view and set it as the content of our activity.
		mPreview = new CameraPreview(this, mCamera);
		FrameLayout preview = (FrameLayout) findViewById(R.id.camera_preview);
		preview.addView(mPreview);

	}
}
