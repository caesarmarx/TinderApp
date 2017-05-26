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

public class InputEmailActivity extends AppCompatActivity {

    @Bind(R.id.edt_email)
    TextInputEditText edtEmailAddr;

    private String emailAddr;

    private Animation shake;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input_email);
        ButterKnife.bind(this);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle(R.string.back);

        shake = AnimationUtils.loadAnimation(InputEmailActivity.this, R.anim.edittext_shake);
    }

    @OnClick(R.id.btn_next)
    void onClickBtnNext() {
        emailAddr = edtEmailAddr.getText().toString();

        if(!checkEmailAddress()) return;

        onCheckEmailSuccess();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        }

        return super.onOptionsItemSelected(item);
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

    private void showInfoNotice(TextInputEditText target) {
        target.startAnimation(shake);
        if (target.requestFocus()) {
            getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
        }
    }

    private void onCheckEmailSuccess() {
        Intent intent = new Intent(InputEmailActivity.this, InputCodeActivity.class);
        startActivity(intent);
    }
}