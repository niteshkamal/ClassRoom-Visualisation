package com.example.classroomvis;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.Toast;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class Second extends Activity {

    private static HashMap sortByValues(HashMap map) {
        List list = new LinkedList(map.entrySet());
        Collections.sort(list, new Comparator() {
            public int compare(Object o1, Object o2) {
                return ((Comparable) ((Map.Entry) (o2)).getValue())
                        .compareTo(((Map.Entry) (o1)).getValue());
            }
        });

        HashMap sortedHashMap = new LinkedHashMap();
        for (Iterator it = list.iterator(); it.hasNext();) {
            Map.Entry entry = (Map.Entry) it.next();
            sortedHashMap.put(entry.getKey(), entry.getValue());
        }
        return sortedHashMap;
    }
    String filename = "file.txt";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.second);

        final File file1 = new  File(getExternalFilesDir(null),"stats1.txt");
        LinearLayout layoutVertical2 = (LinearLayout) findViewById(R.id.linear2);
        LinearLayout layoutVertical1 = (LinearLayout) findViewById(R.id.linear1);
        Button button0 = (Button) findViewById(R.id.button0);
        Button button1 = (Button) findViewById(R.id.button1);
        final GlobalClass globalClass = (GlobalClass) getApplicationContext();

        button0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast toast = Toast.makeText(getApplicationContext(),"Task Started",Toast.LENGTH_LONG);
                toast.show();
                String text = "Interval:- " +globalClass.getInterval() +" , Task:- "+globalClass.getKar();
                String text1 = readFile(filename);
                text= text1 +"\n\n"+ text;
                saveFile(filename,text);
                writeToFile(file1,text,getApplicationContext());
                int a=globalClass.getKar();
                a=a+1;
                globalClass.setKar(a);

            }
        });

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast toast = Toast.makeText(getApplicationContext(),"Task Ended",Toast.LENGTH_LONG);
                toast.show();
                String text = "  Task "+(globalClass.getKar()-1)+" ended.";
                String text1 = readFile(filename);
                text= text1 +"\n\n"+ text;
                saveFile(filename,text);
                writeToFile(file1,text,getApplicationContext());
                finish();
            }
        });

        Intent intent = getIntent();
        ArrayList<String> hashMap = (ArrayList<String>) intent.getSerializableExtra("hashMap");
        final ArrayList<String> nameMap = (ArrayList<String>) intent.getSerializableExtra("nameMap");
        final ArrayList<String> rollMap = (ArrayList<String>) intent.getSerializableExtra("rollMap");
        HashMap<Integer, Float> map = (HashMap<Integer, Float>) intent.getSerializableExtra("valmap");
        final HashMap<Integer, Float> mapi = sortByValues(map);
        int r = (int) intent.getSerializableExtra("row");
        int c = (int) intent.getSerializableExtra("col");
        int ir = (int) intent.getSerializableExtra("irow");
        int ic = (int) intent.getSerializableExtra("icol");
        float avg = globalClass.getThreshold();


        GradientDrawable gd = new GradientDrawable();
        gd.setColor(0xFF00FF00); // Changes this drawbale to use a single color instead of a gradient
        gd.setCornerRadius(5);
        gd.setStroke(1, 0xFF000000);

        GradientDrawable gd1 = new GradientDrawable();
        gd1.setColor(Color.parseColor("#FF0000")); // Changes this drawbale to use a single color instead of a gradient
        gd1.setCornerRadius(5);
        gd1.setStroke(1, 0xFF000000);

        GradientDrawable gd2 = new GradientDrawable();
        gd2.setColor(Color.parseColor("#FFFF00")); // Changes this drawbale to use a single color instead of a gradient
        gd2.setCornerRadius(5);
        gd2.setStroke(1, 0xFF000000);


        GradientDrawable gdr = new GradientDrawable();
        gdr.setCornerRadius(10);
        gdr.setStroke(10, 0xFF00FF00);

        GradientDrawable gdr1 = new GradientDrawable();
        gdr1.setCornerRadius(10);
        gdr1.setStroke(10,Color.parseColor("#FF0000"));

        GradientDrawable gdr2 = new GradientDrawable();
        gdr2.setCornerRadius(10);
        gdr2.setStroke(10,Color.parseColor("#FFFF00"));

        final ArrayList<ArrayList<ImageButton>> imageArray = new  ArrayList<ArrayList<ImageButton>>();
        final TableLayout table1 = new TableLayout(getApplicationContext());
        table1.setLayoutParams(new TableLayout.LayoutParams(TableLayout.LayoutParams.MATCH_PARENT,TableLayout.LayoutParams.MATCH_PARENT));
        table1.setStretchAllColumns(true);
        for(int row =0;row < ir;row++)
        {
            ArrayList<ImageButton> cur1 = new ArrayList<ImageButton>();
            TableRow currentRow1 = new TableRow(getApplicationContext());
            currentRow1.setLayoutParams(new TableLayout.LayoutParams(TableLayout.LayoutParams.MATCH_PARENT, TableLayout.LayoutParams.MATCH_PARENT, 1.0f));
            currentRow1.setGravity(Gravity.CENTER);
            for(int col=0;col<ic;col++)
            {
                ImageButton currentButton1 = new ImageButton(getApplicationContext());
                currentButton1.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT, TableRow.LayoutParams.MATCH_PARENT,0.5f));
                //currentButton1.setImageResource(R.drawable.image);
                currentButton1.setAdjustViewBounds(true);
                int k = row*ic + col;
                currentButton1.setId(k);
                if(hashMap.get(k).compareTo("n")==0)
                    currentButton1.setBackground(gdr);
                else if(hashMap.get(k).compareTo("c")==0)
                    currentButton1.setBackground(gdr1);
                else
                    currentButton1.setBackground(gdr2);
                cur1.add(col,currentButton1);
                currentRow1.addView(currentButton1);
            }
            imageArray.add(row,cur1);
            table1.addView(currentRow1);
        }
        layoutVertical1.addView(table1);

        for(int i=0;i<ir;i++)
        {
            for(int j=0;j<ic;j++)
            {
                imageArray.get(i).get(j).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast toast= Toast.makeText(getApplicationContext(),
                                "Student Id:"+rollMap.get(v.getId())+"\n Name:"+nameMap.get(v.getId()), Toast.LENGTH_LONG);
                        toast.setGravity(Gravity.CENTER_VERTICAL|Gravity.CENTER_HORIZONTAL, 0, 0);
                        toast.show();
                        Date currentTime = Calendar.getInstance().getTime();
                        SimpleDateFormat dateFormat = new SimpleDateFormat("hh.mm.ss aa");
                        String text = dateFormat.format(currentTime) +"  Name "+nameMap.get(v.getId())+",Roll no: "+rollMap.get(v.getId())+" in activity 2";
                        String text1 = readFile(filename);
                        text= text1 +"\n"+ text;
                        saveFile(filename,text);
                        writeToFile(file1,text,getApplicationContext());

                    }
                });
            }
        }

        final ArrayList<ArrayList<Button>> buttonArray = new  ArrayList<ArrayList<Button>>();
        final TableLayout table = new TableLayout(getApplicationContext());
        table.setLayoutParams(new TableLayout.LayoutParams(TableLayout.LayoutParams.MATCH_PARENT,TableLayout.LayoutParams.MATCH_PARENT));
        table.setStretchAllColumns(true);
        for (int row = 0; row < r; row++) {
            ArrayList<Button> cur = new ArrayList<Button>();
            TableRow currentRow = new TableRow(getApplicationContext());
            currentRow.setLayoutParams(new TableLayout.LayoutParams(TableLayout.LayoutParams.MATCH_PARENT, TableLayout.LayoutParams.MATCH_PARENT, 1.0f));
            currentRow.setGravity(Gravity.CENTER);
            for (int button = 0; button < c; button++) {

                Button currentButton = new Button(getApplicationContext());
                currentButton.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT, TableRow.LayoutParams.MATCH_PARENT,0.5f));
                currentButton.setBackground(gd);
                int k = row*c + button;
                currentButton.setId(k);
                currentButton.setText(Integer.toString(currentButton.getId()));
                cur.add(button,currentButton);
                currentRow.addView(currentButton);
            }
            buttonArray.add(row,cur);
            table.addView(currentRow);
        }
        layoutVertical2.addView(table);

        int aki = 0;
        for (Map.Entry<Integer, Float> entry : mapi.entrySet())
        {
            if(entry.getValue() < avg)
            {
                break;
            }
            else if((entry.getValue() >= avg) && aki < 3)
            {
                int hi = entry.getKey();
                int row1 = hi/c;
                int col1 = hi % c;
                buttonArray.get(row1).get(col1).setBackground(gd1);
                aki++;
            }
            else if((entry.getValue() >= avg) && aki < 6 && aki >=3)
            {
                int hi1 = entry.getKey();
                int row2 = hi1/c;
                int col2 = hi1 % c;
                buttonArray.get(row2).get(col2).setBackground(gd2);
                aki++;
            }
        }


        for(int i=0;i<r;i++)
        {
            for(int j=0;j<c;j++)
            {
                buttonArray.get(i).get(j).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Date currentTime = Calendar.getInstance().getTime();
                        SimpleDateFormat dateFormat = new SimpleDateFormat("hh.mm.ss aa");
                        String text = dateFormat.format(currentTime) +"  button  "+ v.getId() + " in activity 2";
                        String text1 = readFile(filename);
                        text= text1 +"\n"+ text;
                        saveFile(filename,text);
                        writeToFile(file1,text,getApplicationContext());
                        finish();
                    }
                });
            }
        }

    }
    public void saveFile(String file,String text)
    {
        try {
            FileOutputStream fos = openFileOutput(file,Context.MODE_PRIVATE);
            fos.write(text.getBytes());
            fos.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public String readFile (String file)
    {
        String text = "";
        try {
            FileInputStream fis = openFileInput(file);
            int size = fis.available();
            byte[] buffer = new byte[size];
            fis.read(buffer);
            fis.close();
            text= new String(buffer);



        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return text;
    }
    private void writeToFile(File file, String text, Context context){
        try
        {

            BufferedWriter buf = new BufferedWriter(new FileWriter(file));
            buf.append(text);
            buf.newLine();
            buf.close();
        }
        catch(IOException e)
        {
            e.printStackTrace();
        }
    }
}

