package jxgi.com.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;

import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.google.android.gms.common.SignInButton;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import jxgi.com.R;
import jxgi.com.consts.CommonConsts;
import jxgi.com.util.SharedPrefManager;
import jxgi.com.util.StringUtil;

public class SignInActivity extends AppCompatActivity {

    @Bind(R.id.edt_email)
    TextInputEditText edtEmailAddr;
    @Bind(R.id.edt_password)
    TextInputEditText edtPassword;

    @Bind(R.id.btn_signin)
    Button btnEmailSignIn;
    @Bind(R.id.btn_facebook_signin)
    LoginButton btnFacebookSignIn;
    @Bind(R.id.btn_google_signin)
    SignInButton btnGoogleSignIn;

    private CallbackManager callbackManager;

    private Animation shake;

    private String emailAddr;
    private String password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signin);
        ButterKnife.bind(this);

        shake = AnimationUtils.loadAnimation(SignInActivity.this, R.anim.edittext_shake);

        callbackManager = CallbackManager.Factory.create();

        btnFacebookSignIn.setReadPermissions("email");
        btnFacebookSignIn.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                // App code
            }

            @Override
            public void onCancel() {
                // App code
            }

            @Override
            public void onError(FacebookException exception) {
                // App code
            }
        });
    }

    @OnClick(R.id.btn_signin)
    void onClickEmailSignIn() {
        emailAddr = edtEmailAddr.getText().toString();
        password = edtPassword.getText().toString();

        if(!checkEmailAddress()) return;
        if(!checkPassword()) return;

        int usertype = SharedPrefManager.getInstance(this).getUserType();

        if(usertype == CommonConsts.USER_TYPE_CLIENT) {
            Intent intent = new Intent(SignInActivity.this, ClientHomeActivity.class);
            startActivity(intent);

            finish();
        } else if (usertype == CommonConsts.USER_TYPE_CONTRACTOR) {
            Intent intent = new Intent(SignInActivity.this, ContractorHomeActivity.class);
            startActivity(intent);

            finish();
        }
    }

    private boolean checkEmailAddress() {
        if (StringUtil.isEmpty(emailAddr)) {
            showInfoNotice(edtEmailAddr);
            return false;
        }

        if (!android.util.Patterns.EMAIL_ADDRESS.matcher(emailAddr).matches()) {
            showInfoNotice(edtEmailAddr);
            return false;
        }

        return true;
    }

    private boolean checkPassword() {
        if (StringUtil.isEmpty(password)) {
            showInfoNotice(edtPassword);
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

    @OnClick(R.id.tv_create_account)
    void onClickTvCreateAccount() {
        Intent intent = new Intent(SignInActivity.this, SignUpActivity.class);
        startActivity(intent);
    }

    @OnClick(R.id.tv_forgot_password)
    void onClickTvForgotPassword() {
        Intent intent = new Intent(SignInActivity.this, InputEmailActivity.class);
        startActivity(intent);
    }

    @Override
    protected void onActivityResult(final int requestCode, final int resultCode, final Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        callbackManager.onActivityResult(requestCode, resultCode, data);
    }
}