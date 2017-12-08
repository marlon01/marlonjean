package marlon.com.libiki;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AddClientActivity extends AppCompatActivity {
    private static EditText name,num;
    private static ClientManager bd ;
    private static Button btn_insert;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_client);

        btn_insert = (Button)findViewById(R.id.btn_insert);

        bd = new ClientManager(this);
        btn_insert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                name = (EditText)findViewById(R.id.name_input);
                num = (EditText)findViewById(R.id.num_input);

                if(!name.getText().toString().isEmpty() & !num.getText().toString().isEmpty()){
                    Client client = new Client(name.getText().toString(),num.getText().toString());
                    boolean result = bd.insertClient(client);
                    if(result){
                        Toast.makeText(AddClientActivity.this, "Add successefull", Toast.LENGTH_SHORT).show();
                        finish();
                    }else{
                        Toast.makeText(AddClientActivity.this, "Add failled", Toast.LENGTH_SHORT).show();
                    }
                }else if(num.getText().toString().isEmpty()){
                    Toast.makeText(AddClientActivity.this, "Num field must not be empty", Toast.LENGTH_SHORT).show();
                }else if(name.getText().toString().isEmpty()){
                    Toast.makeText(AddClientActivity.this, "Name field must not be empty", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(AddClientActivity.this, "Name and Num fields must not be empty", Toast.LENGTH_SHORT).show();
                }


            }
        });
    }
}
