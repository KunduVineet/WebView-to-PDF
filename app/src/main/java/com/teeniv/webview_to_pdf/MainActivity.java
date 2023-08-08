package com.teeniv.webview_to_pdf;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.print.PrintAttributes;
import android.print.PrintDocumentAdapter;
import android.print.PrintJob;
import android.print.PrintManager;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

public class MainActivity extends AppCompatActivity {
    AppCompatButton btn;
    WebView web;
    WebView printweb;

    ProgressBar bar;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn = findViewById(R.id.btn);
        web = findViewById(R.id.web);
        bar = findViewById(R.id.bar);

        web.setWebViewClient(new WebViewClient()
        {
            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                bar.setVisibility(View.VISIBLE);
                super.onPageStarted(view, url, favicon);
            }
            @Override
            public void onPageFinished(WebView view, String url) {
                bar.setVisibility(View.GONE);
                super.onPageFinished(view, url);
                printweb = web;
            }
        });

        web.loadUrl("https://www.google.com");

        btn.setOnClickListener(v -> {
            if(printweb!=null)
            {
                PrintTheWebPage(printweb);
            }
            else
            {
                Toast.makeText(MainActivity.this, "Webpage not fully loaded", Toast.LENGTH_SHORT).show();
            }
        });
    }
    PrintJob printJob;

    boolean printbtnpressed = false;

    private void PrintTheWebPage(WebView web)
    {
        printbtnpressed = true;

        PrintManager printManager = (PrintManager) this
                .getSystemService(Context.PRINT_SERVICE);

        String jobname = getString(R.string.app_name)+ "Webpage" +web.getUrl();

        PrintDocumentAdapter printDocumentAdapter =  web.createPrintDocumentAdapter(jobname);

        assert printManager != null;
        printJob = printManager.print(jobname,printDocumentAdapter,
                new PrintAttributes.Builder().build());

    }

    @Override
    protected void onResume() {
        super.onResume();

        if(printJob != null && printbtnpressed)
        {
            if (printJob.isCompleted()) {
                // Showing Toast Message
                Toast.makeText(this, "Completed", Toast.LENGTH_SHORT).show();
            } else if (printJob.isStarted()) {
                // Showing Toast Message
                Toast.makeText(this, "isStarted", Toast.LENGTH_SHORT).show();

            } else if (printJob.isBlocked()) {
                // Showing Toast Message
                Toast.makeText(this, "isBlocked", Toast.LENGTH_SHORT).show();

            } else if (printJob.isCancelled()) {
                // Showing Toast Message
                Toast.makeText(this, "isCancelled", Toast.LENGTH_SHORT).show();

            } else if (printJob.isFailed()) {
                // Showing Toast Message
                Toast.makeText(this, "isFailed", Toast.LENGTH_SHORT).show();

            } else if (printJob.isQueued()) {
                // Showing Toast Message
                Toast.makeText(this, "isQueued", Toast.LENGTH_SHORT).show();

            }

            printbtnpressed = false;
        }

    }

    public void onBackPressed() {
        if(web.canGoBack())
        {
            web.goBack();
        }
        else
        {
            super.onBackPressed();
        }
    }
}