package com.moneysaving.app;

import android.app.Dialog;
import android.content.Context;
import android.database.Cursor;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

public class RecyclerAdapterForCategories extends RecyclerView.Adapter<RecyclerAdapterForCategories.ViewHolder> {

    private List<String> categoriesList;
    private Context context;
    private String usid;
    DBHelper dbHelper;
    private String d;
    Dialog addAmount;
    RecyclerView recyclerView;

    RecyclerAdapterForCategories(Context context, List<String> Cats, String userID, RecyclerView recyclerView1) {
        this.context = context;
        this.categoriesList = Cats;
        this.usid = userID;
        dbHelper = new DBHelper(context);
        DateFormat dateFormat = new SimpleDateFormat("MM");
        Date date = new Date();
        d = dateFormat.format(date);
        addAmount = new Dialog(context);
        recyclerView = recyclerView1;
    }

    @NonNull
    @Override
    public RecyclerAdapterForCategories.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row, parent, false);
        return new RecyclerAdapterForCategories.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final RecyclerAdapterForCategories.ViewHolder holder, final int position) {

        if (position == 0)
            holder.imageView.setImageDrawable(context.getDrawable(R.drawable.foodicon));
        else if (position == 1)
            holder.imageView.setImageDrawable(context.getDrawable(R.drawable.entert));
        else if (position == 2)
            holder.imageView.setImageDrawable(context.getDrawable(R.drawable.car));
        else if (position == 3)
            holder.imageView.setImageDrawable(context.getDrawable(R.drawable.homeicon));
        else if (position == 4)
            holder.imageView.setImageDrawable(context.getDrawable(R.drawable.clothin));
        else if (position == 5)
            holder.imageView.setImageDrawable(context.getDrawable(R.drawable.electronics));
        else if (position == 6)
            holder.imageView.setImageDrawable(context.getDrawable(R.drawable.health));
        else if (position == 7)
            holder.imageView.setImageDrawable(context.getDrawable(R.drawable.children));
        else if (position == 8)
            holder.imageView.setImageDrawable(context.getDrawable(R.drawable.work));
        else
            holder.imageView.setImageDrawable(context.getDrawable(R.drawable.others));


        holder.Name.setText(categoriesList.get(position));
        if (position == 0) {
            try {
                Cursor cursor = dbHelper.get_category(Integer.valueOf(usid), "Food", Integer.valueOf(d));
                cursor.moveToFirst();
                holder.KRONAS.setText(cursor.getString(0));
                try {
                    Cursor c1 = dbHelper.get_ex(Integer.valueOf(usid), "Food", Integer.valueOf(d));
                    c1.moveToFirst();
                    if (Integer.valueOf(c1.getString(0)) > Integer.valueOf(cursor.getString(0))) {


                        holder.KRONAS.setText("Amount consumed");
                    } else {

                        int t = Integer.valueOf(cursor.getString(0)) - Integer.valueOf(c1.getString(0));
                        holder.KRONAS.setText(String.valueOf(t));

                    }
                } catch (Exception ex) {
                    ex.printStackTrace();
                }


            } catch (Exception ex) {
                ex.printStackTrace();
            }
        } else if (position == 1) {
            try {
                Cursor cursor2 = dbHelper.get_category(Integer.valueOf(usid), "Entertainment", Integer.valueOf(d));
                cursor2.moveToFirst();
                holder.KRONAS.setText(cursor2.getString(0));
                try {
                    Cursor c2 = dbHelper.get_ex(Integer.valueOf(usid), "Entertainment", Integer.valueOf(d));
                    c2.moveToFirst();
                    if (Integer.valueOf(c2.getString(0)) > Integer.valueOf(cursor2.getString(0))) {

                        holder.KRONAS.setText("Amount consumed");

                    } else {
                        int t1 = Integer.valueOf(cursor2.getString(0)) - Integer.valueOf(c2.getString(0));
                        holder.KRONAS.setText(String.valueOf(t1));


                    }
                } catch (Exception ex) {
                    ex.printStackTrace();
                }

            } catch (Exception ex) {
                ex.printStackTrace();
            }
        } else if (position == 2) {
            try {
                Cursor cursor3 = dbHelper.get_category(Integer.valueOf(usid), "Car", Integer.valueOf(d));
                cursor3.moveToFirst();
                holder.KRONAS.setText(cursor3.getString(0));
                try {
                    Cursor c3 = dbHelper.get_ex(Integer.valueOf(usid), "Car", Integer.valueOf(d));
                    c3.moveToFirst();
                    if (Integer.valueOf(c3.getString(0)) > Integer.valueOf(cursor3.getString(0))) {
                        holder.KRONAS.setText("Amount consumed");
                    } else {


                        int t2 = Integer.valueOf(cursor3.getString(0)) - Integer.valueOf(c3.getString(0));
                        // if(t2>Integer.valueOf(c3.getString(0))) {
                        holder.KRONAS.setText(String.valueOf(t2));
                        //  }
                    }
                } catch (Exception ex) {
                    ex.printStackTrace();
                }


            } catch (Exception ex) {
                ex.printStackTrace();
            }
        } else if (position == 3) {
            try {
                Cursor cursor4 = dbHelper.get_category(Integer.valueOf(usid), "Home", Integer.valueOf(d));
                cursor4.moveToFirst();
                holder.KRONAS.setText(cursor4.getString(0));
                try {
                    Cursor c4 = dbHelper.get_ex(Integer.valueOf(usid), "Home", Integer.valueOf(d));
                    c4.moveToFirst();
                    if (Integer.valueOf(c4.getString(0)) > Integer.valueOf(cursor4.getString(0))) {
                        holder.KRONAS.setText("Amount Consumed");
                    } else {
                        int t3 = Integer.valueOf(cursor4.getString(0)) - Integer.valueOf(c4.getString(0));

                        holder.KRONAS.setText(String.valueOf(t3));


                    }
                } catch (Exception ex) {
                    ex.printStackTrace();
                }


            } catch (Exception ex) {
                ex.printStackTrace();
            }
        } else if (position == 4) {
            try {
                Cursor cursor5 = dbHelper.get_category(Integer.valueOf(usid), "Clothing", Integer.valueOf(d));
                cursor5.moveToFirst();
                holder.KRONAS.setText(cursor5.getString(0));
                try {
                    Cursor c5 = dbHelper.get_ex(Integer.valueOf(usid), "Clothing", Integer.valueOf(d));
                    c5.moveToFirst();
                    if (Integer.valueOf(c5.getString(0)) > Integer.valueOf(cursor5.getString(0))) {
                        int t4 = Integer.valueOf(cursor5.getString(0)) - Integer.valueOf(c5.getString(0));


                        holder.KRONAS.setText("Amount consumed");

                    } else {
                        int t4 = Integer.valueOf(cursor5.getString(0)) - Integer.valueOf(c5.getString(0));
                        holder.KRONAS.setText(String.valueOf(t4));

                    }
                } catch (Exception ex) {
                    ex.printStackTrace();
                }


            } catch (Exception ex) {
                ex.printStackTrace();
            }
        } else if (position == 5) {
            try {
                Cursor cursor6 = dbHelper.get_category(Integer.valueOf(usid), "Electronics", Integer.valueOf(d));
                cursor6.moveToFirst();
                holder.KRONAS.setText(cursor6.getString(0));
                try {
                    Cursor c6 = dbHelper.get_ex(Integer.valueOf(usid), "Electronics", Integer.valueOf(d));
                    c6.moveToFirst();
                    if (Integer.valueOf(c6.getString(0)) > Integer.valueOf(cursor6.getString(0))) {

                        holder.KRONAS.setText("Amount Consumed");


                    } else {

                        int t5 = Integer.valueOf(cursor6.getString(0)) - Integer.valueOf(c6.getString(0));
                        holder.KRONAS.setText(String.valueOf(t5));
                    }
                } catch (Exception ex) {
                    ex.printStackTrace();
                }


            } catch (Exception ex) {
                ex.printStackTrace();
            }
        } else if (position == 6) {
            try {
                Cursor cursor7 = dbHelper.get_category(Integer.valueOf(usid), "Health", Integer.valueOf(d));
                cursor7.moveToFirst();
                holder.KRONAS.setText(cursor7.getString(0));
                try {
                    Cursor c7 = dbHelper.get_ex(Integer.valueOf(usid), "Heatlh", Integer.valueOf(d));
                    c7.moveToFirst();
                    if (Integer.valueOf(c7.getString(0)) > Integer.valueOf(cursor7.getString(0))) {

                        holder.KRONAS.setText("Amount Consumed");

                    } else {

                        int t6 = Integer.valueOf(cursor7.getString(0)) - Integer.valueOf(c7.getString(0));
                        holder.KRONAS.setText(String.valueOf(t6));
                    }
                } catch (Exception ex) {
                    ex.printStackTrace();
                }


            } catch (Exception ex) {
                ex.printStackTrace();
            }
        } else if (position == 7) {
            try {
                Cursor cursor8 = dbHelper.get_category(Integer.valueOf(usid), "Children", Integer.valueOf(d));
                cursor8.moveToFirst();
                holder.KRONAS.setText(cursor8.getString(0));
                try {
                    Cursor c8 = dbHelper.get_ex(Integer.valueOf(usid), "Children", Integer.valueOf(d));
                    c8.moveToFirst();
                    if (Integer.valueOf(c8.getString(0)) > Integer.valueOf(cursor8.getString(0))) {
                        int t7 = Integer.valueOf(cursor8.getString(0)) - Integer.valueOf(c8.getString(0));

                        holder.KRONAS.setText("Amount Comsumed");

                    } else {
                        int t7 = Integer.valueOf(cursor8.getString(0)) - Integer.valueOf(c8.getString(0));
                        holder.KRONAS.setText(String.valueOf(t7));

                    }
                } catch (Exception ex) {
                    ex.printStackTrace();
                }


            } catch (Exception ex) {
                ex.printStackTrace();
            }
        } else if (position == 8) {
            try {
                Cursor cursor9 = dbHelper.get_category(Integer.valueOf(usid), "Work", Integer.valueOf(d));
                cursor9.moveToFirst();
                holder.KRONAS.setText(cursor9.getString(0));
                try {
                    Cursor c9 = dbHelper.get_ex(Integer.valueOf(usid), "Work", Integer.valueOf(d));
                    c9.moveToFirst();
                    if (Integer.valueOf(c9.getString(0)) > Integer.valueOf(cursor9.getString(0))) {

                        holder.KRONAS.setText("Amount Consumed");


                    } else {
                        int t8 = Integer.valueOf(cursor9.getString(0)) - Integer.valueOf(c9.getString(0));
                        holder.KRONAS.setText(String.valueOf(t8));
                    }
                } catch (Exception ex) {
                    ex.printStackTrace();
                }


            } catch (Exception ex) {
                ex.printStackTrace();
            }
        } else {
            try {
                Cursor cursor10 = dbHelper.get_category(Integer.valueOf(usid), "Other", Integer.valueOf(d));
                cursor10.moveToFirst();
                holder.KRONAS.setText(cursor10.getString(0));
                try {
                    Cursor c10 = dbHelper.get_ex(Integer.valueOf(usid), "Other", Integer.valueOf(d));
                    c10.moveToFirst();
                    if (Integer.valueOf(c10.getString(0)) > Integer.valueOf(cursor10.getString(0))) {

                        holder.KRONAS.setText("Amount Comsumed");


                    } else {
                        int t9 = Integer.valueOf(cursor10.getString(0)) - Integer.valueOf(c10.getString(0));
                        holder.KRONAS.setText(String.valueOf(t9));
                    }
                } catch (Exception ex) {
                    ex.printStackTrace();
                }


            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }


    }

    @Override
    public int getItemCount() {
        return categoriesList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        View mView;

        TextView Name, KRONAS;
        ImageView imageView;

        ViewHolder(View itemView) {
            super(itemView);
            mView = itemView;
            Name = mView.findViewById(R.id.categoryName);
            KRONAS = mView.findViewById(R.id.categoryAmount);
            imageView = mView.findViewById(R.id.categoryImage);
            mView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    final int pos = getAdapterPosition();
                    addAmount = new Dialog(context);
                    addAmount.requestWindowFeature(Window.FEATURE_NO_TITLE);
                    addAmount.setContentView(R.layout.addnewamountdialogue);
                    Objects.requireNonNull(addAmount.getWindow()).setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                    addAmount.setCancelable(true);
                    final EditText amount;
                    Button saveNow;
                    amount = addAmount.findViewById(R.id.enteramount);
                    saveNow = addAmount.findViewById(R.id.saveamount);
                    saveNow.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            DateFormat dateFormat = new SimpleDateFormat("MM");
                            Date date = new Date();
                            String d1 = dateFormat.format(date);
                            String temp = amount.getText().toString();


                            if ((!temp.isEmpty())) {
                                long a = dbHelper.insertCategoryInDb(Integer.parseInt(usid), categoriesList.get(pos), temp, Integer.valueOf(d1));
                                if (a > 1) {
                                    if (addAmount != null)
                                        if (addAmount.isShowing())
                                            addAmount.dismiss();
                                    notifyDataSetChanged();
                                    showSnackBar("Amount has been added");
                                }
                            } else {
                                showSnackBar("Please enter a valid amout, your amount is greater than income");
                            }

                        }
                    });
                    addAmount.show();
                }
            });
        }
    }

    private void showSnackBar(String show) {
        Snackbar snackbar;
        snackbar = Snackbar.make(recyclerView, show, Snackbar.LENGTH_SHORT);
        View sbView = snackbar.getView();
        sbView.setBackgroundColor(ContextCompat.getColor(context, R.color.colorPrimaryDark));
        TextView textView = sbView.findViewById(android.support.design.R.id.snackbar_text);
        textView.setTextColor(context.getResources().getColor(android.R.color.white));

        snackbar.setAction("OK", new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            }
        })
                .setActionTextColor(context.getResources().getColor(android.R.color.white))
                .show();
    }
}