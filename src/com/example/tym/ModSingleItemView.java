// small change to JSON meta tag names 11/11/13 ACJ

package com.example.tym;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.text.Spanned;
import android.widget.TextView;
 
public class ModSingleItemView extends Activity {
    // Declare Variables
    String mtitle;
    String mname; 
    String mleader;
    String mcrn;
    String mlevel;
    String msemester;
    String mprogramme;
    String mratio;
    String msyllabus;
    



 
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Get the view from singleitemview.xml
        setContentView(R.layout.mod_singleitemview);
 
        Intent i = getIntent();
        // Get the result of title
        mtitle = i.getStringExtra("moduleTitle");
        mname = i.getStringExtra("shortName");
        mleader = i.getStringExtra("moduleLeader");
        mcrn = i.getStringExtra("crn");
        mlevel = i.getStringExtra("level");
        msemester = i.getStringExtra("semester");
        mprogramme = i.getStringExtra("programmes");
        mratio = i.getStringExtra("examRatio");
        msyllabus = i.getStringExtra("syllabus");

 
        // Locate the TextView in singleitemview.xml
        TextView txtdate = (TextView) findViewById(R.id.date);
        TextView txtname = (TextView) findViewById(R.id.content);
        TextView txtlead = (TextView) findViewById(R.id.lead);
        TextView txtcr = (TextView) findViewById(R.id.cr);
        TextView txtlev = (TextView) findViewById(R.id.lev);
        TextView txtsem = (TextView) findViewById(R.id.sem);
        TextView txtpro = (TextView) findViewById(R.id.pro);
        TextView txtrat = (TextView) findViewById(R.id.rat);
        TextView txtsyl = (TextView) findViewById(R.id.syl);

        
        Spanned styledSyllabus= Html.fromHtml(msyllabus);
        
        // Set results to the TextView
        txtdate.setText(mtitle);
        txtname.setText(mname);
        txtlead.setText(mleader);
        txtcr.setText(mcrn);
        txtlev.setText(mlevel);
        txtsem.setText(msemester);
        txtpro.setText(mprogramme);
        txtrat.setText(mratio);
        txtsyl.setText(styledSyllabus);

    }
}