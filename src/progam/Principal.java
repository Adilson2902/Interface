package progam;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

import javax.swing.JOptionPane;

import models.entities.AlugarCarros;
import models.entities.Veiculo;
import models.service.BrazilTaxService;
import models.service.RentalService;



public class Principal {

	public static void main(String[] args) {
		try {
		Scanner sc = new Scanner(System.in);
		SimpleDateFormat sf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
		
		System.out.println(" Enter rental data");
		System.out.println(" Enter Car Model:");
		String carModel = sc.nextLine();
		System.out.print("Pickup :");
		Date start = sf.parse(sc.nextLine());
		System.out.println("Return :");
		Date finish = sf.parse(sc.nextLine());
		
		AlugarCarros ac = new AlugarCarros(start, finish,new Veiculo(carModel));
		
		System.out.println(" Price for hours");
		double pricePerHours = sc.nextDouble();
		System.out.println(" Price for days");
		double pricePerDays = sc.nextDouble();
		
		RentalService rt = new RentalService(pricePerDays, pricePerHours, new BrazilTaxService());
		
		rt.processInvoice(ac);
		
		System.out.println(" Invoice :");
		System.out.println(" Basic Payment:"+ ac.getFatura().getBasicPayment());
		System.out.println(" Tax:"+ ac.getFatura().getTax());
		System.out.println(" Total Payment"+ ac.getFatura().getTotalPayment());
		
		sc.close();
	}catch(ParseException e) {
		
		JOptionPane.showMessageDialog(null,e.getMessage());
	}
	}
}
