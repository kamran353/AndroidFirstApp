package com.example.templatebasecvapp.Adapters;

import android.content.DialogInterface;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import com.example.templatebasecvapp.Data.DbContext;
import com.example.templatebasecvapp.Entities.UserJob;
import com.example.templatebasecvapp.Entities.UserProject;
import com.example.templatebasecvapp.EventClasses.EducationEvent;
import com.example.templatebasecvapp.EventClasses.JobEvent;
import com.example.templatebasecvapp.R;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

public class JobAdapter extends RecyclerView.Adapter<JobAdapter.ViewHolder> {
    private List<UserJob> userJobList;
    private DbContext dbContext;
    private boolean isLocal;
    // Pass in the contact array into the constructor
    public JobAdapter(List<UserJob> list, DbContext dbContext,boolean b) {
        userJobList = list;
        this.dbContext=dbContext;
        isLocal=b;
    }
    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView txtInstitute,txtJobTitle,txtDate,txtAddtionalinfo,txtDescription;
        public Button btnDelete,btnUpdate;
        public ViewHolder(final View view) {
            super(view);
            txtInstitute =itemView.findViewById(R.id.txtOrganization);
            txtJobTitle = itemView.findViewById(R.id.txtJobTitle);
            txtDate = itemView.findViewById(R.id.txtJobDate);
            txtAddtionalinfo=itemView.findViewById(R.id.txtAdditionalInformation);
            txtDescription=itemView.findViewById(R.id.txtDescription);
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
                                    userJobList.remove(position);
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
                public void onClick(View view)
                {
                    int position=getAdapterPosition();
                    JobEvent event=new JobEvent(position);
                    EventBus.getDefault().post(event);
                }
            });
        }
    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        // Create a new view, which defines the UI of the list item
        View view= LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.job_item, viewGroup, false);
        return new ViewHolder(view);
    }
    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        UserJob job = userJobList.get(position);
        holder.txtInstitute.setText(" "+job.getCompanyName());
        holder.txtDescription.setText(" "+job.getJobDescription());
        holder.txtJobTitle.setText(job.getJobTitle().toUpperCase());
        holder.txtAddtionalinfo.setText(job.getAdditionInformation());
        holder.txtDate.setText("Duration: "+job.getFromYear()+" - "+job.getToYear());
        if(!isLocal){
            holder.btnDelete.setVisibility(View.GONE);
            holder.btnUpdate.setVisibility(View.GONE);
        }
    }
    @Override
    public int getItemCount() {
        return userJobList.size();
    }
}