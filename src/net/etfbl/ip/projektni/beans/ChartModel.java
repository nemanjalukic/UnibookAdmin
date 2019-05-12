package net.etfbl.ip.projektni.beans;

import java.io.Serializable;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;

import org.primefaces.model.chart.Axis;
import org.primefaces.model.chart.AxisType;
import org.primefaces.model.chart.BarChartModel;
import org.primefaces.model.chart.ChartSeries;
import org.primefaces.model.chart.DateAxis;
import org.primefaces.model.chart.LineChartModel;
import org.primefaces.model.chart.LineChartSeries;
import org.primefaces.model.chart.PieChartModel;

import net.etfbl.ip.projektni.dao.UserDAO;

@ManagedBean(name = "chartModel")
public class ChartModel implements Serializable {
 
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

    private LineChartModel dateModel;
    private Integer max = 200;
 
    @PostConstruct
    public void init() {
    	createDateModel();
    }
    public LineChartModel getDateModel() {
        return dateModel;
    }
    private void createDateModel() {
        dateModel = new LineChartModel();
        LineChartSeries series1 = new LineChartSeries();
       
       /* series1.set("2014-01-01", 51);
        series1.set("2014-01-06", 22);
        series1.set("2014-01-12", 65);
        series1.set("2014-01-18", 74);
        series1.set("2014-01-24", 24);
        series1.set("2014-01-30", 51);*/
 

        
        Date d= new Date();
        UserBean userBean = new UserBean();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH");
        System.out.println(formatter.format(d));
        series1.setLabel("Series 1");
        for(int i=24;i>=0;i--) {
        	Date d1=new Date();
			try {
				d1 = new Date(formatter.parse(formatter.format(d)).getTime()-3600*1000*i);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        	//System.out.println(i+"A"+formatter.format(d1));
        	Date d2=new Date(d1.getTime()+3600*1000);
        	SimpleDateFormat formatter1 = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        	 series1.set(formatter.format(d1),userBean.getUsersPerHourCount(formatter1.format(d1), formatter.format(d2)));
        	 //System.out.println(i+"A"+userBean.getUsersPerHourCount(new Timestamp(d1.getTime()), new Timestamp(d2.getTime())));
        }

 
        dateModel.addSeries(series1);
 
        dateModel.setTitle("Broj korisnika u poslijednja 24 sata");
        //dateModel.setZoom(true);
        dateModel.getAxis(AxisType.Y).setLabel("Korisnici");
        dateModel.getAxis(AxisType.Y).setTickCount(1);
        dateModel.getAxis(AxisType.Y).setMin(0);
        DateAxis axis = new DateAxis("Vrijeme");
        axis.setTickAngle(90);
        axis.setTickFormat("%#d.%#m.%y %H:%M");
        axis.setMin(formatter.format(new Date(d.getTime()-3600*1000*24)));
        axis.setMax(formatter.format(d));
        
        dateModel.getAxes().put(AxisType.X, axis);
    }

	public Integer getMax() {
		return max;
	}

	public void setMax(Integer max) {
		this.max = max;
	}
}