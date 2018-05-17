package iman.reserve.hotel;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.firebase.ui.auth.AuthUI;
import com.firebase.ui.auth.ErrorCodes;
import com.firebase.ui.auth.IdpResponse;

import java.util.Arrays;

import iman.reserve.hotel.R;

public class LoginActivity extends AppCompatActivity {
    private static final int RC_SIGN_IN = 123;

    public static Intent createIntent(Context context) {
        return new Intent(context, LoginActivity.class);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
/*        FirebaseAuth auth = FirebaseAuth.getInstance();
        if (auth.getCurrentUser() != null) {
            Intent intent = new Intent(this,CityActivity.class);
            startActivity(intent);
            // already signed in
        } else {*/
            startActivityForResult(
                    // not signed in
                    // Get an instance of AuthUI based on the default app
                    AuthUI.getInstance()
                            .createSignInIntentBuilder()
                            .setTheme(R.style.AppTheme)
                            .setAvailableProviders(Arrays.asList(
                                    new AuthUI.IdpConfig.EmailBuilder().build(),
                                    new AuthUI.IdpConfig.PhoneBuilder().setDefaultCountryIso("ir").build(),
                                    new AuthUI.IdpConfig.GoogleBuilder().build()))
                            .setIsSmartLockEnabled(false)
                            .build(),
                    RC_SIGN_IN);

        }


    //}

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // RC_SIGN_IN is the request code you passed into startActivityForResult(...) when starting the sign in flow.
        if (requestCode == RC_SIGN_IN) {
            IdpResponse response = IdpResponse.fromResultIntent(data);

            // Successfully signed in
            if (resultCode == RESULT_OK) {
                startActivity(CityActivity.createIntent(this, response));
                finish();
            } else {
                // Sign in failed
                if (response == null) {
                    // User pressed back button

                        Toast.makeText(this, "بای.",
                                Toast.LENGTH_SHORT).show();
                        super.finish();       // bye

                    //showSnackbar(R.string.sign_in_cancelled);
                    return;
                }

                if (response.getError().getErrorCode() == ErrorCodes.NO_NETWORK) {
                    Toast.makeText(this, R.string.no_internet_connection,
                            Toast.LENGTH_SHORT).show();

                    //showSnackbar(R.string.no_internet_connection);
                    return;
                }
                Toast.makeText(this, R.string.unknown_error,
                        Toast.LENGTH_SHORT).show();

                //showSnackbar(R.string.unknown_error);
                //Log.e(AuthUiActivity, "Sign-in error: ", response.getError());
            }
        }
    }

}
