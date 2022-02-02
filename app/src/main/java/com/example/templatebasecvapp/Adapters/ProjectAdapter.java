package com.example.templatebasecvapp.Adapters;

import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import com.example.templatebasecvapp.Data.DbContext;
import com.example.templatebasecvapp.Entities.UserEducation;
import com.example.templatebasecvapp.Entities.UserProject;
import com.example.templatebasecvapp.EventClasses.ProjectEvent;
import com.example.templatebasecvapp.R;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

public class ProjectAdapter extends RecyclerView.Adapter<ProjectAdapter.ViewHolder> {
    private List<UserProject> userProjectList;
    private DbContext dbContext;
    private boolean isLocal;
    // Pass in the contact array into the constructor
    public ProjectAdapter(List<UserProject> list, DbContext dbContext,boolean b) {
        userProjectList = list;
        this.dbContext=dbContext;
        isLocal=b;
    }
    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView txtInstitute,txtProject,txtDate,txtAddtionalinfo;
        public Button btnDelete,btnUpdate;
        public ViewHolder(final View view) {
            super(view);
            txtInstitute =itemView.findViewById(R.id.txtCompanyName);
            txtProject = itemView.findViewById(R.id.txtProject);
            txtDate = itemView.findViewById(R.id.txtProjectDate);
            txtAddtionalinfo=itemView.findViewById(R.id.txtAdditionalInformation);
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
                                    userProjectList.remove(position);
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
                    ProjectEvent event=new ProjectEvent(position);
                    EventBus.getDefault().post(event);
                }
            });
        }
    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        // Create a new view, which defines the UI of the list item
        View view= LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.project_item, viewGroup, false);
        return new ViewHolder(view);
    }
    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        UserProject project = userProjectList.get(position);
        holder.txtInstitute.setText(project.getOrganizationName());
        holder.txtProject.setText(project.getProjectTitle().toUpperCase());
        holder.txtAddtionalinfo.setText(project.getAdditionInformation());
        holder.txtDate.setText(project.getFromYear()+" - "+project.getToYear());
        if(!isLocal){
            holder.btnDelete.setVisibility(View.GONE);
            holder.btnUpdate.setVisibility(View.GONE);
        }
    }
    @Override
    public int getItemCount() {
        return userProjectList.size();
    }
}
