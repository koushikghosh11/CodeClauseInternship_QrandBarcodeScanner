## QR & Barcode Scanner App - CodeClause Internship

This Android application allows you to scan QR codes and barcodes using your device's camera. 

### Features:

* **Runtime Permissions:** The app requests permission to access the camera before using it for scanning.
* **Autofocus:** The camera automatically focuses on the code for better capture accuracy.
* **Scan Results:** Upon successful scanning, the app displays the decoded text and automatically copies it to your clipboard for easy pasting. 

### Getting Started

1. Download and install the app on your Android device.
2. Launch the app.
3. If prompted, grant the camera permission when requested.
4. Point the camera at the QR code or barcode you want to scan.
5. The app will automatically detect and decode the code.
6. The scanned text will be displayed on the screen and copied to your clipboard.

**Note:** To paste the copied text from the clipboard, simply navigate to another application and use the paste functionality (usually long-press on a text input field).


### CodeClause Internship Project

This app was developed as part of an internship at CodeClause.

### Additional Notes

* This app scans a variety of QR code and barcode formats. 
* The app uses the [ZXing library](https://github.com/zxing/zxing) (through [Budiyev's wrapper](https://github.com/yuriy-budiyev/code-scanner)) for code scanning functionalities.

We hope you find this QR & Barcode Scanner app useful!
