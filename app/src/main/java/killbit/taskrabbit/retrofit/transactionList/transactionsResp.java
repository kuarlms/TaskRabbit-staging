
package killbit.taskrabbit.retrofit.transactionList;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class transactionsResp implements Serializable
{

    @SerializedName("status")
    @Expose
    private Integer status;
    @SerializedName("export_result")
    @Expose
    private List<ExportResult> exportResult = null;
    private final static long serialVersionUID = 6518407804650120602L;

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public List<ExportResult> getExportResult() {
        return exportResult;
    }

    public void setExportResult(List<ExportResult> exportResult) {
        this.exportResult = exportResult;
    }

}
