package com.vdex.menulibrary;

import java.io.IOException;
import java.util.List;

import android.content.Context;
import android.hardware.Camera;
import android.hardware.Camera.AutoFocusCallback;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.widget.Toast;

public class CameraPreview extends SurfaceView implements
		SurfaceHolder.Callback, AutoFocusCallback {
	private static final String TAG = "CameraPreview";
	private SurfaceHolder mHolder;
	private Camera mCamera;
	private Context Context;

	public CameraPreview(Context context, Camera camera) {
        super(context);
        this.Context = context;
        mCamera = camera;

        // Install a SurfaceHolder.Callback so we get notified when the
        // underlying surface is created and destroyed.
        mHolder = getHolder();
        mHolder.addCallback(this);
        // deprecated setting, but required on Android versions prior to 3.0
        mHolder.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);
        
      
        
    }

	public void surfaceCreated(SurfaceHolder holder) {
		// The Surface has been created, now tell the camera where to draw the
		// preview.
		try {
			mCamera.setPreviewDisplay(holder);					
			mCamera.startPreview();
			  Camera.Parameters params = mCamera.getParameters();
		        
		        
		        List<String> focusModes = params.getSupportedFocusModes();
		        if (focusModes!=null && focusModes.contains(Camera.Parameters.FOCUS_MODE_AUTO)) {
		          // Autofocus mode is supported
		        	params.setFocusMode(Camera.Parameters.FOCUS_MODE_AUTO);
		        	Toast.makeText(Context, "FOCUS_MODE_AUTO", Toast.LENGTH_SHORT ).show();
		        	// set Camera parameters
		        	mCamera.setParameters(params);
		        }
			mCamera.autoFocus(this);
			 Toast.makeText(Context, "Start Focus", Toast.LENGTH_SHORT ).show(); 
		} catch (IOException e) {
			Log.d(TAG, "Error setting camera preview: " + e.getMessage());
		}
	}

	public void surfaceDestroyed(SurfaceHolder holder) {
		// empty. Take care of releasing the Camera preview in your activity.
		if (mCamera != null) {
			mCamera.release(); // release the camera for other applications
			mCamera = null;
		}
	}

	public void surfaceChanged(SurfaceHolder holder, int format, int w, int h) {
		// If your preview can change or rotate, take care of those events here.
		// Make sure to stop the preview before resizing or reformatting it.

		if (mHolder.getSurface() == null) {
			// preview surface does not exist
			return;
		}

		// stop preview before making changes
		try {
			mCamera.stopPreview();
		} catch (Exception e) {
			// ignore: tried to stop a non-existent preview
		}

		// set preview size and make any resize, rotate or
		// reformatting changes here

		// start preview with new settings
		try {
			// mHolder.setFixedSize(width, height)
			mCamera.setPreviewDisplay(mHolder);
			mCamera.startPreview();

		} catch (Exception e) {
			Log.d(TAG, "Error starting camera preview: " + e.getMessage());
		}
	}

	@Override
	public void onAutoFocus(boolean success, Camera camera) {
		// TODO Auto-generated method stub
		 if (success)
         {
             Toast.makeText(Context, "Focused", Toast.LENGTH_SHORT ).show();             
         }
		
	}
}