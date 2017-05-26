package jxgi.com.ui;

import android.content.ActivityNotFoundException;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Patterns;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.Toast;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import de.hdodenhof.circleimageview.CircleImageView;
import jxgi.com.R;
import jxgi.com.application.TenderApplication;
import jxgi.com.consts.CommonConsts;
import jxgi.com.util.SharedPrefManager;
import jxgi.com.util.StringUtil;

public class CreateProfileActivity extends AppCompatActivity {

    @Bind(R.id.edt_country)
    TextInputEditText edtCountry;
    @Bind(R.id.edt_mobile_number)
    TextInputEditText edtMobileNumber;
    @Bind(R.id.edt_occupation)
    TextInputEditText edtOccupation;
    @Bind(R.id.iv_photo)
    CircleImageView ivPhoto;
    @Bind(R.id.iv_camera)
    ImageView ivCamera;

    private static final int SELECT_COUNTRY = 1;
    private static final int TAKE_PHOTO = 2;
    private static final int SELECT_GALLERY = 3;
    private static final int PIC_CROP = 4;

    private String selectedCountry = "";
    private String mobileNum;
    private String occupation;
    private String strPhoto = "";

    private Uri picUri;

    private Animation shake;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_profile);
        ButterKnife.bind(this);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle(R.string.create_profile);

        edtCountry.setKeyListener(null);
        edtCountry.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                switch (motionEvent.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        break;
                    case MotionEvent.ACTION_UP:
                        Intent intent = new Intent(CreateProfileActivity.this, SelectCountryActivity.class);
                        intent.putExtra("country", selectedCountry);

                        startActivityForResult(intent, SELECT_COUNTRY);
                        view.performClick();
                        break;
                    default:
                        break;
                }
                return true;
            }
        });

        shake = AnimationUtils.loadAnimation(CreateProfileActivity.this, R.anim.edittext_shake);
    }

    @OnClick(R.id.btn_next)
    void onClickBtnNext() {
        selectedCountry = edtCountry.getText().toString();
        mobileNum = edtMobileNumber.getText().toString();
        occupation = edtOccupation.getText().toString();

        if(!checkCountry()) return;
        if(!checkMobileNumber()) return;
        if(!checkOccupation()) return;
        if(!checkPhoto()) return;

        SharedPrefManager.getInstance(this).saveUserBaseCountry(selectedCountry);
        SharedPrefManager.getInstance(this).saveUserMobile(mobileNum);
        SharedPrefManager.getInstance(this).saveUserOccupation(occupation);
        SharedPrefManager.getInstance(this).saveUserPhoto(strPhoto);

        if(SharedPrefManager.getInstance(this).getUserType() == CommonConsts.USER_TYPE_CONTRACTOR) {
            Intent intent = new Intent(CreateProfileActivity.this, ChooseCountriesActivity.class);
            startActivity(intent);
        } else {
            Intent intent = new Intent(CreateProfileActivity.this, RulesAndRegulationsActivity.class);
            startActivity(intent);
        }
    }

    private boolean checkCountry() {
        if (StringUtil.isEmpty(selectedCountry)) {
            showInfoNotice(edtCountry);
            return false;
        }

        return true;
    }

    private boolean checkMobileNumber() {
        if (StringUtil.isEmpty(mobileNum)) {
            showInfoNotice(edtMobileNumber);
            return false;
        }

        if (!Patterns.PHONE.matcher(mobileNum).matches()) {
            showInfoNotice(edtMobileNumber);
            return false;
        }

        return true;
    }

    private boolean checkOccupation() {
        if (StringUtil.isEmpty(occupation)) {
            showInfoNotice(edtOccupation);
            return false;
        }

        return true;
    }

    private boolean checkPhoto() {
        if (StringUtil.isEmpty(strPhoto)) {
            Toast.makeText(CreateProfileActivity.this, R.string.upload_photo, Toast.LENGTH_SHORT).show();
            return false;
        }

        return true;
    }

    private void showInfoNotice(TextInputEditText target) {
        target.startAnimation(shake);
        if (target.requestFocus()) {
            getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
        }
    }

    @OnClick(R.id.iv_photo)
    void onClickIvPhoto() {
        final CharSequence[] items = { "Take Photo", "Choose from Library",
                "Cancel" };
        AlertDialog.Builder builder = new AlertDialog.Builder(CreateProfileActivity.this);
        builder.setTitle("Add Photo!");
        builder.setItems(items, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int item) {
                if (items[item].equals("Take Photo")) {
                    cameraIntent();
                } else if (items[item].equals("Choose from Library")) {
                    galleryIntent();
                } else if (items[item].equals("Cancel")) {
                    dialog.dismiss();
                }
            }
        });
        builder.show();
    }

    private void cameraIntent()
    {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(intent, TAKE_PHOTO);
    }

    private void galleryIntent()
    {
        Intent intent = new Intent(Intent.ACTION_PICK,android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(intent, SELECT_GALLERY);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        }

        return super.onOptionsItemSelected(item);
    }

    private void performCrop(){
        try {
            Intent cropIntent = new Intent("com.android.camera.action.CROP");
            cropIntent.setDataAndType(picUri, "image/*");
            cropIntent.putExtra("crop", "true");
            cropIntent.putExtra("aspectX", 1);
            cropIntent.putExtra("aspectY", 1);
            cropIntent.putExtra("outputX", 256);
            cropIntent.putExtra("outputY", 256);
            cropIntent.putExtra("return-data", true);
            startActivityForResult(cropIntent, PIC_CROP);
        }
        catch(ActivityNotFoundException anfe){
            //display an error message
            anfe.printStackTrace();
            String errorMessage = "Whoops - your device doesn't support the crop action!";
            Toast toast = Toast.makeText(this, errorMessage, Toast.LENGTH_SHORT);
            toast.show();
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent intent) {
        if (requestCode == SELECT_COUNTRY && resultCode == RESULT_OK) {
            selectedCountry = intent.getStringExtra("country");
            edtCountry.setText(selectedCountry);
        } else if (resultCode == RESULT_OK && requestCode == TAKE_PHOTO) {
            picUri = intent.getData();
            performCrop();
        } else if (resultCode == RESULT_OK && requestCode == SELECT_GALLERY) {
            picUri = intent.getData();
            performCrop();
        }else if(resultCode == RESULT_OK && requestCode == PIC_CROP){
            Bundle extras = intent.getExtras();
            Bitmap thePic = extras.getParcelable("data");

            TenderApplication.bmUserPhoto = thePic;

            strPhoto = StringUtil.bitmapToString(thePic);


            ivCamera.setVisibility(View.GONE);
            ivPhoto.setImageBitmap(thePic);
        }
    }
}