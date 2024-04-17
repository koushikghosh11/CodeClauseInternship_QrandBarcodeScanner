package com.example.qrandbarcode;

import android.Manifest;
import android.content.ClipData;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;
import android.content.ClipboardManager;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.budiyev.android.codescanner.AutoFocusMode;
import com.budiyev.android.codescanner.CodeScanner;
import com.budiyev.android.codescanner.CodeScannerView;
import com.budiyev.android.codescanner.ScanMode;


public class MainActivity extends AppCompatActivity {

    private static final int CAMERA_ACCESS = 482;

    // Scanner object for handling QR code scanning
    private CodeScanner qrCodeScanner;

    // View element for displaying the camera preview
    private CodeScannerView cameraPreviewView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toast.makeText(this, "Qr and Barcode scan app for CodeClause Internship", Toast.LENGTH_SHORT).show();

        // Initialize camera preview view
        cameraPreviewView = findViewById(R.id.scanner_area);

        setCameraAccess();


        // Create QR code scanner instance
        qrCodeScanner = new CodeScanner(this, cameraPreviewView);

        qrCodeScanner.setCamera(CodeScanner.CAMERA_BACK);
        qrCodeScanner.setFormats(CodeScanner.ALL_FORMATS);
        qrCodeScanner.setAutoFocusMode(AutoFocusMode.SAFE);
        qrCodeScanner.setScanMode(ScanMode.CONTINUOUS);
        qrCodeScanner.setAutoFocusEnabled(true);
        qrCodeScanner.setFlashEnabled(false);

        // Set callback to handle successful scan results
        qrCodeScanner.setDecodeCallback(result -> runOnUiThread(() -> {
            String scannedText = result.getText();

            // copy scan string to clipboard
            ClipboardManager cb = (ClipboardManager)getSystemService(CLIPBOARD_SERVICE);
            ClipData clip = ClipData.newPlainText("scan", scannedText);
            cb.setPrimaryClip(clip);
            Toast.makeText(this, "Copied to clipboard", Toast.LENGTH_SHORT).show();

            // Display toast with scanned text
            Toast.makeText(MainActivity.this, scannedText, Toast.LENGTH_SHORT).show();
        }));

        qrCodeScanner.setErrorCallback(err -> runOnUiThread(() -> Log.e("Main", "Camera error: "+ err.getMessage())));

        // Set click listener to start camera preview on tap
        cameraPreviewView.setOnClickListener(view -> qrCodeScanner.startPreview());
    }

    @Override
    protected void onResume() {
        super.onResume();
        qrCodeScanner.startPreview();
    }

    @Override
    protected void onPause() {
        qrCodeScanner.releaseResources();
        super.onPause();
    }

    private void reqPermission(){
        String[] permissions = new String[]{Manifest.permission.CAMERA};
        ActivityCompat.requestPermissions(this, permissions, CAMERA_ACCESS);
    }

    private void setCameraAccess(){
        int camera_permission = ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA);
        if (camera_permission != PackageManager.PERMISSION_GRANTED) reqPermission();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == CAMERA_ACCESS ) {
            if (grantResults.length == 0 || grantResults[0] != PackageManager.PERMISSION_GRANTED) {
                // Permission denied
                Toast.makeText(this, "Camera permission required !", Toast.LENGTH_SHORT).show();
            }
        }
    }
}