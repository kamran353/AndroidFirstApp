package com.example.templatebasecvapp.Adapters;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import com.example.templatebasecvapp.Data.DbContext;
import com.example.templatebasecvapp.Mappings.UserAndCv;
import com.example.templatebasecvapp.PersonalInformationActivity;
import com.example.templatebasecvapp.PreviewCvActivity;
import com.example.templatebasecvapp.R;
import com.example.templatebasecvapp.SecondTemplate;
import com.example.templatebasecvapp.ShowCvDetails;

import java.util.List;
public class CvAdapter extends RecyclerView.Adapter<CvAdapter.ViewHolder> {
    private List<UserAndCv> userAndCvList;
    private  DbContext dbContext;
    // Pass in the contact array into the constructor
    public CvAdapter(List<UserAndCv> list, DbContext dbContext) {
        userAndCvList = list;
        this.dbContext=dbContext;
    }
    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView nameTextView,createdAt;
        public Button btnDelete,btnUpdate;
        public ViewHolder(final View view) {
            super(view);

            nameTextView =itemView.findViewById(R.id.name);
            createdAt = itemView.findViewById(R.id.createdDate);
            btnDelete=itemView.findViewById(R.id.btnDelete);
            btnUpdate=itemView.findViewById(R.id.btnUpdate);
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int position=getAdapterPosition();
                    UserAndCv userAndCv=userAndCvList.get(position);
                    Intent intent=new Intent(view.getContext(), PreviewCvActivity.class);
                    intent.putExtra("UserId",userAndCv.user.getUserId());
                    view.getContext().startActivity(intent);
                }
            });
            btnDelete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int position=getAdapterPosition();
                    UserAndCv userAndCv=userAndCvList.get(position);
                    dbContext.userCvDao().DeleteCv(userAndCv.userCv);
                    Toast.makeText(view.getContext(),"Deleted Successfully",Toast.LENGTH_LONG).show();
                    removeFromList(position);
                }
            });
            btnUpdate.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int position=getAdapterPosition();
                    UserAndCv userAndCv=userAndCvList.get(position);
                    Intent intent=new Intent(view.getContext(), PersonalInformationActivity.class);
                    intent.putExtra("UserId",userAndCv.user.getUserId());
                    view.getContext().startActivity(intent);
                }
            });
        }

    }
    public void removeFromList(int position){
        userAndCvList.remove(position);
        notifyDataSetChanged();
    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        // Create a new view, which defines the UI of the list item
        View view= LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.cv_item, viewGroup, false);
        return new ViewHolder(view);
    }
    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        UserAndCv userAndCv = userAndCvList.get(position);
        holder.createdAt.setText(userAndCv.userCv.getCreatedDate());
        holder.nameTextView.setText(userAndCv.user.getUserName());
    }
    @Override
    public int getItemCount() {
        return userAndCvList.size();
    }
    public  void addItem(UserAndCv friend){
        userAndCvList.add(friend);
        notifyDataSetChanged();
    }
}
