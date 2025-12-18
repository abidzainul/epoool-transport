package com.semengresik.epoool_transportasi.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.exifinterface.media.ExifInterface;
import androidx.recyclerview.widget.RecyclerView;
import com.semengresik.epoool_transportasi.Models.PengalihanModel;
import com.semengresik.epoool_transportasi.R;
import com.semengresik.epoool_transportasi.Utils.Function;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes.dex */
public class AdapterPengalihan extends RecyclerView.Adapter<AdapterPengalihan.ViewHolder> {
    private Context context;
    private List<PengalihanModel> list;
    private Listener mListener;
    private List<Integer> selectedIndex = new ArrayList();

    public interface Listener {
        void onItemClick(int i);
    }

    public void setListener() {
        this.mListener = this.mListener;
    }

    public AdapterPengalihan(List<PengalihanModel> list, Context context, Listener listener) {
        this.list = list;
        this.context = context;
        this.mListener = listener;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        return new ViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_pengalihan, viewGroup, false));
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void onBindViewHolder(ViewHolder viewHolder, final int i) {
        PengalihanModel pengalihanModel = this.list.get(i);
        if (pengalihanModel.getStatusApproval().equals(ExifInterface.GPS_MEASUREMENT_3D)) {
            viewHolder.tvStatus.setVisibility(View.GONE);
        } else {
            viewHolder.tvStatus.setVisibility(View.VISIBLE);
            String statusApproval = pengalihanModel.getStatusApproval();
            statusApproval.hashCode();
            if (statusApproval.equals("4")) {
                viewHolder.tvStatus.setText("Approved");
                viewHolder.tvStatus.setTextColor(this.context.getResources().getColor(R.color.green_approved));
            } else if (statusApproval.equals("5")) {
                viewHolder.tvStatus.setText("Rejected");
                viewHolder.tvStatus.setTextColor(this.context.getResources().getColor(R.color.bpRed));
            } else {
                viewHolder.tvStatus.setText("");
            }
        }
        String strTranslateDay = Function.translateDay(pengalihanModel.getTanggalPengajuan());
        viewHolder.tvTgl.setText(strTranslateDay + ", " + Function.toTanggalBaru(pengalihanModel.getTanggalPengajuan()));
        viewHolder.tvResi.setText("No. SPJ: " + pengalihanModel.getNoSpj());
        viewHolder.tvDist.setText(pengalihanModel.getNamaDistributor() + " (" + pengalihanModel.getKdDistributor() + ")");
        viewHolder.tvGudang.setText(pengalihanModel.getNamaGudang() + " (" + pengalihanModel.getKdGudang() + ")");
        viewHolder.view.setOnClickListener(new View.OnClickListener() { // from class: com.semengresik.epoool_transportasi.Adapters.AdapterPengalihan.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                AdapterPengalihan.this.mListener.onItemClick(i);
            }
        });
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        return this.list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView tvDist;
        public TextView tvGudang;
        public TextView tvResi;
        public TextView tvStatus;
        public TextView tvTgl;
        public View view;

        public ViewHolder(View view) {
            super(view);
            this.view = view;
            this.tvResi = (TextView) view.findViewById(R.id.tv_resi);
            this.tvTgl = (TextView) view.findViewById(R.id.tv_tgl_pengajuan);
            this.tvStatus = (TextView) view.findViewById(R.id.tv_status);
            this.tvGudang = (TextView) view.findViewById(R.id.tv_gudang);
            this.tvDist = (TextView) view.findViewById(R.id.tv_dist_item_delivery);
        }
    }
}
