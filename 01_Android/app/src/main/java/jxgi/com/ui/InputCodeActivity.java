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
import jxgi.com.util.StringUtil;

public class InputCodeActivity extends AppCompatActivity {

    @Bind(R.id.edt_verify_code)
    TextInputEditText edtVerifyCode;

    private String verifyCode;

    private Animation shake;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input_verification);
        ButterKnife.bind(this);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle(R.string.back);

        shake = AnimationUtils.loadAnimation(InputCodeActivity.this, R.anim.edittext_shake);
    }

    @OnClick(R.id.btn_next)
    void onClickBtnNext() {
        verifyCode = edtVerifyCode.getText().toString();

        if(!checkVerificationCode()) return;

        onCheckVerificationSuccess();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        }

        return super.onOptionsItemSelected(item);
    }

    private boolean checkVerificationCode() {
        if (StringUtil.isEmpty(verifyCode)) {
            showInfoNotice(edtVerifyCode);
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

    private void onCheckVerificationSuccess() {
        Intent intent = new Intent(InputCodeActivity.this, SetPasswordActivity.class);
        startActivity(intent);
    }
}