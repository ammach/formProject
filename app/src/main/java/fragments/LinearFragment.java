package fragments;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.ammach.formproject.R;

import java.util.ArrayList;

import dao.Personne;
import dao.PersonneDao;
import utils.ImageUtil;

/**
 * Created by ammach on 2/7/2016.
 */
public class LinearFragment extends Fragment {

    class LinearAdapter extends RecyclerView.Adapter<LinearAdapter.LinearHolder>{

        Context context;
        ArrayList<Personne> data;
        LayoutInflater layoutInflater;
        LinearHolder linearHolder;

        public LinearAdapter(Context context, ArrayList<Personne> data) {
            this.context = context;
            this.data = data;
            layoutInflater=LayoutInflater.from(context);
        }

        @Override
        public LinearAdapter.LinearHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View v=layoutInflater.inflate(R.layout.linear_row,null);
            linearHolder=new LinearHolder(v);
            return linearHolder;
        }

        @Override
        public void onBindViewHolder(LinearAdapter.LinearHolder holder, int position) {
               holder.textView.setText(data.get(position).getPrenom()+" "+data.get(position).getNom());
               Bitmap bitmap= ImageUtil.getImage(data.get(position).getImage());
               holder.imageView.setImageBitmap(bitmap);
        }

        @Override
        public int getItemCount() {
            return data.size();
        }

        class LinearHolder extends RecyclerView.ViewHolder{

            TextView textView;
            ImageView imageView;
            public LinearHolder(View itemView) {
                super(itemView);
                textView= (TextView) itemView.findViewById(R.id.linearTxt);
                imageView= (ImageView) itemView.findViewById(R.id.linearImg);
            }
        }
    }


    RecyclerView linearRecyclerView;
    ArrayList<Personne> data;
    PersonneDao personneDao;
    public static LinearFragment getInstance(){
        return new LinearFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        data=new ArrayList<Personne>();
        personneDao=new PersonneDao(getActivity());
        data=personneDao.listPersonnes();
        View v=inflater.inflate(R.layout.linear_fragment,container,false);
        linearRecyclerView= (RecyclerView) v.findViewById(R.id.linearRecyclerView);
        linearRecyclerView.setAdapter(new LinearAdapter(getActivity(),data));
        linearRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        return v;
    }


}
