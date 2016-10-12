
public class Latte implements CupCoffee, OerEcht{

	@Override
	public void zetten() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void bonenMalen() {
		// TODO Auto-generated method stub
		//CupCoffee.super.bonenMalen();  <- Andere keuze
		OerEcht.super.bonenMalen();
	}

}
