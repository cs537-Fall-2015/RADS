package RADS.RADS_Test;


import java.awt.Color; 
import java.awt.BasicStroke; 
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import org.jfree.chart.ChartPanel; 
import org.jfree.chart.JFreeChart; 
import org.jfree.data.xy.XYDataset; 
import org.jfree.data.xy.XYSeries; 
import org.jfree.ui.ApplicationFrame; 
import org.jfree.ui.RefineryUtilities; 
import org.jfree.chart.plot.XYPlot; 
import org.jfree.chart.ChartFactory; 
import org.jfree.chart.plot.PlotOrientation; 
import org.jfree.data.xy.XYSeriesCollection; 
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;


public class XYLineChart_AWT extends ApplicationFrame 
{
   public XYLineChart_AWT( String applicationTitle, String chartTitle )
   {
      super(applicationTitle);
      JFreeChart xylineChart = ChartFactory.createXYLineChart(
         chartTitle ,
         "Time" ,
         "Dose rates" ,
         createDataset() ,
         PlotOrientation.VERTICAL ,
         true , true , false);
         
      ChartPanel chartPanel = new ChartPanel( xylineChart );
      chartPanel.setPreferredSize( new java.awt.Dimension( 560 , 367 ) );
      final XYPlot plot = xylineChart.getXYPlot( );
      XYLineAndShapeRenderer renderer = new XYLineAndShapeRenderer( );
      renderer.setSeriesPaint( 0 , Color.BLUE );
      renderer.setSeriesPaint( 1 , Color.GREEN );
      renderer.setSeriesPaint( 2 , Color.YELLOW );
      renderer.setSeriesStroke( 0 , new BasicStroke( 4.0f ) );
      renderer.setSeriesStroke( 1 , new BasicStroke( 3.0f ) );
      renderer.setSeriesStroke( 2 , new BasicStroke( 2.0f ) );
      plot.setRenderer( renderer ); 
      setContentPane( chartPanel ); 
   }
   
   private XYDataset createDataset( )
   {
      final XYSeries Dose = new XYSeries( "Doserates V/S Time" );          
         
      
      String filePath = "RADS/data.json";
  	  JSONParser parser = new JSONParser();
  	   try {
  		JSONArray obj = (JSONArray) parser.parse(new FileReader(filePath));
  		for(Object o : obj) {
  			JSONObject jsonObject = (JSONObject) o;
  			String dratestr = (String) jsonObject.get("Dose rates");
  			Double drates = Double.parseDouble(dratestr);
  			String time = (String) jsonObject.get("Time");
  			Double dtime = Double.parseDouble(time);
  			Dose.add(dtime,drates);
  			
  		}
  	}  catch (FileNotFoundException e) {
			System.out.println("No file found.");
			e.printStackTrace();
		} catch (IOException e) {
			System.out.println("I/O exception found.");
			e.printStackTrace();
		} catch (ParseException e) {
			System.out.println("Parse exception found.");
			e.printStackTrace();
		}
  		
  	
            
      final XYSeriesCollection dataset = new XYSeriesCollection( );          
      dataset.addSeries( Dose );          
     
      return dataset;
   }
   
}
