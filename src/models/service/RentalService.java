package models.service;

import models.entities.AlugarCarros;
import models.entities.Fatura;

public class RentalService {
  private Double pricePerDay;
  private Double pricePerHour;
  
  private TaxService taxSer;

public RentalService(Double pricePerDay, Double pricePerHour, TaxService taxSer) {
	
	this.pricePerDay = pricePerDay;
	this.pricePerHour = pricePerHour;
	this.taxSer = taxSer;
}
 
public void processInvoice(AlugarCarros alugarCarros) {
	
	long t1 = alugarCarros.getStart().getTime();
	long t2 = alugarCarros.getFinish().getTime();
	
	double hours = (t2 - t1)/1000/60/60;
    double basicPayment;	
	if(hours <= 12) {
		basicPayment = Math.ceil(hours)*pricePerHour;
	}else {
		basicPayment = Math.ceil(hours/24)*pricePerDay;
	}
	
	double tax = taxSer.tax(basicPayment);
     alugarCarros.setFatura(new Fatura(basicPayment, tax));

}
  
  
  
  
}
