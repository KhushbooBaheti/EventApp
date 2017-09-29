package com.example.user.eventapp.fragments;

import android.Manifest;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.user.eventapp.R;
import com.example.user.eventapp.Utilties.backGroundWorker;
import com.example.user.eventapp.basic.CustomDialog;
import com.example.user.eventapp.basic.FilePath;
import com.example.user.eventapp.basic.MainActivity;

import net.gotev.uploadservice.MultipartUploadRequest;
import net.gotev.uploadservice.UploadNotificationConfig;

import java.util.UUID;

import static android.app.Activity.RESULT_OK;


public class PartRegFragment extends Fragment implements View.OnClickListener{

    private Button btnPay;
    private Button btnUpload;
    private Button btnRegister;
    private Button btnSelect;
    private Button btnGuidelines;
    private String TAG_ID,paperTopic;
    private int TAG_CID;
    private String uid,cid;
    private String name;
    private String mobile;
    private String email;
    private String paperStatus = "uploaded";
    private EditText namefield;
    private EditText mobilefield;
    private EditText emailfield;
    private EditText topicField;
    private String checktype = "2";
    private LinearLayout paper;
    private boolean readCond;
    public static final String UPLOAD_URL = "http://eventapp.000webhostapp.com/presenterPDF.php";


    //Pdf request code
    private int PICK_PDF_REQUEST = 1;

    //storage permission code
    private static final int STORAGE_PERMISSION_CODE = 123;


    //Uri to store the image uri
    private Uri filePath;
    public PartRegFragment() {

    }



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);







    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {



        View view = inflater.inflate(R.layout.fragment_part_reg, container, false);
        namefield=(EditText)view.findViewById(R.id.name);
        mobilefield=(EditText)view.findViewById(R.id.mobile);
        emailfield=(EditText)view.findViewById(R.id.email);
        //topicField = (EditText)view.findViewById(R.id.editText2);
        Bundle bundle = this.getArguments();
        if (bundle != null) {
            TAG_CID = bundle.getInt("Conf_id");
        }
        cid=Integer.toString(TAG_CID);
        SharedPreferences sharedPreferences = this.getActivity().getSharedPreferences("UID_Details", Context.MODE_PRIVATE);
        name=sharedPreferences.getString("Name","no name defined");
        mobile=sharedPreferences.getString("Mobile_No","0000000000");
        email=sharedPreferences.getString("Email","xxxxxx@YYYYYY");
        namefield.setText(name);
        mobilefield.setText(mobile);
        emailfield.setText(email);
        namefield.setEnabled(false);
        mobilefield.setEnabled(false);
        emailfield.setEnabled(false);
        uid=this.getActivity().getSharedPreferences("loggedIn info", Context.MODE_PRIVATE).getString("uid","");
        return view;

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        btnPay=(Button) getActivity().findViewById(R.id.btnPay);
        btnUpload=(Button) getActivity().findViewById(R.id.btnUpload);
        btnSelect = (Button)getActivity().findViewById(R.id.btnSelect);
        btnRegister=(Button) getActivity().findViewById(R.id.btnRegister);
        paper = (LinearLayout)getActivity().findViewById(R.id.linear_paper);
        btnGuidelines = (Button)getActivity().findViewById(R.id.btnGuidelines);
        btnPay.setOnClickListener(this);
        btnUpload.setOnClickListener(this);
        btnRegister.setOnClickListener(this);
        btnSelect.setOnClickListener(this);
        btnGuidelines.setOnClickListener(this);
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnPay:
                Toast.makeText(getActivity(),"Paid.....",Toast.LENGTH_LONG).show();
                break;
            case R.id.btnUpload:
                //Toast.makeText(getActivity(),"Paid.....",Toast.LENGTH_SHORT).show();
                AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(getContext());
                LayoutInflater inflater = getActivity().getLayoutInflater();
                final View dialogView = inflater.inflate(R.layout.alert_paper_topic, null);
                dialogBuilder.setView(dialogView);

                final EditText edt = (EditText) dialogView.findViewById(R.id.paper_topic);

                dialogBuilder.setTitle("Upload your Paper");
                dialogBuilder.setMessage("Enter Your Paper Topic:");
                dialogBuilder.setPositiveButton("Done", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {
                        paperTopic=edt.getText().toString();
                        uploadMultipart();
                        dialog.dismiss();

                    }
                });
                dialogBuilder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {
                        dialog.cancel();
                    }
                });
                AlertDialog b = dialogBuilder.create();
                b.show();
                break;
            case R.id.btnRegister:
                CustomDialog cdd = new CustomDialog(getActivity());
                cdd.setCancelable(true);
                cdd.setType("listenerRegistration");
                cdd.setChecktype("2");
                cdd.setCid(cid);
                cdd.setUid(uid);
                cdd.setPaper(paper);
                cdd.show();
                readCond = cdd.isReadCond();
                break;
            case R.id.btnSelect:
                showFileChooser();
                break;
            case R.id.btnGuidelines:
                String pdfurl = "http://eventapp.000webhostapp.com/AndroidPdfUpload/uploads/1.pdf";
                String googleDocsUrl = "http://docs.google.com/viewer?url="+pdfurl;
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setDataAndType(Uri.parse(googleDocsUrl ), "text/html");
                startActivity(intent);
                break;
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public void uploadMultipart() {
        //getting name for the image
        //String name = "Humra PDF";

        //getting the actual path of the image
        String path = FilePath.getPath(getActivity(), filePath);

        if (path == null) {

            Toast.makeText(getActivity(), "Please move your .pdf file to internal storage and retry", Toast.LENGTH_LONG).show();
        } else {
            //Uploading code
            try {
                String uploadId = UUID.randomUUID().toString();

                //Creating a multi part request
                new MultipartUploadRequest(getActivity(), uploadId, UPLOAD_URL)
                        .addFileToUpload(path, "pdf")
                        .addParameter("status",paperStatus)//Adding file
                        .addParameter("topic",paperTopic)
                        .addParameter("uid",uid)
                        .addParameter("cid",cid)//Adding text parameter to the request
                        .setNotificationConfig(new UploadNotificationConfig())
                        .setMaxRetries(2)
                        .startUpload(); //Starting the upload
                Toast.makeText(getActivity(),"Uploaded Successfully",Toast.LENGTH_SHORT).show();

            } catch (Exception exc) {
                Toast.makeText(getActivity(), exc.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }
    }


    //method to show file chooser
    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public void showFileChooser() {
        Intent intent = new Intent();
        intent.setType("application/pdf");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select Pdf"), PICK_PDF_REQUEST);

        // uploadMultipart();
    }

    //handling the image chooser activity result
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PICK_PDF_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null) {
            filePath = data.getData();
        }
    }


    //Requesting permission
    private void requestStoragePermission() {
        if (ContextCompat.checkSelfPermission(getActivity(), Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED)
            return;

        if (ActivityCompat.shouldShowRequestPermissionRationale(getActivity(), Manifest.permission.READ_EXTERNAL_STORAGE)) {
            //If the user has denied the permission previously your code will come to this block
            //Here you can explain why you need this permission
            //Explain here why you need this permission
        }
        //And finally ask for the permission
        ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, STORAGE_PERMISSION_CODE);
    }


    //This method will be called when the user will tap on allow or deny
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {

        //Checking the request code of our request
        if (requestCode == STORAGE_PERMISSION_CODE) {

            //If permission is granted
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                //Displaying a toast
                Toast.makeText(getActivity(), "Permission granted now you can read the storage", Toast.LENGTH_LONG).show();
            } else {
                //Displaying another toast if permission is not granted
                Toast.makeText(getActivity(), "Oops you just denied the permission", Toast.LENGTH_LONG).show();
            }
        }
    }
}



