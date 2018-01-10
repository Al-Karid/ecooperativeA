package com.example.grenciss.ecooperative.Binders;

import android.content.Context;
import android.support.v7.widget.RecyclerView;

import com.ahamed.multiviewadapter.DataListManager;
import com.ahamed.multiviewadapter.RecyclerAdapter;
import com.example.grenciss.ecooperative.Adapters.Assignation;
import com.example.grenciss.ecooperative.Adapters.NetError;

import java.util.List;

/**
 * Created by grenciss on 1/10/18.
 */

public class MultiListAdapter extends RecyclerAdapter {

    private DataListManager<Assignation> assignationDataListManager;
    private DataListManager<NetError> errorDataListManager;
    private Context context;

    public MultiListAdapter(Context context)
    {
        this.context = context;
        errorDataListManager = new DataListManager<NetError>(this);
        assignationDataListManager = new DataListManager<Assignation>(this);

        addDataManager(errorDataListManager);
        addDataManager(assignationDataListManager);

        registerBinder(new ErrorCardBinder());
        registerBinder(new AssignationCardBinder(context));
    }

    public void addError(NetError errorList)
    {
        errorDataListManager.add(errorList);
    }

    public void addAssignation(List<Assignation> assignationList)
    {
        assignationDataListManager.set(assignationList);
    }
}
