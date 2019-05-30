import dogdb.dog_select;
import java.util.ArrayList;
import java.util.Random;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import org.chartistjsf.model.chart.AspectRatio;
import org.chartistjsf.model.chart.Axis;
import org.chartistjsf.model.chart.AxisType;
import org.chartistjsf.model.chart.ChartSeries;
import org.chartistjsf.model.chart.LineChartModel;
import org.chartistjsf.model.chart.LineChartSeries;
import org.primefaces.event.ItemSelectEvent;

@ManagedBean
public class ChartDataBean {
        private ArrayList<Double> lista = new ArrayList();
        private ArrayList<Double> lista1 = new ArrayList();
        private ArrayList<Double> lista2 = new ArrayList();
        private ArrayList<Double> lista3 = new ArrayList();
	private LineChartModel lineModel;
       
        
	public ChartDataBean() throws Exception {
            createLineModel();
	}

	
        public void createLineModel() {
            
            
                Random random = new Random();
		lineModel = new LineChartModel();
		lineModel.setAspectRatio(AspectRatio.GOLDEN_SECTION);

		lineModel.addLabel("1");
		lineModel.addLabel("2");
		lineModel.addLabel("3");
		lineModel.addLabel("4");
		lineModel.addLabel("5");
		lineModel.addLabel("6");
		lineModel.addLabel("7");
		lineModel.addLabel("8");
                lineModel.addLabel("9");
		lineModel.addLabel("10");

                LineChartSeries series1 = new LineChartSeries();
		series1.setName("Froo Froo" );
                dog_select dog = new dog_select();
                dog.select_temp_byID("Froo");
                lista = dog.getLista();
                for (Double elem:lista){
                    series1.set(elem);
                }

		LineChartSeries series2 = new LineChartSeries();
		series2.setName("Dog Virtual 1");
                
                dog_select dog1 = new dog_select();
                dog1.select_temp_byID("DV1");
                lista1 = dog1.getLista();
                for (Double elem1:lista1){
                    series2.set(elem1);
                }



		LineChartSeries series3 = new LineChartSeries();
		series3.setName("Dog Virtual 2");
                
                dog_select dog2 = new dog_select();
                dog2.select_temp_byID("DV2");
                lista2 = dog2.getLista();
                for (Double elem2:lista2){
                    series3.set(elem2);
                }
                
                LineChartSeries series4 = new LineChartSeries();
		series3.setName("Dog Virtual 3");
                
                dog_select dog3 = new dog_select();
                dog3.select_temp_byID("DV3");
                lista3 = dog3.getLista();
                for (Double elem2:lista3){
                    series4.set(elem2);
                }
                
		Axis xAxis = lineModel.getAxis(AxisType.X);
		lineModel.addSeries(series1);
		lineModel.addSeries(series2);
		lineModel.addSeries(series3);
                lineModel.addSeries(series4);

		lineModel.setAnimateAdvanced(true);
		lineModel.setShowTooltip(true);
                
		

	}
        
        
      
	public void itemSelect(ItemSelectEvent event) {
		FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Item selected", "Item Value: "
				+ ((ChartSeries) lineModel.getSeries().get(event.getSeriesIndex())).getData().get(event.getItemIndex())
				+ ", Series name:" + ((ChartSeries) lineModel.getSeries().get(event.getSeriesIndex())).getName());

		FacesContext.getCurrentInstance().addMessage(event.getComponent().getClientId(), msg);
	}
	
		/**
	 * @return the lineModel
	 */
	public LineChartModel getLineModel() {
		return lineModel;
	}

	/**
	 * @param lineModel
	 *            the lineModel to set
	 */
	public void setLineModel(LineChartModel lineModel) {
		this.lineModel = lineModel;
	}

}