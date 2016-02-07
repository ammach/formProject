package fragments;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
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
public class GridFragment extends Fragment {

    class GridAdapter extends RecyclerView.Adapter<GridAdapter.GridHolder>{

        Context context;
        ArrayList<Personne> data;
        LayoutInflater layoutInflater;
        GridHolder gridHolder;

        public GridAdapter(Context context, ArrayList<Personne> data) {
            this.context = context;
            this.data = data;
            layoutInflater=LayoutInflater.from(context);
        }

        @Override
        public GridAdapter.GridHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View v=layoutInflater.inflate(R.layout.grid_row,null);
            gridHolder=new GridHolder(v);
            return gridHolder;
        }

        @Override
        public void onBindViewHolder(GridAdapter.GridHolder holder, int position) {
            holder.textView.setText(data.get(position).getPrenom()+" "+data.get(position).getNom());
            Bitmap bitmap= ImageUtil.getImage(data.get(position).getImage());
            holder.imageView.setImageBitmap(bitmap);
        }

        @Override
        public int getItemCount() {
            return data.size();
        }

        class GridHolder extends RecyclerView.ViewHolder{

            TextView textView;
            ImageView imageView;
            public GridHolder(View itemView) {
                super(itemView);
                textView= (TextView) itemView.findViewById(R.id.gridTxt);
                imageView= (ImageView) itemView.findViewById(R.id.gridImg);
            }
        }
    }


    RecyclerView gridRecyclerView;
    ArrayList<Personne> mdata;
    PersonneDao personneDao;
    public static GridFragment getInstance(){
        return new GridFragment();
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mdata=new ArrayList<Personne>();
        personneDao=new PersonneDao(getActivity());
        mdata=personneDao.listPersonnes();
        View v=inflater.inflate(R.layout.grid_fragment,container,false);
        gridRecyclerView= (RecyclerView) v.findViewById(R.id.gridRecyclerView);
        gridRecyclerView.setAdapter(new GridAdapter(getActivity(),mdata));
        GridLayoutManager gridLayoutManager=new GridLayoutManager(getActivity(),3);
        gridLayoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {
                return 3-position%3;
            }
        });
        gridRecyclerView.setLayoutManager(gridLayoutManager);
        return v;
    }
}
