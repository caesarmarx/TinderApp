package jxgi.com.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import jxgi.com.R;
import jxgi.com.util.SharedPrefManager;
import jxgi.com.util.StringUtil;

public class SetPasswordActivity extends AppCompatActivity {

    @Bind(R.id.edt_password)
    TextInputEditText edtPassword;
    @Bind(R.id.edt_confirm_password)
    TextInputEditText edtConfirmPassword;

    private String password;
    private String confirmPassword;

    private Animation shake;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_password);
        ButterKnife.bind(this);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle(R.string.set_password);

        shake = AnimationUtils.loadAnimation(SetPasswordActivity.this, R.anim.edittext_shake);
    }

    @OnClick(R.id.btn_submit)
    void onClickBtnSubmit() {
        password = edtPassword.getText().toString();
        confirmPassword = edtConfirmPassword.getText().toString();

        if(!checkPassword()) return;
        if(!checkConfirmPassword()) return;

        if(!checkPasswordCorrection()) return;

        onSetPwdSuccess();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        }

        return super.onOptionsItemSelected(item);
    }

    private boolean checkPassword() {
        if (StringUtil.isEmpty(password)) {
            showInfoNotice(edtPassword);
            return false;
        }

        return true;
    }

    private boolean checkConfirmPassword() {
        if (StringUtil.isEmpty(confirmPassword)) {
            showInfoNotice(edtConfirmPassword);
            return false;
        }

        return true;
    }

    private boolean checkPasswordCorrection() {
        if(password.equals(confirmPassword)) {
            return true;
        }
        else {
            showInfoNotice(edtPassword);

            return false;
        }
    }

    private void showInfoNotice(TextInputEditText target) {
        target.startAnimation(shake);
        if (target.requestFocus()) {
            getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
        }
    }

    private void onSetPwdSuccess() {
        Intent intent = new Intent(SetPasswordActivity.this, SignInActivity.class);

        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);

        startActivity(intent);
    }
}