package com.example.templatebasecvapp.Adapters;

import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import com.example.templatebasecvapp.Data.DbContext;
import com.example.templatebasecvapp.Entities.UserEducation;
import com.example.templatebasecvapp.EventClasses.EducationEvent;
import com.example.templatebasecvapp.EventClasses.ProjectEvent;
import com.example.templatebasecvapp.Mappings.UserAndCv;
import com.example.templatebasecvapp.PersonalInformationActivity;
import com.example.templatebasecvapp.R;
import com.example.templatebasecvapp.ShowCvDetails;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

public class EducationAdapter extends RecyclerView.Adapter<EducationAdapter.ViewHolder> {
    private List<UserEducation> userEducationList;
    private DbContext dbContext;
    private boolean isLocal;
    // Pass in the contact array into the constructor
    public EducationAdapter(List<UserEducation> list, DbContext dbContext,boolean b) {
        userEducationList = list;
        this.dbContext=dbContext;
        isLocal=b;
    }
    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView txtInstitute,txtDegree,txtDate,txtObtMarks,txtTotalMarks,txtPercentage,txtDescription;
        public Button btnDelete,btnUpdate;
        public ViewHolder(final View view) {
            super(view);
            txtInstitute =itemView.findViewById(R.id.txtInstituteName);
            txtDegree = itemView.findViewById(R.id.txtDegree);
            txtDate = itemView.findViewById(R.id.txtEducationDate);
            txtObtMarks =itemView.findViewById(R.id.txtObtMarks);
            txtTotalMarks = itemView.findViewById(R.id.txtTotalMarks);
            txtPercentage = itemView.findViewById(R.id.txtPercentage);
            txtDescription = itemView.findViewById(R.id.txtDescription);
            btnDelete=itemView.findViewById(R.id.btnDelete);
            btnUpdate=itemView.findViewById(R.id.btnUpdate);
            btnDelete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int position=getAdapterPosition();
                    alertMessage(position);
                }
                public void alertMessage(int position)
                {
                    DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            switch (which) {
                                case DialogInterface.BUTTON_POSITIVE:
                                    // Yes button clicked
                                    userEducationList.remove(position);
                                    notifyDataSetChanged();
                                    break;
                                case DialogInterface.BUTTON_NEGATIVE:
                                    break;
                            }
                        }
                    };
                    AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());
                    builder.setMessage("Are you sure?")
                            .setPositiveButton("Yes", dialogClickListener)
                            .setNegativeButton("No", dialogClickListener).show();
                }
            });
            btnUpdate.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int position=getAdapterPosition();
                    EducationEvent event=new EducationEvent(position);
                    EventBus.getDefault().post(event);
                }
            });
        }
    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        // Create a new view, which defines the UI of the list item
        View view= LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.education_item, viewGroup, false);
        return new ViewHolder(view);
    }
    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        UserEducation education = userEducationList.get(position);
        holder.txtInstitute.setText(education.getInstitution());
        holder.txtDegree.setText(education.getType().toUpperCase());
        holder.txtPercentage.setText(education.getPercentage()+"");
        holder.txtDescription.setText(education.getDescription());
        holder.txtObtMarks.setText(education.getObtainedMarks()+"");
        holder.txtTotalMarks.setText(education.getTotalMarks()+"");
        holder.txtDate.setText("Duration: "+education.getFromYear()+" - "+education.getToYear());
        if(!isLocal){
            holder.btnDelete.setVisibility(View.GONE);
            holder.btnUpdate.setVisibility(View.GONE);
        }
    }
    @Override
    public int getItemCount() {
        return userEducationList.size();
    }
}
