package logic;


public class DataCreator {

	public double alpha;
	
	public DataCreator() {
		
		do {
			alpha = normalRandom();
		}while(alpha<0);
	}
	public double getNormalDistributedY (double x)
	{
		double y = alpha*x + normalRandom()*10;
		
		return y;
	}
	
	private double normalRandom () {
		return Math.sqrt( -2.0d * Math.log( Math.random() ) ) * 
				Math.sin( 2.0d * Math.PI * Math.random() );

	}

	public static void main(String[] args) {
		
		DataCreator d = new DataCreator();
		for(int i=0; i<100; i++){
			System.out.println("x: "+i+", y: "+d.getNormalDistributedY(i));
		}
	}
}
